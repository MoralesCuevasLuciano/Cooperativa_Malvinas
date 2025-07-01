package cooperativa_malvinas.models.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerException {

    @ExceptionHandler(Exception.class)
    private ProblemDetail createProblemDetail(HttpStatus status, String title, String detail, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setTitle(title);
        problem.setInstance(URI.create(request.getRequestURL().toString()));
        problem.setProperty("timestamp", OffsetDateTime.now());
        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ProblemDetail problem = createProblemDetail(HttpStatus.BAD_REQUEST, "Validation Failed", "Validation error in request", request);
        problem.setProperty("errors", errors);
        return problem;
    }


    @ExceptionHandler(DniCuitMismatchException.class)
    public ProblemDetail handlerDniCuitMismatchException(DniCuitMismatchException ex, HttpServletRequest request) {
        return createProblemDetail(HttpStatus.BAD_REQUEST, "DNI/CUIT Mismatch",
                "The provided DNI and CUIT do not match.", request);
    }

    @ExceptionHandler(UserExistsException.class)
    public ProblemDetail handlerUserExistsException(UserExistsException ex, HttpServletRequest request) {
        return createProblemDetail(HttpStatus.BAD_REQUEST, "User Already Exists",
                "A user with the provided DNI already exists", request);
    }

    @ExceptionHandler(AddressExistsException.class)
    public ProblemDetail handlerAddressExistsException(AddressExistsException ex, HttpServletRequest request) {
        return createProblemDetail(HttpStatus.BAD_REQUEST, "Address Already Exists",
                "An address with the provided details already exists", request);
    }
}
