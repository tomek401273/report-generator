package com.tgrajkowski.test;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tgrajkowski.test.bean.LimitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfigurations() throws UnknownHostException {
		LimitConfiguration limitConfiguration = new LimitConfiguration(configuration.getMaximum(),
				configuration.getMinimum());


		InetAddress inetAddress = InetAddress.getLocalHost();
		System.out.println("IP Address:- " + inetAddress.getHostAddress());
		System.out.println("Host Name:- " + inetAddress.getHostName());
		System.out.println(limitConfiguration.toString());

		return limitConfiguration;
	}
	
//	@GetMapping("/fault-tolerance-example")
//	@HystrixCommand(fallbackMethod="fallbackRetrieveConfiguration")
//	public LimitConfiguration retrieveConfiguration() {
//		throw new RuntimeException("Not available");
//	}
//
//	public LimitConfiguration fallbackRetrieveConfiguration() {
//		return new LimitConfiguration(999, 9);
//	}

}
