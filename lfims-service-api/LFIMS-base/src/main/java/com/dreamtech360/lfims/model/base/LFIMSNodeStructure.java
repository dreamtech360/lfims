package com.dreamtech360.lfims.model.base;

import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;
import com.dreamtech360.lfims.util.LFIMSUtils;

public abstract class LFIMSNodeStructure<T> {
	
	public abstract LFIMSNode<T> getNodeType();
	public abstract LFIMSNodeStructure<T> getTopNodeStructure();
	//If the Node is part of a Composite structure and is not a collection then this method returns the key to store the reference value
	//else his method should return null
	public String getCompositKeyIdentifier() throws LFIMSServiceException{
			
		
		if( !isNodeTypeCollection() && LFIMSUtils.isCompositNode(getClass())){
			throw new LFIMSServiceException("The property to store the reference key for a composit node is not set please set the value before proceeding");
		}
		return null;
			
	}
	//If this value is false then the implementing class must override the method getCompositKeyIdentifier and return the attribute name to store the composit key
	//reference if the node is of type composit
	public abstract boolean isNodeTypeCollection();
	

}
