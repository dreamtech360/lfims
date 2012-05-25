package com.dreamtech360.lfims.model.base;

//If the model keeps references of other model then the model should implement this interface
//for example BankMaster keeps references to BrancMaster
public interface Referenceable {
	
	public LFIMSObjectReference getReferenceMetaDetails();
	
}
