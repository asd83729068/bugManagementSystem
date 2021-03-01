package com.cqj.project.controller.request;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class DeveloperUpdateRequestBean {
	@ApiModelProperty(value="id,必填", required=true)
	private Integer id;
	@ApiModelProperty(value = "性别")
	private String sex;
	@ApiModelProperty(value = "名字")
	private String name;
	@ApiModelProperty(value = "生日")
	private Date birth;
	@ApiModelProperty(value = "手机号码")
	private String phone;
	@ApiModelProperty(value = "电子邮箱")
	 private String email;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
