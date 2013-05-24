/**
 * All Rights Reserved 2012 - Epro Technologies. www.epro-tech.com
 */

package com.epro.framework.client.service;

import java.util.List;

import com.epro.framework.client.model.ClientListInfo;
import com.epro.framework.model.Clients;

/**
 * 
 * @author dineshkumard
 *
 * Date: 08 May 2013
 *
 */
public interface ClientService {

	public long saveClients(Clients clients);

	public Clients getClientById(int clientId);

	public boolean deleteClient(Clients clients);

	public boolean isClientNameExist(Clients clients);

	public boolean isEmailExist(Clients clients);

 	public List<ClientListInfo> getClientsDataTable(int startRecord, int recordsToShow, String clientNameKey);

}
