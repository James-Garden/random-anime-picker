package uk.jamesgarden.randomanimepicker.welcome;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class UsernameFormValidator implements Validator {

  @Override
  public boolean supports(@NonNull Class<?> clazz) {
    return UsernameForm.class.equals(clazz);
  }

  @Override
  public void validate(@NonNull Object target, @NonNull Errors errors) {
    ValidationUtils.rejectIfEmpty(
        errors,
        "username",
        "username.required",
        "Enter a username"
    );
  }
}
