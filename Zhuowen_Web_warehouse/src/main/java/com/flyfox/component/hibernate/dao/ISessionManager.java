package com.flyfox.component.hibernate.dao;

import org.hibernate.Session;

public interface ISessionManager {

	public boolean isTrans();
	
	public void begin() throws Exception;

	public void end(boolean isCommit) throws Exception;

	public void openSession();
	
	public Session getSession();

	public void closeSession();

	

}
