package br.com.zenon.fraud;

import br.com.zenon.fraud.domain.Transaction;
import br.com.zenon.fraud.useCase.TransactionIngestor;

import java.util.List;

import static java.lang.IO.println;

public class ZenonApplication {
  static void main() {

    TransactionIngestor transactionIngestor = new TransactionIngestor();
    List<Transaction> transactions = transactionIngestor.extractTransactionFromFile("data/paysim_with_bad_data.csv");

    println(transactions.size());
    println(transactions);
  }


}
