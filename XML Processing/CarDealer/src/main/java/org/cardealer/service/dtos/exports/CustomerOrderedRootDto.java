package org.cardealer.service.dtos.exports;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerOrderedRootDto implements Serializable {

    @XmlElement(name = "customers")
    private List<CustomerOrderDto> customerOrderDtoList;

    public List<CustomerOrderDto> getCustomerOrderDtoList() {
        return customerOrderDtoList;
    }

    public void setCustomerOrderDtoList(List<CustomerOrderDto> customerOrderDtoList) {
        this.customerOrderDtoList = customerOrderDtoList;
    }
}
