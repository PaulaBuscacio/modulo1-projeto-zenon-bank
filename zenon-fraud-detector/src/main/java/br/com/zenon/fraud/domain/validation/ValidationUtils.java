package br.com.zenon.fraud.domain.validation;

import java.math.BigDecimal;
import java.util.Objects;

public class ValidationUtils {

  public static BigDecimal validateValue(String value, String propertyName) {
    validateNullOrEmpty(value, propertyName);
    BigDecimal valueAsBigDecimal = new BigDecimal(value);
    if (valueAsBigDecimal.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException(propertyName + " should be positive: " + value);
    }
    return valueAsBigDecimal;
  }

  public static String validateNullOrEmptyString(String value, String propertyName) {
    validateNullOrEmpty(value, propertyName);
    return value;
  }

  private static void validateNullOrEmpty(String value, String propertyName) {
    if (Objects.isNull(value) || value.isBlank()) {
      throw new IllegalArgumentException(propertyName + " should not be empty");
    }
  }
}
