package com.dreamtech360.lfims.model.search.query;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.jcr.Binary;
import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.ValueFormatException;

public class LFIMSStringValue implements Value {

	private String content=null;
	
	public LFIMSStringValue(String content){
		super();
		this.content=content;
	}
	public Binary getBinary() throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean getBoolean() throws ValueFormatException,
			RepositoryException {
		// TODO Auto-generated method stub
		return false;
	}

	public Calendar getDate() throws ValueFormatException, RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	public BigDecimal getDecimal() throws ValueFormatException,
			RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	public double getDouble() throws ValueFormatException, RepositoryException {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getLong() throws ValueFormatException, RepositoryException {
		// TODO Auto-generated method stub
		return 0;
	}

	public InputStream getStream() throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getString() throws ValueFormatException,
			IllegalStateException, RepositoryException {
		// TODO Auto-generated method stub
		return content;
	}

	public int getType() {
		// TODO Auto-generated method stub
		return PropertyType.STRING;
	}

}