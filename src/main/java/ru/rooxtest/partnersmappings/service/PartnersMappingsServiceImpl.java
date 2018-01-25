package ru.rooxtest.partnersmappings.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rooxtest.partnersmappings.domain.Customer;
import ru.rooxtest.partnersmappings.domain.PartnerMapping;
import ru.rooxtest.partnersmappings.repository.CustomerRepository;
import ru.rooxtest.partnersmappings.repository.PartnerMappingRepository;

import java.util.List;
import java.util.UUID;

/**
 * Сервис доступа к БД
 */
@Service
@Transactional
public class PartnersMappingsServiceImpl implements PartnersMappingsService {
    private static final Logger log = LoggerFactory.getLogger(PartnersMappingsServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PartnerMappingRepository partnerMappingRepository;

    @Transactional(readOnly = true)
    public List<Customer> findAllCustomers() {
        log.info("Reading all Customers");
        List<Customer> customers = customerRepository.findAll();
        log.info("Read " + customers.size() + " customers");
        return customers;
    }

    @Transactional(readOnly = true)
    public Customer findCustomerById(UUID id) {
        log.info("Reading customer by id: " + id);
        Customer customer = customerRepository.findById(id);
        log.info("Read: " + customer);
        return customer;
    }

    @Override
    public Customer findCustomerByLogin(String login) {
        log.info("Reading customer by login: " + login);
        Customer customer = customerRepository.findByLogin(login);
        log.info("Read: " + customer);
        return customer;    }

    @Transactional(readOnly = true)
    public List<PartnerMapping> findPartnerMappingsByCustomerId(UUID customerId) {
        log.info("Reading PartnerMappings by customerId: " + customerId);
        List<PartnerMapping> partnerMappings = partnerMappingRepository.findByCustomerId(customerId);
        log.info("Read " + partnerMappings.size() + " PartnerMappings");
        return partnerMappings;
    }

    @Transactional(readOnly = true)
    public PartnerMapping findPartnerMapping(UUID id) {
        log.info("Reading PartnerMapping: " + id);
        PartnerMapping partnerMapping = partnerMappingRepository.find(id);
        log.info("Read " + partnerMapping);
        return partnerMapping;
    }

    public void savePartnerMapping(PartnerMapping partnerMapping) {
        log.info("Saving " + partnerMapping);
        partnerMappingRepository.save(partnerMapping);
        log.info("Saving complete");
    }

    public void removePartnerMapping(UUID id) {
        log.info("Removing PartnerMapping: " + id);
        partnerMappingRepository.remove(id);
        log.info("Removing complete");
    }

}
