package com.epro.patient.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epro.patient.beans.Patient;
import com.epro.patient.service.IPatientService;


@Controller
@RequestMapping("/patient")
public class PatientController {
	

	@Resource(name="patientService")
	private IPatientService patientService;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		System.out.println("hit patient controller");
		return "patientView";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listPatient(ModelMap model) {
		System.out.println("UserController showUsers @RequestMapping success ");
    	List<Patient> patientList = patientService.getAll();

		/*List<Patient> patientList = new ArrayList<Patient>();
		for(int i=0;i<10;i++){
			Patient patient = new Patient();
			patient.setFirstName("firstName"+i);
			patient.setLastName("lastName"+i);
			patient.setAge(30+i);
			patient.setLocation("location"+i);
			patientList.add(patient);
		}*/
		System.out.println("list.size" + patientList.size());
		model.addAttribute("Results", patientList);
		model.addAttribute("rows", patientList.size());
		model.addAttribute("page", 1);
		model.addAttribute("total", patientList.size());
		model.addAttribute("Results", patientList);
		return "jsonView";
	}
	
	
	/**
     * Add a new patient
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(
    		@RequestParam("firstName") String firstName,
    		@RequestParam("lastName") String lastName,
    		@RequestParam("age") int age,
    		@RequestParam("location") String location
    ) {
    	System.out.println("Received request to add a new patient");
    	
    	// Construct our new patient object. Take note the id is not required.
    	// Assign the values from the parameters
    	Patient patient = new Patient();
		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setAge(age);
		patient.setLocation(location);
    	Boolean success = patientService.add(patient);
    	
		ModelMap model =  new ModelMap();
		model.addAttribute("patient",patient);
		return "jsonView";
    	
	}
    
    
    /**
     * Edit the current patient.
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(
    		@RequestParam("id") String patientId,
    		@RequestParam("firstName") String firstName,
    		@RequestParam("lastName") String lastName,
    		@RequestParam("age") String age,
    		@RequestParam("location") String location
    ) {
    	System.out.println("Received request to edit patient");
    
    	// Construct our patient object
    	// Assign the values from the parameters

    	Patient patient = new Patient();
    	patient.setPatientId(Long.valueOf(patientId));
		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setAge(Integer.valueOf(age));
		patient.setLocation(location);
		
    	Boolean success = patientService.edit(patient);
    	
		ModelMap model =  new ModelMap();
		model.addAttribute("patient",patient);
		return "jsonView";

	}
    
    /**
     * Delete an existing patient
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(
    		@RequestParam("id") String patientId
    ) {
    	
    	System.out.println("Received request to delete an existing patient");
    	
    	// Construct our patient object. We just need the id for deletion.
    	// Assign the values from the parameters
    	Patient patient = new Patient();
    	patient.setPatientId(Long.valueOf(patientId));
    	
    	// Do custom validation here or in your service
    	
    	// Call service to add
    	Boolean success = patientService.delete(patient);
		ModelMap model =  new ModelMap();
    	model.addAttribute("patient",patient);
    	// Check if successful
    	if ( success == true ) {
    		// Success. Return a custom response
    		
    		
    	} else {
    		// A failure. Return a custom response as well
    		
    	}
		return "jsonView";
    	
	}
    
    /**
     * Delete an existing patient
     */
    @RequestMapping(value = "/editCell", method = RequestMethod.POST)
    public String editCell(
    		@RequestParam("id") String patientId,
    		@RequestParam("age") String age

    ) {
    	
    	System.out.println("Received request to edit cell");
    	
    	// Construct our patient object. We just need the id for deletion.
    	// Assign the values from the parameters
    	Patient patient = new Patient();
    	patient.setPatientId(Long.valueOf(patientId));
    	patient.setAge(Integer.valueOf(age));
    	// Do custom validation here or in your service
    	
    	// Call service to add
    	Boolean success = patientService.editCell(patient);
		ModelMap model =  new ModelMap();
    	model.addAttribute("patient",patient);
    	// Check if successful
    	if ( success == true ) {
    		// Success. Return a custom response
    		
    		
    	} else {
    		// A failure. Return a custom response as well
    		
    	}
		return "jsonView";
    	
	}
}
