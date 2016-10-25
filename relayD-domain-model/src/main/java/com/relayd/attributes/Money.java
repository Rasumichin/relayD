package com.relayd.attributes;

import java.math.BigInteger;
import java.util.Currency;
import java.util.Locale;

/**
 * So stellt sich der Meister eine Quantity Klasse vor. In diesem Fall Money!
 *
 * @author Martin Fowler HIMSELF!
 */
public class Money implements Comparable<Money> {
	private BigInteger amount;
	private Currency currency;

	public Money(double anAmount, Currency aCurrency) {
		amount = BigInteger.valueOf(Math.round(anAmount * 100));
		currency = aCurrency;
	}

	public Money(long anAmount, Currency aCurrency) {
		amount = BigInteger.valueOf(anAmount * 100);
		currency = aCurrency;
	}

	public static Money dollars(double amount) {
		return new Money(amount, Currency.getInstance(Locale.GERMANY));
	}

	public Money add(Money arg) {
		assertSameCurrencyAs(arg);
		return new Money(amount.add(arg.amount), currency, true);
	}

	public Money subtract(Money arg) {
		return add(arg.negate());
	}

	void assertSameCurrencyAs(Money arg) {
		if (!currency.equals(arg.currency)) {
			throw new IllegalArgumentException("money math mismatch");
		}
	}

	private Money(BigInteger amountInPennies, Currency aCurrency, boolean privacyMarker) {
		if (amountInPennies == null) {
			throw new IllegalArgumentException("amountInPennies should not be null!");
		}
		if (currency == null) {
			throw new IllegalArgumentException("aCurrency should not be null!");
		}
		amount = amountInPennies;
		currency = aCurrency;
	}

	public Money negate() {
		return new Money(amount.negate(), currency, true);
	}

	public double amount() {
		return amount.doubleValue() / 100;
	}

	public Currency currency() {
		return currency;
	}

	public boolean greaterThan(Money arg) {
		return (compareTo(arg) == 1);
	}

	public boolean lessThan(Money arg) {
		return (compareTo(arg) == -1);
	}

	@Override
	public int compareTo(Money moneyArg) {
		assertSameCurrencyAs(moneyArg);
		return amount.compareTo(moneyArg.amount);
	}

	@Override
	public boolean equals(Object arg) {
		if (!(arg instanceof Money)) {
			return false;
		}
		Money other = (Money) arg;
		return (currency.equals(other.currency) && (amount.equals(other.amount)));
	}

	@Override
	public int hashCode() {
		return amount.hashCode();
	}
}