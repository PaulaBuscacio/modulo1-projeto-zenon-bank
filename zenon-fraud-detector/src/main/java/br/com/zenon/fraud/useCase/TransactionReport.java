package br.com.zenon.fraud.useCase;

import br.com.zenon.fraud.domain.Transaction;
import br.com.zenon.fraud.infra.TransactionIngestor;

import java.math.BigDecimal;
import java.util.List;

public class TransactionReport {

  TransactionIngestor transactionIngestor = new TransactionIngestor();


  public String printReport(String fileName, Integer limit) {
    List<Transaction> transactions = transactionIngestor.extractTransactionFromFileLazy(fileName, limit);
    int totalFrauds = transactions.stream().filter(Transaction::isFraud).toList().size();
    BigDecimal totalTransactionsValues =
        transactions.stream().map(Transaction::amount).reduce(BigDecimal.ZERO, BigDecimal::add);

    return "Total de linhas: " + transactions.size() +
        "\nTotal de fraudes: " + totalFrauds +
        "\nValor total transacionado: " + totalTransactionsValues;

  }

  public String benchmarkReport(String fileName, Integer limit) {
    long startTime1 = System.currentTimeMillis();
    transactionIngestor.extractTransactionFromFile(fileName, limit);
    long endTime1 = System.currentTimeMillis();
    long startTime2 = System.currentTimeMillis();
    transactionIngestor.extractTransactionFromFileLazy(fileName, limit);
    long endTime2 = System.currentTimeMillis();

    long duration1 = endTime1 - startTime1;
    long duration2 = endTime2 - startTime2;

    return "Tempo de execução usando Files.readString: " + duration1 + " ms"
        + "\nTempo de execução usando Files.readAllLines: " + duration2 + " ms";
  }


}
