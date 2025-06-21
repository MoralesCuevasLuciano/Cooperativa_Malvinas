package cooperativa_malvinas.controllers;

import cooperativa_malvinas.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerMember() {
        // Implement the logic to register a member
        // This is a placeholder; actual implementation will depend on your requirements
        return ResponseEntity.ok("Member registered successfully");
    }


}
