package uk.jamesgarden.randomanimepicker.welcome;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import uk.jamesgarden.randomanimepicker.maluser.MalUserService;
import uk.jamesgarden.randomanimepicker.utils.UrlUtils;

@Service
public class WelcomeFormValidator implements Validator {

  private static final String USERNAME_FIELD = "username";
  private final MalUserService malUserService;

  @Autowired
  public WelcomeFormValidator(MalUserService malUserService) {
    this.malUserService = malUserService;
  }

  @Override
  public boolean supports(@NonNull Class<?> clazz) {
    return WelcomeForm.class.equals(clazz);
  }

  @Override
  public void validate(@NonNull Object target, @NonNull Errors errors) {
    var form = (WelcomeForm) target;

    ValidationUtils.rejectIfEmpty(
        errors,
        USERNAME_FIELD,
        "%s.required".formatted(USERNAME_FIELD),
        "Enter a username"
    );

    if (!errors.hasFieldErrors(USERNAME_FIELD)
        && (!(malUserService.existsByUsername(form.getUsername()) || validateUsernameExistsExternally(form.getUsername())))) {
      // Using short-circuited OR to avoid unnecessary request if we know that an entered username exists
        errors.rejectValue(
            USERNAME_FIELD,
            "%s.invalid".formatted(USERNAME_FIELD),
            "Could not retrieve list for that user, check the name and try again"
        );
    }
  }

  private boolean validateUsernameExistsExternally(String username) {
    try {
      var client = HttpClient.newHttpClient();
      var request = HttpRequest.newBuilder(
              URI.create(UrlUtils.getListUrl(username)))
          .method("HEAD", HttpRequest.BodyPublishers.noBody())
          .build();
      var response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return HttpStatus.NOT_FOUND.value() != (response.statusCode());
    } catch (Exception e) {
      if (InterruptedException.class.equals(e.getClass())) {
        Thread.currentThread().interrupt();
      }
      return false;
    }
  }
}
