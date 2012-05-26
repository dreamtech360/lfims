package com.dreamtech360.lfims.model.api.casemanagement;

import java.util.Date;
import com.dreamtech360.lfims.model.base.LFIMSCompositObject;

public interface CaseDiary extends  LFIMSCompositObject<CaseDiary> {
	
	
	
	public Date getHearingDate(); 
	public String getBeforeBenchName();
	public String getOurAdvocateName();
	public String getAdvocateName(); 
	public String getOurWitnessDetails(); 
	public String getOthersWitnessDetails();
	public String getOurDocuments();
	public String getOthersDocuments(); 
	public String getCaseCompletionMethod(); 
	public String getOtherDetails();
	public int getQuicky();
	public Date getNextHearingDate();
	public Date getNextDatePurpose() ;

	
	
}
