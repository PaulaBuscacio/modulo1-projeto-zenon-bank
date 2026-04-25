package br.com.zenon.fraud;

public enum TransactionTypeEnum {

  CASH_IN ("CASH_IN"),
  CASH_OUT ("CASH_OUT"),
  DEBIT ("DEBIT"),
  PAYMENT ("PAYMENT");

 private String type;

 TransactionTypeEnum(String type){
   this.type=type;
 }

}
