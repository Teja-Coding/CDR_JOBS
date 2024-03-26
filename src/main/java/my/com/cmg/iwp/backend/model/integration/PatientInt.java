package my.com.cmg.iwp.backend.model.integration;

// Generated Nov 29, 2012 5:15:32 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * PhPatientInt generated by hbm2java
 */
@Entity
@Table(name = "t_patient_int")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PatientInt implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long patientIntSeqno;
	private String patientMrn;
	private String patientIdno;
	private String patientIdType;
	private String patientPosition;
	private String patientPrefix;
	private Character vip;
	private Character government;
	private String approximateAge;
	private String fullName;
	private Date dob;
	private String gender;
	private String nationality;
	private String ethnicity;
	private String religion;
	private String otherReligion;
	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String state;
	private String postcode;
	private String country;
	private String homePhone;
	private String mobilePhone;
	private String officePhone;
	private String fax;
	private String parameter1;
	private String parameter2;
	private BigDecimal parameter3;
	private BigDecimal parameter4;
	private Date parameter5;
	private Long createdBy;
	private Date createdDate;
	private Long updatedBy;
	private Date updatedDate;
	private String otherIdno;
	private String otherIdType;
	private String governmentRelationship;
	private Character noKnownAllergy;
	private String otherEthnicity;
	private BigDecimal patientHeight;
	private BigDecimal patientWeight;
	private BigDecimal patientBmi;
	
	private String refPatientMrn;

	public PatientInt() {
		super();
	}

	public PatientInt(long patientIntSeqno) {
		super();
		this.patientIntSeqno = patientIntSeqno;
	}

	public PatientInt(long patientIntSeqno, String patientMrn,
			String patientIdno, String patientIdType, String patientPosition,
			String patientPrefix, Character vip, Character government,
			String approximateAge, String fullName, Date dob, String gender,
			String nationality, String ethnicity, String religion,
			String otherReligion, String address1, String address2,
			String address3, String city, String state, String postcode,
			String country, String homePhone, String mobilePhone,
			String officePhone, String fax, String parameter1,
			String parameter2, BigDecimal parameter3, BigDecimal parameter4,
			Date parameter5, Character activeFlag, Long createdBy,
			Date createdDate, Long updatedBy, Date updatedDate,
			String otherIdno, String otherIdType,
			String governmentRelationship, Character noKnownAllergy,
			String otherEthnicity, BigDecimal patientHeight,
			BigDecimal patientWeight, BigDecimal patientBmi) {
		super();
		this.patientIntSeqno = patientIntSeqno;
		this.patientMrn = patientMrn;
		this.patientIdno = patientIdno;
		this.patientIdType = patientIdType;
		this.patientPosition = patientPosition;
		this.patientPrefix = patientPrefix;
		this.vip = vip;
		this.government = government;
		this.approximateAge = approximateAge;
		this.fullName = fullName;
		this.dob = dob;
		this.gender = gender;
		this.nationality = nationality;
		this.ethnicity = ethnicity;
		this.religion = religion;
		this.otherReligion = otherReligion;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.city = city;
		this.state = state;
		this.postcode = postcode;
		this.country = country;
		this.homePhone = homePhone;
		this.mobilePhone = mobilePhone;
		this.officePhone = officePhone;
		this.fax = fax;
		this.parameter1 = parameter1;
		this.parameter2 = parameter2;
		this.parameter3 = parameter3;
		this.parameter4 = parameter4;
		this.parameter5 = parameter5;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.otherIdno = otherIdno;
		this.otherIdType = otherIdType;
		this.governmentRelationship = governmentRelationship;
		this.noKnownAllergy = noKnownAllergy;
		this.otherEthnicity = otherEthnicity;
		this.patientHeight = patientHeight;
		this.patientWeight = patientWeight;
		this.patientBmi = patientBmi;
	}

	@Id
	@Column(name = "patient_int_seqno", unique = true, nullable = false)
	@SequenceGenerator(name="patient_int_seqno", sequenceName="t_patient_int_seq", allocationSize  = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="patient_int_seqno")
	@JsonIgnore
	public long getPatientIntSeqno() {
		return this.patientIntSeqno;
	}

	public void setPatientIntSeqno(long patientIntSeqno) {
		this.patientIntSeqno = patientIntSeqno;
	}

	@Column(name = "patient_mrn", nullable = false, length = 20)
	public String getPatientMrn() {
		return this.patientMrn;
	}

	public void setPatientMrn(String patientMrn) {
		this.patientMrn = patientMrn;
	}

	@Column(name = "patient_idno", length = 20)
	public String getPatientIdno() {
		return this.patientIdno;
	}

	public void setPatientIdno(String patientIdno) {
		this.patientIdno = patientIdno;
	}

	@Column(name = "patient_id_type", length = 20)
	public String getPatientIdType() {
		return this.patientIdType;
	}

	public void setPatientIdType(String patientIdType) {
		this.patientIdType = patientIdType;
	}

	@Column(name = "patient_position", length = 10)
	public String getPatientPosition() {
		return this.patientPosition;
	}

	public void setPatientPosition(String patientPosition) {
		this.patientPosition = patientPosition;
	}

	@Column(name = "patient_prefix", length = 15)
	public String getPatientPrefix() {
		return this.patientPrefix;
	}

	public void setPatientPrefix(String patientPrefix) {
		this.patientPrefix = patientPrefix;
	}

	@Column(name = "vip")
	public Character getVip() {
		return this.vip;
	}

	public void setVip(Character vip) {
		this.vip = vip;
	}

	@Column(name = "government")
	public Character getGovernment() {
		return this.government;
	}

	public void setGovernment(Character government) {
		this.government = government;
	}

	@Column(name = "approximate_age", length = 60)
	public String getApproximateAge() {
		return this.approximateAge;
	}

	public void setApproximateAge(String approximateAge) {
		this.approximateAge = approximateAge;
	}

	@Column(name = "full_name", length = 130)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dob", length = 13)
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "gender", length = 10)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "nationality", length = 20)
	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name = "ethnicity", length = 20)
	public String getEthnicity() {
		return this.ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	@Column(name = "religion", length = 20)
	public String getReligion() {
		return this.religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	@Column(name = "other_religion", length = 20)
	public String getOtherReligion() {
		return this.otherReligion;
	}

	public void setOtherReligion(String otherReligion) {
		this.otherReligion = otherReligion;
	}

	@Column(name = "address1", length = 100)
	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@Column(name = "address2", length = 100)
	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@Column(name = "address3", length = 100)
	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	@Column(name = "city", length = 100)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "state", length = 20)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "postcode", length = 10)
	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "country", length = 20)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "home_phone", length = 20)
	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@Column(name = "mobile_phone", length = 20)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "office_phone", length = 20)
	public String getOfficePhone() {
		return this.officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@Column(name = "fax", length = 20)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "parameter1", length = 100)
	public String getParameter1() {
		return this.parameter1;
	}

	public void setParameter1(String parameter1) {
		this.parameter1 = parameter1;
	}

	@Column(name = "parameter2", length = 100)
	public String getParameter2() {
		return this.parameter2;
	}

	public void setParameter2(String parameter2) {
		this.parameter2 = parameter2;
	}

	@Column(name = "parameter3", precision = 8, scale = 4)
	public BigDecimal getParameter3() {
		return this.parameter3;
	}

	public void setParameter3(BigDecimal parameter3) {
		this.parameter3 = parameter3;
	}

	@Column(name = "parameter4", precision = 8, scale = 4)
	public BigDecimal getParameter4() {
		return this.parameter4;
	}

	public void setParameter4(BigDecimal parameter4) {
		this.parameter4 = parameter4;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "parameter5", length = 13)
	public Date getParameter5() {
		return this.parameter5;
	}

	public void setParameter5(Date parameter5) {
		this.parameter5 = parameter5;
	}

	@Column(name = "created_by")
	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false, length = 29)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", nullable = false, length = 29)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "other_idno", length = 20)
	public String getOtherIdno() {
		return this.otherIdno;
	}

	public void setOtherIdno(String otherIdno) {
		this.otherIdno = otherIdno;
	}

	@Column(name = "other_id_type", length = 20)
	public String getOtherIdType() {
		return this.otherIdType;
	}

	public void setOtherIdType(String otherIdType) {
		this.otherIdType = otherIdType;
	}

	@Column(name = "government_relationship", length = 20)
	public String getGovernmentRelationship() {
		return this.governmentRelationship;
	}

	public void setGovernmentRelationship(String governmentRelationship) {
		this.governmentRelationship = governmentRelationship;
	}

	@Column(name = "no_known_allergy")
	public Character getNoKnownAllergy() {
		return this.noKnownAllergy;
	}

	public void setNoKnownAllergy(Character noKnownAllergy) {
		this.noKnownAllergy = noKnownAllergy;
	}

	@Column(name = "other_ethnicity", length = 20)
	public String getOtherEthnicity() {
		return this.otherEthnicity;
	}

	public void setOtherEthnicity(String otherEthnicity) {
		this.otherEthnicity = otherEthnicity;
	}

	@Column(name = "patient_height", precision = 8)
	public BigDecimal getPatientHeight() {
		return this.patientHeight;
	}

	public void setPatientHeight(BigDecimal patientHeight) {
		this.patientHeight = patientHeight;
	}

	@Column(name = "patient_weight", precision = 8)
	public BigDecimal getPatientWeight() {
		return this.patientWeight;
	}

	public void setPatientWeight(BigDecimal patientWeight) {
		this.patientWeight = patientWeight;
	}

	@Column(name = "patient_bmi", precision = 8)
	public BigDecimal getPatientBmi() {
		return this.patientBmi;
	}

	public void setPatientBmi(BigDecimal patientBmi) {
		this.patientBmi = patientBmi;
	}
	
	@Column(name = "ref_patient_mrn")
	public String getRefPatientMrn() {
		return refPatientMrn;
	}

	public void setRefPatientMrn(String refPatientMrn) {
		this.refPatientMrn = refPatientMrn;
	}

}

