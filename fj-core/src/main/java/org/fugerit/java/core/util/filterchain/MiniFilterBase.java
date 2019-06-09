package org.fugerit.java.core.util.filterchain;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MiniFilterBase implements MiniFilter {

	public static String genKey() {
		return UUID.randomUUID().toString();
	}
	
	protected static Logger logger = LoggerFactory.getLogger( MiniFilter.class );

	public MiniFilterBase( String key, String description, int defaultBehaviour ) {
		this.key = key;
		this.description = description;
		this.defaultBehaviour = defaultBehaviour;
	}
	
	public MiniFilterBase( String key, int defaultBehaviour ) {
		this( key, key, defaultBehaviour );
	}
	
	public MiniFilterBase( int defaultBehaviour ) {
		this( genKey(), defaultBehaviour );
	}
	
	public MiniFilterBase() {
		this.defaultBehaviour = MiniFilter.CONTINUE;
	}
	
	private String description;
	
	private String key;
	
	private int defaultBehaviour;
	
	@Override
	public int getDefaultBehaviour() {
		return this.defaultBehaviour;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public String toString() {
		return this.getClass().getSimpleName()+"["+this.getKey()+",default-behaviour:"+this.getDefaultBehaviour()+"]";
	}
	
	@Override
	public void config(String key, String description, Integer defaultBehaviour) {
		if ( key == null ) {
			key = genKey();
		}
		if ( description == null ) {
			description = key;
		}
		if ( defaultBehaviour == null ) {
			defaultBehaviour = CONTINUE; 
		}
		this.key = key;
		this.description = description;
		this.defaultBehaviour = defaultBehaviour.intValue();
	}

	@Override
	public abstract int apply(MiniFilterContext context, MiniFilterData data) throws Exception;
	
}