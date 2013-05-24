package com.epro.framework.client.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.epro.framework.client.model.ClientListInfo;
import com.epro.framework.client.model.FileUpload;
import com.epro.framework.client.service.ClientService;
import com.epro.framework.login.util.UserSessionInfo;
import com.epro.framework.model.Clients;
import com.epro.framework.model.Roles;
import com.epro.framework.util.ApplicationConstants;

/*
 * ClientController.java
 * Class which is used to controls the client creation, update, delete and display clients list.
 *
 * @version 1.0 .
 * @author dineshkumard
 */
@Controller
@RequestMapping("/clients")
public class ClientController {

	private Logger log = Logger.getLogger(ClientController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientValidator clientValidator;

	/**
	 * Method which will be used for populating command and form object
	 * arguments.
	 * 
	 * @param binder
	 *            Binder for data binding from web request parameters to
	 *            JavaBean objects.
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dobFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, "modifiedDate", new CustomDateEditor(dobFormat, true));
	}

	/**
	 * This method used to show the clientList.jsp.
	 * 
	 * @param model
	 * @param session
	 * @param request
	 */
	@RequestMapping(value = "/showClients", method = RequestMethod.GET)
	public String showClients(ModelMap model, HttpSession session, HttpServletRequest request) {
		try {
			if (request.getParameter("response") != null) {
				model.addAttribute("response", request.getParameter("response"));
			}

			if (request.getParameter("errorresponse") != null) {
				model.addAttribute("errorresponse", request.getParameter("errorresponse"));
			}
			
			FileUpload uploadImage = new FileUpload();
			model.addAttribute("uploadImage",uploadImage);
			
			UserSessionInfo user = null;
			if (request.getSession().getAttribute(ApplicationConstants.USER_SESSION_KEY) != null) {
				user = (UserSessionInfo) request.getSession().getAttribute(ApplicationConstants.USER_SESSION_KEY);
			}
			
		} catch (Exception e) {
			log.error("Exception in show clients ::" + ExceptionUtils.getStackTrace(e));
		}
		return "clientsList";
	}

	/**
	 * This method will show create client form.
	 * 
	 * @param model
	 * @param request
	 * @return createClientPage page
	 */
	@RequestMapping(value = "/createClient", method = RequestMethod.GET)
	public String createClient(ModelMap model, HttpServletRequest request) {
		try {
			Clients clients = new Clients();

			clients.setStatus(false);

			UserSessionInfo userSessionInfo = null;
			if (request.getSession().getAttribute(ApplicationConstants.USER_SESSION_KEY) != null) {
				userSessionInfo = (UserSessionInfo) request.getSession().getAttribute(ApplicationConstants.USER_SESSION_KEY);
			}

			// List<Roles> roleInfoList = clientService.getRoleInfo();
			// model.addAttribute("roleInfoList", roleInfoList);

			model.addAttribute("clients", clients);
			model.addAttribute("headers", "New Client");
			model.addAttribute("Save", "Save");
		} catch (Exception e) {
			log.error("Exception in showing create client page::" + ExceptionUtils.getStackTrace(e));
		}
		return "createClient";
	}

	/**
	 * This Method used to save the newly created Client
	 * 
	 * @param Clients
	 * @param result
	 * @param model
	 * @param session
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/saveClient", method = RequestMethod.POST)
	public String saveClient(@ModelAttribute("clients") Clients clients, BindingResult result, ModelMap model, HttpSession session, Locale locale) {
		List<Roles> roleInfoList = null;
		try {
			clientValidator.validate(clients, result);

			if (!result.hasErrors()) {
				// Save
				
				long clientId = clientService.saveClients(clients);

				if (clientId == 0) {
					log.debug("Client " + clients.getClientName() + " save failed.");
					model.addAttribute("Error", messageSource.getMessage("client.save.failure", new String[] { clients.getClientName() }, locale));
					model.addAttribute("clients", clients);
					// model.addAttribute("roleInfoList", roleInfoList);
					model.addAttribute("headers", "New Client");
					if (clients.getClientId() != 0) {
						model.addAttribute("Save", "Modify");
					} else {
						model.addAttribute("Save", "Save");
					}

					return "createClient";

				} else {
					log.info("Client " + clients.getClientName() + " saved successfully.");
					model.addAttribute("response", "Client \"" + clients.getClientName() + "\" saved successfully");
					model.addAttribute("errorresponse", "");
					model.addAttribute("searchClientName", "");
					model.addAttribute("headers", "Clients");
					
					FileUpload uploadImage = new FileUpload();
					model.addAttribute("uploadImage",uploadImage);
					
					return "clientsList";
					// return "redirect:../clients/showClients.do";
				}

			} else {
				// roleInfoList = clientService.getRoleInfo();
				// model.addAttribute("roleInfoList", roleInfoList);
				model.addAttribute("headers", "New Client");
				if (clients.getClientId() != 0) {
					model.addAttribute("Save", "Modify");
				} else {
					model.addAttribute("Save", "Save");
				}
				return "createClient";
			}

		} catch (Exception e) {
			model.addAttribute("client", clients);
			model.addAttribute("response", "");
			model.addAttribute("errorresponse", "Failed to save client!");
			// model.addAttribute("roleInfoList", roleInfoList);
			model.addAttribute("headers", "New Client");
			model.addAttribute("Save", "Save");
			log.error("Exception in saveClient::" + ExceptionUtils.getStackTrace(e));
		}
		model.addAttribute("Error", messageSource.getMessage("client.save.failure", new String[] { clients.getClientName() }, locale));
		return "createClient";
	}

	/**
	 * This method used for getting the information of client
	 * 
	 * @param clientId
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/getClientDetails", method = RequestMethod.POST)
	public void getClientDetails(int clientId, ModelMap model, HttpServletResponse response, HttpSession session) {
		try {
			SimpleDateFormat dobFormat = new SimpleDateFormat("MM/dd/yyyy");
			Clients clients = null;
			if (clientId > 0) {
				clients = clientService.getClientById(clientId);

				if (clients != null && clients.getClientId() > 0) {

					JSONObject jsonObject = new JSONObject();
					jsonObject.put("clientName", clients.getClientName());
					jsonObject.put("address", clients.getAddress());
					jsonObject.put("businessType", clients.getBusinessType());
					jsonObject.put("city", clients.getCity());
					jsonObject.put("contactPerson", clients.getContactPerson());
					jsonObject.put("country", clients.getCountry());
					jsonObject.put("description", "" + clients.getDescription());
					jsonObject.put("emailId", clients.getEmailId());
					jsonObject.put("status", clients.getStatus());
					jsonObject.put("faxNo", clients.getFaxNo());
					jsonObject.put("workPhone", clients.getWorkPhone());
					jsonObject.put("mobileNumber", "" + clients.getMobileNumber());
					jsonObject.put("state", "" + clients.getState());
					jsonObject.put("zip", "" + clients.getZip());

					String modifiedDate = dobFormat.format(clients.getModifiedDate());
					jsonObject.put("modifieddate", "" + modifiedDate);

					response.getWriter().write(jsonObject.toString());

				} else {
					log.error("client Id not found::::" + clientId);
				}
			}
		} catch (Exception e) {
			log.error("Exception in get client details ::" + ExceptionUtils.getStackTrace(e));
		}
	}

	/**
	 * This method used to edit already created client
	 * 
	 * @param clientId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editClient", method = RequestMethod.GET)
	public String editClient(String clientId, ModelMap model, HttpServletResponse response, HttpSession session) {

		try {
			Clients clients = clientService.getClientById(Integer.parseInt(clientId));

			// List<Roles> roleInfoList = ();
			// model.addAttribute("roleInfoList", roleInfoList);

			model.addAttribute("clients", clients);
			model.addAttribute("headers", "Modify Client");
			model.addAttribute("Save", "Modify");
		} catch (Exception e) {
			log.error("Exception in edit clients ::" + ExceptionUtils.getStackTrace(e));
		}
		return "createClient";
	}

	/**
	 * 
	 * This method used to delete the client
	 * 
	 * @param clientId
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteClient", method = RequestMethod.GET)
	public String deleteClient(int clientId, HttpServletResponse response, ModelMap model, HttpSession session, HttpServletRequest request) {
		try {
			boolean result = false;
			Clients clients = clientService.getClientById(clientId);
			if (clients != null) {
				result = clientService.deleteClient(clients);
			}

			if (result) {
				model.addAttribute("response", "Client \"" + clients.getClientName() + "\" deleted successfully");
				model.addAttribute("errorresponse", "");
			} else {
				model.addAttribute("response", "");
				model.addAttribute("errorresponse", "Failed to delete client!");
			}

		} catch (Exception e) {
			log.error("Exception in delete client::" + ExceptionUtils.getStackTrace(e));
			model.addAttribute("response", "");
			model.addAttribute("errorresponse", "Failed to delete client!");
		}
		return "redirect:../clients/showClients.do";
	}

	/**
	 * This method is to get the roleId and roleName.
	 * 
	 * @param roleLevel
	 * @param tenantId
	 * @param subTenantId
	 * @param response
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/getRoleInfo", method = RequestMethod.GET)
	 * public @ResponseBody String getRoleInfo(int roleLevel, int tenantId, int
	 * subTenantId, HttpServletResponse response) { try { ArrayList arrayObj =
	 * new ArrayList(); List<Roles> roleInfoList = userService.getRoleInfo();
	 * 
	 * for (int i = 0; i < roleInfoList.size(); i++) { Roles roleInfo =
	 * roleInfoList.get(i);
	 * 
	 * JSONObject jsonObject = new JSONObject(); jsonObject.put("roleId",
	 * roleInfo.getRoleId()); jsonObject.put("roleName",
	 * roleInfo.getRoleName());
	 * 
	 * arrayObj.add(jsonObject); }
	 * 
	 * JSONObject myObj = new JSONObject(); myObj.put("total",
	 * roleInfoList.size()); myObj.put("success", true); myObj.put("roleInfo",
	 * arrayObj);
	 * 
	 * return myObj.toString();
	 * 
	 * } catch (Exception e) { log.error("Exception in getRoleInfo::" +
	 * ExceptionUtils.getStackTrace(e)); }
	 * 
	 * return ""; }
	 */

	/**
	 * This method used to show the client List in Data table.
	 * 
	 * @param ClientNameKey
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/clientDataTable", method = RequestMethod.GET)
	public @ResponseBody
	String clientDataTable(String clientNameKey, Model model, HttpSession session, HttpServletRequest request) {
		try {
			int startRecord = 0;
			int recordsToShow = 0;

			String sEcho = request.getParameter("sEcho");

			if (request.getParameter("iDisplayStart") != null) {
				startRecord = Integer.parseInt(request.getParameter("iDisplayStart"));
				startRecord = startRecord + 1;
			}

			if (request.getParameter("iDisplayLength") != null) {
				recordsToShow = Integer.parseInt(request.getParameter("iDisplayLength"));
			}

			ArrayList arrayObj = new ArrayList();
			List<ClientListInfo> clientList = new ArrayList<ClientListInfo>();
			JSONObject itemObj = new JSONObject();

			clientList = clientService.getClientsDataTable(startRecord, recordsToShow, clientNameKey);

			int totalRows = 0;
			for (int i = 0; i < clientList.size(); i++) {
				ClientListInfo clientListInfo = clientList.get(i);
				totalRows = clientListInfo.getTotalRows();
				itemObj = JSONObject.fromObject(clientListInfo);
				arrayObj.add(itemObj);
			}
			JSONObject myObj = new JSONObject();
			myObj.put("sEcho", sEcho);
			myObj.put("iTotalRecords", totalRows);
			myObj.put("iTotalDisplayRecords", totalRows);
			myObj.put("aaData", arrayObj);

			// convert the JSON object to string and send the response back
			return myObj.toString();
		} catch (Exception e) {
			log.error("Exception in get clients Data Table details::" + ExceptionUtils.getStackTrace(e));
		}
		return "";
	}
	
	@RequestMapping(value = "/uploadClientLogo", method = RequestMethod.POST)
	public String uploadTenantImage(@ModelAttribute("uploadImage") FileUpload fileUpload, int clientId,String uid, HttpServletRequest request, HttpSession session, HttpServletResponse response, Model model) throws Exception {
		try {
			log.info("Etner in to the Upload Image");
			log.debug("Enter in to UPload Image");

			MultipartFile file = fileUpload.getFile();
			InputStream inputStream = null;
			OutputStream outputStream = null;
			System.out.println("Id: "+clientId);
			System.out.println("Id: "+file);
			System.out.println("Id: "+file.getOriginalFilename());
			System.out.println("Id: "+fileUpload.getClientId());
			
			String name = "";
			if(clientId != 0){
				
				Clients clients = clientService.getClientById((int)clientId);
				name = clients.getClientName();
				
				if (file != null && file.getSize() > 0 ) {
					
					inputStream = file.getInputStream();
					
					String destFileName = ApplicationConstants.CLIENT_LOGO_PATH;
					File imageFile = new File(destFileName);
					imageFile.mkdirs();
					
					String fileType = ".png";
					
					String[] fileName = file.getOriginalFilename().split("\\.");
					if (fileName.length > 0) {
						fileType = fileName[fileName.length - 1];
					}
					
					String imageFullPath = "";
					
					if(clientId != 0){
						imageFullPath = imageFile.getAbsolutePath() + "\\" + "Client-" + clients.getClientId() + "." + fileType.toLowerCase();
					}
					
					outputStream = new FileOutputStream(imageFullPath);
					
					int readBytes = 0;
					while ((readBytes = inputStream.read()) != -1) {
						outputStream.write(readBytes);
					}
					outputStream.close();
					inputStream.close();
					
					log.debug("File Upload in the Path of :" + imageFullPath);
					
					clients.setLogoPath(imageFullPath);
					long modifiedClientId = clientService.saveClients(clients);
					System.out.println("Return Id: "+modifiedClientId);
					
					if(modifiedClientId > 0){
						System.out.println("client Image saved successfully");
						request.setAttribute("resultType", "S");
						request.setAttribute("result", "Client Logo Uploaded Successfully");
						request.setAttribute("imageFileName", file.getOriginalFilename());
					}else{
						System.out.println("client Image not successfully");
						request.setAttribute("resultType", "F");
						request.setAttribute("result", "Image Upload Failed!");
					}
				}
			}else{
				request.setAttribute("resultType", "F");
				request.setAttribute("result", "Image Upload Failed!");
			}
		} catch (Exception e) {
			request.setAttribute("resultType", "F");
			request.setAttribute("result", "Please upload image size between 20KB to 500 KB!");
			log.error("Error While Uploading Image" + ExceptionUtils.getStackTrace(e));
		}
		return "logoImageUpload";
	}
}
