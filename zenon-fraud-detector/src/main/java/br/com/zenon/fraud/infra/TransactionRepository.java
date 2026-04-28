package br.com.zenon.fraud.infra;

public interface TransactionRepository {

  Long getSearchTimeNameOrigiLastTransaction(String fileName, Integer limit);

}
