package br.com.zenon.fraud.infra;

import br.com.zenon.fraud.domain.Transaction;
import br.com.zenon.fraud.domain.TransactionTypeEnum;
import br.com.zenon.fraud.domain.vo.TransactionCustomer;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class TransactionSQLRepository implements TransactionRepository {
  private static final int CHUNK_SIZE = 5000; // definindo o tamanho do chunk
  @Override
  public Long getSearchTimeNameOrigiLastTransaction(String fileName, Integer limit) {
    return 0L;
  }

  @Override
  public Transaction findByOriginName(String originName) {

    String sql = "select id,step, type, amount, nameOrig, oldBalanceOrig, newBalanceOrig, nameDest, oldBalanceDest, newBalanceDest, isFraud, isFlaggedFraud" +
        " from transaction where nameOrig = ?";
    try (Connection conn =
             DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction?useSSL=false&allowPublicKeyRetrieval=true", System.getenv("db_user"), System.getenv("db_pass"));
         PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, originName);

      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          return new Transaction(
              rs.getLong("id"),
              rs.getInt("step"),
              TransactionTypeEnum.valueOf(rs.getString("type")),
              rs.getBigDecimal("amount"),
              new TransactionCustomer(
                  rs.getString("nameOrig"),
                  rs.getBigDecimal("oldBalanceOrig"),
                  rs.getBigDecimal("newBalanceOrig")
              ),
              new TransactionCustomer(
                  rs.getString("nameDest"),
                  rs.getBigDecimal("oldBalanceDest"),
                  rs.getBigDecimal("newBalanceDest")
              ),
              rs.getBoolean("isFraud"),
              rs.getBoolean("isFlaggedFraud")
          );
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Transaction error: " + e.getMessage(), e);
    }

    throw new RuntimeException("Transação não encontrada para o nome de origem: " + originName);
  }

  @Override
  public Transaction save(Transaction transaction) {
    String sql = "insert into transaction (step, type, amount, nameOrig, oldBalanceOrig, newBalanceOrig, nameDest, oldBalanceDest, newBalanceDest, isFraud, isFlaggedFraud) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn =
             DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction?useSSL=false&allowPublicKeyRetrieval=true", System.getenv("db_user"), System.getenv("db_pass"));
         PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, transaction.step());
      ps.setString(2, transaction.type().name());
      ps.setBigDecimal(3, transaction.amount());
      ps.setString(4, transaction.origin().name());
      ps.setBigDecimal(5, transaction.origin().oldBalance());
      ps.setBigDecimal(6, transaction.origin().newBalance());
      ps.setString(7, transaction.recipient().name());
      ps.setBigDecimal(8, transaction.recipient().oldBalance());
      ps.setBigDecimal(9, transaction.recipient().newBalance());
      ps.setBoolean(10, transaction.isFraud());
      ps.setBoolean(11, transaction.isFlaggedFraud());
      ps.execute();

    } catch (SQLException e) {
      throw new RuntimeException("Transaction error: " + e.getMessage(), e);
    }
    return null;
  }

  public void saveAll(List<Transaction> transactions)  {

    HikariConfig config = new HikariConfig();
    config.setJdbcUrl("jdbc:mysql://localhost:3306/transaction?useSSL=false&allowPublicKeyRetrieval=true");
    config.setUsername(System.getenv("db_user"));
    config.setPassword(System.getenv("db_pass"));
    config.setMaximumPoolSize(20); // definindo o máximo de conexões no pool
    config.setMinimumIdle(1);
    config.setConnectionTimeout(50000);
    DataSource dataSource = new HikariDataSource(config);


    String sql = "insert into transaction (step, type, amount, nameOrig, oldBalanceOrig, newBalanceOrig, nameDest, oldBalanceDest, newBalanceDest, isFraud, isFlaggedFraud) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection connection = dataSource.getConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
      connection.setAutoCommit(false);
      int count = 0;

      for (Transaction transaction : transactions) {
        ps.setInt(1, transaction.step());
        ps.setString(2, transaction.type().name());
        ps.setBigDecimal(3, transaction.amount());
        ps.setString(4, transaction.origin().name());
        ps.setBigDecimal(5, transaction.origin().oldBalance());
        ps.setBigDecimal(6, transaction.origin().newBalance());
        ps.setString(7, transaction.recipient().name());
        ps.setBigDecimal(8, transaction.recipient().oldBalance());
        ps.setBigDecimal(9, transaction.recipient().newBalance());
        ps.setBoolean(10, transaction.isFraud());
        ps.setBoolean(11, transaction.isFlaggedFraud());
        ps.addBatch();

        count++;
        if (count % CHUNK_SIZE == 0) {
          ps.executeBatch();
          connection.commit();
          ps.clearBatch();
        }
      }

      if (count % CHUNK_SIZE != 0) {
        ps.executeBatch();
        connection.commit();
        ps.clearBatch();
      }
    } catch (SQLException e) {
      throw new RuntimeException("Transaction error: " + e.getMessage(), e);
    }
  }

  public void deleteAll() {
    String sql = "truncate table transaction";
    try (Connection conn =
             DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction?useSSL=false&allowPublicKeyRetrieval=true", System.getenv("db_user"), System.getenv("db_pass"));
         PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.execute();
    } catch (SQLException e) {
      throw new RuntimeException("Transaction error: " + e.getMessage(), e);
    }
  }
}
