package com.acme.oop;

import com.acme.oop.crm.domain.model.aggregates.Customer;
import com.acme.oop.shared.domain.model.valueobjects.Address;

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
    }
}
