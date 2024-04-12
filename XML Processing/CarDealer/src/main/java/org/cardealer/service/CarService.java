package org.cardealer.service;

import jakarta.xml.bind.JAXBException;

public interface CarService {
    void SeedCars() throws JAXBException;

    void exportToyotaCars() throws JAXBException;

    void exportCarsAndParts() throws JAXBException;
}
