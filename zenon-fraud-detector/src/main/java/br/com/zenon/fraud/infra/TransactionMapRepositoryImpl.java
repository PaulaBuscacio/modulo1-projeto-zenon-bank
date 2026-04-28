package br.com.zenon.fraud.infra;

import br.com.zenon.fraud.domain.Transaction;
import br.com.zenon.fraud.domain.vo.TransactionCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionMapRepositoryImpl implements TransactionRepository {

  private static Logger log = LoggerFactory.getLogger(TransactionMapRepositoryImpl.class);

  private  Map<String, Transaction> transactionsAsMap = getTransactionsAsMap("data/PS_20174392719_1491204439457_log.csv", 100000);

  @Override
  public Long getSearchTimeNameOrigiLastTransaction(String fileName, Integer limit) {
    Long start = System.nanoTime();
    transactionsAsMap.get("C1868032458");
    Long end = System.nanoTime();
    return end - start;
  }

  private  Map<String, Transaction> getTransactionsAsMap(String fileName, Integer limit) {

      String fileAsString;
      try {
        fileAsString = Files.readString(Path.of(fileName));
      } catch (IOException e) {
        throw new RuntimeException("Nao foi possível ler o arquivo" + fileName, e);
      }
      if (fileAsString.isEmpty()) {
        log.warn("O arquivo {} está vazio", fileName);
        return Map.of();
      }
      String[] lines = fileAsString.split("\n");
      int maxValue = limit == null ? lines.length - 1 : limit;

       return Arrays.stream(lines)
          .skip(1)
          .limit(maxValue)
          .map(line -> {
            String[] fields = line.split(",");
            try {
              TransactionCustomer originTransactionCustomer = new TransactionCustomer(fields[3], fields[4], fields[5]);
              TransactionCustomer destTransactionCustomer = new TransactionCustomer(fields[6], fields[7], fields[8]);
              return Map.entry(originTransactionCustomer.name(), new Transaction(Integer.valueOf(fields[0]), fields[1], fields[2], originTransactionCustomer, destTransactionCustomer, fields[9], fields[10]));

            } catch (RuntimeException e) {
              System.err.println("Error: " + line + " | " + e.getClass() + ": " + e.getMessage());
            }
            return null;
          })
           .filter(Objects::nonNull)
           .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

}
