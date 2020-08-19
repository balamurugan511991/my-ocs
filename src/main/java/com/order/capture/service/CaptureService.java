package com.order.capture.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.order.capture.service.intf.CaptureIntf;

@Service
class CaptureService implements CaptureIntf {

	private final Logger logger = LogManager.getLogger(CaptureService.class);
	
	@Autowired
	JmsTemplate jmsTemplate;

	@Override
	public String capture(String order) {

		jmsTemplate.convertAndSend("OrderTransactionQueue", order);
		logger.info("sent order to queue");
		return "successfully sent";
	}

}