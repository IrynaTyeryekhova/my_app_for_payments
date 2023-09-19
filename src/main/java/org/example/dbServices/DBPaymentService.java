package org.example.dbServices;

import org.example.connection.DataSource;
import org.example.entities.CardAccount;
import org.example.entities.Payment;
import org.example.services.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBPaymentService {
//    public static final Logger LOG= Logger.getLogger(DBPaymentService.class.getName());
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public DBPaymentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//    public int insertPayment(Double paymentSum, String purposePayment, String email, String number) throws DBException {
//        Connection con = null;
//        PreparedStatement stmt = null;
//        int result = 0;
//        final String queryMySQL = "INSERT INTO payments (date, sum, purpose_payment, client_id, card_number, status_id) values (?, ?, ?, ?, ?, ?)";
//        try {
//            con = DataSource.getConnection();
//            stmt = con.prepareStatement(queryMySQL, Statement.RETURN_GENERATED_KEYS);
//            stmt.setString(1, LocalDate.now().toString());
//            stmt.setDouble(2, paymentSum);
//            stmt.setString(3, purposePayment);
//            stmt.setString(4, email);
//            stmt.setString(5, number);
//            stmt.setInt(6, Statuses.PREPARED);
//
//            int insertAmount = stmt.executeUpdate();
//            if (insertAmount > 0) {
//                result = 1;
//                }
//        } catch (SQLException e) {
//            LOG.info("SQLException in insertPayment method");
//            return result;
//        } finally {
//            new DBCloseService().close(stmt);
//            new DBCloseService().close(con);
//        }
//        return result;
//    }
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

        //        String query = "SELECT count(*) AS count FROM payments WHERE card_number=?";
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query);) {
//            stmt.setString(1, number);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    paymentCount = (rs.getInt(Fields.COUNT));
//                }
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in getCountAllPaymentsForCard method");
//            return -1;
//        }
//        return paymentCount;
    }
    public List<Payment> findAllPaymentsForCardWithLimit(String cardNumber, String orderBy, String typeSort, int limit, int offset) {
        List<Payment> payments = new ArrayList<>();
        try {
            payments = jdbcTemplate.query("SELECT * FROM payments INNER JOIN statuses ON payments.status = statuses.id WHERE cardNumber=? ORDER BY " + orderBy + " " + typeSort + " limit ? offset ?" , new Object[]{cardNumber, limit, offset}, new BeanPropertyRowMapper<>(Payment.class));
        } catch (DataAccessException ignored) {
            return null;
        }
        return payments;
//        Payment payment = null;
//        String query = "SELECT * FROM payments INNER JOIN statuses ON payments.status_id = statuses.id WHERE card_number=? ORDER BY " + orderBy + " " + typeSort + " limit ? offset ?";
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query);) {
//            stmt.setString(1, cardNumber);
//            stmt.setInt(2, limit);
//            stmt.setInt(3, offset);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    payment = new Payment(rs.getString(Fields.PAYMENT_DATE), rs.getDouble(Fields.PAYMENT_SUM), rs.getString(Fields.PAYMENT_PURPOSE), rs.getString(Fields.PAYMENT_CLIENT), rs.getString(Fields.PAYMENT_CARD), rs.getString(Fields.STATUS_NAME));
//                    payment.setId(rs.getInt(Fields.PAYMENTS_ID));
//                    payments.add(payment);
//                }
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in findAllPaymentsForCardWithLimit method");
//            return null;
//        }
//        return payments;
    }
//
//    public List<Payment> findAllPaymentsForStatus(String email, int status, String orderBy, String typeSort, int limit, int offset) {
//        List<Payment> payments = new ArrayList<>();
//        Payment payment = null;
//        String query = "SELECT * FROM payments INNER JOIN statuses ON payments.status_id = statuses.id WHERE client_id=? AND status_id=? ORDER BY " + orderBy + " " + typeSort + " limit ? offset ?";
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query);) {
//            stmt.setString(1, email);
//            stmt.setInt(2, status);
//            stmt.setInt(3, limit);
//            stmt.setInt(4, offset);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    payment = new Payment(rs.getString(Fields.PAYMENT_DATE), rs.getDouble(Fields.PAYMENT_SUM), rs.getString(Fields.PAYMENT_PURPOSE), rs.getString(Fields.PAYMENT_CLIENT), rs.getString(Fields.PAYMENT_CARD), rs.getString(Fields.STATUS_NAME));
//                    payment.setId(rs.getInt(Fields.PAYMENTS_ID));
//                    payments.add(payment);
//                }
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in findAllPaymentsForStatus method");
//            return null;
//        }
//        return payments;
//    }
//
//    public ArrayList<Payment> findAllPayments(String email, String orderBy, String typeSort, int limit, int offset) {
//        ArrayList<Payment> payments = new ArrayList<>();
//        Payment payment = null;
//        String query = "SELECT * FROM payments INNER JOIN statuses ON payments.status_id = statuses.id WHERE client_id=? ORDER BY " + orderBy + " " + typeSort + " limit ? offset ?";
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query);) {
//            stmt.setString(1, email);
//            stmt.setInt(2, limit);
//            stmt.setInt(3, offset);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    payment = new Payment(rs.getString(Fields.PAYMENT_DATE), rs.getDouble(Fields.PAYMENT_SUM), rs.getString(Fields.PAYMENT_PURPOSE), rs.getString(Fields.PAYMENT_CLIENT), rs.getString(Fields.PAYMENT_CARD), rs.getString(Fields.STATUS_NAME));
//                    payment.setId(rs.getInt(Fields.PAYMENTS_ID));
//                    payments.add(payment);
//                }
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in findAllPayments method");
//            return null;
//        }
//        return payments;
//    }
//
//    public int getCountAllPaymentsForClient(String email) {
//        int paymentCount = 0;
//        String query = "SELECT count(*) AS count FROM payments WHERE client_id=?";
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query);) {
//            stmt.setString(1, email);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    paymentCount = (rs.getInt(Fields.COUNT));
//                }
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in getCountAllPaymentsForClient method");
//            return -1;
//        }
//        return paymentCount;
//    }
//
//    public int getCountAllPaymentsForStatus(String email, int status) {
//        int paymentCount = 0;
//        String query = "SELECT count(*) AS count FROM payments WHERE client_id=? AND status_id=?";
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query);) {
//            stmt.setString(1, email);
//            stmt.setInt(2, status);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    paymentCount = (rs.getInt(Fields.COUNT));
//                }
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in getCountAllPaymentsForStatus method");
//            return -1;
//        }
//        return paymentCount;
//    }
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
