package com.springbook.ioc.injection;

import java.util.Properties;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import com.springbook.ioc.injection.CollectionBean;

public class CollectionBeanClient {
	public static void main(String[] args) {
		AbstractApplicationContext factory =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		CollectionBean bean = (CollectionBean)factory.getBean("collectionBean");
		Properties addressList = bean.getAddressList();
		
		String address = addressList.get("고길동").toString();
		String address2 = addressList.get("마이콜").toString();
		
		System.out.println("고길동 주소 : " + address);
		System.out.println("마이콜 주소 : " + address2);
//		for (String address : addressList) {
//			System.out.println(address.toString());
//		}
		factory.close();
	}
}
