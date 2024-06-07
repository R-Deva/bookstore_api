package com.codesimple.bookstore.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

import com.codesimple.bookstore.common.Constant;



@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String gender;
	private String emailId;
	private String phoneNumber;
	private String userType = Constant.USER_TYPE.NORMAL;
	private String password;
	private Boolean isActive = true; 
	private Integer loginCount = 0;
	private String ssoType;
	private LocalDateTime loginAt;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public String getSsoType() {
		return ssoType;
	}
	public void setSsoType(String ssoType) {
		this.ssoType = ssoType;
	}
	public LocalDateTime getLoginAt() {
		return loginAt;
	}
	public void setLoginAt(LocalDateTime loginAt) {
		this.loginAt = loginAt;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
	@PrePersist
	public void onSave()
	{
		LocalDateTime currentDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		this.createdAt = currentDateTime;
		this.updatedAt = currentDateTime;
	}
	
	@PostPersist
	public void onUpdate() {
		
		LocalDateTime currentDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		this.updatedAt = currentDateTime;
	}
	
}
