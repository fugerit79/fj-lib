/*
 *
		Fugerit Java Library is distributed under the terms of :

                                 Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/


	Full license :
		http://www.apache.org/licenses/LICENSE-2.0
		
	Project site: 
		https://www.fugerit.org/
	
	SCM site :
		https://github.com/fugerit79/fj-lib
	
 *
 */
package org.fugerit.java.core.db.dao.rse;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.fugerit.java.core.db.dao.RSExtractor;

/**
 * Convert a ResultSet record to a Property object.
 * 
 * Property data are column name / column value set.
 *
 * @author Fugerit
 *
 */
public abstract class PropertyRSE implements RSExtractor<Properties> {
	
	/**
	 * Creates a new reusable PropertyRSE
	 * 
	 * @return	the RSExtractor
	 */
	public static PropertyRSE newReusableRSE() {
		return new PropertyRSE() {
			@Override
			public Properties extractNext(ResultSet rs) throws SQLException {
				Properties props = new Properties();
				ResultSetMetaData rsmd = rs.getMetaData();
				int cols = rsmd.getColumnCount();
				for ( int k=1; k<=cols; k++) {
					String name = rsmd.getColumnName( k );
					String value = rs.getString( name );
					if ( value != null ) {
						props.setProperty( name, value );	
					}
				}
				return props;
			}
		};
	}
	
	/**
	 * Creates a new PropertyRSE non-reusable (columns name are cached)
	 * 
	 * @param configRS		the result set used for configuration
	 * @return				the RSExctractor
	 * @throws SQLException	in case of issues
	 */
	public static PropertyRSE newNoReusableRSE( final ResultSet configRS ) throws SQLException {
		return new PropertyRSECached( configRS );
	}

}

class PropertyRSECached extends PropertyRSE {
	
	private List<String> columnNames;

	public PropertyRSECached( ResultSet configRS ) throws SQLException {
		super();
		this.columnNames = new ArrayList<String>();
		ResultSetMetaData rsmd = configRS.getMetaData();
		int cols = rsmd.getColumnCount();
		for ( int k=1; k<=cols; k++) {
			this.columnNames.add( rsmd.getColumnName( k ) );
		}
	}

	@Override
	public Properties extractNext(ResultSet rs) throws SQLException {
		Properties props = new Properties();
		for ( int k=1; k<=this.columnNames.size(); k++) {
			String name = this.columnNames.get( k );
			String value = rs.getString( name );
			if ( value != null ) {
				props.setProperty( name, value );	
			}
		}
		return props;
	}
	
	
	
	
}