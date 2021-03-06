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
package org.fugerit.java.core.cfg.helpers;

import java.util.Properties;

import org.fugerit.java.core.cfg.ConfigException;

/**
 * <p>Base class for implementations of ConfigurableObject supporting only
 * the <code>configure(Element)</code> method.
 * The <code>configure(Properties)</code> method will throw an exception.</p>
 *
 * @author Fugerit
 *
 */
public abstract class XMLConfigurableObject extends AbstractConfigurableObject {

	/*
	 * 
	 */
	private static final long serialVersionUID = -3032211194907648543L;

	/* (non-Javadoc)
	 * @see org.fugerit.java.core.cfg.ConfigurableObject#configure(java.util.Properties)
	 */
	public void configure(Properties props) throws ConfigException {
		throw ( new ConfigException( "Properties configuration not supported" ) );
	}

}
