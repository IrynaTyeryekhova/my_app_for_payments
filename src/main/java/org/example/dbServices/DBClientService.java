package org.example.dbServices;

import org.example.entities.Client;
import org.example.services.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBClientService {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public DBClientService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//    static DBClientService instance;
//    public static final Logger LOG= Logger.getLogger(DBClientService.class.getName());
//    private DBClientService() {
//    }
//    public static synchronized DBClientService getInstance() {
//        if (instance == null) {
//            instance = new DBClientService();
//        }
//        return instance;
//    }
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
//
//    public List<Client> findAllClientsForRole(int role) {
//        List<Client> clients = new ArrayList<>();
//        Client client = null;
//        String query = "SELECT * FROM clients WHERE clients.role_id = ?";
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query);) {
//            stmt.setInt(1, role);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    client = new Client(rs.getString(Fields.E_MAIL), rs.getString(Fields.NAME));
//                    clients.add(client);
//                }
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in findAllClientsForRole method");
//            return null;
//        }
//        return clients;
//    }
//
//    public List<Client> findAllClientsForRole(int role, String orderBy, String typeSort, int limit, int offset) {
//        List<Client> clients = new ArrayList<>();
//        Client client = null;
//        String query = "SELECT * FROM clients INNER JOIN statuses ON clients.status_id=statuses.id " +
//                "WHERE clients.role_id = ? ORDER BY "
//                + orderBy + " " + typeSort + " limit ? offset ?";
//        try (Connection con = DataSource.getConnection();
//             PreparedStatement stmt = con.prepareStatement(query);) {
//            stmt.setInt(1, role);
//            stmt.setInt(2, limit);
//            stmt.setInt(3, offset);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    client = new Client(rs.getString(Fields.E_MAIL), rs.getString(Fields.NAME));
//                    client.setStatus(rs.getString(Fields.STATUS_NAME));
//                    clients.add(client);
//                }
//            }
//        } catch (SQLException e) {
//            LOG.info("SQLException in findAllClientsForRole method");
//        }
//        return clients;
//    }


}
