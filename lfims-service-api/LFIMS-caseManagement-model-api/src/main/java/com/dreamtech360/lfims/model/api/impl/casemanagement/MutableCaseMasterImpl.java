package com.dreamtech360.lfims.model.api.impl.casemanagement;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.api.casemanagement.MutableCaseMaster;
import com.dreamtech360.lfims.model.api.casemanagement.CaseMaster;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class MutableCaseMasterImpl implements MutableCaseMaster{


	private int id;
	private String uuid;
	private String respondentName;
	private String caseNo;
	private String rpmaNo;
	private String bankName;
	private String branchName;
	private String courtName;
	private Date limitDate;
	private String caseNature;
	private double caseAmount;
	private Date filingDate;
	private Date judgementDate;
	private Date certificateDate;
	private Date closingDate;
	private String oldCourtName;
	private String oldCourtNumber;
	private String casePosition;
	private String caseForum;
	private String otherAdvocateName;
	private String otherAdvocatePhone;
	private boolean specialOfficerAppointed;
	private Date caseTransferDate;


	public MutableCaseMasterImpl(){

	}

	public MutableCaseMasterImpl(int id,
			//String uuid,
			String respondentName,
			String caseNo,
			String rpmaNo,
			String bankName,
			String branchName,
			String courtName,
			Date limitDate,
			String caseNature,
			double caseAmount,
			Date filingDate,
			Date judgementDate,
			Date certificateDate,
			Date closingDate,
			String oldCourtName,
			String oldCourtNumber,
			String casePosition,
			String caseForum,
			String otherAdvocateName,
			String otherAdvocatePhone,
			boolean specialOfficerAppointed,
			Date caseTransferDate)
	{
		this.id=id;
	//	this.uuid=uuid;
		this.respondentName=respondentName;
		this.caseNo=caseNo;
		this.rpmaNo=rpmaNo;
		this.bankName=bankName;
		this.branchName=branchName;
		this.courtName=courtName;
		this.limitDate=limitDate;
		this.caseNature=caseNature;
		this.caseAmount=caseAmount;
		this.filingDate=filingDate;
		this.judgementDate=judgementDate;
		this.certificateDate=certificateDate;
		this.closingDate=closingDate;
		this.oldCourtName=oldCourtName;
		this.oldCourtNumber=oldCourtNumber;
		this.casePosition=casePosition;
		this.caseForum=caseForum;
		this.otherAdvocateName=otherAdvocateName;
		this.otherAdvocatePhone=otherAdvocatePhone;
		this.specialOfficerAppointed=specialOfficerAppointed;
		this.caseTransferDate=caseTransferDate;

	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRespondentName() {
		return respondentName;
	}

	public void setRespondentName(String respondentName) {
		this.respondentName = respondentName;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getRpmaNo() {
		return rpmaNo;
	}

	public void setRpmaNo(String rpmaNo) {
		this.rpmaNo = rpmaNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	public String getCaseNature() {
		return caseNature;
	}

	public void setCaseNature(String caseNature) {
		this.caseNature = caseNature;
	}

	public double getCaseAmount() {
		return caseAmount;
	}

	public void setCaseAmount(double caseAmount) {
		this.caseAmount = caseAmount;
	}

	public Date getFilingDate() {
		return filingDate;
	}

	public void setFilingDate(Date filingDate) {
		this.filingDate = filingDate;
	}

	public Date getJudgementDate() {
		return judgementDate;
	}

	public void setJudgementDate(Date judgementDate) {
		this.judgementDate = judgementDate;
	}

	public Date getCertificateDate() {
		return certificateDate;
	}

	public void setCertificateDate(Date certificateDate) {
		this.certificateDate = certificateDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public String getOldCourtName() {
		return oldCourtName;
	}

	public void setOldCourtName(String oldCourtName) {
		this.oldCourtName = oldCourtName;
	}

	public String getOldCourtNumber() {
		return oldCourtNumber;
	}

	public void setOldCourtNumber(String oldCourtNumber) {
		this.oldCourtNumber = oldCourtNumber;
	}

	public String getCasePosition() {
		return casePosition;
	}

	public void setCasePosition(String casePosition) {
		this.casePosition = casePosition;
	}

	public String getCaseForum() {
		return caseForum;
	}

	public void setCaseForum(String caseForum) {
		this.caseForum = caseForum;
	}

	public String getOtherAdvocateName() {
		return otherAdvocateName;
	}

	public void setOtherAdvocateName(String otherAdvocateName) {
		this.otherAdvocateName = otherAdvocateName;
	}

	public String getOtherAdvocatePhone() {
		return otherAdvocatePhone;
	}

	public void setOtherAdvocatePhone(String otherAdvocatePhone) {
		this.otherAdvocatePhone = otherAdvocatePhone;
	}

	public boolean isSpecialOfficerAppointed() {
		return specialOfficerAppointed;
	}

	public void setSpecialOfficerAppointed(boolean specialOfficerAppointed) {
		this.specialOfficerAppointed = specialOfficerAppointed;
	}

	public Date getCaseTransferDate() {
		return caseTransferDate;
	}

	public void setCaseTransferDate(Date caseTransferDate) {
		this.caseTransferDate = caseTransferDate;
	}

	public String getJSONString() throws LFIMSModelException {

		return getJSON().toString();
	}
	public JSONObject getJSON() throws LFIMSModelException {
		JSONObject object= new JSONObject();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			object.put("id", this.id);
			object.put("uuid", this.uuid);
			object.put("respondentName",this.respondentName);		
			object.put("caseNo",this.caseNo);			
			object.put("rpmaNo",this.rpmaNo);			
			object.put("bankName",this.bankName);			
			object.put("branchName",this.branchName);			
			object.put("courtName",this.courtName);			
			object.put("limitDate",	formatter.format(this.limitDate));			
			object.put("caseNature",this.caseNature);			
			object.put("caseAmount",this.caseAmount);			
			object.put("filingDate",formatter.format(this.filingDate));			
			object.put("judgementDate",formatter.format(this.judgementDate));		
			object.put("certificateDate",formatter.format(this.certificateDate));		
			object.put("closingDate",formatter.format(this.closingDate));		
			object.put("oldCourtName",this.oldCourtName);		
			object.put("oldCourtNumber",this.oldCourtNumber);		
			object.put("casePosition",this.casePosition);		
			object.put("caseForum",this.caseForum);			
			object.put("otherAdvocateName",this.otherAdvocateName);		
			object.put("otherAdvocatePhone",this.otherAdvocatePhone);		
			object.put("specialOfficerAppointed",this.specialOfficerAppointed);	
			object.put("caseTransferDate",formatter.format(this.caseTransferDate));		



		} catch (Exception e) {
			throw new LFIMSModelException(e);
		}
		return object;
	}
	public int compareTo(CaseMaster o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getUuid() {
		// TODO Auto-generated method stub
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid=uuid;
		
	}

		
}
