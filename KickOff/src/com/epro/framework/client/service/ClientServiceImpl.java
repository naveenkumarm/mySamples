/**
 * All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
 */

package com.epro.framework.client.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epro.framework.client.dao.ClientDAO;
import com.epro.framework.client.model.ClientListInfo;
import com.epro.framework.model.Clients;

/*
 * ClientServiceImpl.java
 * Class description goes here.
 *
 * @version 1.0 
 * @author Dineshkumar
 */
@Service("clientService")
public class ClientServiceImpl implements ClientService {
	
	
	@Autowired
	private ClientDAO clientDAO;
	

	@Override
	public long saveClients(Clients clients) {
		
		clients.setModifiedDate(new Date());
		clients.setIsDeleted(false);
		return clientDAO.saveClients(clients);
	}

	@Override
	public Clients getClientById(int clientId) {
		return clientDAO.getClientById(clientId);
	}

	@Override
	public boolean deleteClient(Clients clients) {
		return clientDAO.deleteClient(clients);
	}

	@Override
	public boolean isEmailExist(Clients clients) {
		return clientDAO.isEmailExist(clients);
	}

	@Override
	public List<ClientListInfo> getClientsDataTable(int startRecord, int recordsToShow, String clientNameKey) {
		return clientDAO.getClientsDataTable(startRecord, recordsToShow, clientNameKey);
	}

	@Override
	public boolean isClientNameExist(Clients clients) {
		return clientDAO.isClientNameExist(clients);
	}
	
}
