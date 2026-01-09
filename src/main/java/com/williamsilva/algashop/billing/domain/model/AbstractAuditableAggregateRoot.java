package com.williamsilva.algashop.billing.domain.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditableAggregateRoot<T extends AbstractAggregateRoot<T>> extends AbstractAggregateRoot<T> {

    @CreatedBy
    protected UUID createdByUserId;

    @CreatedDate
    protected OffsetDateTime createdAt;

    @LastModifiedBy
    protected UUID lastModifiedByUserId;

    @LastModifiedDate
    protected OffsetDateTime lastModifiedDate;

    @Version
    protected long version;

    public UUID getCreatedByUserId() {
        return createdByUserId;
    }

    private void setCreatedByUserId(UUID createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    private void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getLastModifiedByUserId() {
        return lastModifiedByUserId;
    }

    private void setLastModifiedByUserId(UUID lastModifiedByUserId) {
        this.lastModifiedByUserId = lastModifiedByUserId;
    }

    public OffsetDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    private void setLastModifiedDate(OffsetDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public long getVersion() {
        return version;
    }

    private void setVersion(long version) {
        this.version = version;
    }
}
