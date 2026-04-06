package com.acme.oop.crm.domain.model.aggregates;

import com.acme.oop.shared.domain.model.valueobjects.Address;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Represents a Customer aggregate in the CRM bounded context.
 *
 * @author Open Source Application Development Team
 */
@Getter
public class Customer {
    // TODO: Add CustomerId id;
    @Setter @NonNull private String name;
    @Setter @NonNull private String email;
    @Setter @NonNull private Address address;


    /**
     * Creates a new Customer aggregate with the given name, email, and address.
     * @param name      the name of the customer, it must not be null or blank.
     * @param email     the email of the customer, it must not be null or blank and should be a valid email format.
     * @param address   the address of the customer, it must not be null.
     */
    public Customer(@NonNull String name, @NonNull String email, @NonNull Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    /**
     * Updates the contact information of the customer.
     * @param email     the new email of the customer, it must not be null or blank and should be a valid email format.
     * @param address   the new address of the customer, it must not be null.
     */
    public void updateContactInfo(@NonNull String email, @NonNull Address address) {
        this.email = email;
        this.address = address;
    }

    /**
     * Updates the contact information of the customer.
     * @param email the new email of the customer, it must not be null or blank and should be a valid email format.
     */
    public void updateContactInfo(@NonNull String email) {
        this.email = email;
    }

    /**
     * Returns the contact information of the customer.
     * @return the contact information of the customer, in the format "Name, <Email>, Address".
     */
    public String getContactInfo() {
        return String.format("%s, <%s>, %s", name, email, address);
    }
}
