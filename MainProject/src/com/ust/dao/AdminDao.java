package com.ust.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ust.model.AdminRole;
import com.ust.model.AssignLabTest;
import com.ust.model.DoctorAppointment;
import com.ust.model.DoctorObservation;
import com.ust.model.DoctorStaff;
import com.ust.model.History;
import com.ust.model.LabTest;
import com.ust.model.Medicine;
import com.ust.model.Patient;
import com.ust.model.Prescription;
import com.ust.model.Staff;

public class AdminDao implements iAdminDao {

	JdbcTemplate template;
	/* (non-Javadoc)
	 * @see com.ust.dao.iAdminDao#setTemplate(org.springframework.jdbc.core.JdbcTemplate)
	 */
	@Override
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

/*---------------------------------------ADMIN MODULE-------------------------------------------------*/

	//Role based username and password verification
	
	@Override
	public AdminRole selectRole(String username, String password) {
		String sql = "select cm_roleTable.roleId,roleName from cm_roleTable join cm_staffTable on"
				+ "(cm_roleTable.roleId=cm_staffTable.roleId) where username=?"
				+" and password=?" ;
		return template.queryForObject(sql, new Object[] {username,password},
				new BeanPropertyRowMapper<AdminRole>(AdminRole.class));
	}

	@Override
	public AdminRole selectRoleName(String username, String password) {

		AdminRole ad = selectRole(username, password);
		int id = ad.getRoleId();
		if (id == 1) {
			String sql1 = "select cm_doctorTable.dId,cm_roleTable.roleId,cm_roleTable.roleName from cm_doctorTable join cm_staffTable "
					+ "on (cm_doctorTable.sId=cm_staffTable.sId) join cm_roleTable on"
					+ "(cm_roleTable.roleId=cm_staffTable.roleId) where username=?"
					+ " and password=?" ;
			return template.queryForObject(sql1, new Object[] {username,password},
					new BeanPropertyRowMapper<AdminRole>(AdminRole.class));
		} else {
			String sql = "select cm_roleTable.roleId,roleName from cm_roleTable join cm_staffTable on"
					+ "(cm_roleTable.roleId=cm_staffTable.roleId) where username='"
					+ username + "' and password='" + password + "'";
			return template.queryForObject(sql, new Object[] {},
					new BeanPropertyRowMapper<AdminRole>(AdminRole.class));
		}
	}

/*----------------------------------------------STAFF--------------------------------------------------*/
	
	@Override
	public List<Staff> getStaff() {
		return template
		.query("select sId,sName,roleName,sPhNo,sEmail,sGender,sAddr,sExp,isActive,"
				+ "createdDate,dob from cm_staffTable stf join cm_roleTable role"
				+ " on(stf.roleId=role.roleId) where roleName!='Doctor' and isActive='yes'",
						new RowMapper<Staff>() {
							public Staff mapRow(ResultSet rs, int row)
									throws SQLException {
								Staff s = new Staff();
								s.setsId(rs.getInt(1));
								s.setsName(rs.getString(2));
								s.setRoleName(rs.getString(3));
								s.setsPhNo(rs.getString(4));
								s.setsEmail(rs.getString(5));
								s.setsGender(rs.getString(6));
								s.setsAddr(rs.getString(7));
								s.setsExp(rs.getString(8));
								s.setIsActive(rs.getString(9));
								s.setCreatedDate(rs.getDate(10));
								s.setDOB(rs.getDate(11));
								return s;
							}
						});
	}

	// view staff by name

	@Override
	public List<Staff> getStaffByName(String sName) {
		return template
				.query("select sId,sName,roleName,sPhNo,sEmail,"
						+ "sGender,sAddr,sExp,isActive,createdDate,dob "
						+ "from cm_staffTable stf join cm_roleTable role "
						+ "on(stf.roleId=role.roleId)"
						+ "where sName='" + sName + "'",
						new RowMapper<Staff>() {
							public Staff mapRow(ResultSet rs, int row)
									throws SQLException {
								Staff s = new Staff();
								s.setsId(rs.getInt(1));
								s.setsName(rs.getString(2));
								s.setRoleName(rs.getString(3));
								s.setsPhNo(rs.getString(4));
								s.setsEmail(rs.getString(5));
								s.setsGender(rs.getString(6));
								s.setsAddr(rs.getString(7));
								s.setsExp(rs.getString(8));
								s.setIsActive(rs.getString(9));
								s.setCreatedDate(rs.getDate(10));
								s.setDOB(rs.getDate(11));

								return s;
							}
						});
	}

	//View Staff by Id
		
		@Override
		public Staff getStaffById(int sId){
			String sql="select sId,sName,roleName,sPhNo,sGender,"
					+ "sExp,sPhNo,username,password,sAddr,DOB,sEmail from"
					+ " cm_staffTable stf join cm_roleTable role "
					+ "on(stf.roleId=role.roleId) where sId=?";
			return template.queryForObject(sql, new Object[] { sId },
					new BeanPropertyRowMapper<Staff>(Staff.class));
		}
		
	// disable staff
	   
	    @Override
		public int disableStaff(int sId) {
			String sql = "update cm_staffTable set isActive='no' where sId=?"+ "";
			return template.update(sql,new Object[] { sId });
		}
	    
    //get id  from role name 
	   
	    @Override
		public AdminRole getRoleName(String roleName) {
			String sql = "select roleId from cm_roleTable where roleName=?";
			return template.queryForObject(sql, new Object[] {roleName },
					new BeanPropertyRowMapper<AdminRole>(AdminRole.class));
		}
	    
	 // save staff
		
		@Override
		public int saveStaff(Staff staff) {
			String roleName = staff.getRoleName();
			AdminRole ad = getRoleName(roleName);

			SimpleDateFormat ft = new SimpleDateFormat("YYYY-MM-dd");
			String sqlDate = ft.format(staff.getDOB());

			String sql = "insert into cm_staffTable(roleId,sName,DOB,sGender,sAddr,"
					+ "sExp,sPhNo,sEmail,username,password,isActive,createdDate) values "
					+ "("
					+ ad.getRoleId()
					+ ",'"
					+ staff.getsName()
					+ "',"
					+ "TO_DATE('"
					+ sqlDate
					+ "','YYYY-MM-dd')"
					+ ",'"
					+ staff.getsGender()
					+ "','"
					+ staff.getsAddr()
					+ "','"
					+ staff.getsExp()
					+ "','"
					+ staff.getsPhNo()
					+ "','"
					+ staff.getsEmail()
					+ "','"
					+ staff.getUsername()
					+ "','"
					+ staff.getPassword()
					+ "','"
					+ "yes"
					+ "',"
					+ "TO_DATE('"+ java.time.LocalDate.now() + "','YYYY-MM-DD')" + ")";

			return template.update(sql);
		}
		
		//update staff
	    
	    @Override
		public int updateStaff(Staff staff) {
	                 SimpleDateFormat ft = new SimpleDateFormat("YYYY-MM-dd");
	                 String sqlDate = ft.format(staff.getDOB());

	                 String sql = "update cm_staffTable set sName='"+staff.getsName()
	                 + "',"
	                 + "DOB="+"TO_DATE('"+sqlDate+ "','YYYY-MM-DD')"
	                 + ","
	                 + "sGender='"+staff.getsGender()
	                 + "',"
	                 + "sAddr='"+staff.getsAddr()
	                 + "',"
	                 + "sExp='"+staff.getsExp()
	                 + "',"
	                 + "sPhNo='"+staff.getsPhNo()
	                 + "',"
	                 + "sEmail='"+staff.getsEmail()
	                 + "'"
	                 +" where sId="+staff.getsId()+"";
	                 return template.update(sql);
	                 }

	
/*----------------------------------------------DOCTOR--------------------------------------------------------*/	
	
	// view doctor list
	
	@Override
	public List<DoctorStaff> getDoctor() {
		return template
				.query("select doc.sId,sName,sGender,sAddr,sExp,sPhNo,sEmail,isActive"
						+ ",createdDate,dob,dSpec,dId,dQualification from cm_doctorTable"
						+ " doc join cm_staffTable stf on(doc.sId=stf.sId) where isActive='yes'"
						+ " order by doc.sId desc ",
						new RowMapper<DoctorStaff>() {
							public DoctorStaff mapRow(ResultSet rs, int row)
									throws SQLException {
								DoctorStaff doc = new DoctorStaff();
								doc.setsId(rs.getInt(1));
								doc.setsName(rs.getString(2));
								doc.setsGender(rs.getString(3));
								doc.setsAddr(rs.getString(4));
								doc.setsExp(rs.getString(5));
								doc.setsPhNo(rs.getString(6));
								doc.setsEmail(rs.getString(7));
								doc.setIsActive(rs.getString(8));
								doc.setCreatedDate(rs.getDate(9));
								doc.setDOB(rs.getDate(10));
								doc.setdSpec(rs.getString(11));
								doc.setdId(rs.getInt(12));
								doc.setdQualification(rs.getString(13));
								return doc;

							}
						});
	}

	// view doctor by name
	
	@Override
	public List<DoctorStaff> getDocByName(String sName) {
		return template
				.query("select stf.sId,sName,sGender,sAddr,sExp,sPhNo,sEmail,isActive"
						+ ",createdDate,dob,dSpec,dId,dQualification from cm_doctorTable "
						+ "doc join cm_staffTable stf on(doc.sId=stf.sId) where sName='"
						+ sName + "'", new RowMapper<DoctorStaff>() {
					public DoctorStaff mapRow(ResultSet rs, int row)
							throws SQLException {
						DoctorStaff doc = new DoctorStaff();
						doc.setsId(rs.getInt(1));
						doc.setsName(rs.getString(2));
						doc.setsGender(rs.getString(3));
						doc.setsAddr(rs.getString(4));
						doc.setsExp(rs.getString(5));
						doc.setsPhNo(rs.getString(6));
						doc.setsEmail(rs.getString(7));
						doc.setIsActive(rs.getString(8));
						doc.setCreatedDate(rs.getDate(9));
						doc.setDOB(rs.getDate(10));
						doc.setdSpec(rs.getString(11));
						doc.setdId(rs.getInt(12));
						doc.setdQualification(rs.getString(13));
						return doc;

					}
				});
	}
	
	//view doctor by id
	
	@Override
	public DoctorStaff getDoctorById(int dId){
		String sql="select stf.sId,cdt.dId,sName,sGender,sAddr,sExp,sPhNo,sEmail,isActive,username,password"
		+ ",createdDate,dob,dSpec,doc.dId,dQualification,consultFee,sunday,monday,tuesday,wednesday"
		+ ",thursday,friday,saturday from cm_doctorTable doc join "
		+ "cm_staffTable stf on(doc.sId=stf.sId)join cm_doctorDayTable cdt"
		+ " on (doc.dId=cdt.dId) where cdt.dId=?";
		return template.queryForObject(sql, new Object[] { dId },
		new BeanPropertyRowMapper<DoctorStaff>(DoctorStaff.class));
		}
	
	 // disable doctor
		
		@Override
		public int disableDoctor(int dId) {
			String sql = "update cm_StaffTable set isActive='Not Active' where sId=(select sId"
					+ " from cm_DoctorTable where dId=?)";
			return template.update(sql, new Object[] { dId });
		}
	
/*------------------------------------------MEDICINE------------------------------------------------------------*/
	
		// view medicine list
	
		@Override
		public List<Medicine> getMedicine() {
			return template
					.query("select medId,medName,medPrice,manufacturer,createdDate,isActive"
							+ " from cm_medicineTable where isActive=0",
							new RowMapper<Medicine>() {
								public Medicine mapRow(ResultSet rs, int row)
										throws SQLException {
									Medicine a1 = new Medicine();

									a1.setMedId(rs.getInt(1));
									a1.setMedName(rs.getString(2));
									a1.setMedPrice(rs.getInt(3));
									a1.setManufacturer(rs.getString(4));
									a1.setCreatedDateM(rs.getDate(5));
									a1.setIsActive(rs.getInt(6));
									return a1;

								}
							});
		}
		
		// view medicine by name
		
		@Override
		public List<Medicine> getMedByName(String medName) {
			return template
					.query("select medId,medName,medPrice,manufacturer,isActive,createdDate "
							+ "from cm_medicineTable  where medName='"+medName+"'",
							new RowMapper<Medicine>() {
								public Medicine mapRow(ResultSet rs, int row)
										throws SQLException {
									Medicine a1 = new Medicine();

									a1.setMedId(rs.getInt(1));
									a1.setMedName(rs.getString(2));
									a1.setMedPrice(rs.getInt(3));
									a1.setManufacturer(rs.getString(4));
									a1.setIsActive(rs.getInt(5));
									a1.setCreatedDateM(rs.getDate(6));
									return a1;

								}
							});
		}
		
		// get all medicine
	
		@Override
		public List<Medicine> getAllMedicines() {
			return template.query(
					"Select medId,medName,medPrice,isActive,manufacturer,"
							+ "createdDate from cm_medicineTable",
					new RowMapper<Medicine>() {
						public Medicine mapRow(ResultSet rs, int row)
								throws SQLException {
							Medicine doc = new Medicine();
							doc.setMedId(rs.getInt(1));
							doc.setMedName(rs.getString(2));
							doc.setMedPrice(rs.getDouble(3));
							doc.setIsActive(rs.getInt(4));
							doc.setManufacturer(rs.getString(5));
							doc.setCreatedDateM(rs.getDate(6));
							return doc;
						}
					});
		}
		
		// View Medicine by Id
	
		@Override
		public Medicine getMedById(int medId) {
			String sql = "select medId,medName,medPrice,manufacturer from "
					+ "cm_medicineTable where medId=?";
			return template.queryForObject(sql, new Object[] { medId },
					new BeanPropertyRowMapper<Medicine>(Medicine.class));
		}
		
		// disable medicine
	
		@Override
		public int disableMedicine(int medId) {
			
			String sql = "update cm_medicineTable set isActive=1 where medId=?";
			return template.update(sql,new Object[] { medId });
		}
		
		// save medicine
		
		@Override
		public int saveMed(Medicine a1) {
			String sql = "insert into cm_medicineTable(medName,medPrice,isActive,"
					+ "manufacturer,createdDate) values"
					+ "('"
					+ a1.getMedName()
					+ "',"
					+ a1.getMedPrice()
					+ ","+0+",'"
					+ a1.getManufacturer()
					+ "',TO_DATE('"+ java.time.LocalDate.now() + "','YYYY-MM-DD'))";
			return template.update(sql);
		}
		
		//update medicine
		
		@Override
		public int updateMed(Medicine a1) {
			String sql = "update cm_medicineTable set medName='" + a1.getMedName()
			+ "',medPrice=" + a1.getMedPrice() + ",isActive=" + a1.getIsActive()
					+ ",manufacturer='" + a1.getManufacturer()
	                + "' where medId="
					+ a1.getMedId() + "";
			return template.update(sql);
		}
		
		//save doctor
		
		@Override
		public int saveDoctor(DoctorStaff ds) {

			SimpleDateFormat ft = new SimpleDateFormat("YYYY-MM-dd");
			String sqlDate = ft.format(ds.getDOB());

			String sql = "insert into cm_staffTable(roleId,sName,DOB,sGender,sAddr,sExp,sPhNo,"
					+ "sEmail,username,password,isActive,createdDate)values("
					+ 1
					+ ",'"
					+ ds.getsName()
					+ "',"
					+ "TO_DATE('" + sqlDate + "','YYYY-MM-DD')"
					+ ",'"
					+ ds.getsGender()
					+ "','"
					+ ds.getsAddr()
					+ "','"
					+ ds.getsExp()
					+ "','"
					+ ds.getsPhNo()
					+ "','"
					+ ds.getsEmail()
					+ "','"
					+ ds.getUsername()
					+ "','"
					+ ds.getPassword()
					+ "','"
					+ "yes" + "',"
					+ "TO_DATE('" + java.time.LocalDate.now() + "','YYYY-MM-DD')"
					+ ")";

			template.update(sql);
			Integer maxId = getSequence1();
			String sql1 = "insert into cm_doctorTable(sId,dSpec,dQualification,consultFee)values("
					+ maxId
					+ ",'"
					+ ds.getdSpec()
					+ "','"
					+ ds.getdQualification()
					+ "'," + ds.getConsultFee() + ")";

			template.update(sql1);
			Integer maxdId = getSequence2();
			String sql2 = "insert into cm_doctorDayTable(dId,sunday,monday,tuesday,"
					+ "wednesday,thursday,friday,saturday) values("
					+ maxdId
					+ ",'"
					+ ds.getSunday()
					+ "','"
					+ ds.getMonday()
					+ "','"
					+ ds.getTuesday()
					+ "','"
					+ ds.getWednesday()
					+ "','"
					+ ds.getThursday()
					+ "','"
					+ ds.getFriday()
					+ "','"
					+ ds.getSaturday()
					+ "')";
			return template.update(sql2);

		}

		private Integer getSequence1() {
			Integer seq;
			String sql = "select MAX(sId)from cm_staffTable";

			seq = template.queryForObject(sql, new Object[] {}, Integer.class);
			return seq;
		}

		private Integer getSequence2() {
			Integer seq;
			String sql = "select MAX(dId)from cm_doctorTable";
			seq = template.queryForObject(sql, new Object[] {}, Integer.class);
			return seq;
		}

		

/*---------------------------------------PATIENT------------------------------------------------------------------*/		
		
		// view patient list
	
		@Override
		public List<Patient> getPatients() {
			return template
			.query("select regId,pFName,pLName,pGender,DOB,pAddr,"
					+ "pPhNo,pBloodGrp,createdDate from cm_patientTable order by regId desc",
							new RowMapper<Patient>() {
								@Override
								public Patient mapRow(ResultSet rs, int row)
										throws SQLException {
									Patient pa = new Patient();
									pa.setRegId(rs.getInt(1));
									pa.setpFName(rs.getString(2));
									pa.setpLName(rs.getString(3));
									pa.setpGender(rs.getString(4));
									pa.setDOB(rs.getDate(5));
									pa.setpAddr(rs.getString(6));
									pa.setpPhNo(rs.getString(7));
									pa.setpBloodGrp(rs.getString(8));
									pa.setCreatedDate(rs.getDate(9));
									return pa;
								}
							});
		}

		// view patient by name
	
		@Override
		public List<Patient> getPatientByName(String regName) {
			return template.query(
					"select regId,pFName,pLName,pGender,DOB,pAddr,pPhNo,pBloodGrp,"
							+ " createdDate from cm_patientTable where pFName='"
							+ regName + "'", new RowMapper<Patient>() {
						@Override
						public Patient mapRow(ResultSet rs, int row)
								throws SQLException {
							Patient pa = new Patient();
							pa.setRegId(rs.getInt(1));
							pa.setpFName(rs.getString(2));
							pa.setpLName(rs.getString(3));
							pa.setpGender(rs.getString(4));
							pa.setDOB(rs.getDate(5));
							pa.setpAddr(rs.getString(6));
							pa.setpPhNo(rs.getString(7));
							pa.setpBloodGrp(rs.getString(8));
							pa.setCreatedDate(rs.getDate(9));
							return pa;

						}
					});
		}
		
		// get patient info

		@Override
		public Patient getPatientByRegId(int regId) {
			String sql = "select regId,pFName,pLName,pGender,DOB,pAddr,pPhNo,"
					+ "pBloodGrp,createdDate from cm_patientTable where regId=?";
			return template.queryForObject(sql, new Object[] { regId },
					new BeanPropertyRowMapper<Patient>(Patient.class));

		}
		
		// save patient
	
		@Override
		public int savePatient(Patient pa) {

			SimpleDateFormat ft = new SimpleDateFormat("YYYY-MM-dd");
			String sqlDate = ft.format(pa.getDOB());

			String sql = "insert into cm_patientTable(pFName,pLName,pGender,DOB,"
					+ "pAddr,pPhNo,pBloodGrp,createdDate) values('"
					+ pa.getpFName()
					+ "','"
					+ pa.getpLName()
					+ "','"
					+ pa.getpGender()
					+ "',"
					+ "TO_DATE('"+ sqlDate+ "','YYYY-MM-dd')"
					+ ",'"
					+ pa.getpAddr()
					+ "','"
					+ pa.getpPhNo()
					+ "','"
					+ pa.getpBloodGrp()
					+ "',"
					+ "TO_DATE('"+ java.time.LocalDate.now()+"','YYYY-MM-DD')"
					+ ")";
			return template.update(sql);
		}
		
		//update patient
	     /* (non-Javadoc)
		 * @see com.ust.dao.iAdminDao#updatePatient(com.ust.model.Patient)
		 */
	    @Override
		public int updatePatient(Patient pa) {
			
			SimpleDateFormat ft=new SimpleDateFormat("YYYY-MM-dd"); 
			String sqlDate=ft.format(pa.getDOB());
			
			String sql = "update cm_patientTable set pFName='" + pa.getpFName()
					+ "',pLName='" + pa.getpLName() + "',pGender='" + pa.getpGender()
					+ "',DOB=" + "TO_DATE('"+ sqlDate + "','YYYY-MM-DD')"+ ",pAddr='" + pa.getpAddr()
					+ "',pPhNo='"+ pa.getpPhNo() + "',pBloodGrp='" + pa.getpBloodGrp()
					+ "' where regId="+ pa.getRegId() + "";

			return template.update(sql);

		}
		
	// add appoinment
	
	@Override
	public int addAppoinment(Patient pa, int regId, int dId) {
		String sql = "insert into cm_appoinmentTable(regId,dId,dateOfApp,consultStatus) values("
				+ regId
				+ ","
				+ dId
				+ ","
				+ "TO_DATE('"
				+ java.time.LocalDate.now() + "','YYYY-MM-DD')" + ",'no')";
		return template.update(sql);

	}


	private Integer getSequence() {
		Integer seq;
		String sql = "select MAX(sId)from cm_staffTable";
		seq = template.queryForObject(sql, new Object[] {}, Integer.class);
		return seq;
	}

	/*---------------------------------DOCTOR MODULE-------------------------------------*/

	// add patient comments

	@Override
	public int addPatientComments(DoctorObservation obs,int regId,int dId) {
		String sql = "insert into cm_doctorObsTable(obserDate,obsNotes,regId,dId) values("
				+ "TO_DATE('"+ java.time.LocalDate.now() + "','YYYY-MM-DD')"
				+ ",'"
				+ obs.getObsNotes()
				+ "',"
				+ "?" + "," + "?" + ")";
		return template.update(sql,new Object[] { regId,dId });
	}

	@Override
	public int addlabtestRequest(int regId, int dId) {
		//Integer labId = doc_getLabId(labName);
		String sql = "insert into cm_assignLabTable(regId,dId,labId,assigLabDate,sampleStatus,testStatus)"
				+ "values("
				+ regId
				+ ","
				+ dId
				+ ","
				+ 1
				+ ",TO_DATE('"+ java.time.LocalDate.now() + "','YYYY-MM-DD'),'no','no')";

		return template.update(sql);
	}

	// get doctor appointment

	@Override
	public List<DoctorAppointment> getTodaysDoctorAppointment(int dId) {
		return template
				.query("select cd.dId,ca.appId,ca.regId,cp.pFName,cp.pLName,consultStatus from cm_appoinmentTable ca"
						+ " join cm_patientTable cp on ca.regId=cp.regId join cm_doctorTable cd on "
						+ "ca.dId=cd.dId where cd.dId="
						+ dId
						+ " and dateOfApp="
						+ " TO_DATE('"+ java.time.LocalDate.now() + "','YYYY-MM-DD')",
						new RowMapper<DoctorAppointment>() {
							public DoctorAppointment mapRow(ResultSet rs,
									int row) throws SQLException {
								DoctorAppointment dj = new DoctorAppointment();
								dj.setdId(rs.getInt(1));
								dj.setAppId(rs.getInt(2));
								dj.setRegId(rs.getInt(3));
								dj.setpFName(rs.getString(4));
								dj.setpLName(rs.getString(5));
								dj.setConsultStatus(rs.getString(6));
								return dj;
							}

						});
	}

	
	// get todays appointment

		@Override
		public List<DoctorAppointment> getTodaysAppointment() {
		return template
		.query("select cp.regId,cp.pFName,cs.sName from cm_patientTable cp"
		+ " join cm_appoinmentTable ca on ( ca.regId=cp.regId ) join cm_doctorTable cd on "
		+ "ca.dId=cd.dId join cm_staffTable cs on (cs.sId=cd.sId) where ca.dateOfApp= "
		+ " TO_DATE('"
		+ java.time.LocalDate.now() + "','YYYY-MM-DD')" + "",
		new RowMapper<DoctorAppointment>() {
		public DoctorAppointment mapRow(ResultSet rs,
		int row) throws SQLException {
		DoctorAppointment dj = new DoctorAppointment();
		
		dj.setRegId(rs.getInt(1));
		dj.setpFName(rs.getString(2));
		dj.setsName(rs.getString(3));
	
		return dj;
		}

		});
		}

	/*----------------------------------------------------------------------------------------*/

	@Override
	public List<DoctorStaff> getAvailableDoctorByDay() {

		LocalDate now = LocalDate.now();
		DayOfWeek now1 = now.getDayOfWeek();
		System.out.println(now1);

		return template
				.query("select cm_doctordayTable.dId,cm_staffTable.sName from cm_doctordayTable"
						+ " join cm_doctorTable on(cm_doctordayTable.dId=cm_doctorTable.dId)"
						+ " join cm_staffTable"
						+ " on(cm_doctorTable.sId=cm_staffTable.sId) where "
						+ now1 + "='true'", new RowMapper<DoctorStaff>() {
					public DoctorStaff mapRow(ResultSet rs, int row)
							throws SQLException {
						DoctorStaff doc = new DoctorStaff();
						doc.setdId(rs.getInt(1));
						doc.setsName(rs.getString(2));

						return doc;
					}
				});

	}


	@Override
	public DoctorStaff getDoctorInfo(String sName) {

		LocalDate now = LocalDate.now();
		DayOfWeek now1 = now.getDayOfWeek();
		System.out.println(now1);
		String sql = "select cm_doctordayTable.dId,cm_staffTable.sName,cm_doctorTable.dSpec,"
				+ " cm_doctorTable.consultFee from cm_doctordayTable"
				+ " join cm_doctorTable on(cm_doctordayTable.dId=cm_doctorTable.dId) join cm_staffTable"
				+ " on(cm_doctorTable.sId=cm_staffTable.sId) where "
				+ now1
				+ "='true' and sName= '" + sName + "'";

		return template.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<DoctorStaff>(DoctorStaff.class));

	}

	// add lab test request
	
			@Override
			public AssignLabTest getLabId(String labName) {
				String sql = "select labId from cm_labTestTable where lName=?";
				return template.queryForObject(sql, new Object[] {labName},
						new BeanPropertyRowMapper<AssignLabTest>(AssignLabTest.class));
			

			}

	// add lab request

	@Override
	public int addLabTestRequest(AssignLabTest lab,int regId,int dId) {
			
		AssignLabTest labtest  = getLabId(lab.getlName());
		
			int labId = labtest.getLabId();

			String sql = "insert into cm_assignLabTable(regId,dId,labId,assigLabDate,sampleStatus,testStatus)"
					+ "values(?,?,?"
					+ ",TO_DATE('"+ java.time.LocalDate.now() + "', 'YYYY-MM-DD'),'no','no')";
			 return template.update(sql, new Object[] {regId,dId, labId });
		
	}

	// get all test

	@Override
	public List<LabTest> getLabTest() {
		return template.query(
				"select labId,lName,lFee,description from cm_labTestTable",
				new RowMapper<LabTest>() {
					public LabTest mapRow(ResultSet rs, int row)
							throws SQLException {
						LabTest lab = new LabTest();
						lab.setLabId(rs.getInt(1));
						lab.setlName(rs.getString(2));
						lab.setlFee(rs.getInt(3));
						lab.setDescription(rs.getString(4));
						return lab;

					}
				});
	}

	

	/*--------------------------patient history------------------------------------------*/

	//patient list
	@Override
	public List<Patient> getPatient(int regId) {
		return template.query(
				"select regId,pFName,pLName,DOB from cm_patientTable where regId="
						+ regId, new RowMapper<Patient>() {
					public Patient mapRow(ResultSet rs, int row)
							throws SQLException {
						Patient s = new Patient();
						s.setRegId(rs.getInt(1));
						s.setpFName(rs.getString(2));
						s.setpLName(rs.getString(3));
						s.setDOB(rs.getDate(4));
						return s;
					}
				});
	}
	
	
	@Override
	public Patient getPatientDetails(int regId){
		
		
		String sql = "select regId,pFName,pLName,DOB from cm_patientTable where regId=?";
				
		return template.queryForObject(sql, new Object[] {regId},
				new BeanPropertyRowMapper<Patient>(Patient.class));
}

	@Override
	public List<Prescription> patientPrescriptionMedicine(int regId) {
		return template
				.query("select cm_staffTable.sName,cm_prescriptionTable.medDays,cm_prescriptionTable.prescDate,"
						+ "cm_prescriptionTable.medFreq,cm_medicineTable.medId,cm_medicineTable.medName  from cm_staffTable join cm_doctorTable "
						+ "on(cm_staffTable.sId=cm_doctorTable.sId) join cm_prescriptionTable "
						+ "on(cm_doctorTable.dId=cm_prescriptionTable.dId)"
						+ "join cm_medicineTable on(cm_prescriptionTable.medId=cm_medicineTable.medId)"
						+ "where cm_prescriptionTable.regId=" + regId,
						new RowMapper<Prescription>() {
							public Prescription mapRow(ResultSet rs, int row)
									throws SQLException {
								Prescription ppm = new Prescription();

								ppm.setsName(rs.getString(1));
								ppm.setMedDays(rs.getInt(2));
								ppm.setPrescDate(rs.getDate(3));
								ppm.setMedFreq(rs.getString(4));
								ppm.setMedId(rs.getInt(5));
								ppm.setMedName(rs.getString(6));
								return ppm;
							}
						});

	}

	@Override
	public List<AssignLabTest> patientLab(int regId) {
		return template
				.query("select cm_staffTable.sName,cm_assignLabTable.assigLabDate,cm_labTestTable.labId,"
						+ " cm_labTestTable.lName from cm_staffTable join cm_doctorTable on"
						+ "(cm_staffTable.sId=cm_doctorTable.sId) join cm_assignLabTable"
						+" on(cm_doctorTable.dId=cm_assignLabTable.dId)join cm_labTestTable"
						+ " on(cm_assignLabTable.labId=cm_labTestTable.labId) where "
						+ "cm_assignLabTable.regId="
						+ regId, new RowMapper<AssignLabTest>() {
					public AssignLabTest mapRow(ResultSet rs, int row)
							throws SQLException {
						AssignLabTest pl = new AssignLabTest();

						pl.setsName(rs.getString(1));
						pl.setAssigLabDate(rs.getDate(2));
						pl.setAssignLabId(rs.getInt(3));
						pl.setlName(rs.getString(4));
						return pl;
					}
				});

	}

	
	@Override
	public List<DoctorObservation> patientObservation(int regId) {
		return template
				.query("select cm_staffTable.sName,cm_doctorObsTable.dObsId,"
						+ "cm_doctorObsTable.obsNotes,cm_doctorObsTable.obserDate " 
                         +" from cm_staffTable join cm_doctorTable on"
                         + "(cm_staffTable.sId=cm_doctorTable.sId) join cm_doctorObsTable"
                         +" on(cm_doctorTable.dId=cm_doctorObsTable.dId)"
                         + " where cm_doctorObsTable.regId="
						+ regId, new RowMapper<DoctorObservation>() {
					public DoctorObservation mapRow(ResultSet rs, int row)
							throws SQLException {
						DoctorObservation po = new DoctorObservation();

						po.setsName(rs.getString(1));
						po.setdObsId(rs.getInt(2));
						po.setObsNotes(rs.getString(3));
						return po;
					}
				});

	}

	//update doctor
	@Override
	public int updateDoctor(DoctorStaff aBean) {


		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = new java.util.Date();
		long t = date.getTime();
		java.sql.Date sqlDate = new java.sql.Date(t);


		String sql = "update cm_staffTable set sName='" + aBean.getsName()
		+ "',DOB=" + "TO_DATE('" + sqlDate + "','YYYY-MM-dd')"
		+ ",sGender='" + aBean.getsGender() + "',sAddr='"
		+ aBean.getsAddr() + "',sExp='" + aBean.getsExp() + "',sPhNo='"
		+ aBean.getsPhNo() + "',sEmail='" + aBean.getsEmail()
		+ "',username='" + aBean.getUsername() + "',password='"
		+ aBean.getPassword() + "',isActive='yes' where sId= "
		+ aBean.getsId() + "";
		template.update(sql, new Object[] {});

		String sql1 = "update cm_doctorTable set dSpec='" + aBean.getdSpec()
		+ "',dQualification='" + aBean.getdQualification()
		+ "',consultFee=" + aBean.getConsultFee() + " where sId="
		+ aBean.getsId() + "";
		template.update(sql1, new Object[] {});

		int dId = getSeqDid(aBean.getsId());

		String sql2 = "update cm_DoctorDayTable set sunday='"
		+ aBean.getSunday() + "',monday='" + aBean.getMonday()
		+ "',tuesday='" + aBean.getTuesday() + "',wednesday='"
		+ aBean.getWednesday() + "',thursday='" + aBean.getThursday()
		+ "',friday='" + aBean.getFriday() + "',saturday='"
		+ aBean.getSaturday() + "'where dId=" + dId + "";
		return template.update(sql2, new Object[] {});

		}

		@Override
		public int getSeqDid(int sId) {
		int id1;
		String sql = "select dId from cm_doctorTable where sId=?";

		id1 = template.queryForObject(sql, new Object[] { sId }, Integer.class);
		return id1;
		}
	
	
	
	//get patient by phone
	
		@Override
		public List<Patient> getPatientByPhone(int pPhNo) {
			return template.query(
					"select regId,pFName,pLName,pGender,DOB,pAddr,pPhNo,pBloodGrp,"
							+ " createdDate from cm_patientTable where pPhNo='"
							+ pPhNo + "'", new RowMapper<Patient>() {
						@Override
						public Patient mapRow(ResultSet rs, int row)
								throws SQLException {
							Patient pa = new Patient();
							pa.setRegId(rs.getInt(1));
							pa.setpFName(rs.getString(2));
							pa.setpLName(rs.getString(3));
							pa.setpGender(rs.getString(4));
							pa.setDOB(rs.getDate(5));
							pa.setpAddr(rs.getString(6));
							pa.setpPhNo(rs.getString(7));
							pa.setpBloodGrp(rs.getString(8));
							pa.setCreatedDate(rs.getDate(9));
							return pa;

						}
					});

		}

/*-----------------------------------------------add prescription-----------------------------------------------------*/	

		@Override
		public Integer d_getMedicineId(String medName) {
			String sql = "select medId from cm_medicineTable where medName = ?";
			return template.queryForObject(sql, new Object[] { medName },
					Integer.class);

		}

	
		@Override
		public int saveDoctorPrescription(Prescription doc,int regId,int dId) {
			Integer medId = d_getMedicineId(doc.getMedName());
			String sql = "insert into cm_prescriptionTable(dId,regId,medId,medFreq,medDays,prescDate) values("
					+"?,"
					+"?,"
					+"?,"
					+ "'"
					+ doc.getMedFreq()
					+ "',"
					+ doc.getMedDays()
					+ ",TO_DATE('"
					+ java.time.LocalDate.now()
					+ "', 'YYYY-MM-DD'))";
			return template.update(sql,new Object[] { dId,regId,medId });
		}

		@Override
		public List<Prescription> getAllPrescription(int regId,
				int dId) {
			return template
					.query("select p.dId,p.regId,p.prescdate,p.medDays , p.medFreq ,m.medName from cm_prescriptiontable p join cm_medicineTable m on p.medid=m.medid where trunc(prescdate) = trunc(sysdate) and regId = "
							+ regId + "and dId=" + dId + " ",

					new RowMapper<Prescription>() {
						public Prescription mapRow(ResultSet rs,
								int row) throws SQLException {
							Prescription db = new Prescription();
							db.setdId(rs.getInt(1));
							db.setRegId(rs.getInt(2));
							db.setPrescDate(rs.getDate(3));
							db.setMedDays(rs.getInt(4));
							db.setMedFreq(rs.getString(5));
							db.setMedName(rs.getString(6));
							return db;
						}
					});
		}


		@Override
		public List<Medicine> medicinelist() {
			return template.query("select medId ,medName from cm_MedicineTable",
					new RowMapper<Medicine>() {
						public Medicine mapRow(ResultSet rs, int row)
								throws SQLException {
							Medicine db = new Medicine();
							db.setMedId(rs.getInt(1));
							db.setMedName(rs.getString(2));
							return db;
						}
					});
		}

	
	
	
	
}
