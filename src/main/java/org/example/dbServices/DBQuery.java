package org.example.dbServices;

public class DBQuery {
    public static final String GET_CLIENT_INFO = "SELECT * FROM clients " +
            "INNER JOIN roles INNER JOIN statuses ON clients.role=roles.id AND clients.status=statuses.id  " +
            "WHERE clients.eMail=?";
    public static final String INSERT_CLIENT = "INSERT INTO clients (eMail, password, userName, role, status) values (?, ?, ?, ?, ?);";
}
