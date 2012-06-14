package com.dreamtech360.lfims.model.api.casemanagement;

import java.util.Date;

import com.dreamtech360.lfims.model.base.MutableLFIMSEntityObject;

public interface MutableCaseDiary extends CaseDiary, MutableLFIMSEntityObject<CaseDiary> {
	
	public void setDiaryPostingDate(Date diaryPostingDate); 
	public void setHearingDate(Date hearingDate) ;
	public void setBeforeBenchName(String beforeBenchName) ;
	public void setOurAdvocateName(String ourAdvocateName);
	public void setAdvocateName(String advocateName) ;
	public void setOurWitnessDetails(String ourWitnessDetails);
	public void setOthersWitnessDetails(String othersWitnessDetails);
	public void setOurDocuments(String ourDocuments);
	public void setOthersDocuments(String othersDocuments);
	public void setCaseCompletionMethod(String caseCompletionMethod) ;
	public void setOtherDetails(String otherDetails);
	public void setQuicky(int quicky) ;
	public void setNextHearingDate(Date nextHearingDate);
	public void setNextDatePurpose(Date nextDatePurpose);
	
	}
