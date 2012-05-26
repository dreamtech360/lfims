package com.dreamtech360.lfims.model.api.casemanagement;

import java.util.Date;

import com.dreamtech360.lfims.model.base.LFIMSCompositObject;

public interface CaseMaster extends LFIMSCompositObject<CaseMaster>  {
	
	
	public String getRespondentName();
	public String getCaseNo(); 
	public String getRpmaNo();
	public String getBankName();
	public String getBranchName();
	public String getCourtName();
	public Date getLimitDate();
	public String getCaseNature(); 
	public double getCaseAmount();
	public Date getFilingDate() ;
	public Date getJudgementDate(); 
	public Date getCertificateDate();
	public Date getClosingDate();
	public String getOldCourtName(); 
	public String getOldCourtNumber();
	public String getCasePosition(); 
	public String getCaseForum();
	public String getOtherAdvocateName();
	public String getOtherAdvocatePhone();
	public boolean isSpecialOfficerAppointed();
	public Date getCaseTransferDate(); 
	
	
}
