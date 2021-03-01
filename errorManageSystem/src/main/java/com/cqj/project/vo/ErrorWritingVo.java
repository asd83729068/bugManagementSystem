package com.cqj.project.vo;

import java.util.Date;

public class ErrorWritingVo {
	
	private Integer id;

	private Integer status;

    private String errortheme;

    private Date createdate;

    private Date dealdate;
    
    private Date enddate;
    
    private String demoname;

    private Integer ponderance;
    
    private Integer priority;

    private String note;

    private String version;
    
    private String errorofwriting;
    
    private String reporterName;
    
    private String dealerName;
    
    private String amaldarName;
    

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

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getDealdate() {
		return dealdate;
	}

	public void setDealdate(Date dealdate) {
		this.dealdate = dealdate;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReporterName() {
		return reporterName;
	}

	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getAmaldarName() {
		return amaldarName;
	}

	public void setAmaldarName(String amaldarName) {
		this.amaldarName = amaldarName;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	
	
}
