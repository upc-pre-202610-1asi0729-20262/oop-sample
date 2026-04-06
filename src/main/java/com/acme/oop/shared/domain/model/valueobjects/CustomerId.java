package com.acme.oop.shared.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a Customer identifier for a customer across the bounded contexts.
 *
 * @param value the unique identifier value for the customer, it must not be null.
 *
 * @author Open Source Application Development Team
 */
public record CustomerId(UUID value) {
    /**
     * Creates a new CustomerId with the given value.
     * @param value the unique identifier value for the customer, it must not be null.
     */
    public CustomerId {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("CustomerId value cannot be null");
        }
    }

    /**
     * Creates a new CustomerId with a random UUID value.
     */
    public CustomerId() {
        this(UUID.randomUUID());
    }

    /**
     * Returns the string representation of the CustomerId.
     * @return  the string representation of the CustomerId, which is the string representation of the UUID value.
     */
    @Override
    public String toString() {
        return value.toString();
    }
}
