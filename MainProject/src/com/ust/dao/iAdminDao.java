package com.ust.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ust.model.AdminRole;
import com.ust.model.AssignLabTest;
import com.ust.model.DoctorAppointment;
import com.ust.model.DoctorObservation;
import com.ust.model.DoctorStaff;
import com.ust.model.LabTest;
import com.ust.model.Medicine;
import com.ust.model.Patient;
import com.ust.model.Prescription;
import com.ust.model.Staff;

public interface iAdminDao {

	public abstract void setTemplate(JdbcTemplate template);

	//select role name from role table for the corresponding username and password
	public abstract AdminRole selectRole(String username, String password);

	public abstract AdminRole selectRoleName(String username, String password);

	/*----------------------------------------------STAFF--------------------------------------------------*/
	// view staff list
	public abstract List<Staff> getStaff();

	// view staff by name
	public abstract List<Staff> getStaffByName(String sName);

	//View Staff by Id
	public abstract Staff getStaffById(int sId);

	// disable staff
	public abstract int disableStaff(int sId);

	//get id  from role name 
	public abstract AdminRole getRoleName(String roleName);

	// save staff
	public abstract int saveStaff(Staff staff);

	//update staff
	public abstract int updateStaff(Staff staff);

	// view doctor list
	public abstract List<DoctorStaff> getDoctor();

	// view doctor by name
	public abstract List<DoctorStaff> getDocByName(String sName);

	//view doctor by id
	public abstract DoctorStaff getDoctorById(int dId);

	// disable doctor
	public abstract int disableDoctor(int dId);

	// view medicine list
	public abstract List<Medicine> getMedicine();

	// view medicine by name
	public abstract List<Medicine> getMedByName(String medName);

	// get all medicine
	public abstract List<Medicine> getAllMedicines();

	// View Medicine by Id
	public abstract Medicine getMedById(int medId);

	// disable medicine
	public abstract int disableMedicine(int medId);

	// save medicine
	public abstract int saveMed(Medicine a1);

	//update medicine
	public abstract int updateMed(Medicine a1);

	//save doctor
	public abstract int saveDoctor(DoctorStaff ds);

	// view patient list
	public abstract List<Patient> getPatients();

	// view patient by name
	public abstract List<Patient> getPatientByName(String regName);

	// get patient info
	public abstract Patient getPatientByRegId(int regId);

	// save patient
	public abstract int savePatient(Patient pa);

	//update patient
	public abstract int updatePatient(Patient pa);

	// add appoinment
	public abstract int addAppoinment(Patient pa, int regId, int dId);

	// add patient comments
	public abstract int addPatientComments(DoctorObservation obs, int regId,
			int dId);

	public abstract int addlabtestRequest(int regId, int dId);

	// get doctor appointment
	public abstract List<DoctorAppointment> getTodaysDoctorAppointment(int dId);

	// get todays appointment
	public abstract List<DoctorAppointment> getTodaysAppointment();

	public abstract List<DoctorStaff> getAvailableDoctorByDay();

	public abstract DoctorStaff getDoctorInfo(String sName);

	// add lab test request
	public abstract AssignLabTest getLabId(String labName);

	// add lab request
	public abstract int addLabTestRequest(AssignLabTest lab, int regId, int dId);

	// get all test
	public abstract List<LabTest> getLabTest();

	public abstract List<Patient> getPatient(int regId);

	public abstract Patient getPatientDetails(int regId);

	public abstract List<Prescription> patientPrescriptionMedicine(int regId);

	public abstract List<AssignLabTest> patientLab(int regId);

	public abstract List<DoctorObservation> patientObservation(int regId);

	///////////////////////////////////////////////////////////update doctor////////////////////////
	public abstract int updateDoctor(DoctorStaff aBean);

	public abstract int getSeqDid(int sId);

	public abstract List<Patient> getPatientByPhone(int pPhNo);

	public abstract Integer d_getMedicineId(String medName);

	public abstract int saveDoctorPrescription(Prescription doc, int regId,
			int dId);

	public abstract List<Prescription> getAllPrescription(int regId, int dId);

	public abstract List<Medicine> medicinelist();

}