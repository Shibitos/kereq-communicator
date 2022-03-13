package com.kereq.communicator.chat.controller;

import com.kereq.communicator.chat.service.ConversationService;
import com.kereq.communicator.chat.service.MessageStorageService;
import com.kereq.communicator.common.dto.ConversationDTO;
import com.kereq.communicator.common.dto.MessageDTO;
import com.kereq.communicator.common.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

	private final MessageStorageService messageStorageService;

	private final ConversationService conversationService;

	private final ModelMapper modelMapper;

	public ChatController(MessageStorageService messageStorageService, ConversationService conversationService, ModelMapper modelMapper) {
		this.messageStorageService = messageStorageService;
		this.conversationService = conversationService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/messages/{recipientId}")
	public Page<MessageDTO> getMessages(@PathVariable("recipientId") long recipientId,
										@AuthenticationPrincipal UserDTO user,
										@PageableDefault(sort = {"sendDate"}, direction = Sort.Direction.DESC) Pageable page) {
		return messageStorageService.getMessagesFor(user.getId(), recipientId, page)
				.map(message -> modelMapper.map(message, MessageDTO.class));
	}

	@GetMapping("/history")
	public Page<ConversationDTO> getChatHistory(@AuthenticationPrincipal UserDTO user,
												@PageableDefault(sort = {"lastMessageDate"}, direction = Sort.Direction.DESC) Pageable page) {
		return conversationService.getLastConversations(user.getId(), page)
				.map(conversation -> modelMapper.map(conversation, ConversationDTO.class));
	}
}
