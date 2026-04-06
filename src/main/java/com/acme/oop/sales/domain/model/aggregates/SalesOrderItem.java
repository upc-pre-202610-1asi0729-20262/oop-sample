package com.acme.oop.sales.domain.model.aggregates;

import com.acme.oop.sales.domain.model.valueobjects.ProductId;
import com.acme.oop.shared.domain.model.valueobjects.Money;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a Sales Order Item, part of Sales Order aggregate in the 'Sales' bounded context.
 * @author Open Source Application Development Team
 */
@Getter
public class SalesOrderItem {
    private final ProductId productId;
    @Setter private int quantity;
    @Setter private Money unitPrice;

    /**
     * Creates a new Sales Order Item with the given product ID, quantity, and unit price.
     * @param productId the ID of the product being ordered
     * @param quantity  the quantity of the product being ordered. It must be greater than 0
     * @param unitPrice the price per unit of the product. It must be greater than 0 and have a valid currency code
     */
    public SalesOrderItem(@NonNull ProductId productId, int quantity, @NonNull Money unitPrice) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be greater than 0");
        if (unitPrice.amount().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Unit price must be greater than 0");
        if (Objects.isNull(unitPrice.currency()) || Objects.isNull(unitPrice.currency().getCurrencyCode()))
            throw new IllegalArgumentException("Currency code cannot be null or blank");
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    /**
     * Calculates the price of the item based on the quantity and unit price.
     * @return  the total price for this item (quantity * unit price)
     */
    public Money calculateItemPrice() {
        return unitPrice.multiply(quantity);
    }

}
