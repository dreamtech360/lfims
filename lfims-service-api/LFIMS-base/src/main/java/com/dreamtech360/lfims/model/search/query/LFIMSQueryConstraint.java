package com.dreamtech360.lfims.model.search.query;

import java.util.List;

import javax.jcr.query.qom.Constraint;

import com.dreamtech360.lfims.model.service.exception.LFIMSServiceException;

public interface LFIMSQueryConstraint<T> {

	List<Constraint> getConstraints() throws LFIMSServiceException;
}
