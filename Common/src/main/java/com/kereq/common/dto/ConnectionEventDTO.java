package com.kereq.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@Getter
public class ConnectionEventDTO implements Serializable {

    private static final long serialVersionUID = -7191952766031734303L;

    private String type;

    private long userId;

    public interface Type {
        String CONNECT = "C";
        String DISCONNECT = "D";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectionEventDTO that = (ConnectionEventDTO) o;
        return userId == that.userId && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, userId);
    }

    @Override
    public String toString() {
        return "ConnectionEventDTO{" +
                "type='" + type + '\'' +
                ", userId=" + userId +
                '}';
    }
}
