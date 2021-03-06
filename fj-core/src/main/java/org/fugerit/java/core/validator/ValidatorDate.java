package org.fugerit.java.core.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;
import org.fugerit.java.core.lang.helpers.StringUtils;

public class ValidatorDate extends BasicValidator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5690219274192449044L;

	public static final String KEY_DATEFORMAT = "dateFormat";
	
	public static final String KEY_MINDATE = "minDate";
	
	public static final String KEY_MAXDATE = "maxDate";
	
	public static final String ERROR_KEY_DATE = "error.date";
	
	public static final String ERROR_KEY_DATE_MIN = "error.date.min";
	
	public static final String ERROR_KEY_DATE_MAX = "error.date.max";

	private String dateFormat;

	private String minDate;
	
	private String maxDate;
	
	public String getDateFormat() {
		return dateFormat;
	}

	public String getMinDate() {
		return minDate;
	}

	public String getMaxDate() {
		return maxDate;
	}

	protected Date setDate( SimpleDateFormat sdf, String d ) throws ParseException {
		Date res = null;
		if ( StringUtils.isNotEmpty( d ) ) {
			res = sdf.parse( d );
		}
		return res;
	}
	
	@Override
	public void checkConfig() throws ConfigException {
		super.checkConfig();
		if ( StringUtils.isEmpty( this.getDateFormat() ) ) {
			throw new ConfigException( "You must configure a 'dateFormat' attribute for this validator "+this.getId() );
		}
	}

	@Override
	public void configure( Properties atts ) throws ConfigException {
		super.configure(atts);
		try {
			String dateFormat = atts.getProperty( KEY_DATEFORMAT ); 
			if ( StringUtils.isNotEmpty( dateFormat ) ) {
				this.dateFormat = dateFormat;
			}
			String minDate = atts.getProperty( KEY_MINDATE );
			if ( StringUtils.isNotEmpty( minDate ) ) {
				this.minDate = minDate;
			}
			String maxDate = atts.getProperty( KEY_MAXDATE );
			if ( StringUtils.isNotEmpty( maxDate ) ) {
				this.maxDate = maxDate;
			}
		} catch (Exception e) {
			throw new ConfigException( e );
		}
	}

	@Override
	public boolean validate(ValidatorContext context) throws Exception {
		String minDate = this.checkOverride( context, this.getMinDate(), KEY_MINDATE );
		String maxDate = this.checkOverride( context, this.getMaxDate(), KEY_MAXDATE );
		return super.validate( context ) && this.validate( context, minDate, maxDate );
	}
	
	protected boolean validate( ValidatorContext context, String minDate, String maxDate ) throws Exception {
		boolean valid = true;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat( this.getDateFormat() );
			Date d = sdf.parse( context.getValue() );
			if ( StringUtils.isNotEmpty( minDate ) && d.before( sdf.parse( minDate ) ) ) {
				valid = false;
				String message = this.formatMessage( context.getBundle() , ERROR_KEY_DATE_MIN, context.getLabel(), context.getValue(), minDate );
				context.getResult().addError( context.getFieldId(), message );
			}
			if ( StringUtils.isNotEmpty( maxDate ) && d.after( sdf.parse( maxDate ) ) ) {
				valid = false;
				String message = this.formatMessage( context.getBundle() , ERROR_KEY_DATE_MAX, context.getLabel(), context.getValue(), maxDate );
				context.getResult().addError( context.getFieldId(), message );
			}
		} catch (Exception e) {
			valid = false;
			String message = this.formatMessage( context.getBundle() , ERROR_KEY_DATE, context.getLabel(), context.getValue(), StringUtils.valueWithDefault( this.getInfo(), this.getDateFormat() ) );
			context.getResult().addError( context.getFieldId(), message );
		}
		return valid;
	}

}
