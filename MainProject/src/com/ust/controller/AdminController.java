package com.ust.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.dao.iAdminDao;
import com.ust.model.AdminRole;
import com.ust.model.AssignLabTest;
import com.ust.model.DoctorObservation;
import com.ust.model.DoctorStaff;
import com.ust.model.Medicine;
import com.ust.model.Patient;
import com.ust.model.Prescription;
import com.ust.model.Staff;

@RestController
public class AdminController {

	@Autowired
	iAdminDao dao;

	/*----------------------------------------ADMIN MODULE-----------------------------------------------*/

	// verify login
	@RequestMapping(value = "/api/admin/{username}/{userpassword}", method = RequestMethod.GET)
	@ResponseBody
	public AdminRole selectRoleName(@PathVariable("username") String username,
			@PathVariable("userpassword") String password) {
		return dao.selectRoleName(username, password);
	}

	// view staff list
	@RequestMapping(value = "/api/staff/{sName}", method = RequestMethod.GET)
	@ResponseBody
	public List getStaff(Model m, @PathVariable("sName") String sName) {
		List list;
		if (sName.equals("null")) {
			list = dao.getStaff();
		} else {
			list = dao.getStaffByName(sName);
		}

		return list;
	}

	// view staff by id
	@RequestMapping(value = "/api/staffbyid/{sId}", method = RequestMethod.GET)
	@ResponseBody
	public Staff getStaffById(@PathVariable("sId") int sId) {
		Staff s = dao.getStaffById(sId);
		return s;
	}

	// view doctor list
	@RequestMapping(value = "/api/doctor/{sName}", method = RequestMethod.GET)
	@ResponseBody
	public List getDoctor(Model m, @PathVariable("sName") String sName) {
		List list;
		if (sName.equals("null")) {
			list = dao.getDoctor();
		} else {
			list = dao.getDocByName(sName);
		}

		return list;
	}

	// get doctor by id
	@RequestMapping(value = "/api/doctorbyid/{dId}", method = RequestMethod.GET)
	@ResponseBody
	public DoctorStaff getDoctorById(@PathVariable("dId") int dId) {
		DoctorStaff a1 = dao.getDoctorById(dId);
		return a1;
	}

	/*
	 * // save staff
	 * 
	 * @RequestMapping(value = "/api/insertstaff", method = RequestMethod.POST)
	 * public void saveStaff(@RequestBody Staff s) {
	 * 
	 * dao.saveStaff(s); }
	 */

	@RequestMapping(value = "/api/updatestaff", method = {
	RequestMethod.POST, RequestMethod.PUT })
	public void insertStaff(@RequestBody Staff s)
	{
		if (s.getsId() == 0) {

			dao.saveStaff(s);
		}
		else
		{
			dao.updateStaff(s);

		}
	}

	// disable staff
	@RequestMapping(value = "/api/disablestaff/{sId}", method = RequestMethod.PUT)
	@ResponseBody
	public void disableStaff(@PathVariable("sId") int sId) {
		dao.disableStaff(sId);
	}

	// disable doctor
	@RequestMapping(value = "/api/disableDoctor/{dId}", method = RequestMethod.PUT)
	void doctorDisable(@PathVariable("dId") int dId) {
		dao.disableDoctor(dId);
	}

	// disable medicine
	@RequestMapping(value = "/api/disableMedicine/{medId}", method = RequestMethod.PUT)
	void disableMedicine(@PathVariable("medId") int medId) {
		dao.disableMedicine(medId);
	}

	// view medicine list
	@RequestMapping(value = "/api/medicine/{mName}", method = RequestMethod.GET)
	@ResponseBody
	public List getMedicine(Model m, @PathVariable("mName") String mName) {
		List list;
		if (mName.equals("null")) {
			list = dao.getMedicine();
		} else {
			list = dao.getMedByName(mName);
		}

		return list;
	}

	// get medicine for edit
	@RequestMapping(value = "/api/medicinebyid/{medId}", method = RequestMethod.GET)
	@ResponseBody
	public Medicine getMedicineById(@PathVariable("medId") int medId) {
		Medicine a1 = dao.getMedById(medId);
		return a1;
	}

	/*-----------------------------------------FRONT OFFICE MODULE-------------------------------------------*/

	// view patient list
	@RequestMapping(value = "/api/patients/{regName}", method = RequestMethod.GET)
	@ResponseBody
	public List getPatient(Model m, @PathVariable("regName") String regName) {
		List list;
		if (regName.equals("null")) {
			list = dao.getPatients();
		} else {
			list = dao.getPatientByName(regName);
		}

		return list;
	}
	
	@RequestMapping(value = "/api/patientsearch/{pPhNo}", method = RequestMethod.GET)
	@ResponseBody
	public List getPatient(Model m, @PathVariable("pPhNo") int pPhNo) {
		List list;
		list=dao.getPatientByPhone(pPhNo);

		return list;
	}
	
	

	// get patient info
	@RequestMapping(value = "/api/patient/{regId}", method = RequestMethod.GET)
	@ResponseBody
	public Patient getPatientById(@PathVariable("regId") int regId) {
		return dao.getPatientByRegId(regId);
	}

	// get available doctors
	@RequestMapping(value = "/api/doctoravail", method = RequestMethod.GET)
	@ResponseBody
	public List getAvailableDoctorByDay(Model m) {

		List list = dao.getAvailableDoctorByDay();
		return list;
	}

	@RequestMapping(value = "/api/doctoravail/{sName}", method = RequestMethod.GET)
	@ResponseBody
	public DoctorStaff getDoctorInfo(Model m,
			@PathVariable("sName") String sName) {

		DoctorStaff ds = dao.getDoctorInfo(sName);
		return ds;
	}

	/*--------------------------------DOCTOR MODULE------------------------------*/

	// get all tests
	@RequestMapping(value = "/api/labtests", method = RequestMethod.GET)
	@ResponseBody
	public List getLabTest(Model m) {
		List list = dao.getLabTest();
		return list;
	}

	/*// get all medicine
	@ResponseBody
	@RequestMapping(value = "/api/doctor/medicine", method = RequestMethod.GET)
	public List getAllMedicines() {
		List medicineList = dao.getAllMedicines();
		return medicineList;
	}*/

	// add patient comments
	@RequestMapping(value = "/api/insertdoccomments/{regId}/{dId}", method = RequestMethod.POST)
	public void insertDoctorComments(@RequestBody DoctorObservation obs,@PathVariable("regId")int regId,
			@PathVariable("dId")int dId) {
		dao.addPatientComments(obs,regId,dId);
	}

	/*
	 * // add lab test request
	 * 
	 * @RequestMapping(value = "/api/assignLab/{regId}/{dId}", method =
	 * RequestMethod.POST) public void insertBook(@RequestBody AssignLabTest
	 * doc_bean,
	 * 
	 * @PathVariable("regId") int regId,@PathVariable("dId") int dId) {
	 * dao.addlabtestRequest(regId,dId); }
	 */

	/*// get todays appointment
	@RequestMapping(value = "/doctor/appoinment", method = RequestMethod.GET)
	@ResponseBody
	public List viewTodaysAppointments() {
		List listtoday = dao.getTodaysAppointment();
		return listtoday;
	}*/
	
	@RequestMapping(value = "/api/doctor/appoinment", method = RequestMethod.GET)
	@ResponseBody
	public List viewTodaysAppointments() {
	List listtoday = dao.getTodaysAppointment();
	return listtoday;
	}

	// get doctor appointment
	@RequestMapping(value = "/api/drappoinment/{dId}", method = RequestMethod.GET)
	@ResponseBody
	public List viewTodaysDoctorAppointments(@PathVariable("dId") int dId) {
		List listdoctoday = dao.getTodaysDoctorAppointment(dId);
		return listdoctoday;
	}

	// save medicine
	@RequestMapping(value = "/api/updatemedicines", method = {
			RequestMethod.POST, RequestMethod.PUT })
	public void insertMedicine(@RequestBody Medicine a1)

	{
		if (a1.getMedId() == 0) {

			dao.saveMed(a1);
		} else {
			dao.updateMed(a1);
		}
	}
/*
	// save staff
	@RequestMapping(value = "/api/savestaff", method = RequestMethod.POST)
	public void insertStaff(@RequestBody Staff s) throws ParseException {

		dao.saveStaff(s);
	}*/

	// save patient
	@RequestMapping(value = "/api/insertpatient", method = {RequestMethod.POST,
			RequestMethod.PUT})
	public void insertPatient(@RequestBody Patient pa) {
		if(pa.getRegId()==0)
		{
			dao.savePatient(pa);
		}
		else
		{
			dao.updatePatient(pa);
		}

		
	}

	// save doctor
	@RequestMapping(value = "/api/insertdoctor", method = RequestMethod.POST)
	public void insertDoctor(@RequestBody DoctorStaff ds) {
		dao.saveDoctor(ds);
	}

	// update doctor
	@RequestMapping(value = "/api/updatedoctor", method = RequestMethod.PUT)
	public void updateDoctor(@RequestBody DoctorStaff ds) {
		dao.updateDoctor(ds);
	}

	/*// add prescription
	@RequestMapping(value = "api/insertprescription", method = RequestMethod.POST)
	public void insertDoctorPrescription(@RequestBody Prescription d[]) {
		System.out.println("Insde Doctor prescription");
		dao.saveDoctorPrescription(d);

	}*/

	/*
	 * //view history
	 * 
	 * @RequestMapping(value = "/api/patienthistory/{regId}", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public List getPatientHistory(Model
	 * m,@PathVariable("regId") int regId) { List list =
	 * dao.viewPatientHistory(regId); return list; }
	 */

	@RequestMapping(value = "/api/assignLab/{regId}/{dId}", method = RequestMethod.POST)
	public void assignLabRequest(@RequestBody AssignLabTest lab,
			@PathVariable("regId") int regId, @PathVariable("dId") int dId) {
		dao.addLabTestRequest(lab,regId,dId);
	}

	@RequestMapping(value = "/api/addappoinment/{regId}/{dId}", method = RequestMethod.POST)
	public void assignLabRequest(@RequestBody Patient pa,
			@PathVariable("regId") int regId, @PathVariable("dId") int dId) {
		dao.addAppoinment(pa, regId, dId);
	}

	/*----------------------------patient history----------------------*/

	@RequestMapping(value = "/api/patienthistory/{regId}", method = RequestMethod.GET)
	@ResponseBody
	public List patientInfo(Model m, @PathVariable("regId") int regId) {
		List list = dao.getPatient(regId);
		return list;
	}

	@RequestMapping(value = "/api/labhistory/{regId}", method = RequestMethod.GET)
	@ResponseBody
	public List patientLab(Model m, @PathVariable("regId") int regId) {
		List list = dao.patientLab(regId);
		return list;
	}

	@RequestMapping(value = "/api/prescriptionhistory/{regId}", method = RequestMethod.GET)
	@ResponseBody
	public List patientPrescriptionMedicine(Model m,
			@PathVariable("regId") int regId) {
		List list = dao.patientPrescriptionMedicine(regId);
		return list;
	}

	@RequestMapping(value = "/api/observationhistory/{regId}", method = RequestMethod.GET)
	@ResponseBody
	public List patientObservation(Model m, @PathVariable("regId") int regId) {
		List list = dao.patientObservation(regId);
		return list;
	}

/*---------------------------------------------------------------------------------------------------*/
	@ResponseBody
 	@RequestMapping(value = "/api/doctor/medicine", method = RequestMethod.GET)
 	public List getAllMedicines() {
 		List medicineList = dao.medicinelist();
 		return medicineList;
 	}
	
	
	@RequestMapping(value = "/api/insertpres/{regId}/{dId}",method = RequestMethod.POST)
	public void insertDoctorPrescription(@RequestBody Prescription doc,@PathVariable("regId")int regId,
			@PathVariable("dId")int dId)
	{
	System.out.println("insert prescr");
	dao.saveDoctorPrescription(doc,regId,dId);

	}
	
	
	@RequestMapping(value = "/api/allpreslist/{regId}/{dId}", method = RequestMethod.GET)
	  public List<Prescription> getAllPrescription(@PathVariable("regId") int regId ,@PathVariable("dId") int dId )
	  {
	     List presList=dao.getAllPrescription(regId , dId);
	 return presList;
	  }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
