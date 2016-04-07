package com.flyfox.component.hibernate.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateSessionManager implements ISessionManager {

	public Session session;

	public SessionFactory sessionFactory;

	public boolean hasTransaction = false;

	private org.hibernate.Transaction transaction;

	public Session getSession() {
		return session;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean isTrans() {
		return hasTransaction;
	}

	public void setHasTransaction(boolean hasTransaction) {
		this.hasTransaction = hasTransaction;
	}

	public void openSession() {
		this.session = sessionFactory.openSession();
	}

	public void closeSession() {
		if (session.isOpen()) {
			session.close();
		}
	}

	public void begin() throws Exception {
		openSession();
		transaction = session.beginTransaction();
		this.setHasTransaction(true);
	}

	public void end(boolean isCommit) throws InterruptedException {
		try {
			if (isTrans()) {
				this.setHasTransaction(false);
				if (isCommit)
					transaction.commit();
				else {
					transaction.rollback();
				}
				transaction = null;
			}
		} catch (HibernateException e) {
			throw e;
		} finally {
			
			closeSession();
		}
	}
}
