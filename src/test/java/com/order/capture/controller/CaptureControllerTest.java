package com.order.capture.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.capture.dto.PackageOrder;
import com.order.capture.service.intf.CaptureIntf;

@WebMvcTest(CaptureController.class)
class CaptureControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CaptureIntf captureIntf;
	
	@Test
	void capture() throws Exception{
		
		PackageOrder packageOrder = new PackageOrder();
		packageOrder.setOrderCount(1);
		packageOrder.setOrderNumber("1234");
		
		ObjectMapper om = new ObjectMapper();
		
		
		Mockito.when(captureIntf.capture(Mockito.anyString())).thenReturn("success");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/order")
					.contentType(MediaType.APPLICATION_JSON)
					.content( om.writeValueAsString(packageOrder) )
				).
				andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("success"));
		
	}

}
