package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Customer(String nameOrig,
                       BigDecimal oldbalanceOrg,
                       BigDecimal newbalanceOrig) {
}
