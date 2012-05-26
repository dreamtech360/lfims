package com.dreamtech360.lfims.model.base;

public abstract class LFIMSNode<T> {

	public abstract String getNodeName();
	public abstract String getNodeType();
	//public abstract int getDepth();
	public boolean hasNodeKey(){
		if (getNodeKey()==null)
			return false;
		return true;
	}
	public abstract LFIMSNodeKey<T> getNodeKey();
	
	
}
