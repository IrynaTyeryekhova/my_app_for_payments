package org.example.dbServices;

import org.example.entities.CardAccount;
import org.example.entities.Client;
import org.example.entities.Payment;
import org.example.services.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBClientService {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public DBClientService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Client getClientInfo(String email) {
        return jdbcTemplate.query(DBQuery.GET_CLIENT_INFO, new Object[]{email}, new BeanPropertyRowMapper<>(Client.class))
            .stream().findAny().orElse(null);
}

    public int insertClient(Client client) {
        int resultInsert = 0;
        try {
            resultInsert = jdbcTemplate.update(DBQuery.INSERT_CLIENT, client.geteMail(), client.getPassword(), client.getuserName(), Roles.USER, Statuses.UNBLOCK);
        } catch (DataAccessException ignored) {}
        return resultInsert;
    }
//
//    public int updateClientStatus(String email, int status) throws DBException {
//        Connection con = null;
//        PreparedStatement stmt = null;
//        int result = 0;
//        final String queryMySQL = "UPDATE clients SET clients.status_id=? WHERE clients.e_mail=?";
//        try {
//            con = DataSource.getConnection();
//            stmt = con.prepareStatement(queryMySQL, Statement.RETURN_GENERATED_KEYS);
//            stmt.setInt(1, status);
//            stmt.setString(2, email);
//            int insertAmount = stmt.executeUpdate();
//            if (insertAmount > 0) {
//                result = 1;
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in updateClientStatus method");
//            return result;
//        } finally {
//            new DBCloseService().close(stmt);
//            new DBCloseService().close(con);
//        }
//        return result;
//    }

    public List<Client> findAllClientsForRole(int role) {
        List<Client> clients;
        try {
            clients = jdbcTemplate.query(DBQuery.FIND_ALL_CLIENTS_FOR_ROLE,
                    new Object[]{role},
                    new BeanPropertyRowMapper<>(Client.class));
        } catch (DataAccessException ignored) {
            return null;
        }
        return clients;
    }

    public List<Client> findAllClientsForRoleWithLimit(int role, String orderBy, String typeSort, int limit, int offset) {
        List<Client> clients;
        try {
            clients = jdbcTemplate.query("SELECT * FROM clients " +
                                             "INNER JOIN statuses " +
                                             "ON clients.status=statuses.id " +
                                             "WHERE clients.role = ? ORDER BY "
                                             + orderBy + " " + typeSort + " limit ? offset ?",
                                             new Object[]{role, limit, offset},
                                             new BeanPropertyRowMapper<>(Client.class));
        } catch (DataAccessException ignored) {
            return null;
        }
        return clients;
    }
}
