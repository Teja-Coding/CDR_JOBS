package my.com.cmg.iwp.util;

import java.util.Map;

import my.com.cmg.iwp.maintenance.model.RefCodes;
import oracle.jdbc.driver.Message;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyApplicationContextAware implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;
	
	public static Map<String, my.com.cmg.iwp.util.Message> messageMap;
	
	public static Map<String,Map<String,RefCodes>> refCodesMap;
	
	public static boolean isRefCodesMapExists; 

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}
	
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static Map<String, my.com.cmg.iwp.util.Message> getMessageMap() {
		return messageMap;
	}

	public static void setMessageMap(Map<String, my.com.cmg.iwp.util.Message> map) {
		MyApplicationContextAware.messageMap = map;
	}

	public static Map<String, Map<String, RefCodes>> getRefCodesMap() {
		return refCodesMap;
	}

	public static void setRefCodesMap(Map<String, Map<String, RefCodes>> refCodesMap) {
		MyApplicationContextAware.refCodesMap = refCodesMap;
	}
	
	
}
