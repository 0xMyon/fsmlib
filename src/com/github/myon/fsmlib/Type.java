package com.github.myon.fsmlib;

/**
 * @author 0xMyon
 *
 * basic type interface
 * @param <O> super-type
 */
public interface Type<O> {

	/**
	 * checks, if an object is contained
	 * @param object the checked objects
	 * @return true, if objects is contained
	 */
	public boolean contains(O object);

}
