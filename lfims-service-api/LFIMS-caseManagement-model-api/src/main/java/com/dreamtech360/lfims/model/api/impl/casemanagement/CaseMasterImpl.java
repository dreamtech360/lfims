package com.dreamtech360.lfims.model.api.impl.casemanagement;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.annotations.LFIMSCacheEntry;
import com.dreamtech360.lfims.model.api.casemanagement.CaseMaster;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class CaseMasterImpl implements CaseMaster{

	private int id;
	private String uuid;
	@LFIMSCacheEntry(cacheName="respondentName",valueField="id")
	private String respondentName;
	@LFIMSCacheEntry(cacheName="caseNo",valueField="id")
	private String caseNo;
	@LFIMSCacheEntry(cacheName="rpmaNo",valueField="id")
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
	public String getRespondentName() {
		return respondentName;
	}

	public int getId(){
		return id;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public String getRpmaNo() {
		return rpmaNo;
	}
	public String getBankName() {
		return bankName;
	}
	public String getBranchName() {
		return branchName;
	}
	public String getCourtName() {
		return courtName;
	}
	public Date getLimitDate() {
		return limitDate;
	}
	public String getCaseNature() {
		return caseNature;
	}
	public double getCaseAmount() {
		return caseAmount;
	}
	public Date getFilingDate() {
		return filingDate;
	}
	public Date getJudgementDate() {
		return judgementDate;
	}
	public Date getCertificateDate() {
		return certificateDate;
	}
	public Date getClosingDate() {
		return closingDate;
	}
	public String getOldCourtName() {
		return oldCourtName;
	}
	public String getOldCourtNumber() {
		return oldCourtNumber;
	}
	public String getCasePosition() {
		return casePosition;
	}
	public String getCaseForum() {
		return caseForum;
	}
	public String getOtherAdvocateName() {
		return otherAdvocateName;
	}
	public String getOtherAdvocatePhone() {
		return otherAdvocatePhone;
	}
	public boolean isSpecialOfficerAppointed() {
		return specialOfficerAppointed;
	}
	public Date getCaseTransferDate() {
		return caseTransferDate;
	}


	public CaseMasterImpl(int id,
					
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
							String otherAdcocatePhone,
							boolean specialOfficerAppointed,
							Date caseTransferDate)
	{
		this.id=id;
	
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
		this.otherAdvocatePhone=otherAdcocatePhone;
		this.specialOfficerAppointed=specialOfficerAppointed;
		this.caseTransferDate=caseTransferDate;

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

		return 0;
	}

	public String getUuid() {
	
		return uuid;
	}
	

	public void setUuid(String Uuid) {
		this.uuid=Uuid;
	}

	

}
