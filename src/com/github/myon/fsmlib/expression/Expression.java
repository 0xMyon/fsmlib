package com.github.myon.fsmlib.expression;

import com.github.myon.fsmlib.container.Sequence;
import com.github.myon.fsmlib.immutable.ClosedLanguage;

/**
 * @author 0xMyon
 *
 * immutable based on underlying language
 * @param <O> based object type
 * @param <T> underlying type
 */
public abstract class Expression<O, T extends ClosedLanguage<O, O, T, ?>> implements ClosedLanguage<O, O, Expression<O,T>, Expression.Factory<O,T>> {

	private final ClosedLanguage.Factory<O, O, T, ?> factory;

	public Expression(final ClosedLanguage.Factory<O, O, T, ?> factory) {
		this.factory = factory;
	}

	public T evaluate() {
		return this.convert(this.factory);
	}

	@Override
	public Expression<O,T> complement() {
		return ComplementExpression.create(this.factory, this);
	}

	@Override
	public Expression<O,T> union(final Expression<O,T> that) {
		return UnionExpression.create(this.factory, this, that);
	}

	@Override
	public Expression<O,T> concat(final Expression<O,T> that) {
		return SequenceExpression.create(this.factory, this,that);
	}

	@Override
	public Expression<O,T> iteration() {
		return IterationExpression.create(this.factory, this);
	}

	@Override
	public boolean isEmpty() {
		return this.evaluate().isEmpty();
	}

	@Override
	public boolean isFinite() {
		return this.evaluate().isFinite();
	}

	@Override
	public boolean containsAll(final Expression<O,T> that) {
		return this.evaluate().containsAll(that.evaluate());
	}

	@Override
	public boolean contains(final Sequence<O> object) {
		return this.evaluate().contains(object);
	}

	@Override
	public boolean isEpsilon() {
		return this.evaluate().isEpsilon();
	}



	@Override
	public Expression<O,T> option() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Factory<O, T> factory() {
		return new Factory<>();
	}

	public static final class Factory<O,T extends ClosedLanguage<O, O, T, ?>> implements
	ClosedLanguage.Factory<O, O, Expression<O,T>, Factory<O,T>> {


		@Override
		@SuppressWarnings("unchecked")
		public Expression<O, T> intersection(final O... objects) {
			// TODO Auto-generated method stub
			return null;
		}

		// TODO replace null

		@Override
		public Expression<O, T> element(final O object) {
			return new ElementaryExpression<O, T>(null, object);
		}
		@Override
		public Expression<O, T> epsilon() {
			return new SequenceExpression<>(null);
		}
		@Override
		public Expression<O, T> empty() {
			return new UnionExpression<>(null);
		}

	}



}
