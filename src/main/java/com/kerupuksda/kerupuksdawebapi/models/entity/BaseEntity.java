package com.kerupuksda.kerupuksdawebapi.models.entity;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(length = 36)
    private UUID id;
    @CreatedBy
    @Column(name = "created_by", length = 100)
    protected String createdBy;
    @CreatedDate
    @Column(name = "created_date")
    protected LocalDateTime createdDate;
    @LastModifiedBy
    @Column(name = "updated_by", length = 100)
    protected String updatedBy;
    @LastModifiedDate
    @Column(name = "updated_date")
    protected LocalDateTime updatedDate;

}
