package com.acme.oop;

import com.acme.oop.crm.domain.model.aggregates.Customer;
import com.acme.oop.sales.domain.model.aggregates.SalesOrder;
import com.acme.oop.sales.domain.model.valueobjects.ProductId;
import com.acme.oop.shared.domain.model.valueobjects.Address;
import com.acme.oop.shared.domain.model.valueobjects.Money;

import java.math.BigDecimal;
import java.util.Currency;

public class Main {
    static void main() {
        // Shared context
        Address address = new Address("123 Main St", "Springfield", "IL", "62701");
        System.out.println("First Address: " + address);
        Address anotherAddress = new Address("456 Elm St", "Springfield", "IL", "62701");
        System.out.println("Second Address: " + anotherAddress);
        System.out.println("Creating a customer");
        Customer customer = new Customer("John Doe", "john.doe@gmail.com", address);
        System.out.println("Customer Contact Info: " + customer.getContactInfo());
        SalesOrder order = new SalesOrder(customer.getId());
        Money price = new Money(new BigDecimal("29.99"), Currency.getInstance("USD"));
        ProductId productId = new ProductId();
        order.addItem(productId, 2, price);
        System.out.println("Order ID:" + order.getId() +
                " Customer ID: " + order.getCustomerId() +
                " Order Date: " + order.getOrderDate() +
                " Total Amount: " + order.getTotalAmountAsString());
    }
}
