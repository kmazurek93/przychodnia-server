package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.AddressWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Address;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.ExceptionCause;
import edu.wmi.dpri.przychodnia.server.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.NotFoundExceptionThrower.throwExceptionIfNull;

/**
 * Created by lupus on 22.09.16.
 */
@Service
public class AddressService {

    @Inject
    private AddressRepository repository;
    @Inject
    private UserService userService;

    @Transactional
    public Address saveAddress(Address toSave) {
        return repository.save(toSave);
    }

    @Transactional
    public Address updateAddress(AddressWebModel toUpdate) {
        Address address = repository.findOne(toUpdate.getId());
        throwExceptionIfNull(toUpdate.getId(), address, ExceptionCause.MODIFICATION, Address.class);
        updateValues(address, toUpdate);
        return repository.save(address);
    }

    @Transactional
    public Address createOrUpdateMailingAddress(AddressWebModel toCreateOrUpdate) {
        Address address = repository.findOne(toCreateOrUpdate.getId());
        if (address == null) {
            address = new Address();
            updateValues(address, toCreateOrUpdate);
            return repository.save(address);
        } else {
            updateValues(address, toCreateOrUpdate);
            return repository.save(address);
        }
    }

    private void updateValues(Address address, AddressWebModel toUpdate) {
        address.setCountry(toUpdate.getCountry());
        address.setHouse(toUpdate.getHouse());
        address.setApartment(toUpdate.getApartment());
        address.setProvince(toUpdate.getProvince());
        address.setCity(toUpdate.getCity());
        address.setPostCode(toUpdate.getPostCode());
        address.setStreet(toUpdate.getStreet());
    }

    @Transactional(readOnly = true)
    public Address findOne(Long id) {
        Address foundAddress = repository.findOne(id);
        throwExceptionIfNull(id, foundAddress, ExceptionCause.RETRIEVAL, Address.class);
        return foundAddress;
    }

    @Transactional
    public void deleteOne(Long id) {
        Address foundAddress = repository.findOne(id);
        throwExceptionIfNull(id, foundAddress, ExceptionCause.DELETION, Address.class);
        repository.delete(id);
    }

    @Transactional
    public List<Address> getAll() {
        return newArrayList(repository.findAll());
    }
}
