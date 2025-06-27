package cooperativa_malvinas.controllers;

import cooperativa_malvinas.models.dto.MemberDTO;
import cooperativa_malvinas.models.entities.MemberEntity;
import cooperativa_malvinas.services.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<MemberEntity> registerMember(@Valid @RequestBody MemberDTO member) {
        memberService.createMember(member);
        return new ResponseEntity<>(memberService.createMember(member), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<MemberEntity> updateMember(@PathVariable Long id, @Valid @RequestBody MemberDTO member) {
        MemberEntity updatedMember = memberService.updateMember(member);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }


}
