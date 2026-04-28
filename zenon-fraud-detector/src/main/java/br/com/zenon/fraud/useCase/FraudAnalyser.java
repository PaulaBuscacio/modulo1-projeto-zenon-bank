package br.com.zenon.fraud.useCase;

import br.com.zenon.fraud.domain.Transaction;
import br.com.zenon.fraud.domain.vo.TransactionCustomer;
import br.com.zenon.fraud.infra.TransactionIngestor;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * responsável pela análise de fraudes com base em uma lista de transações.
 *
 */
public class FraudAnalyser {

  TransactionIngestor transactionIngestor = new TransactionIngestor();

  public List<Transaction> getIsFraudTransactions(String fileName, Integer limit) {
    List<Transaction> transactions = transactionIngestor.extractTransactionFromFile(fileName, limit);
    return transactions.stream().filter(Transaction::isFraud).sorted((Comparator.comparing(Transaction::amount).reversed())).toList();
  }

  public List<BigDecimal> getFraudAmountsWithLimit(List<BigDecimal> amounts, Integer limit) {
    int limitValue = limit == null ? amounts.size() : limit;
    return amounts.subList(0, limitValue);
  }

  public List<String> getMostSuspiciousCustomers(List<Transaction> fraudTransactions, Integer numberOfCustomers) {
    int limitCustomers = numberOfCustomers == null ? fraudTransactions.size() : numberOfCustomers;
    return fraudTransactions.stream()
        .map(Transaction::origin)
        .map(TransactionCustomer::name)
        .distinct()
        .limit(limitCustomers).toList();
  }

  public BigDecimal getFraudTotalAmount(List<BigDecimal> fraudAmounts) {
    return fraudAmounts.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public List<BigDecimal> getFraudAmounts(List<Transaction> fraudTransactions) {
    return fraudTransactions.stream()
        .map(Transaction::amount).toList();
  }

  public Map<String, Integer> getTotalFraudsByType(List<Transaction> transactions) {
    return transactions.stream()
        .map(Transaction::type)
        .collect(Collectors.groupingBy(Enum::name, Collectors.summingInt(t -> 1)));

  }
}
