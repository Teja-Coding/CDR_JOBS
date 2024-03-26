package my.com.cmg.iwp.integration.schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.com.cmg.iwp.backend.model.integration.IntegrationLog;
import my.com.cmg.iwp.backend.model.integration.OutsourceOrderInt;
import my.com.cmg.iwp.backend.model.integration.decorator.OutsourceOrderIntList;
import my.com.cmg.iwp.maintenance.model.DistributionFacList;
import my.com.cmg.iwp.maintenance.model.ExternalFacility;
import my.com.cmg.iwp.maintenance.service.ExternalFacilityService;
import my.com.cmg.iwp.maintenance.service.IntegrationLogService;
import my.com.cmg.iwp.maintenance.service.OutsourceOrderIntService;
import my.com.cmg.iwp.maintenance.service.RefCodesService;
import my.com.cmg.iwp.util.RestUtil;
import my.com.cmg.iwp.webui.constant.OrderManagementConstant;
import my.com.cmg.iwp.webui.constant.RefCodeConstant;

public class CdrJob extends QuartzJobBean {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ExternalFacilityService externalFacilityService;

	@Autowired
	private IntegrationLogService integrationLogService;
	
	@Autowired
	private RefCodesService refCodesService;

	@Autowired
	private OutsourceOrderIntService outsourceOrderIntService;

	@Override
	protected void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		List<OutsourceOrderInt> outsourceOrderInts =
				this.outsourceOrderIntService.getOutsourceOrderInt(OrderManagementConstant.ORDER_TYPE_CDR, RefCodeConstant.BOOLEAN_YES);
		System.out.println("============> CDR OutsourceOrderInt size: " + outsourceOrderInts.size());
		Map<String, OutsourceOrderIntList> outsourceOderIntMap = new HashMap<String, OutsourceOrderIntList>();
		Map<String, String> notReachableFacilitiesMap = new HashMap<String, String>();
		
		for (OutsourceOrderInt outsourceOrderInt : outsourceOrderInts) {
			String ptjCode = outsourceOrderInt.getReferredPtjTo();
			String factCode = outsourceOrderInt.getReferredFactTo();
			String ptjFactCode = ptjCode + ":" + factCode;
			
			ExternalFacility externalFacility = getExternalFacilityService()
					.findByFactCodeAndPtjCode(factCode, ptjCode);
			
			DistributionFacList distFacBean = getExternalFacilityService().getDistributionFacListByFacCode(factCode);
			
			try {
				if(RestUtil.isReachable(externalFacility.getServerIp(), factCode, notReachableFacilitiesMap, distFacBean)){
					if (outsourceOderIntMap.containsKey(ptjFactCode)) {
						OutsourceOrderIntList outsourceOrderIntList = outsourceOderIntMap.get(ptjFactCode);
						outsourceOrderIntList.getOutsourceOrderInts().add(outsourceOrderInt);
					} else {
						OutsourceOrderIntList outsourceOrderIntList = new OutsourceOrderIntList();
						outsourceOrderIntList.getOutsourceOrderInts().add(outsourceOrderInt);
						outsourceOderIntMap.put(ptjFactCode, outsourceOrderIntList);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (String ptjFactCode : outsourceOderIntMap.keySet()) {
			String listOfTnxNo = "";
			OutsourceOrderIntList outsourceOrderIntList = outsourceOderIntMap.get(ptjFactCode);
			List<Long> seqnos = new ArrayList<Long>();
			for (OutsourceOrderInt outsourceOrderInt: outsourceOrderIntList.getOutsourceOrderInts()) {
				seqnos.add(outsourceOrderInt.getOutsourceIntSeqno());
				listOfTnxNo = listOfTnxNo +","+outsourceOrderInt.getTnxNo();
			}
			IntegrationLog integrationLog = new IntegrationLog();
			String[] spliz = ptjFactCode.split(":"); 
			String ptjCode = spliz[RestUtil.PTJCODE];
			String factCode = spliz[RestUtil.FACTCODE];
			HttpEntity<OutsourceOrderIntList> httpRpEntity = new HttpEntity<OutsourceOrderIntList>(
					outsourceOrderIntList);
			ExternalFacility externalFacility = getExternalFacilityService()
					.findByFactCodeAndPtjCode(factCode, ptjCode);
			DistributionFacList distFacBean = getExternalFacilityService().getDistributionFacListByFacCode(factCode);	
			String uri = RestUtil.getUri(getRefCodesService(),
					externalFacility, RestUtil.CDR_POST);
						
			String remark = "Out Source Internal Order  through Cdrjob the out soruce transaction numbers are  " +listOfTnxNo;
			remark = remark.length() > 500 ? remark.substring(0,499) :remark; 
			insertIntegrationLog(integrationLog, "CDRJOB", RefCodeConstant.INITIATE_PROCESS, seqnos.size(), "", remark, 2);
			try {
//				if (uri != null && RestUtil.isReachable(externalFacility.getServerIp(), factCode, notReachableFacilitiesMap)) {
					System.err.println(uri);
					ObjectMapper mapper = new ObjectMapper();
					try {
						System.err.println("==========********==========>cdrJson string:  "
								+ mapper.writeValueAsString(outsourceOrderIntList));
					} catch (JsonGenerationException e) {
						e.printStackTrace();
						break;
					} catch (JsonMappingException e) {
						e.printStackTrace();
						break;
					} catch (IOException e) {
						e.printStackTrace();
						break;
					}
					
					try {
						
						if (uri != null && RestUtil.isReachable(externalFacility.getServerIp(), factCode, notReachableFacilitiesMap, distFacBean)) {
						
							updateIntegrationLog(integrationLog, RefCodeConstant.SENT_REQUEST, "", remark);
							ResponseEntity<Integer> httpRequest = getRestTemplate().postForEntity(uri, httpRpEntity, Integer.class);
							updateIntegrationLog(integrationLog, RefCodeConstant.SUCCESS, "", remark);
						
							/*if(httpRequest.getStatusCode() == HttpStatus.OK)
							{
								updateIntegrationLog(integrationLog, RefCodeConstant.SUCCESS, "", remark);
							}
							else
							{
								updateIntegrationLog(integrationLog, RefCodeConstant.UNSUCCESS, "", remark);
							}*/
						
						}else {
							if (outsourceOrderIntList.getOutsourceOrderInts().isEmpty() == false) {
								System.out.println("============> PN: " + outsourceOrderIntList.getOutsourceOrderInts().get(0).getSourceOutsourceNo()+ " has invalid server_ip, server_port, " +
										"the to_facility_code is: " + factCode + " , the to_ptj_code is: " + ptjCode);
								String errorMsg = outsourceOrderIntList.getOutsourceOrderInts().get(0).getSourceOutsourceNo()+ " has invalid server_ip, server_port, " +
										"the to_facility_code is: " + factCode + " , the to_ptj_code is: " + ptjCode;
								updateIntegrationLog(integrationLog, RefCodeConstant.UNSUCCESS, errorMsg, remark);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						String errorMsg = e.getMessage().length() > 500 ? e.getMessage().substring(0, 499) : e.getMessage();
						updateIntegrationLog(integrationLog, RefCodeConstant.UNSUCCESS, errorMsg, remark);
					}
					//				spubHdrsIntService.updateSpubHdrInt(RefCodeConstant.BOOLEAN_NO,
					//						ptjCode, factCode);
					this.outsourceOrderIntService.updateOutsourceOrderInt(seqnos, RefCodeConstant.BOOLEAN_NO);
				/*} else {
					if (outsourceOrderIntList.getOutsourceOrderInts().isEmpty() == false) {
						System.err.println("============> CDR: " + outsourceOrderIntList.getOutsourceOrderInts().get(0).getSourceOutsourceNo()+ " has invalid server_ip, server_port, " +
								"the to_facility_code is: " + factCode + " , the to_ptj_code is: " + ptjCode);
						String errorMsg = outsourceOrderIntList.getOutsourceOrderInts().get(0).getSourceOutsourceNo()+ " has invalid server_ip, server_port, " +
								"the to_facility_code is: " + factCode + " , the to_ptj_code is: " + ptjCode;
						updateIntegrationLog(integrationLog, RefCodeConstant.UNSUCCESS, errorMsg, "Exception came");
					}
				}*/
			} catch (Exception e) {
				
				String errorMsg = e.getMessage().length() > 500 ? e.getMessage().substring(0, 499) : e.getMessage();
				updateIntegrationLog(integrationLog, RefCodeConstant.UNSUCCESS, errorMsg, "RestUtil.isReachable() --> Exception came in Ping method");
			}
		}
	}
	
	public void updateIntegrationLog(IntegrationLog integrationLog, String status, String errorMsg, String remarks) {
		integrationLog.setStatus(status);
		integrationLog.setErrorMsg(errorMsg);
		integrationLog.setRemarks(remarks);
		integrationLog.setEndDate(new Date());
		getIntegrationLogService().saveOrUpdate(integrationLog);
	}

	public void insertIntegrationLog(IntegrationLog integrationLog, String moduleCode, String status, int recordCount, String errorMsg, String remarks, int createdBy){
		integrationLog.setModuleCode(moduleCode);
		integrationLog.setStatus(status);
		integrationLog.setStartDate(new Date());
		integrationLog.setRecordCount(recordCount);
		integrationLog.setErrorMsg(errorMsg);
		integrationLog.setRemarks(remarks);
		integrationLog.setCreatedBy(createdBy);
		integrationLog.setCreatedDate(new Date());
		getIntegrationLogService().saveOrUpdate(integrationLog);
	}

	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public ExternalFacilityService getExternalFacilityService() {
		return this.externalFacilityService;
	}

	public void setExternalFacilityService(
			ExternalFacilityService externalFacilityService) {
		this.externalFacilityService = externalFacilityService;
	}

	public RefCodesService getRefCodesService() {
		return this.refCodesService;
	}

	public void setRefCodesService(RefCodesService refCodesService) {
		this.refCodesService = refCodesService;
	}

	public OutsourceOrderIntService getOutsourceOrderIntService() {
		return this.outsourceOrderIntService;
	}

	public void setOutsourceOrderIntService(
			OutsourceOrderIntService outsourceOrderIntService) {
		this.outsourceOrderIntService = outsourceOrderIntService;
	}

	public IntegrationLogService getIntegrationLogService() {
		return integrationLogService;
	}

	public void setIntegrationLogService(IntegrationLogService integrationLogService) {
		this.integrationLogService = integrationLogService;
	}
}
