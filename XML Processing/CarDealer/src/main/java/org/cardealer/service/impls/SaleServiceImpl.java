package org.cardealer.service.impls;

import jakarta.xml.bind.JAXBException;
import org.cardealer.data.entities.Car;
import org.cardealer.data.entities.Customer;
import org.cardealer.data.entities.Part;
import org.cardealer.data.entities.Sale;
import org.cardealer.data.repositories.CarRepository;
import org.cardealer.data.repositories.CustomerRepository;
import org.cardealer.data.repositories.SaleRepository;
import org.cardealer.service.SaleService;
import org.cardealer.service.dtos.exports.CarDto;
import org.cardealer.service.dtos.exports.SaleDiscountDto;
import org.cardealer.service.dtos.exports.SaleDiscountsRootDto;
import org.cardealer.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {
    private List<Double> discounts = List.of(1.0, 0.95, 0.9, 0.85, 0.8, 0.7, 0.6, 0.5);
    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private XmlParser xmlParser;

    private ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository, XmlParser xmlParser) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedSales() {
        if (this.saleRepository.count() == 0) {
            for (int i = 0; i < 50; i++) {
                Sale sale = new Sale();
                sale.setCar(getRandomCar());
                sale.setCustomer(getRandomCustomer());
                sale.setDiscount(getRandomDiscount());
                this.saleRepository.saveAndFlush(sale);
            }
        }
    }

    @Override
    public void exportSales() throws JAXBException {
        List<SaleDiscountDto> saleDiscountDtos = this.saleRepository
                .findAll()
                .stream()
                .map(s -> {
                    SaleDiscountDto saleDiscountDto = this.modelMapper.map(s, SaleDiscountDto.class);
                    CarDto car = this.modelMapper.map(s.getCar(), CarDto.class);

                    saleDiscountDto.setCarDto(car);
                    saleDiscountDto.setCustomerName(s.getCustomer().getName());
                    saleDiscountDto.setPrice(s.getCar().getParts().stream().map(Part::getPrice).reduce(BigDecimal::add).get());
                    saleDiscountDto.setPriceWithDiscount(saleDiscountDto.getPrice().multiply(BigDecimal.valueOf(s.getDiscount())));
                    return saleDiscountDto;
                })
                .collect(Collectors.toList());

        SaleDiscountsRootDto saleDiscountsRootDto = new SaleDiscountsRootDto();
        saleDiscountsRootDto.setSaleDiscountDtos(saleDiscountDtos);

        this.xmlParser.exportToFile(SaleDiscountsRootDto.class, saleDiscountsRootDto, "src/main/resources/xml/exports/sale-discount.xml");
    }

    private double getRandomDiscount() {
        return discounts.get(
                ThreadLocalRandom.current().nextInt(1, discounts.size()));
    }

    private Customer getRandomCustomer() {
        return this.customerRepository.findById(
                ThreadLocalRandom.current().nextLong(1, this.customerRepository.count() + 1)).get();
    }

    private Car getRandomCar() {
        return this.carRepository.findById(
                ThreadLocalRandom.current().nextLong(1, this.carRepository.count() + 1)).get();
    }
}
