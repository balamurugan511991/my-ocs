package com.order.capture.service.intf;

import org.springframework.stereotype.Service;

@Service
public interface CaptureIntf {

	public String capture(String order);
	
}
