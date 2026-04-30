package br.com.zenon.fraud;

import br.com.zenon.fraud.useCase.TransactionReport;

import static java.lang.IO.println;

public class ReportMain {

  static void main() {
    TransactionReport transactionReport = new TransactionReport();
    String report = transactionReport.printReport("data/PS_20174392719_1491204439457_log.csv", null);
    System.out.println(report);

    //Benchmark extração de arquivo usando Files.readString vs Files.readAllLines
   String benchmark = transactionReport.benchmarkReport("data/PS_20174392719_1491204439457_log.csv", null);

   println("\n-----------------Files.readString vs Files.readAllLines--------------------------------------");

   System.out.println(benchmark);
  }
}
