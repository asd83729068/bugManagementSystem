package com.cqj.project.controller.request;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class ManagerAddRequestBean {
	@ApiModelProperty(value = "用户名", required = true)
	private String username;
	@ApiModelProperty(value = "密码", required = true)
	private String password;
	@ApiModelProperty(value = "姓名", required = true)
	private String name;
	@ApiModelProperty(value = "性别")
	private String sex;
	@ApiModelProperty(value = "生日")
	private Date birth;
	@ApiModelProperty(value = "手机号码")
	private String phone;
	@ApiModelProperty(value = "电子邮箱")
	 private String email;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
