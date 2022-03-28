package com.kereq.communicator.notification.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

	private final ModelMapper modelMapper;

	public NotificationController(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
}
