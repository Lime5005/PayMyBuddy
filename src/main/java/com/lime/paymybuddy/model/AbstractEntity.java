package com.lime.paymybuddy.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
@MappedSuperclass// A mapped superclass is a special type of class that is not persistent itself, but has subclasses that are persistent.
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    void createdAt() {
        this.createdAt = this.updatedAt = new Date().toInstant();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = new Date().toInstant();
    }
}
