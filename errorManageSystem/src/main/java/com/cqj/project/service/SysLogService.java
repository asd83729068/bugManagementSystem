package com.cqj.project.service;



import com.cqj.project.dao.entity.Manager;
import com.cqj.project.dao.entity.Syslog;

public interface SysLogService {
	
	public int add(Syslog sysLog,Manager manager);

}
