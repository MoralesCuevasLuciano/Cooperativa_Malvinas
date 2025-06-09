package cooperativa_malvinas.services;

import cooperativa_malvinas.models.entities.MemberEntity;
import cooperativa_malvinas.repositories.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberEntity save(MemberEntity member) {
        return memberRepository.save(member);
    }

}
