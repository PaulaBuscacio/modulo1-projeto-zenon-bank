package br.com.zenon.fraud.useCase;

import br.com.zenon.fraud.domain.Transaction;
import br.com.zenon.fraud.infra.TransactionIngestor;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class TransactionReport {

  TransactionIngestor transactionIngestor = new TransactionIngestor();


  public String printReport(String fileName, Integer limit, Locale locale) {
    locale = Optional.ofNullable(locale).orElse(Locale.of("pt", "BR"));
    ResourceBundle rb = ResourceBundle.getBundle("report", locale);
    NumberFormat format = NumberFormat.getCurrencyInstance(locale);
    List<Transaction> transactions = transactionIngestor.extractTransactionFromFileLazy(fileName, limit);
    int totalFrauds = transactions.stream().filter(Transaction::isFraud).toList().size();
    BigDecimal totalTransactionsValues =
        transactions.stream().map(Transaction::amount).reduce(BigDecimal.ZERO, BigDecimal::add);

    return rb.getString("total.lines") + ": " + transactions.size() +
        "\n" + rb.getString("total.frauds") + ": " + totalFrauds +
        "\n" + rb.getString("total.value") + ": " + format.format(totalTransactionsValues);

  }

}
