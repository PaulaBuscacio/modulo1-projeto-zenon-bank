package br.com.zenon.fraud.infra;

import br.com.zenon.fraud.domain.vo.TransactionCustomer;
import br.com.zenon.fraud.domain.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.IO.println;

/**
 * responsável pela ingestão de dados de transaçōes do PaySim.
 * Recebe um nome de arquivo e retorna uma List<Transaction>
 */

public class TransactionIngestor {

  private static Logger log = LoggerFactory.getLogger(TransactionIngestor.class);

  public List<Transaction> extractTransactionFromFile(String fileName, Integer limit) {
    String fileAsString;
    List<Transaction> transactions = new ArrayList<>();
    try {
      fileAsString = Files.readString(Path.of(fileName));
    } catch (IOException e) {
      throw new RuntimeException("Nao foi possível ler o arquivo" + fileName, e);
    }
    if (fileAsString.isEmpty()) {
      log.warn("O arquivo {} está vazio", fileName);
      return transactions;
    }
    String[] lines = fileAsString.split("\n");
    int maxValue = limit == null ? lines.length - 1 : limit;
    Arrays.stream(lines)
        .skip(1)
        .limit(maxValue)
        .forEach(line -> {
          String[] fields = line.split(",");
          try {
            TransactionCustomer originTransactionCustomer = new TransactionCustomer(fields[3], fields[4], fields[5]);
            TransactionCustomer destTransactionCustomer = new TransactionCustomer(fields[6], fields[7], fields[8]);
            transactions.add(new Transaction(Integer.valueOf(fields[0]), fields[1], fields[2], originTransactionCustomer, destTransactionCustomer, fields[9], fields[10]));
          } catch (RuntimeException e) {
            System.err.println("Error: " + line + " | " + e.getClass() + ": " + e.getMessage());
          }
        });
    return transactions;
  }

  public List<Transaction> extractTransactionFromFileLazy(String fileName, Integer limit) {
    List<String> lines;
    List<Transaction> transactions = new ArrayList<>();
    try {
      lines = Files.readAllLines(Path.of(fileName));
    } catch (IOException e) {
      throw new RuntimeException("Nao foi possível ler o arquivo" + fileName, e);
    }
    if (lines.isEmpty()) {
      log.warn("O arquivo {} está vazio", fileName);
      return transactions;
    }
    int maxValue = limit == null ? lines.size() - 1 : limit;
    lines.stream()
        .skip(1)
        .limit(maxValue)
        .forEach(line -> {
          String[] fields = line.split(",");
          try {
            TransactionCustomer originTransactionCustomer = new TransactionCustomer(fields[3], fields[4], fields[5]);
            TransactionCustomer destTransactionCustomer = new TransactionCustomer(fields[6], fields[7], fields[8]);
            transactions.add(new Transaction(Integer.valueOf(fields[0]), fields[1], fields[2], originTransactionCustomer, destTransactionCustomer, fields[9], fields[10]));
          } catch (RuntimeException e) {
            System.err.println("Error: " + line + " | " + e.getClass() + ": " + e.getMessage());
          }
        });
    return transactions;
  }

  static void main() {
    TransactionIngestor transactionIngestor = new TransactionIngestor();
    transactionIngestor.extractTransactionFromFileLazy("data/PS_20174392719_1491204439457_log.csv", 1000000);
    transactionIngestor.extractTransactionFromFile("data/PS_20174392719_1491204439457_log.csv", 1000000);


  }

}
