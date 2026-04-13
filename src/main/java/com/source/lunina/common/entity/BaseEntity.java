package com.source.lunina.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    public static Sort getDefaultSorting() {
        return Sort.by(Sort.Direction.DESC, "createdAt");
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Deleted")
    private Boolean deleted = false;

    @Column(name = "CreatedAt", nullable = false)
    private Date createdAt;

    @Column(name = "UpdatedAt")
    private Date updatedAt;

    @PrePersist
    protected void prePersist() {
        if (this.createdAt == null)
            createdAt = new Date();
        if (this.updatedAt == null)
            updatedAt = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new Date();
    }

    @PreRemove
    protected void preRemove() {
        this.updatedAt = new Date();
    }
}
