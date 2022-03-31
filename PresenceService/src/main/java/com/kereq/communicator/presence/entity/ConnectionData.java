package com.kereq.communicator.presence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "connections")
public class ConnectionData {

    @Id
    private String id;

    private Long userId;

    private String instanceId;

    public ConnectionData(Long userId, String instanceId) {
        this.userId = userId;
        this.instanceId = instanceId;
    }
}
