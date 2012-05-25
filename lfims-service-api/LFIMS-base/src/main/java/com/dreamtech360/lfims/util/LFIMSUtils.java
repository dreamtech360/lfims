package com.dreamtech360.lfims.util;

import java.lang.reflect.ParameterizedType;

import com.dreamtech360.lfims.model.base.LFIMSCompositObject;

public class LFIMSUtils {

	public static boolean isCompositNode(Class<?> nodeClass){
		boolean isCompositNode=false;
		Class<?> node= (Class<?>) ((ParameterizedType) nodeClass.getGenericSuperclass()).getActualTypeArguments()[0];
		Class<?> classes[]=node.getInterfaces();
		for(int i=0;i<classes.length;i++){
			if(LFIMSCompositObject.class.equals(classes[i])){
				isCompositNode=true; 
				break;
			}
		}
		return isCompositNode;
	}
}
