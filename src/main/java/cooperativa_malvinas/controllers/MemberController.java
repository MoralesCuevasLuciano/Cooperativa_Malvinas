package cooperativa_malvinas.controllers;

import cooperativa_malvinas.models.dto.MemberDTO;
import cooperativa_malvinas.models.dto.MemberUpdateDTO;
import cooperativa_malvinas.models.entities.MemberEntity;
import cooperativa_malvinas.services.MemberService;
import jakarta.validation.Valid;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    public ResponseEntity<MemberEntity> updateMember(@PathVariable Long id, @Valid @RequestBody MemberUpdateDTO memberUpdateDTO) {
        MemberEntity updatedMember = memberService.updateMember(id, memberUpdateDTO);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    @PutMapping("/update/{id}/desactivate")
    public ResponseEntity<MemberEntity> desactivateMember(
            @PathVariable Long id,
            @RequestParam(value = "leaveDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate leaveDate) {

        MemberEntity desactivatedMember = memberService.desactivateMember(id, leaveDate);
        return new ResponseEntity<>(desactivatedMember, HttpStatus.OK);
    }


    public ResponseEntity<List<MemberEntity>> getAllActiveMembers() {
        MemberEntity member = memberService.getMemberById(id);
        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
}
