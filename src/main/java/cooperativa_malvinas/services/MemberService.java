package cooperativa_malvinas.services;

import cooperativa_malvinas.models.dto.MemberDTO;
import cooperativa_malvinas.models.dto.MemberUpdateDTO;
import cooperativa_malvinas.models.entities.AddressEntity;
import cooperativa_malvinas.models.entities.MemberEntity;
import cooperativa_malvinas.models.exceptions.DniCuitMismatchException;
import cooperativa_malvinas.models.exceptions.UserExistsException;
import cooperativa_malvinas.models.mappers.AddressMapper;
import cooperativa_malvinas.repositories.AddressRepository;
import cooperativa_malvinas.repositories.MemberRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import cooperativa_malvinas.models.mappers.MemberMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;

    public MemberService(MemberRepository memberRepository, AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
        this.memberRepository = memberRepository; // Assuming addressRepository is not used in this service
    }

    public MemberEntity createMember(MemberDTO memberDTO) {
        // Validate the member DTO
        Optional<MemberEntity> existingMember = memberRepository.findByDniAndLeaveDateIsNull(memberDTO.getDni());
        if (existingMember.isPresent()) {
            throw new UserExistsException("Member with DNI " + memberDTO.getDni() + " already exists.");
        }

        int countDigitsDni = memberDTO.getDni().length();// Cuenta los digitos del DNI
        String cuitDni = memberDTO.getCuit().substring(2, countDigitsDni + 2); // Extrae los digitos del DNI del CUIT, asumiendo que el CUIT tiene 10 u 11 digitos y el DNI 7 u 8 digitos
        if( !memberDTO.getDni().equals(cuitDni)){
            throw new DniCuitMismatchException("The DNI and CUIT do not match.");
        }

        Optional<AddressEntity> existingAddress = addressRepository.findByStreetAndNumeralAndCityAndFloorAndApartment(
                memberDTO.getAddress().getStreet(),
                memberDTO.getAddress().getNumeral(),
                memberDTO.getAddress().getCity(),
                memberDTO.getAddress().getFloor(),
                memberDTO.getAddress().getApartment()
        );

        AddressEntity savedAddress = existingAddress.orElseGet(() -> addressRepository.save(memberDTO.getAddress()));

        MemberEntity memberEntity = MemberEntity.builder()
                .firstName(memberDTO.getFirstName())
                .secondName(memberDTO.getSecondName())
                .fatherSurname(memberDTO.getFatherSurname())
                .motherSurname(memberDTO.getMotherSurname())
                .email(memberDTO.getEmail())
                .phone(memberDTO.getPhone())
                .address(savedAddress)
                .dni(memberDTO.getDni())
                .cuit(memberDTO.getCuit())
                .birthDate(memberDTO.getBirthDate())
                .joinDate(memberDTO.getJoinDate())
                .role(memberDTO.getRole())
                .build();
        return memberRepository.save(memberEntity);
    }

    public MemberEntity updateMember(Long id, MemberUpdateDTO memberUpdateDTO) {
        MemberEntity memberEntity = memberRepository.findById(id)
                .orElseThrow(() -> new UserExistsException("Member with ID " + id + " does not exist."));

        // Update the member entity with the new values
        BeanUtils.copyProperties(memberUpdateDTO, memberEntity, "id");

        // Save the updated entity
        return memberRepository.save(memberEntity);
    }

    public MemberEntity desactivateMember(Long id, LocalDate leaveDate) {
        MemberEntity memberEntity = memberRepository.findById(id)
                .orElseThrow(() -> new UserExistsException("Member with ID " + id + " does not exist."));

        // Check if the member is already desactivated
        if (memberEntity.getLeaveDate() != null) {
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member is already desactivated.");
        }

        // Set the leave date and save the entity
        memberEntity.setLeaveDate(leaveDate);
        return memberRepository.save(memberEntity);
        //credentialsService.deleteByMemberId(id);
    }

    public MemberEntity getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new UserExistsException("Member with ID " + id + " does not exist."));
    }

    public List<MemberDTO> getAllActiveMembers() {
        return memberRepository.findAllByLeaveDateIsNull()
                .stream()
                .map(MemberMapper::toDTO)
                .collect(Collectors.toList());
    }

}
