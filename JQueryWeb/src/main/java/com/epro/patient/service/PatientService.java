/**
 * 
 */
package com.epro.patient.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epro.patient.beans.Patient;

/**
 * Handles CRUD services for patients
 * 
 */
@Service("patientService")
@Transactional
public class PatientService implements IPatientService   {
 	
	private List<Patient> dummyPatientsList = new ArrayList<Patient>();
	
	protected static Logger logger = Logger.getLogger("service");

	public PatientService() {
		// Initialize our in-memory list of patients
		init();
	}
	
	public List<Patient> getAll() {
		logger.debug("Retrieving all patients");

		return dummyPatientsList;
	}
	
	public Patient get( String id ) {
		logger.debug("Retrieving an existing patient");
		
		return dummyPatientsList.get( Integer.valueOf(id) );
		
	}

	public Boolean add( Patient patient ) {
		logger.debug("Adding a new patient");
		
		try {
			// Assign a new id
			patient.setPatientId( Long.valueOf(dummyPatientsList.size()) + 1 );
			
			dummyPatientsList.add(patient);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Boolean delete( Patient patient ) {
		logger.debug("Deleting an existing patient");
		
		try {
			// Retrieve id to delete
			Long id =  Long.valueOf( patient.getPatientId());
			
			// Loop array
			for ( Patient dummyPatient: dummyPatientsList) {
				if ( dummyPatient.getPatientId() == id ) {
					dummyPatientsList.remove(dummyPatient); 
					break;
				}
			}
			
			return true;
		} catch (Exception e) {
			return false;
		}

	}
 	
	public Boolean edit( Patient patient ) {
		logger.debug("Editing an existing patient");
		
		try {
			// Retrieve id to edit
			Long id =  patient.getPatientId();
			
			// Loop array
			for ( Patient dummyPatient: dummyPatientsList) {
				if ( dummyPatient.getPatientId() == id ) {
					dummyPatient.setFirstName( patient.getFirstName());
					dummyPatient.setLastName( patient.getLastName());
					dummyPatient.setAge(patient.getAge());
					dummyPatient.setLocation(patient.getLocation());
					break;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	private void init() {
		// Populate our in-memory, dummy list of patients
		// Normally, the data should come from your DAOs or your persitence layer

		logger.debug("Init in-memory patients");
		
		Patient patient = new Patient();
		patient.setPatientId(Long.valueOf("1"));
		patient.setAge(15);
		patient.setFirstName("John");
		patient.setLastName("Smith");
		patient.setLocation("Uttah");
		dummyPatientsList.add(patient);
		
		patient = new Patient();
		patient.setPatientId(Long.valueOf("2"));
		patient.setAge(25);
		patient.setFirstName("Jane");
		patient.setLastName("Adams");
		patient.setLocation("San Diego");
		dummyPatientsList.add(patient);
		
		patient = new Patient();
		patient.setPatientId(Long.valueOf("3"));
		patient.setAge(35);	
		patient.setFirstName("Jeff");
		patient.setLastName("Mayer");
		patient.setLocation("Los Angeles");
		dummyPatientsList.add(patient);

		
	    patient = new Patient();
		patient.setPatientId(Long.valueOf("4"));
		patient.setAge(23);
		patient.setFirstName("Hugh");
		patient.setLastName("Jackman");
		patient.setLocation("Mexico");
		dummyPatientsList.add(patient);
		
		
		patient = new Patient();
		patient.setPatientId(Long.valueOf("5"));
		patient.setAge(34);
		patient.setFirstName("Chris");
		patient.setLastName("Newman");
		patient.setLocation("Irvine");

		dummyPatientsList.add(patient);
	}

	@Override
	public Boolean editCell(Patient patient) {
		logger.debug("edit age an existing patient");		
		try {
			// Retrieve id to editcell
			Long id =  Long.valueOf( patient.getPatientId());
			
			// Loop array
			for ( Patient dummyPatient: dummyPatientsList) {
				if (dummyPatient.getPatientId() == id ) {
					dummyPatient.setAge(patient.getAge());
					break;
				}
			}
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
