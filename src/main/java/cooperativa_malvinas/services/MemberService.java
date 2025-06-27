package cooperativa_malvinas.services;

import cooperativa_malvinas.models.dto.MemberDTO;
import cooperativa_malvinas.models.dto.MemberUpdateDTO;
import cooperativa_malvinas.models.entities.MemberEntity;
import cooperativa_malvinas.models.exceptions.DniCuitMismatchException;
import cooperativa_malvinas.models.exceptions.UserExistsException;
import cooperativa_malvinas.repositories.MemberRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
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

    public List<MemberEntity> getAllActiveMembers() {
        return memberRepository.findByLeaveDateIsNull();
    }

}
