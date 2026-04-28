package br.com.zenon.fraud;

import br.com.zenon.fraud.infra.TransactionListRepositoryImpl;

import static java.lang.IO.println;

public class ZenonApplication {
  static void main() {


    TransactionListRepositoryImpl transactionRepository = new TransactionListRepositoryImpl();

    transactionRepository.printTransaction("C12345");
    transactionRepository.printTransaction("C1231006815");
    Long searchTime = transactionRepository.getSearchTimeNameOrigiLastTransaction("data/PS_20174392719_1491204439457_log.csv", 1000000);

    println("---------------------------------------------------------------------------------------------");
    println("Tempo de busca da última transação usando lista: " + searchTime + " nanosegundos");
    Long searchTimeMap = transactionRepository.getSearchTimeNameOrigiLastTransaction("data/PS_20174392719_1491204439457_log.csv", 1000000);
    println("Tempo de busca da última transação usando mapa: " + searchTimeMap + " nanosegundos");
    println("---------------------------------------------------------------------------------------------");
    println("Map é mais rápido que list? " + (searchTimeMap < searchTime));
  }
}
