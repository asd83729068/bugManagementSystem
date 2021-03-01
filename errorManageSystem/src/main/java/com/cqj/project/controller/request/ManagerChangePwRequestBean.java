package com.cqj.project.controller.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class ManagerChangePwRequestBean {
	@NotNull(message = "id不能为空")
	@ApiModelProperty(value = "id必填")    
	private Integer id;
	
	@NotNull(message = "旧密码不能为空")
	@ApiModelProperty(value = "旧密码必填")    
	private String oldPassword;
	
	@NotNull(message = "新密码不能为空")
	@ApiModelProperty(value = "新密码必填")    
	private String newPassword;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
