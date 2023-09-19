package org.example.dbServices;

import org.example.entities.CardAccount;
import org.example.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBCardService {
//    public static final Logger LOG= Logger.getLogger(DBCardService.class.getName());
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public DBCardService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertCard(CardAccount card, String email){
        int resultInsert = 0;
        try {
            resultInsert = jdbcTemplate.update(DBQuery.INSERT_CARD,
                                               card.getNumber(),
                                               card.getBalance(),
                                               card.getValidityPeriod(),
                                               card.getPassword(),
                                               Statuses.UNBLOCK,
                                               email);
        } catch (DataAccessException ignored) {}
        return resultInsert;
    }
//
//    public int updateCardStatus(String number, int status, String passwordCard) throws DBException {
//        Connection con = null;
//        PreparedStatement stmt = null;
//        int result = 0;
//        if (passwordCard.equals("false") || equalsCardPassword(number, passwordCard)) {
//            final String queryMySQL = "UPDATE cards SET cards.status_id=? WHERE cards.number=?";
//            try {
//                con = DataSource.getConnection();
//                stmt = con.prepareStatement(queryMySQL, Statement.RETURN_GENERATED_KEYS);
//                stmt.setInt(1, status);
//                stmt.setString(2, number);
//                int insertAmount = stmt.executeUpdate();
//                if (insertAmount > 0) {
//                    result = 1;
//                }
//            } catch (SQLException e) {
//                LOG.info("SQLException in updateCardStatus method");
//                return result;
//            } finally {
//                DBCloseService.close(stmt);
//                DBCloseService.close(con);
//            }
//        } else {
//            LOG.info("Card password not equals in updateCardStatus method");
//            result = 2;
//        }
//        return result;
//    }
//
    public List<CardAccount> findAllCardForClient(String email) {
        return jdbcTemplate.query(DBQuery.FIND_ALL_CARD_FOR_CLIENT, new Object[]{email}, new BeanPropertyRowMapper<>(CardAccount.class));
    }
//
//    public List<CardAccount> findAllCardClientForStatus(String email, int status) throws DBException {
//        List<CardAccount> cards = new ArrayList<>();
//        CardAccount card = null;
//        String query = "SELECT * FROM cards WHERE cards.client_id=? AND cards.status_id=?";
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query);) {
//            stmt.setString(1, email);
//            stmt.setInt(2, status);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    card = new CardAccount(rs.getString(Fields.CARD_NUMBER), rs.getDouble(Fields.CARD_BALANCE), rs.getString(Fields.CARD_VALIDITY_PERIOD));
//                    cards.add(card);
//                }
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in findAllCardClientForStatus method");
//            return null;
//        }
//        return cards;
//    }
//
//    public List<CardAccount> findAllCardForRequestAdmin(int status, String orderBy, String typeSort, int limit, int offset) {
//        List<CardAccount> cards = new ArrayList<>();
//        CardAccount card = null;
//        String query = "SELECT * FROM cards INNER JOIN request_admin ON cards.number = request_admin.card_number " +
//                "WHERE request_admin.status_admin=?  ORDER BY " + orderBy + " " + typeSort + " limit ? offset ?";
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query);) {
//            stmt.setInt(1, status);
//            stmt.setInt(2, limit);
//            stmt.setInt(3, offset);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    card = new CardAccount(rs.getString(Fields.CARD_NUMBER), rs.getDouble(Fields.CARD_BALANCE), rs.getString(Fields.CARD_VALIDITY_PERIOD));
//                    cards.add(card);
//                }
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in findAllCardForRequestAdmin method");
//            return null;
//        }
//        return cards;
//    }
//
//    public List<CardAccount> findAllCardForRequestAdmin(int status) {
//        List<CardAccount> cards = new ArrayList<>();
//        CardAccount card = null;
//        String query = "SELECT * FROM cards INNER JOIN request_admin ON cards.number = request_admin.card_number " +
//                "WHERE request_admin.status_admin = ?";
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query);) {
//            stmt.setInt(1, status);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    card = new CardAccount(rs.getString(Fields.CARD_NUMBER), rs.getDouble(Fields.CARD_BALANCE), rs.getString(Fields.CARD_VALIDITY_PERIOD));
//                    cards.add(card);
//                }
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in findAllCardForRequestAdmin method");
//            return null;
//        }
//        return cards;
//    }
//
//    public int findCountAllCard() {
//        int countCard = 0;
//        String query = "SELECT count(*) AS count FROM cards INNER JOIN statuses ON cards.status_id = statuses.id";
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query);) {
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    countCard = rs.getInt(Fields.COUNT);
//                }
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in findCountAllCard method");
//            return -1;
//        }
//        return countCard;
//    }
//
//    public List<CardAccount> findAllCardWithLimit(String orderBy, String typeSort, int limit, int offset) {
//        List<CardAccount> cards = new ArrayList<>();
//        CardAccount card = null;
//        String query = "SELECT * FROM cards INNER JOIN statuses ON cards.status_id = statuses.id " +
//                "ORDER BY " + orderBy + " " + typeSort + " limit ? offset ?";
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query);) {
//            stmt.setInt(1, limit);
//            stmt.setInt(2, offset);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    card = new CardAccount(rs.getString(Fields.CARD_NUMBER), rs.getDouble(Fields.CARD_BALANCE), rs.getString(Fields.CARD_VALIDITY_PERIOD));
//                    card.setStatus(rs.getString(Fields.NAME));
//                    cards.add(card);
//                }
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in findAllCardWithLimit method");
//            return null;
//        }
//        return cards;
//    }
//
    public CardAccount getCardInfo(String number) {
        return jdbcTemplate.query(DBQuery.GET_CARD_INFO, new Object[]{number}, new BeanPropertyRowMapper<>(CardAccount.class))
                .stream().findAny().orElse(null);
    }
//
//    public int getRequestAdmin(String number, int status) {
//        int count = 0;
//        String query = "SELECT * FROM request_admin WHERE card_number=? AND status_admin=?";
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query);) {
//            stmt.setString(1, number);
//            stmt.setInt(2, status);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    count = 1;
//                }
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in getRequestAdmin method");
//            return -1;
//        }
//        return count;
//    }
//
//    public int insertRequestAdmin(String number, int status, String passwordCard) throws DBException {
//        Connection con = null;
//        PreparedStatement stmt = null;
//        int result = 0;
//        if (passwordCard.equals("false") || equalsCardPassword(number, passwordCard)) {
//            final String queryMySQL = "INSERT INTO request_admin (card_number, status_id, status_admin) values (?, ?, ?)";
//            try {
//                con = DataSource.getConnection();
//                stmt = con.prepareStatement(queryMySQL, Statement.RETURN_GENERATED_KEYS);
//                stmt.setString(1, number);
//                stmt.setInt(2, status);
//                stmt.setInt(3, Statuses.NEW);
//                int insertAmount = stmt.executeUpdate();
//                if (insertAmount > 0) {
//                    result = 1;
//                }
//            } catch (SQLException e) {
//                LOG.info("SQLException in insertRequestAdmin method");
//                return result;
//            } finally {
//                new DBCloseService().close(stmt);
//                new DBCloseService().close(con);
//            }
//        } else result = 2;
//        return result;
//    }
//
//    public int updateRequestAdmin(int cardNumber, int statusRequest) throws DBException {
//        Connection con = null;
//        PreparedStatement stmt = null;
//        int result = 0;
//        final String queryMySQL = "UPDATE request_admin SET status_admin=? WHERE card_number=?";
//        try {
//            con = DataSource.getConnection();
//            stmt = con.prepareStatement(queryMySQL, Statement.RETURN_GENERATED_KEYS);
//            stmt.setInt(1, statusRequest);
//            stmt.setInt(2, cardNumber);
//            int insertAmount = stmt.executeUpdate();
//            if (insertAmount > 0) {
//                result = 1;
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in updateRequestAdmin method");
//            return result;
//        } finally {
//            new DBCloseService().close(stmt);
//            new DBCloseService().close(con);
//        }
//        return result;
//    }
//
//    public int cardBalanceChange(String cardNumber, double newBalance, String passwordCard, boolean passwordСheck) throws DBException {
//        Connection con = null;
//        PreparedStatement stmt = null;
//
//        int result = 0;
//        if (passwordСheck == false || equalsCardPassword(cardNumber, passwordCard)) {
//            String query = "UPDATE cards c SET c.balance=? WHERE c.number=?";
//            try {
//                con = DataSource.getConnection();
//                stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//                new DBCloseService().setAutocommit(con, false);
//
//                stmt.setDouble(1, newBalance);
//                stmt.setString(2, cardNumber);
//                if (stmt.executeUpdate() > 0) result = 1;
//
//            } catch (SQLException e) {
//                LOG.info("SQLException in cardBalanceChange method");
//                new DBCloseService().rollBack(con);
//            } finally {
//                new DBCloseService().setAutocommit(con, true);
//                new DBCloseService().close(stmt);
//                new DBCloseService().close(con);
//            }
//        } else result = 2;
//        return result;
//    }
//
//    public boolean equalsCardPassword(String number, String password) {
//        boolean result = false;
//        String passwordCard;
//        String query = "SELECT * FROM cards  WHERE cards.number=?";
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query);) {
//            stmt.setString(1, number);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    passwordCard = rs.getString(Fields.PASSWORD);
//                    if (passwordCard.equals(password)) {
//                        result = true;
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in equalsCardPassword method");
//            return result;
//        }
//        return result;
//    }
}
