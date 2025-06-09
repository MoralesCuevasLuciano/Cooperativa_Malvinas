package cooperativa_malvinas.validations;

import cooperativa_malvinas.models.entities.MeterTicketEntity;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, MeterTicketEntity> {

    @Override
    public boolean isValid(MeterTicketEntity ticket, ConstraintValidatorContext context) {
        if (ticket == null || ticket.getCutoffDate() == null) {
            return true; // Si alguna fecha es null, dejamos que otras validaciones se encarguen
        }

        return !ticket.getCutoffDate().isBefore(ticket.getStartDate());
    }
}