package my.com.cmg.iwp.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


import my.com.cmg.iwp.backend.model.integration.OutsourceOrderInt;
import my.com.cmg.iwp.backend.model.integration.PatientInt;
import my.com.cmg.iwp.backend.service.integration.cdr.CdrDoseFrequencyIntService;
import my.com.cmg.iwp.backend.service.integration.cdr.CdrHdrIntService;
import my.com.cmg.iwp.backend.service.integration.cdr.CdrInfusionDrugsIntService;
import my.com.cmg.iwp.backend.service.integration.cdr.CdrNormalDrugsIntService;
import my.com.cmg.iwp.maintenance.service.OutsourceOrderIntService;
import my.com.cmg.iwp.webui.constant.RefCodeConstant;

@Controller
public class IntergrationRest {
	
	@Autowired
	private CdrHdrIntService cdrHdrIntService;
	
	@Autowired
	private OutsourceOrderIntService outsourceOrderIntService;

	@Autowired
	private CdrInfusionDrugsIntService cdrInfusionDrugsIntService;
	
	@Autowired
	private CdrNormalDrugsIntService cdrNormalDrugsIntService;
	
	@Autowired
	private CdrDoseFrequencyIntService cdrDoseFrequencyIntService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/cdr/post", method = RequestMethod.POST, headers = "Accept= application/json")
	public @ResponseBody int createCDR(@RequestBody OutsourceOrderInt outsourceOrderInt) {
		System.out.println("====================> creating CDR running");
		outsourceOrderInt.setSendFlag(RefCodeConstant.BOOLEAN_YES);
		outsourceOrderInt.setOutsourceIntSeqno(new Long(0));
		PatientInt patientInt = outsourceOrderInt.getPatientInt();
		System.out.println("============> rp patientIntId: " + patientInt.getPatientIdno());
		System.out.println("============> rp patientIntIdType: " + patientInt.getPatientIdType());
		if (!this.outsourceOrderIntService.isExists(outsourceOrderInt))
			this.outsourceOrderIntService.save(outsourceOrderInt);
		return 0;
	}
	
	
	
	
	
	

	

}
