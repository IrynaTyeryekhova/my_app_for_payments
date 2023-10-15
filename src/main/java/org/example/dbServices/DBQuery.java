package org.example.dbServices;

public class DBQuery {
    public static final String GET_CLIENT_INFO = "SELECT * FROM clients " +
            "INNER JOIN roles INNER JOIN statuses ON clients.role=roles.id AND clients.status=statuses.id  " +
            "WHERE clients.eMail=?";
    public static final String INSERT_CLIENT = "INSERT INTO clients (eMail, password, userName, role, status) values (?, ?, ?, ?, ?);";
    public static final String FIND_ALL_CARD_FOR_CLIENT = "SELECT * FROM cards WHERE cards.client=?";
    public static final String FIND_ALL_CARD_FOR_CLIENT_WITH_STATUS = "SELECT * FROM cards WHERE cards.client=? AND cards.statusCard=?";
    public static final String GET_CARD_INFO = "SELECT * FROM cards INNER JOIN statuses ON cards.statusCard = statuses.id WHERE cards.number=?";
    public static final String INSERT_CARD = "INSERT INTO cards (number, balance, validityPeriod, password, statusCard, client) values (?, ?, ?, ?, ?, ?)";
    public static final String CARD_BALANCE_CHANGE = "UPDATE cards c SET c.balance=? WHERE c.number=?";
    public static final String CARD_STATUS_CHANGE = "UPDATE cards c SET c.statusCard=? WHERE c.number=?";
    public static final String GET_REQUEST_ADMIN = "SELECT count(*) AS count FROM request_admin WHERE card=? AND statusAdmin=?";
    public static final String INSERT_REQUEST_ADMIN = "INSERT INTO request_admin (card, cardStatus, statusAdmin) values (?, ?, ?)";
    public static final String GET_COUNT_ALL_PAYMENTS_FOR_CARD = "SELECT count(*) AS count FROM payments WHERE cardNumber=?";
    public static final String GET_COUNT_ALL_PAYMENTS_FOR_CLIENT = "SELECT count(*) AS count FROM payments WHERE clientEMail=?";
    public static final String FIND_ALL_PAYMENTS_FOR_CARD_WITH_LIMIT = "SELECT * FROM payments INNER JOIN statuses ON payments.status = statuses.id WHERE cardNumber=? ORDER BY ? " + " " + "?" + " " + " limit ? offset ?";
}
