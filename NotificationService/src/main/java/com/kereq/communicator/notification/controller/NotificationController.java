package com.kereq.communicator.notification.controller;

import com.kereq.communicator.common.dto.UserDTO;
import com.kereq.communicator.notification.service.NotificationStorageService;
import com.kereq.communicator.shared.dto.NotificationDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

	private final ModelMapper modelMapper;

	private final NotificationStorageService notificationStorageService;

	public NotificationController(ModelMapper modelMapper, NotificationStorageService notificationStorageService) {
		this.modelMapper = modelMapper;
		this.notificationStorageService = notificationStorageService;
	}

	@GetMapping
	public Page<NotificationDTO> getNotificationHistory(@AuthenticationPrincipal UserDTO user,
												@PageableDefault(sort = {"date"}, direction = Sort.Direction.DESC) Pageable page) {
		return notificationStorageService.getNotificationsFor(user.getId(), page)
				.map(notification -> modelMapper.map(notification, NotificationDTO.class));
	}
}
