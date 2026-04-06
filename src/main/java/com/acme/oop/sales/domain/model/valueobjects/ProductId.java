package com.acme.oop.sales.domain.model.valueobjects;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a Product identifier value object for a product across the bounded contexts.
 * @param value the unique identifier value for the product
 * @author Open Source Application Development Team
 */
public record ProductId(UUID value) {
    /**
     * Creates a new ProductId with the given value.
     * @param value the unique identifier value for the product
     */
    public ProductId {
        if (Objects.isNull(value))
            throw new IllegalArgumentException("ProductId value cannot be null");
    }

    /**
     * Creates a new ProductId with a random UUID value.
     */
    public ProductId() {
        this(UUID.randomUUID());
    }
}
