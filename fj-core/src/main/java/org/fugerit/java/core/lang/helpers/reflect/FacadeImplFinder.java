package org.fugerit.java.core.lang.helpers.reflect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.fugerit.java.core.lang.annotate.DefineImplFinder;

public class FacadeImplFinder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2952618477529786064L;
	
	private List<ImplFinder> finderList;
	
	public static FacadeImplFinder newFacadeDefault() {
		return new FacadeImplFinder( DefineImplFinder.DEFAULT );
	}
	
	public FacadeImplFinder() {
		this.finderList = new ArrayList<ImplFinder>();
	}
	
	public FacadeImplFinder( ImplFinder defaultFinder ) {
		this();
		this.registerFinder( defaultFinder );
	}

	public Iterator<ImplFinder> finders() {
		return this.finderList.iterator();
	}
	
	public void registerFinder( ImplFinder finder ) {
		this.finderList.add( finder );
	}
	
	public void removeFinder( ImplFinder finder ) {
		this.finderList.remove( finder );
	}
	
	public ImplFinder getFinderFor( Class<?> c ) throws Exception {
		ImplFinder finder = null;
		Iterator<ImplFinder> it = this.finders();
		while ( finder == null && it.hasNext() ) {
			ImplFinder current = it.next();
			if ( current.isFinderFor( c ) ) {
				finder = current;
			}
		}
		return finder;
	}
	
	public boolean hasFinderFor( Class<?> c ) throws Exception {
		return (this.getFinderFor( c ) != null);
	}
	
	public Class<?> findImpl( Class<?> c ) throws Exception {
		Class<?> ret = c;
		ImplFinder finder = this.getFinderFor( c );
		if ( finder != null ) {
			ret = finder.findImplFor( c );
		}
		return ret;
	}
	
}
