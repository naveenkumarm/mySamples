/**
 * All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
 */

package com.epro.framework.client.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epro.framework.client.model.ClientListInfo;
import com.epro.framework.model.Clients;

/*
 * ClientDAO.java
 * Class which is used to carry out database operations related to Client information.
 *
 * @version 1.0 .
 * @author Dineshkumar.
 */
@Repository
@Transactional
public class ClientDAO {
	/** Logger for the class */
	private Logger log = Logger.getLogger(ClientDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * This method is to save the client object.
	 * 
	 * @param clients
	 * @return saved client Id else 0.
	 */
	public long saveClients(Clients clients) {
		try {
			if (clients.getClientId() == 0) {
				return (Long) sessionFactory.getCurrentSession().save(clients);
			} else {
				sessionFactory.getCurrentSession().saveOrUpdate(clients);
				return 1;
			}
		} catch (Exception e) {
			log.error("Exception in save client: DAO : " + ExceptionUtils.getStackTrace(e));
		}
		return 0;
	}

	public Clients getClientById(int clientId) {
		Clients client = null;
		try {
			String queryString = "FROM Clients WHERE isDeleted = 'false' AND clientId = " + clientId;
			Query query = sessionFactory.getCurrentSession().createQuery(queryString);
			client = (Clients) query.list().get(0);
			return client;
		} catch (Exception e) {
			log.error("Exception in get client By clientId : DAO :" + ExceptionUtils.getStackTrace(e));
		}
		return client;
	}

	public boolean deleteClient(Clients clients) {
		try {
			clients.setIsDeleted(true);
			sessionFactory.getCurrentSession().saveOrUpdate(clients);
			return true;
		} catch (Exception e) {
			log.error("Exception in delete client : DAO :" + ExceptionUtils.getStackTrace(e));
		}
		return false;
	}

	public boolean isClientNameExist(Clients clients) {
		try {
			StringBuilder queryString = new StringBuilder();
			queryString.append("FROM Clients WHERE isDeleted = 'false' AND clientName = '" + clients.getClientName() + "'");

			if (clients.getClientId() != 0) {
				queryString.append(" AND ClientId !=" + clients.getClientId());
			}
			Query query = sessionFactory.getCurrentSession().createQuery(queryString.toString());
			List<Clients> results = query.list();
			if (results != null && results.size() >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error("Exception in is Client Name Exist : DAO : " + ExceptionUtils.getStackTrace(e));
		}
		return false;
	}

	public boolean isEmailExist(Clients clients) {
		return false;
	}

	public List<ClientListInfo> getClientsDataTable(int startRecord, int recordsToShow, String clientNameKey) {
		try {
			Session session = sessionFactory.getCurrentSession();
			CallableStatement cstmt = session.connection().prepareCall("{call ADMIN_CLIENT_MANAGEMENT(?,?,?)}");
			List<ClientListInfo> clientListInfo = new ArrayList<ClientListInfo>();
			cstmt.setInt(1, startRecord);
			cstmt.setInt(2, recordsToShow);
			cstmt.setString(3, clientNameKey.trim());
			ResultSet rs = cstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					ClientListInfo clientInfo = new ClientListInfo();
					clientInfo.setIndex(rs.getInt(1));
					clientInfo.setClientId(rs.getInt(2));
					clientInfo.setClientName(rs.getString(3));
					clientInfo.setBusinessType(rs.getString(4));
					clientInfo.setContactPerson(rs.getString(5));
					clientInfo.setWorkPhone(rs.getString(6));
					clientInfo.setMobileNumber(rs.getString(7));
					clientInfo.setIsActive(rs.getBoolean(9));
					clientInfo.setEdit(rs.getInt(2));
					clientInfo.setDelete(rs.getInt(2));
					clientInfo.setTotalRows(rs.getInt(10));
					clientListInfo.add(clientInfo);
				}
			}
			return clientListInfo;
		} catch (Exception e) {
			log.error("Exception in get Client Data Table : DAO :" + ExceptionUtils.getStackTrace(e));
		}
		return null;
	}
	/**
	 * This method used to get the role information
	 * 
	 * @param roleLevel
	 * @param subTenantId
	 * @param tenantId
	 * @return list of Roles
	 */
	/*
	 * public List<Roles> getRoleInfo() { try { String queryString =
	 * "FROM Roles WHERE isDeleted='false'"; Query query =
	 * sessionFactory.getCurrentSession().createQuery(queryString); return
	 * query.list(); } catch (Exception e) {
	 * log.error("Exception in getRoleInfo::" +
	 * ExceptionUtils.getStackTrace(e)); return null; }
	 * 
	 * }
	 */

}
