package com.github.myon.fsmlib.immutable;

import com.github.myon.fsmlib.container.Sequence;

/**
 *
 * @author 0xMyon
 *
 * a language is a set of sequences
 * @param <O> underling type
 * @param <T> sub type
 */
public interface ClosedLanguage<O, B, T extends ClosedLanguage<O,B,T>> extends ClosedSymetricSet<Sequence<O>, B, T>, ClosedSequence<O, B, T> {

	/**
	 * creates an iterated language
	 * @return
	 */
	public T iteration();

	/**
	 * creates an optional language
	 */
	public T option();


	@Override
	public Factory<O, B, T> factory();

	@Override
	public default <R extends ClosedSymetricSet<Sequence<O>, B, R>> R convert(final ClosedSymetricSet.Factory<Sequence<O>, B, R> factory) {
		throw new Error();
	}

	@Override
	public default <R extends ClosedSequence<O, B, R>> R convert(final ClosedSequence.Factory<O,B, R> factory) {
		throw new Error();
	}

	public <R extends ClosedLanguage<O, B, R>> R convert(final Factory<O,B, R> factory);


	@SuppressWarnings("unchecked")
	public default boolean contains(final O... objects) {
		return this.contains(new Sequence<O>(objects));
	}

	public static interface Factory<O, B, T extends ClosedLanguage<O, B, T>> extends ClosedSequence.Factory<O, B, T>, ClosedSymetricSet.Factory<Sequence<O>, B, T> {

	}


}
