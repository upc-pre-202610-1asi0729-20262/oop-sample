package com.acme.oop.sales.domain.model.aggregates;

import com.acme.oop.sales.domain.model.valueobjects.ProductId;
import com.acme.oop.shared.domain.model.valueobjects.CustomerId;
import com.acme.oop.shared.domain.model.valueobjects.Money;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a Sales Order aggregate in the 'Sales' bounded context.
 * @author Open Source Application Development Team
 */
public class SalesOrder {
    @Getter
    private final UUID id;
    @Getter
    private final CustomerId customerId;
    @Getter
    private LocalDateTime orderDate;
    private final List<SalesOrderItem> items;
    @Getter
    private Money totalAmount;

    /**
     * Creates a new Sales Order with the given customer ID.
     * @param customerId    The ID of the customer placing the order. Must not be null.
     */
    public SalesOrder(@NonNull CustomerId customerId) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.orderDate = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.totalAmount = Money.zero();
    }

    /**
     * Adds an item to the Sales Order.
     * @param productId     the ID of the product being ordered. Must not be null.
     * @param quantity      the quantity of the product being ordered. Must be greater than 0.
     * @param unitPrice     the price per unit of the product. Must be greater than 0 and have a valid currency code.
     */
    public void addItem(@NonNull ProductId productId, int quantity, @NonNull Money unitPrice) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be greater than 0");
        if (unitPrice.amount().compareTo(Money.zero().amount()) <= 0)
            throw new IllegalArgumentException("Unit price must be greater than 0");
        SalesOrderItem newItem = new SalesOrderItem(productId, quantity, unitPrice);
        items.add(newItem);
        this.totalAmount = calculateTotalAmount();
    }

    /**
     * Calculates the total amount of the order by summing the prices of all items.
     * @return  the total amount of the order as a Money value object.
     */
    public Money calculateTotalAmount() {
        return this.items.stream()
                .map(SalesOrderItem::calculateItemPrice)
                .reduce(Money.zero(), Money::add);
    }

    /**
     * Sets the order date of the Sales Order.
     * @param orderDate the date and time when the order was placed. Must not be null.
     * @return  the SalesOrder instance with the updated order date, allowing for method chaining.
     */
    public SalesOrder withOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    /**
     * Returns the total amount of the order as a string in the format "Amount CurrencyCode".
     * @return  a string representation of the total amount, including the currency code.
     */
    public String getTotalAmountAsString() {
        return this.totalAmount.amount() + " " + this.totalAmount.currency().getCurrencyCode();
    }
}
