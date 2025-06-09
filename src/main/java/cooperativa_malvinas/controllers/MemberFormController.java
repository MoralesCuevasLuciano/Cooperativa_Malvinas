package cooperativa_malvinas.controllers;

import cooperativa_malvinas.models.entities.MemberEntity;
import cooperativa_malvinas.services.MemberService;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;
import javafx.fxml.FXML;

@Component
public class MemberFormController {

    @FXML private TextField nombreField;
    @FXML private TextField apellidoField;

    private final MemberService memberService;

    public MemberFormController(MemberService memberService) {
        this.memberService = memberService;
    }

    @FXML
    public void saveMember() {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();

        MemberEntity miembro = new MemberEntity();
        miembro.setName(nombre);
        miembro.setSurname(apellido);

        memberService.save(miembro);
        System.out.println("Miembro guardado: " + nombre + " " + apellido);
    }
}