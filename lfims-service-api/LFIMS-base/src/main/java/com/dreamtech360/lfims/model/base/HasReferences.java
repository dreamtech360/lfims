package com.dreamtech360.lfims.model.base;

import java.util.Set;

//If the Model is referenced by ant other model then the model should implement this interface
//for example BranchMaster is kept as a reference in BankMaster 
public interface HasReferences {

	Set<Referenceable> getAllReferences();
	Referenceable getReference(String name);
	String getReferenceKey(Referenceable name);
	void setReferenceKey(Referenceable name,String reference);
}
