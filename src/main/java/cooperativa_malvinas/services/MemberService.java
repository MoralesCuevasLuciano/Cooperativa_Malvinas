package cooperativa_malvinas.services;

import cooperativa_malvinas.models.dto.MemberDTO;
import cooperativa_malvinas.models.entities.MemberEntity;
import cooperativa_malvinas.models.exceptions.DniCuitMismatchException;
import cooperativa_malvinas.models.exceptions.UserExistsException;
import cooperativa_malvinas.repositories.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberEntity createMember(MemberDTO member) {
        // Validate the member DTO
        Optional<MemberEntity> existingMember = memberRepository.findByDniAndLeaveDateIsNull(member.getDni());
        if (existingMember.isPresent()) {
            throw new UserExistsException("Member with DNI " + member.getDni() + " already exists.");
        }

        int countDigitsDni = member.getDni().length();// Cuenta los digitos del DNI
        String cuitDni = member.getCuit().substring(2, countDigitsDni + 2); // Extrae los digitos del DNI del CUIT, asumiendo que el CUIT tiene 10 u 11 digitos y el DNI 7 u 8 digitos
        if( !member.getDni().equals(cuitDni)){
            throw new DniCuitMismatchException("The DNI and CUIT do not match.");
        }

        MemberEntity memberEntity = MemberEntity.builder()
                .firstName(member.getFirstName())
                .secondName(member.getSecondName())
                .fatherSurname(member.getFatherSurname())
                .motherSurname(member.getMotherSurname())
                .email(member.getEmail())
                .phone(member.getPhone())
                .address(member.getAddress())
                .dni(member.getDni())
                .cuit(member.getCuit())
                .birthDate(member.getBirthDate())
                .role(member.getRole())
                .build();
        return memberRepository.save(memberEntity);


    }

}
