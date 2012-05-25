package com.dreamtech360.lfims.model.api.casemanagement;

import java.util.Date;
import com.dreamtech360.lfims.model.base.MutableLFIMSEntityObject;

public interface MutableCaseMaster extends CaseMaster, MutableLFIMSEntityObject<CaseMaster> {
	

	public void setId(int id);
	public void setRespondentName(String respondentName);
	public void setCaseNo(String caseNo) ;
	public void setRpmaNo(String rpmaNo);
	public void setBankName(String bankName);
	public void setBranchName(String branchName) ;
	public void setCourtName(String courtName);
	public void setLimitDate(Date limitDate);
	public void setCaseNature(String caseNature);
	public void setCaseAmount(double caseAmount);
	public void setFilingDate(Date filingDate);
	public void setJudgementDate(Date judgementDate);
	public void setCertificateDate(Date certificateDate);
	public void setClosingDate(Date closingDate) ;
	public void setOldCourtName(String oldCourtName);
	public void setOldCourtNumber(String oldCourtNumber);
	public void setCasePosition(String casePosition);
	public void setCaseForum(String caseForum);
	public void setOtherAdvocateName(String otherAdvocateName) ;
	public void setOtherAdvocatePhone(String otherAdcocatePhone);
	public void setSpecialOfficerAppointed(boolean specialOfficerAppointed);
	public void setCaseTransferDate(Date caseTransferDate);
	
		
	}
