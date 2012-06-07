package com.dreamtech360.lfims.model.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.service.exception.LFIMSModelException;

public abstract class  SearchParams<T> {
	
protected abstract LFIMSAttributeMapper<T> getSearchCriteriaKey(String attributeName);

public  Map<LFIMSAttributeMapper<T>,List<String>> parseFilterParameters(String jsonString) throws LFIMSModelException {
	
	JSONArray array=null;
	Map<LFIMSAttributeMapper<T>,List<String>> parameters=new HashMap<LFIMSAttributeMapper<T>,List<String>>();
	try{
		array=new JSONArray(jsonString);
		for(int i=0;i<array.length();i++){
			JSONObject object=array.getJSONObject(i);
			String fieldName=object.getString("property");
			String fieldValue=object.getString("value");
			ArrayList<String> value=new ArrayList<String>();
			value.add(fieldValue);
			parameters.put(getSearchCriteriaKey(fieldName),value);
		}
	}catch(Exception e){
		throw new LFIMSModelException(e);
	}
	return parameters;
}

public  Map<LFIMSAttributeMapper<T>,List<String>> parseSortParameters(String jsonString) throws LFIMSModelException {
	
	return parseFilterParameters(jsonString);
}

	
}
