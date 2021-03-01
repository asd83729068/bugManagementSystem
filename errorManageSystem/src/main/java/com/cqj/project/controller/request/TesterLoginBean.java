package com.cqj.project.controller.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class TesterLoginBean {
	@NotNull(message = "用户名不能为空")
	@ApiModelProperty(value = "用户名必填", required = true)    
	private String username;
	
	@NotNull(message = "密码不能为空")
	@ApiModelProperty(value = "密码必填", required = true)    
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
