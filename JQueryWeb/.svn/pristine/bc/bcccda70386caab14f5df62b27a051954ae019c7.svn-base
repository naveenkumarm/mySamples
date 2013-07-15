package com.epro.patient.service;

import java.util.List;

import com.epro.patient.beans.Patient;

public interface IPatientService {

	/**
	 * Retrieves all patients
	 * 
	 * @return list of patients
	 */
	public List<Patient> getAll();

	/**
	 * Retrieves a single patient based on id
	 * 
	 * @param id the id of the patient
	 * @return the patient
	 */
	public Patient get(String id);

	/**
	 * Add a new patient
	 * 
	 * @param patient the new patient
	 * @return true if successful
	 */
	public Boolean add(Patient patient);

	/**
	 * Delete an existing patient
	 * 
	 * @param patient the existing patient
	 * @return true if successful
	 */
	public Boolean delete(Patient patient);

	/**
	 * Edit an existing patient
	 * 
	 * @param patient the existing patient
	 * @return true if successful
	 */
	public Boolean edit(Patient patient);

	public Boolean editCell(Patient patient);

}