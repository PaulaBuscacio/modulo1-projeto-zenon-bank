package br.com.zenon.fraud;

import br.com.zenon.fraud.infra.TransactionListRepository;

public class ZenonApplication {
  static void main() {


    TransactionListRepository transactionRepository = new TransactionListRepository();

    transactionRepository.printTransaction("C12345");
    transactionRepository.printTransaction("C1231006815");

  }
}
