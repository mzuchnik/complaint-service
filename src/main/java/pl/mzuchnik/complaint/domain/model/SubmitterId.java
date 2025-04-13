package pl.mzuchnik.complaint.domain.model;

import java.util.regex.Pattern;

public record SubmitterId(String email) {

    private static final Pattern RFC5322EmailValidator = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"); // Regular Expression by RFC 5322 for Email Validation

    public SubmitterId {
        if(!isEmailValid(email))
        {
            throw new IllegalArgumentException("Email is invalid");
        }
    }

    private boolean isEmailValid(String email) {
        return email != null &&
                !email.isBlank() &&
                RFC5322EmailValidator.matcher(email).matches();
    }
}
