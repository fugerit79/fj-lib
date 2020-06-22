package org.fugerit.java.core.lang.binding;

import java.util.Date;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Convert any value found from the source binding to a string.
 * 
 * Null value is preserved, otherwise toString() i used.
 * 
 * @author Matteo a.k.a. Fugerit
 *
 */
public class BindingHelperDateToXMLOnly extends BindingHelperDefault {

	public static final String ID = "date-to-xml-only";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 229728343462037771L;
	
	public static final BindingHelper DEFAULT = new BindingHelperDateToXMLOnly();
	
	public BindingHelperDateToXMLOnly() {
		super( ID );
	}

	@Override
	public Object convertValue(BindingContext context, BindingConfig binding, BindingFieldConfig field, Object value) throws Exception {
		Object res = super.convertValue(context, binding, field, value);
		if ( res != null ) {
			Date d = (Date)res;
			java.sql.Date sd = new java.sql.Date( d.getTime() );
			res = DatatypeFactory.newInstance().newXMLGregorianCalendar( sd.toString() );
		}
		return res;
	}

	@Override
	public Class<?> getParamType() {
		return XMLGregorianCalendar.class;
	}

}
