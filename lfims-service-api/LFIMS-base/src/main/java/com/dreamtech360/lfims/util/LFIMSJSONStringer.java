package com.dreamtech360.lfims.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.dreamtech360.lfims.model.base.LFIMSObject;
import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;

public class LFIMSJSONStringer<T> {

	private Map<String,String> extraAttributes=null; 
	private String identifier=null;
	private Collection<LFIMSObject<T>> collection=null;
	

	public LFIMSJSONStringer(LFIMSObject<T> object,Map<String,String> extraAttributes,String identifier){
		this.collection=new ArrayList<LFIMSObject<T>>();
		this.collection.add(object);
		this.extraAttributes=extraAttributes;
		this.identifier=(identifier==null?"":identifier);
	}

	public LFIMSJSONStringer(Collection<LFIMSObject<T>> collection,Map<String,String> extraAttributes,String identifier){
		this.collection=collection;
		this.extraAttributes=extraAttributes;
		this.identifier=(identifier==null?"":identifier);
	}

	public JSONObject getJSON() throws LFIMSServiceException{
		JSONArray array=new JSONArray();
		JSONObject object=new JSONObject();
		try{
			if(extraAttributes!=null){
				Set<String> keySet=extraAttributes.keySet();
				Iterator<String> keyIterator = keySet.iterator();
				while(keyIterator.hasNext()){
					String key=keyIterator.next();
					String value=extraAttributes.get(key);
					object.put(key, value);
				}
			}
			Iterator<LFIMSObject<T>> collectionIterator=collection.iterator();
			while(collectionIterator.hasNext()){
				LFIMSObject<T> item=collectionIterator.next();
				array.put(item.getJSON());
			}
			
			object.put(identifier, array);

		}catch(Exception e){
			throw new LFIMSServiceException(e);
		}
		return object;
	}

	public String getJSONString() throws LFIMSServiceException{ 

		
		return  getJSON().toString();

	}
}
