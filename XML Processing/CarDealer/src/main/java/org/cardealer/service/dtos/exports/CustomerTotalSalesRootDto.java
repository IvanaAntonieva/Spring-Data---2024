package org.cardealer.service.dtos.exports;

import jakarta.xml.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerTotalSalesRootDto implements Serializable {

    @XmlElement(name = "customer")
    private List<CustomerTotalSalesDto> customerTotalSalesDtos;

    public List<CustomerTotalSalesDto> getCustomerTotalSalesDtos() {
        return customerTotalSalesDtos;
    }

    public void setCustomerTotalSalesDtos(List<CustomerTotalSalesDto> customerTotalSalesDtos) {
        this.customerTotalSalesDtos = customerTotalSalesDtos;
    }
}
