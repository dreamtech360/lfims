package com.dreamtech360.lfims.annotations;

import java.lang.annotation.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/*This annotation is applicable for objects that needs to be cached 
 * It can be at a TYPE level that is for a class in such case the cache manager will cache the object itself
 * It can also be for a FIELD in such cased the cache manager will cache the value of the field 
 * 
 * 
 */

/*This annotation is applicable for model attributes that need to be viewed as picklist

1) For example BankMaster, the bank master has to appear as picklist on the case master input screen

2) CaseMaster.caseNo this attribute need to appear as picklist on the casemaster List screen for filtering

The service layer checks of a attribute has this annotation if it has then while DAOoperations like
createRecord, updateRecord etc it updated the cache using the cache service.

NOTE: the cache structure for a perticular attribute would be
a) cacheName ==> The attribute Name
b) CacheKey ==> The attribute VAlue
c) cacheValue ==> A List of model's key

The CLient layer/ REST API the PickList API will then query the cache service and 
return the picklist to client at runtime

*/

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})

public @interface LFIMSCacheEntry  {
	
	//The model's key field example BankMaster's key (i.e. id)
	 String valueField();
	 //This would be the attribute name example for caseNo attribute it would be caseNoList 
	 String cacheName();
	 
}
