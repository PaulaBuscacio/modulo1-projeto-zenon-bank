package br.com.zenon.fraud;

import br.com.zenon.fraud.domain.Transaction;
import br.com.zenon.fraud.infra.TransactionListRepository;
import br.com.zenon.fraud.useCase.FraudAnalyser;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.lang.IO.println;

public class ZenonApplication {
  static void main() {

//    FraudAnalyser fraudAnalyser = new FraudAnalyser();
//    DecimalFormat df = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
//    List<Transaction> fraudTransactions = fraudAnalyser.getIsFraudTransactions("data/PS_20174392719_1491204439457_log.csv", 50000);
//    List<BigDecimal> fraudAmounts = fraudAnalyser.getFraudAmounts(fraudTransactions);
//    List<BigDecimal> biggestAmountFraudTransactions = fraudAnalyser.getFraudAmountsWithLimit(fraudAmounts, 3);
//    List<String> mostSuspiciousCustomers = fraudAnalyser.getMostSuspiciousCustomers(fraudTransactions, 5);
//    BigDecimal totalFraudAmount = fraudAnalyser.getFraudTotalAmount(fraudAmounts);
//    Map<String, Integer> totalFraudsByType = fraudAnalyser.getTotalFraudsByType(fraudTransactions);
//
//    println("1. Total de Fraudes: " + fraudTransactions.size());
//    println("2. Top 3 Fraudes de Maior Valor:");
//    biggestAmountFraudTransactions
//        .forEach(f -> println(df.format(f)));
//    println("3. Clientes suspeitos:");
//    mostSuspiciousCustomers.forEach(System.out::println);
//    println("4. Prejuízo Total: " + df.format(totalFraudAmount));
//    println("5. Fraudes por Tipo: ");
//    totalFraudsByType.forEach((type, count) -> println(" - " + type + ": " + count));

      TransactionListRepository transactionRepository = new TransactionListRepository();

    transactionRepository.printTransaction("C12345");
    transactionRepository.printTransaction("C1231006815");

  }
}
