package cooperativa_malvinas.models.mappers;

import cooperativa_malvinas.models.dto.MemberDTO;
import cooperativa_malvinas.models.entities.MemberEntity;
import org.springframework.beans.BeanUtils;


public class MemberMapper {
    public static MemberEntity toEntity(MemberDTO memberDTO) {
        MemberEntity member = new MemberEntity();
        BeanUtils.copyProperties(memberDTO, member);
        member.setAddress(memberDTO.getAddress());
        return member;
    }

    public static MemberDTO toDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(memberEntity, memberDTO);
        memberDTO.setAddress(memberEntity.getAddress());
        return memberDTO;
    }
}
