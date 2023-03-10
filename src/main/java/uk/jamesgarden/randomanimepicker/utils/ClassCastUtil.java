package uk.jamesgarden.randomanimepicker.utils;

import jakarta.annotation.Nullable;
import java.util.Objects;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

public class ClassCastUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClassCastUtil.class);

  private ClassCastUtil() {
  }

  public static <T> T castOrNull(@NonNull Supplier<T> castMethod,
                                 @Nullable Object source,
                                 @NonNull Class<?> targetClass,
                                 @Nullable String logPrefix) {
    if (Objects.isNull(source)) {
      return null;
    }
    try {
      return castMethod.get();
    } catch (Exception e) {
      var errorMessagePrefix = Objects.nonNull(logPrefix) ? "[%s]".formatted(logPrefix) : "";
      var errorMessage = "%s Could not cast %s to %s".formatted(errorMessagePrefix, source.toString(), targetClass.getName());
      LOGGER.error(errorMessage, e.getCause());
      return null;
    }
  }
}
