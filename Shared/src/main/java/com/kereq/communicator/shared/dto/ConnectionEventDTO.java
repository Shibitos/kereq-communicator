package com.kereq.communicator.shared.dto;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
public class ConnectionEventDTO implements Serializable {

    private static final long serialVersionUID = -7191952766031734303L;

    private final Type type;

    private final long userId;

    private long recipientId;

    private String instanceId;

    public ConnectionEventDTO(Type type, long userId, String instanceId) {
        this.type = type;
        this.userId = userId;
        this.instanceId = instanceId;
    }

    public ConnectionEventDTO(Type type, long userId, long recipientId) {
        this.type = type;
        this.userId = userId;
        this.recipientId = recipientId;
    }

    public enum Type {
        CONNECT("C"),
        DISCONNECT("D");

        Type(String code) {
            this.code = code;
        }

        String code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectionEventDTO that = (ConnectionEventDTO) o;
        return userId == that.userId && type == that.type && Objects.equals(instanceId, that.instanceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, userId, instanceId);
    }

    @Override
    public String toString() {
        return "ConnectionEventDTO{" +
                "type=" + type +
                ", userId=" + userId +
                ", instanceId='" + instanceId + '\'' +
                '}';
    }
}
