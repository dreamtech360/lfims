package com.dreamtech360.lfims.model.search.query;

import javax.jcr.query.qom.Column;
import javax.jcr.query.qom.Ordering;
import javax.jcr.query.qom.Selector;

public interface LFIMSQueryParameters<T> {

	Selector getSelector();
	LFIMSQueryConstraint<T> getConstraint();
	Column[] getColumns();
	Ordering[] getOrdering();
	
}
