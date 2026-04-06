package com.acme.oop.shared.domain.model.valueobjects;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

/**
 * Represents a monetary value.
 * @param amount    the amount of money, it must not be null or negative.
 * @param currency  the currency of the money, it must not be null and must have a valid ISO 4217 code.
 */
public record Money(BigDecimal amount, Currency currency) {
    /**
     * Creates a new Money object.
     * @param amount    the amount of money, it must not be null or negative.
     * @param currency  the currency of the money, it must not be null and must have a valid ISO 4217 code.
     */
    public Money {
        if(Objects.isNull(amount) || amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Amount cannot be null or negative");
        if(Objects.isNull(currency))
            throw new IllegalArgumentException("Currency cannot be null");
        if(amount.scale() > currency.getDefaultFractionDigits())
            throw new IllegalArgumentException("Amount cannot have more than " + currency.getDefaultFractionDigits() + " decimal places");
    }

    /**
     * Returns a Money object representing zero dollars in the default currency (USD).
     * @return  a Money object representing zero dollars in the default currency (USD).
     */
    public static Money zero() {
        return new Money(BigDecimal.ZERO, Currency.getInstance("USD"));
    }

    /**
     * Adds two Money objects together.
     * @param other the other Money object to add, it must not be null and must have the same currency as this Money object.
     * @return  a new Money object representing the sum of this Money object and the other Money object.
     */
    public Money add(Money other) {
        if(!this.currency.equals(other.currency))
            throw new IllegalArgumentException("Cannot add money with different currencies");
        return new Money(this.amount.add(other.amount), this.currency);
    }

    /**
     * Multiplies the amount of money by a given multiplier.
     * @param multiplier    the multiplier to multiply the amount of money by, it must not be negative.
     * @return  a new Money object representing the result of multiplying the amount of money by the given multiplier.
     */
    public Money multiply(int multiplier) {
        return new Money(amount.multiply(BigDecimal.valueOf(multiplier)), currency);
    }
}
