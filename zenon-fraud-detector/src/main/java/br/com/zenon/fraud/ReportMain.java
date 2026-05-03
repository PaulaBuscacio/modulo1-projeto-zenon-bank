package br.com.zenon.fraud;

import br.com.zenon.fraud.useCase.TransactionReport;

import java.util.Locale;

public class ReportMain {

  static void main() {

    TransactionReport transactionReport = new TransactionReport();
    String report = transactionReport.printReport("data/PS_20174392719_1491204439457_log.csv", null, null);
    System.out.println(report);
    String reportUS = transactionReport.printReport("data/PS_20174392719_1491204439457_log.csv", null, Locale.US);
    System.out.println(reportUS);
    String reportBR = transactionReport.printReport("data/PS_20174392719_1491204439457_log.csv", null, Locale.of("pt", "BR"));
    System.out.println(reportBR);

  }
}