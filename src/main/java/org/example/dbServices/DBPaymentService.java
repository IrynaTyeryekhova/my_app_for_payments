package org.example.dbServices;

import org.example.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DBPaymentService {
//    public static final Logger LOG= Logger.getLogger(DBPaymentService.class.getName());
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public DBPaymentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertPayment(Payment payment, String email, String number){
        int result = 0;
        try {
            result = jdbcTemplate.update(DBQuery.INSERT_PAYMENT, LocalDate.now().toString(),
                                                                 payment.getSum(),
                                                                 payment.getPurposePayment(),
                                                                 email,
                                                                 number,
                                                                 Statuses.PREPARED);
        } catch (DataAccessException ignored) {}
        return result;
    }
//
//    public int updatePaymentStatus(int id, int status) throws DBException {
//        Connection con = null;
//        PreparedStatement stmt = null;
//        int result = 0;
//        final String queryMySQL = "UPDATE payments SET status_id=? WHERE id=?";
//        try {
//            con = DataSource.getConnection();
//            stmt = con.prepareStatement(queryMySQL, Statement.RETURN_GENERATED_KEYS);
//            stmt.setInt(1, status);
//            stmt.setInt(2, id);
//            int insertAmount = stmt.executeUpdate();
//            if (insertAmount > 0) {
//                result = 1;
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in updatePaymentStatus method");
//            return result;
//        } finally {
//            new DBCloseService().close(stmt);
//            new DBCloseService().close(con);
//        }
//        return result;
//    }
//
    public int getCountAllPaymentsForCard(String number) {
        int paymentCount = -1;
        try {
            paymentCount = jdbcTemplate.queryForObject(DBQuery.GET_COUNT_ALL_PAYMENTS_FOR_CARD, new Object[]{number}, Integer.class);
        } catch (DataAccessException ignored) {}
        return paymentCount;
    }

    public List<Payment> findAllPaymentsForCardWithLimit(String cardNumber, String orderBy, String typeSort, int limit, int offset) {
        List<Payment> payments;
        try {
            payments = jdbcTemplate.query("SELECT * FROM payments " +
                                              "INNER JOIN statuses " +
                                              "ON payments.status = statuses.id " +
                                              "WHERE cardNumber=? ORDER BY " +
                                              orderBy + " " + typeSort +
                                              " limit ? offset ?",
                                              new Object[]{cardNumber, limit, offset},
                                              new BeanPropertyRowMapper<>(Payment.class));
        } catch (DataAccessException ignored) {
            return null;
        }
        return payments;
    }

    public List<Payment> findAllPaymentsForStatusWithLimit(String email, int status, String orderBy, String typeSort, int limit, int offset) {
        List<Payment> payments;
        try {
            payments = jdbcTemplate.query("SELECT * FROM payments " +
                                              "INNER JOIN statuses " +
                                              "ON payments.status = statuses.id " +
                                              "WHERE clientEMail=? " +
                                              "AND status=? " +
                                              "ORDER BY " +
                                              orderBy + " " + typeSort +
                                              " limit ? offset ?",
                                              new Object[]{email, status, limit, offset},
                                              new BeanPropertyRowMapper<>(Payment.class));
        } catch (DataAccessException ignored) {return null;}
        return payments;
    }

    public List<Payment> findAllPaymentsForClientWithLimit(String email, String orderBy, String typeSort, int limit, int offset) {
        List<Payment> payments;
        try {
            payments = jdbcTemplate.query("SELECT * FROM payments " +
                            "INNER JOIN statuses " +
                            "ON payments.status = statuses.id " +
                            "WHERE clientEMail=? ORDER BY " +
                            orderBy + " " + typeSort +
                            " limit ? offset ?",
                    new Object[]{email, limit, offset},
                    new BeanPropertyRowMapper<>(Payment.class));
        } catch (DataAccessException ignored) {
            return null;
        }
        return payments;
    }

    public int getCountAllPaymentsForClient(String email) {
        int paymentCount = -1;
        try {
            paymentCount = jdbcTemplate.queryForObject(DBQuery.GET_COUNT_ALL_PAYMENTS_FOR_CLIENT, new Object[]{email}, Integer.class);
        } catch (DataAccessException ignored) {}
        return paymentCount;
    }

    public int getCountAllPaymentsForStatus(String email, int status) {
        int paymentCount = -1;
        try {
            paymentCount = jdbcTemplate.queryForObject(DBQuery.GET_COUNT_ALL_PAYMENTS_FOR_STATUS, new Object[]{email, status}, Integer.class);
        } catch (DataAccessException ignored) {}
        return paymentCount;
    }
//
//    public Payment getPaymentInfo(int id) {
//        Payment payment = null;
//        String query = "SELECT * FROM payments INNER JOIN statuses ON payments.status_id = statuses.id WHERE payments.id=?";
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query);) {
//            stmt.setInt(1, id);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    payment = new Payment(rs.getString(Fields.PAYMENT_DATE), rs.getDouble(Fields.PAYMENT_SUM), rs.getString(Fields.PAYMENT_PURPOSE), rs.getString(Fields.PAYMENT_CLIENT), rs.getString(Fields.PAYMENT_CARD), rs.getString(Fields.STATUS_NAME));
//                }
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in getPaymentInfo method");
//            return payment;
//        }
//        return payment;
//    }
}
