package com.dreamtech360.lfims.model.base;

//This interface has to be implemented for nodes that are in hierarchy 
//where all nodes need to share the key like this interface is extreamly useful 
//in the initRootNode() method to set the proper root node 

public interface LFIMSCompositObject<T> extends LFIMSEntityObject<T>  {

	public String getUuid(); 
	public void setUuid(String Uuid);

		
}
