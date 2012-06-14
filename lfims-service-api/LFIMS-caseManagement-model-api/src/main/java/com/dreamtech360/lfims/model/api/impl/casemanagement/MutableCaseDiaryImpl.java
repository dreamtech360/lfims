package com.dreamtech360.lfims.model.api.impl.casemanagement;

import java.util.Date;

import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.api.casemanagement.CaseDiary;
import com.dreamtech360.lfims.model.api.casemanagement.MutableCaseDiary;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class MutableCaseDiaryImpl implements MutableCaseDiary{

	private int id;
	private Date diaryPostingDate;
	private String uuid;
	private Date hearingDate;
	private String beforeBenchName;
	private String ourAdvocateName;
	private String advocateName;
	private String ourWitnessDetails;
	private String othersWitnessDetails;
	private String ourDocuments;
	private String othersDocuments;
	private String caseCompletionMethod;
	private String otherDetails;
	private int quicky;
	private Date nextHearingDate;
	private Date nextDatePurpose;

	public MutableCaseDiaryImpl(
			int id,
			//String uuid,
			Date diaryPostingDate,
			Date hearingDate,
			String beforeBenchName,
			String ourAdvocateName,
			String advocateName,
			String ourWitnessDetails,
			String othersWitnessDetails,
			String ourDocuments,
			String othersDocuments,
			String caseCompletionMethod,
			String otherDetails,
			int quicky,
			Date nextHearingDate,
			Date nextDatePurpose
			){
		this.id=id;
		//this.uuid=uuid;
		this.diaryPostingDate=diaryPostingDate;
		this.hearingDate=hearingDate;
		this.beforeBenchName=beforeBenchName;
		this.ourAdvocateName=ourAdvocateName;
		this.advocateName=advocateName;
		this.ourWitnessDetails=ourWitnessDetails;
		this.othersWitnessDetails=othersWitnessDetails;
		this.ourDocuments=ourDocuments;
		this.othersDocuments=othersDocuments;
		this.caseCompletionMethod=caseCompletionMethod;
		this.otherDetails=otherDetails;
		this.quicky=quicky;
		this.nextHearingDate=nextHearingDate;
		this.nextDatePurpose=nextDatePurpose;
	}

	
	public MutableCaseDiaryImpl(){}

	


public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

public Date getDiaryPostingDate(){
	return diaryPostingDate;
}
public void setDiaryPostingDate(Date diaryPostingDate){
	this.diaryPostingDate=diaryPostingDate;
}
	public Date getHearingDate() {
		return hearingDate;
	}


	public void setHearingDate(Date hearingDate) {
		this.hearingDate = hearingDate;
	}


	public String getBeforeBenchName() {
		return beforeBenchName;
	}


	public void setBeforeBenchName(String beforeBenchName) {
		this.beforeBenchName = beforeBenchName;
	}


	public String getOurAdvocateName() {
		return ourAdvocateName;
	}


	public void setOurAdvocateName(String ourAdvocateName) {
		this.ourAdvocateName = ourAdvocateName;
	}


	public String getAdvocateName() {
		return advocateName;
	}


	public void setAdvocateName(String advocateName) {
		this.advocateName = advocateName;
	}


	public String getOurWitnessDetails() {
		return ourWitnessDetails;
	}


	public void setOurWitnessDetails(String ourWitnessDetails) {
		this.ourWitnessDetails = ourWitnessDetails;
	}


	public String getOthersWitnessDetails() {
		return othersWitnessDetails;
	}


	public void setOthersWitnessDetails(String othersWitnessDetails) {
		this.othersWitnessDetails = othersWitnessDetails;
	}


	public String getOurDocuments() {
		return ourDocuments;
	}


	public void setOurDocuments(String ourDocuments) {
		this.ourDocuments = ourDocuments;
	}


	public String getOthersDocuments() {
		return othersDocuments;
	}


	public void setOthersDocuments(String othersDocuments) {
		this.othersDocuments = othersDocuments;
	}


	public String getCaseCompletionMethod() {
		return caseCompletionMethod;
	}


	public void setCaseCompletionMethod(String caseCompletionMethod) {
		this.caseCompletionMethod = caseCompletionMethod;
	}


	public String getOtherDetails() {
		return otherDetails;
	}


	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}


	public int getQuicky() {
		return quicky;
	}


	public void setQuicky(int quicky) {
		this.quicky= quicky;
	}


	public Date getNextHearingDate() {
		return nextHearingDate;
	}


	public void setNextHearingDate(Date nextHearingDate) {
		this.nextHearingDate = nextHearingDate;
	}


	public Date getNextDatePurpose() {
		return nextDatePurpose;
	}


	public void setNextDatePurpose(Date nextDatePurpose) {
		this.nextDatePurpose = nextDatePurpose;
	}


public String getJSONString() throws LFIMSModelException {
		
		return getJSON().toString();
	}

public JSONObject getJSON() throws LFIMSModelException {
	JSONObject object= new JSONObject();
	try {
		object.put("id", this.id);
		object.put("uuid", this.uuid);
		object.put("hearingDate",this.hearingDate);
		object.put("beforeBenchName",this.beforeBenchName);
		object.put("ourAdvocateName",this.ourAdvocateName);
		object.put("advocateName",this.advocateName);
		object.put("ourWitnessDetails",this.ourWitnessDetails);
		object.put("othersWitnessDetails",this.othersWitnessDetails);
		object.put("ourDocuments",this.ourDocuments);
		object.put("othersDocuments",this.othersDocuments);
		object.put("caseCompletionMethod",this.caseCompletionMethod);
		object.put("otherDetails",this.otherDetails);
		object.put("quicky",this.quicky);
		object.put("nextHearingDate",this.nextHearingDate);
		object.put("nextDatePurpose",this.nextDatePurpose);
	
	} catch (Exception e) {
		throw new LFIMSModelException(e);
	}
	return object;
}

public int compareTo(CaseDiary o) {
	return 0;
}


public String getUuid() {

	return uuid;
}


public void setUuid(String uuid) {
this.uuid=uuid;
	
}


}
