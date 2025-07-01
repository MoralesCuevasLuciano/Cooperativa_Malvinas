package cooperativa_malvinas.models.mappers;

import cooperativa_malvinas.models.dto.AddressDTO;
import cooperativa_malvinas.models.entities.AddressEntity;
import org.springframework.beans.BeanUtils;

public class AddressMapper {
    public static AddressEntity toEntity(AddressDTO addressDTO) {
        AddressEntity address = new AddressEntity();
        BeanUtils.copyProperties(addressDTO, address);
        return address;
    }

    public static AddressDTO toDTO(AddressEntity addressEntity) {
        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(addressEntity, addressDTO);
        return addressDTO;
    }
}
