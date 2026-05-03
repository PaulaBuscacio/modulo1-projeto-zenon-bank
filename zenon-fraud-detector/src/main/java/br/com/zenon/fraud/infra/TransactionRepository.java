package br.com.zenon.fraud.infra;

import br.com.zenon.fraud.domain.Transaction;

public interface TransactionRepository {

  Long getSearchTimeNameOrigiLastTransaction(String fileName, Integer limit);

  Transaction findByOriginName(String originName);
  Transaction save(Transaction transaction);

}
