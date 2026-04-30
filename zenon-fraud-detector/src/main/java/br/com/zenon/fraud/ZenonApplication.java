package br.com.zenon.fraud;

import br.com.zenon.fraud.domain.Transaction;
import br.com.zenon.fraud.infra.TransactionIngestor;
import br.com.zenon.fraud.infra.TransactionListRepositoryImpl;

import java.util.List;

import static java.lang.IO.println;

public class ZenonApplication {
  static void main() {


    TransactionIngestor transactionIngestor = new TransactionIngestor();
    //java.lang.OutOfMemoryError: Java heap space usando -Xmx128m como argumento
    List<Transaction> transactions = transactionIngestor.extractTransactionFromFile("data/PS_20174392719_1491204439457_log.csv", null);
    println(transactions);
  }
}
