package com.manageTeam.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;


/*
 * 생성, 수정 기본 Mapped
 * */
@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseEntity {
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdDate;
	@LastModifiedDate
	private LocalDateTime updatedDate;
	@CreatedBy
	@Column(updatable = false)
	private String createdBy;
	@LastModifiedBy
	private String updatedBy;

}