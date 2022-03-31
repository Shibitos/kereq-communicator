package com.kereq.communicator.shared.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationDTO implements Serializable {

    private static final long serialVersionUID = -5816843143508919146L;

    private String id;

    private UUID uuid;

    private Long sourceUserId;

    private Long recipientId;

    private String title;

    private String content;

    private Date date;

    private boolean read;

    public NotificationDTO(String id, Long sourceUserId, Long recipientId, String title, String content, Date date, boolean read) {
        this.id = id;
        this.sourceUserId = sourceUserId;
        this.recipientId = recipientId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.read = read;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationDTO that = (NotificationDTO) o;
        return read == that.read && Objects.equals(id, that.id) && Objects.equals(uuid, that.uuid) && Objects.equals(sourceUserId, that.sourceUserId) && Objects.equals(recipientId, that.recipientId) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(date, that.date);
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
                "id='" + id + '\'' +
                ", uuid=" + uuid +
                ", sourceUserId=" + sourceUserId +
                ", recipientId=" + recipientId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", read=" + read +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, sourceUserId, recipientId, title, content, date, read);
    }
}
