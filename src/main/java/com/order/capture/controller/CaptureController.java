package com.order.capture.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.capture.dto.PackageOrder;
import com.order.capture.service.intf.CaptureIntf;

@RestController
public class CaptureController {
	
	private static final Logger logger = LogManager.getLogger(CaptureController.class);
	
	@Autowired
	CaptureIntf captureIntf;
	
	@GetMapping("/")
	public String home()
	{
		return "Order Capture is up...!!!";
	}

	@PostMapping("/order")
	public String order(@RequestBody PackageOrder order)
	{
		ObjectMapper om = new ObjectMapper();
		String orderString = null;
		try {
			orderString = om.writeValueAsString(order);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		logger.info("Received order:{}",orderString);
		return captureIntf.capture(orderString);
	}
	
}
