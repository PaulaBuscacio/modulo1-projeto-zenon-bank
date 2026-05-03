package br.com.zenon.fraud;

import br.com.zenon.fraud.domain.Transaction;
import br.com.zenon.fraud.infra.TransactionIngestor;
import br.com.zenon.fraud.infra.TransactionRepository;
import br.com.zenon.fraud.infra.TransactionSQLRepository;

import java.util.List;

import static java.lang.IO.println;

public class DBMain {

  void main() {

    TransactionIngestor ingestor = new TransactionIngestor();
    TransactionRepository repository = new TransactionSQLRepository();

    List<Transaction> transactions = ingestor.extractTransactionFromFileLazy("data/PS_20174392719_1491204439457_log.csv", 10000);

  long start = System.currentTimeMillis();
  transactions.forEach(repository::save);
  long end = System.currentTimeMillis();
  println("Tempo gasto para salvar as transações no banco: " + (end - start) + " ms");

    println(repository.findByOriginName("C1231006815"));
    println("-----------------------------");
    println(repository.findByOriginName("C1234"));
  }

}
