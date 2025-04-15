package pl.mzuchnik.complaint.infrastructure.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.mzuchnik.complaint.domain.exception.NotFoundComplaintException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
class GlobalRestErrorHandler {

    @ExceptionHandler(value = NotFoundComplaintException.class)
    public ProblemDetail handleNotFoundComplaintException(NotFoundComplaintException exception) {

        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pb.setProperty("timestamp", LocalDateTime.now());
        pb.setDetail(exception.getMessage());
        pb.setTitle(HttpStatus.NOT_FOUND.getReasonPhrase());
        return pb;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setProperty("timestamp", LocalDateTime.now());
        pb.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());
        pb.setDetail("Invalid request body");
        pb.setProperty("errors", errors);
        return pb;
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ProblemDetail handleRuntimeException(RuntimeException exception) {
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setProperty("timestamp", LocalDateTime.now());
        pb.setDetail(exception.getMessage());
        pb.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());

        return pb;
    }

}
