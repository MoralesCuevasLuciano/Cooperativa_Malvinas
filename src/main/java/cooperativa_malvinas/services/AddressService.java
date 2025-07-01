package cooperativa_malvinas.services;

import cooperativa_malvinas.models.dto.AddressDTO;
import cooperativa_malvinas.models.entities.AddressEntity;
import cooperativa_malvinas.models.exceptions.AddressExistsException;
import cooperativa_malvinas.models.mappers.AddressMapper;
import cooperativa_malvinas.repositories.AddressRepository;

import java.util.Optional;

public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressEntity saveAddress(AddressDTO addressDTO) {
        AddressEntity address = AddressMapper.toEntity(addressDTO);
        Optional<AddressEntity> existingAddress = addressRepository.findById(address.getId());
        if (existingAddress.isPresent()) {
            throw new AddressExistsException("Address with ID " + address.getId() + " already exists.");
        }
        return addressRepository.save(address);
    }
}
