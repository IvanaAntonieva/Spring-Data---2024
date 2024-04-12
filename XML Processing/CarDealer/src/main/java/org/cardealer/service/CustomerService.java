package org.cardealer.service;

import jakarta.xml.bind.JAXBException;

public interface CustomerService {

    void SeedCustomers() throws JAXBException;

    void exportOrderedCustomers() throws JAXBException;

    void exportCustomersWithBoughtCars() throws JAXBException;
}
