package org.example.dbServices;

import org.example.entities.CardAccount;
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

    public int insertRequestAdmin(String number, int status) {
        int result = 0;
        try { result = jdbcTemplate.update(DBQuery.INSERT_REQUEST_ADMIN, number, status, Statuses.NEW);}
        catch ( DataAccessException ignored) {}
        return result;
    }

    public CardAccount getCardInfo(String number) {
        return jdbcTemplate.query(DBQuery.GET_CARD_INFO, new Object[]{number}, new BeanPropertyRowMapper<>(CardAccount.class))
                .stream().findAny().orElse(null);
    }

    public int getRequestAdmin(String number, int status) {
        int result;
        try { result = jdbcTemplate.queryForObject(DBQuery.GET_REQUEST_ADMIN, new Object[]{number, status}, Integer.class);}
        catch ( DataAccessException | NullPointerException e) { result = -1;}
        return result;
    }

    public int getCountAllCard() {
        int cardCount = -1;
        try {
            cardCount = jdbcTemplate.queryForObject(DBQuery.GET_COUNT_ALL_CARD, new Object[]{}, Integer.class);
        } catch (DataAccessException ignored) {}
        return cardCount;
    }

    public int getCountAllCardForRequestAdmin(int status) {
        int cardCount = -1;
        try {
            cardCount = jdbcTemplate.queryForObject(DBQuery.GET_COUNT_ALL_CARD_FOR_REQUEST_ADMIN, new Object[]{status}, Integer.class);
        } catch (NullPointerException | DataAccessException ignored) {}
        return cardCount;
    }

    public int cardBalanceChange(String cardNumber, double newBalance) {
        int result = 0;
        try {result = jdbcTemplate.update(DBQuery.CARD_BALANCE_CHANGE, newBalance, cardNumber);}
        catch (DataAccessException ignored) {}
        return result;
    }

    public int cardStatusChange(String number, int status) {
        int result = 0;
        try {result = jdbcTemplate.update(DBQuery.CARD_STATUS_CHANGE, status, number);}
        catch (DataAccessException ignored) { }
        return result;
    }

    public int requestAdminChange(String cardNumber, int statusRequest) {
        int result = 0;
        try {result = jdbcTemplate.update(DBQuery.REQUEST_ADMIN_CHANGE, statusRequest, cardNumber);}
        catch (DataAccessException ignored) {}
        return result;
    }

    public List<CardAccount> findAllCardForClient(String email) {
        return jdbcTemplate.query(DBQuery.FIND_ALL_CARD_FOR_CLIENT, new Object[]{email}, new BeanPropertyRowMapper<>(CardAccount.class));
    }

    public List<CardAccount> findAllCardForClientWithStatus(String email, int status) {
        List<CardAccount> cards;
        try {
            cards = jdbcTemplate.query(DBQuery.FIND_ALL_CARD_FOR_CLIENT_WITH_STATUS,
                                       new Object[]{email, status},
                                       new BeanPropertyRowMapper<>(CardAccount.class));
        } catch (DataAccessException ignored) {
            return null;
        }
        return cards;
    }

    public List<CardAccount> findAllCardForRequestAdminWithLimit(int status, String orderBy, String typeSort, int limit, int offset) {
        List<CardAccount> cards;
        try {
            cards = jdbcTemplate.query("SELECT * FROM cards " +
                                           "INNER JOIN request_admin " +
                                           "ON cards.number = request_admin.card " +
                                           "WHERE request_admin.statusAdmin=?  ORDER BY " + orderBy + " " + typeSort + " limit ? offset ?",
                                           new Object[]{status, limit, offset},
                                           new BeanPropertyRowMapper<>(CardAccount.class));
        } catch (DataAccessException ignored) {
            return null;
        }
        return cards;
    }

    public List<CardAccount> findAllCardWithLimit(String orderBy, String typeSort, int limit, int offset) {
        List<CardAccount> cards;
        try {
            cards = jdbcTemplate.query("SELECT * FROM cards " +
                                           "INNER JOIN statuses " +
                                           "ON cards.statusCard = statuses.id " +
                                           "ORDER BY " + orderBy + " " +
                                           typeSort + " limit ? offset ?",
                                           new Object[]{limit, offset},
                                           new BeanPropertyRowMapper<>(CardAccount.class));
        } catch (DataAccessException ignored) {
            return null;
        }
        return cards;
    }







//

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
