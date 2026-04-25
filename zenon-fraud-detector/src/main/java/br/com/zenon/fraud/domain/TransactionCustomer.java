package br.com.zenon.fraud.domain;

import java.math.BigDecimal;

public record TransactionCustomer(String name,
                                  BigDecimal oldbalance,
                                  BigDecimal newbalance) {
}
