package br.com.zenon.fraud.useCase;

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

/**
 * responsável pela ingestão de dados de transaçōes do PaySim.
 * Recebe um nome de arquivo e retorna uma List<Transaction>
 */

public class TransactionIngestor {

  private static Logger log = LoggerFactory.getLogger(TransactionIngestor.class);

  public List<Transaction> extractTransactionFromFile(String fileName) {
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
    Arrays.stream(lines).filter(line -> line != lines[0])
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

}
