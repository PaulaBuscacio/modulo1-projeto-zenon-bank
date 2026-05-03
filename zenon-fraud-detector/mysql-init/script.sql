use transaction;

CREATE TABLE transaction
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    step        INT NOT NULL,
    type        ENUM('CASH_IN', 'CASH_OUT', 'DEBIT', 'PAYMENT', 'TRANSFER') NOT NULL,
    amount      DECIMAL(10, 2) NOT NULL,
    nameOrig    VARCHAR(255) NOT NULL,
    oldBalanceOrig DECIMAL(10, 2) NOT NULL,
    newBalanceOrig DECIMAL(10, 2) NOT NULL,
    nameDest    VARCHAR(255) NOT NULL,
    oldBalanceDest DECIMAL(10, 2) NOT NULL,
    newBalanceDest DECIMAL(10, 2) NOT NULL,
    isFraud     BOOLEAN NOT NULL,
    isFlaggedFraud BOOLEAN NOT NULL
)