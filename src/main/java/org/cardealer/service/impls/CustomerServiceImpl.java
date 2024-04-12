package org.cardealer.service.impls;

import jakarta.xml.bind.JAXBException;
import org.cardealer.data.entities.Customer;
import org.cardealer.data.repositories.CustomerRepository;
import org.cardealer.service.CustomerService;
import org.cardealer.service.dtos.CustomerSeedRootDto;
import org.cardealer.service.dtos.exports.CustomerOrderDto;
import org.cardealer.service.dtos.exports.CustomerOrderedRootDto;
import org.cardealer.service.dtos.exports.CustomerTotalSalesDto;
import org.cardealer.service.dtos.exports.CustomerTotalSalesRootDto;
import org.cardealer.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String FILE_IMPORT_PATH = "src/main/resources/xml/imports/customers.xml";
    private static final String FILE_EXPORT_CUSTOMERS_PATH = "src/main/resources/xml/exports/ordered-customers.xml";
    private static final String FILE_EXPORT_CUSTOMERS_BOUGHT_PATH = "src/main/resources/xml/exports/customer-with-cars.xml";

    private final CustomerRepository customerRepository;

    private final XmlParser xmlParser;

    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, XmlParser xmlParser, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void SeedCustomers() throws JAXBException {
        if (this.customerRepository.count() == 0) {
            CustomerSeedRootDto customerSeedRootDto = this.xmlParser.parse(CustomerSeedRootDto.class, FILE_IMPORT_PATH);
            customerSeedRootDto.getCustomerSeedDtoList().forEach(c ->
                    this.customerRepository.saveAndFlush(this.modelMapper.map(c, Customer.class)));
        }
    }

    @Override
    public void exportOrderedCustomers() throws JAXBException {
        List<CustomerOrderDto> customerOrderDtos = this.customerRepository.findAllByOrderByBirthDateAscIsYoungDriverAsc()
                .stream()
                .map(c -> this.modelMapper.map(c, CustomerOrderDto.class))
                .collect(Collectors.toList());

        CustomerOrderedRootDto customerOrderedRootDto = new CustomerOrderedRootDto();
        customerOrderedRootDto.setCustomerOrderDtoList(customerOrderDtos);

        this.xmlParser.exportToFile(CustomerOrderedRootDto.class, customerOrderedRootDto, FILE_EXPORT_CUSTOMERS_PATH);
    }


    @Override
    public void exportCustomersWithBoughtCars() throws JAXBException {
        List<CustomerTotalSalesDto> collect = this.customerRepository.findAllWithBoughtCars()
                .stream()
                .map(c -> {
                    CustomerTotalSalesDto customerTotalSalesDto = new CustomerTotalSalesDto();
                    customerTotalSalesDto.setFullName(c.getName());
                    customerTotalSalesDto.setBoughtCars(c.getSales().size());
                    double spentMoney = c.getSales()
                            .stream()
                            .mapToDouble(s -> s.getCar().getParts().stream().mapToDouble(p -> p.getPrice().doubleValue()).sum() * s.getDiscount())
                            .sum();
                    customerTotalSalesDto.setSpentMoney(BigDecimal.valueOf(spentMoney));

                    return customerTotalSalesDto;
                })
                .sorted(Comparator.comparing(CustomerTotalSalesDto::getBoughtCars).reversed()
                        .thenComparing(Comparator.comparing(CustomerTotalSalesDto::getSpentMoney).reversed()))
                .collect(Collectors.toList());

        CustomerTotalSalesRootDto customerTotalSalesRootDto = new CustomerTotalSalesRootDto();
        customerTotalSalesRootDto.setCustomerTotalSalesDtos(collect);

        this.xmlParser.exportToFile(CustomerTotalSalesRootDto.class, customerTotalSalesRootDto, FILE_EXPORT_CUSTOMERS_BOUGHT_PATH);
    }
}
