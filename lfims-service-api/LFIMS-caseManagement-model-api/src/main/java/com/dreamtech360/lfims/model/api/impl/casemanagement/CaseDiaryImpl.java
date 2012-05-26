package com.dreamtech360.lfims.model.api.impl.casemanagement;

import java.util.Date;
import org.codehaus.jettison.json.JSONObject;
import com.dreamtech360.lfims.model.api.casemanagement.CaseDiary;
import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public class CaseDiaryImpl implements CaseDiary{
	
	private int id;
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

	public CaseDiaryImpl(
			int id,
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
	
	public int getId() {
		return id;
	}
	public Date getHearingDate() {
		return hearingDate;
	}
	public String getBeforeBenchName() {
		return beforeBenchName;
	}
	public String getOurAdvocateName() {
		return ourAdvocateName;
	}
	public String getAdvocateName() {
		return advocateName;
	}
	public String getOurWitnessDetails() {
		return ourWitnessDetails;
	}
	public String getOthersWitnessDetails() {
		return othersWitnessDetails;
	}
	public String getOurDocuments() {
		return ourDocuments;
	}
	public String getOthersDocuments() {
		return othersDocuments;
	}
	public String getCaseCompletionMethod() {
		return caseCompletionMethod;
	}
	public String getOtherDetails() {
		return otherDetails;
	}
	public int getQuicky() {
		return quicky;
	}
	public Date getNextHearingDate() {
		return nextHearingDate;
	}
	public Date getNextDatePurpose() {
		return nextDatePurpose;
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

	public void setUuid(String Uuid) {
		this.uuid=Uuid;
	}

	

	
	
	
}
