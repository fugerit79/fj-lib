package org.fugerit.java.core.fixed.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FixedFileFieldMap extends HashMap<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4410493087160204864L;

	private List<FixedFileFieldValidationResult> validationErrors;

	public List<FixedFileFieldValidationResult> getValidationErrors() {
		return validationErrors;
	}
	
	public FixedFileFieldMap( int recordLength ) {
		super();
		this.validationErrors = new ArrayList<FixedFileFieldValidationResult>();
		this.recordLength = recordLength;
	}
	
	private int recordLength;
	
}
