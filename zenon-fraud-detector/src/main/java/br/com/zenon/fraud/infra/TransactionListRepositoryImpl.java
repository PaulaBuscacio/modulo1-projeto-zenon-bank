package br.com.zenon.fraud.infra;

import br.com.zenon.fraud.domain.Transaction;

import java.util.List;
import java.util.Optional;

import static java.lang.IO.println;

public class TransactionListRepositoryImpl implements TransactionRepository {

  private TransactionIngestor transactionIngestor = new TransactionIngestor();

  private List<Transaction> transactions = transactionIngestor.extractTransactionFromFile("data/PS_20174392719_1491204439457_log.csv", 100000);


  public void printTransaction(String originName) {
    Optional<Transaction> transactionOpt = getTransaction(originName);
    transactionOpt.ifPresentOrElse(
        transaction -> println(transaction),
        () -> println("Transação não encontrada para o cliente " + originName)
    );

  }

  public Long getSearchTimeNameOrigiLastTransaction(String fileName, Integer limit) {
    Long start = System.nanoTime();
    transactions.getLast();
    Long end = System.nanoTime();
    return end - start;
  }

  @Override
  public Transaction findByOriginName(String originName) {
    return null;
  }

  @Override
  public Transaction save(Transaction transaction) {
    return null;
  }

  private Optional<Transaction> getTransaction(String originName) {
    return transactions.stream()
        .filter(t -> t.origin().name().equals(originName))
        .findFirst();

  }


}
