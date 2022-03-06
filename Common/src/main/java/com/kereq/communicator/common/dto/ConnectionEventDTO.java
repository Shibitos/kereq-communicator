package com.kereq.communicator.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@Getter
public class ConnectionEventDTO implements Serializable {

    private static final long serialVersionUID = -7191952766031734303L;

    private Type type;

    private long userId;

    private String instanceId;

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
