package com.cqj.project.controller.request;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class TesterErrorWritingAddBean {
	@ApiModelProperty(value = "错误报告主题", required = true)
	private String errortheme;
	@ApiModelProperty(value = "程序名称", required = true)
	private String demoname;
	@ApiModelProperty(value = "严重性", required = true)
	private Integer ponderance;
	@ApiModelProperty(value = "优先级", required = true)
	private Integer priority;
	@ApiModelProperty(value = "备注")
    private String note;
	@ApiModelProperty(value = "版本", required = true)
    private String version;
	@ApiModelProperty(value = "错误报告内容",required = true)
	private String errorofwriting;
	@ApiModelProperty(value = "测试人员编号",required = true)
	private Integer reporterid;
	@ApiModelProperty(value="报告状态",required=true)
	private Integer status;
	
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getErrortheme() {
		return errortheme;
	}
	public void setErrortheme(String errortheme) {
		this.errortheme = errortheme;
	}
	public String getDemoname() {
		return demoname;
	}
	public void setDemoname(String demoname) {
		this.demoname = demoname;
	}
	public Integer getPonderance() {
		return ponderance;
	}
	public void setPonderance(Integer ponderance) {
		this.ponderance = ponderance;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getErrorofwriting() {
		return errorofwriting;
	}
	public void setErrorofwriting(String errorofwriting) {
		this.errorofwriting = errorofwriting;
	}
	public Integer getReporterid() {
		return reporterid;
	}
	public void setReporterid(Integer reporterid) {
		this.reporterid = reporterid;
	}
	
	
}
