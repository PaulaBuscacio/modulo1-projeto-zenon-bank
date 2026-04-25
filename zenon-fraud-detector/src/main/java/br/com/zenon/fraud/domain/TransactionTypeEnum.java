package br.com.zenon.fraud.domain;

public enum TransactionTypeEnum {

  CASH_IN ("CASH_IN"),
  CASH_OUT ("CASH_OUT"),
  DEBIT ("DEBIT"),
  PAYMENT ("PAYMENT"),
  TRANSFER("TRANSFER");

 private String type;

 TransactionTypeEnum(String type){
   this.type=type;
 }

}
