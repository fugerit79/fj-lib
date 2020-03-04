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
package org.fugerit.java.core.db.dao;

import java.sql.Connection;

import org.fugerit.java.core.db.connect.ConnectionFactory;

public interface DAOFactory {

	Connection getConnection() throws DAOException;

	ConnectionFactory getConnectionFactory();

	FieldFactory getFieldFactory();

	Object[] getSqlArgs();

	DAOUtils getDaoUtils();

}