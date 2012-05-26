package com.dreamtech360.lfims.model.api.bankmaster;

import com.dreamtech360.lfims.model.base.LFIMSEntityObject;
import com.dreamtech360.lfims.model.base.LFIMSObjectReference;
import com.dreamtech360.lfims.model.base.Referenceable;

public interface BankMaster extends LFIMSEntityObject<BankMaster>  {
	
	
	String getName();
	String getFullName();
	String getHeadOffice();
	String getContactPerson();
	String getContactPhone();
	
	//This inner class solves two purposes
	//1) It keeps the data that is needed by the node that refers it
	//2) It keeps the REFERENCIABLE attribute details on the node that will store the node details that refers it
		public static class BankReference implements Referenceable{
			
			//The data used by the node that has a reference to this node 
			//like bank name which gets stored in the branch node 
			private String payLoad; 
			
			public BankReference(String payLoad){
				this.payLoad=payLoad;
			}

				public LFIMSObjectReference getReferenceMetaDetails() {
				
				return new LFIMSObjectReference() {
					
					//This is the property in the Bank master node that would store the branch master reference
					public String getRefPropertyName() {
						// TODO Auto-generated method stub
						return "lfims:branches";
					}
					//The node type of the reference in this case its branch master
					public String getRefNodeType() {
						
						return "lfims:branchDetails";
					}
					//This is used to look up the parent note from the clild	
					public String getRefNodeKey() {
						
						return BankMaster.class.getName();
					}

					public String getPayload() {
						// TODO Auto-generated method stub
						return payLoad;
					}
				};
			}

				
			
		}
		
		
}
