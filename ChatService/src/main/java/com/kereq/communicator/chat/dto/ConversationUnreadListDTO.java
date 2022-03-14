package com.kereq.communicator.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConversationUnreadListDTO {

    private List<String> unreadConversationIds; //TODO: think of entire history of unreading for user
}
