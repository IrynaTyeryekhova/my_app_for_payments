package org.example.entities;

public class CardAccount {
    private String number;
    private double balance;
    private String validityPeriod;
    private String password;
    private String statusCard;
    private String client;
    private int id;
    public CardAccount() {}

    public CardAccount(String number, double balance, String validilyPeriod) {
        this.number = number;
        this.balance = balance;
        this.validityPeriod = validilyPeriod;
    }

    public CardAccount(String number, double balance, String validilyPeriod, String password) {
        this.number = number;
        this.balance = balance;
        this.validityPeriod = validilyPeriod;
        this.password = password;
    }

    public CardAccount(String number, double balance, String validilyPeriod, String password, String statusCard, String client) {
        this.number = number;
        this.balance = balance;
        this.validityPeriod = validilyPeriod;
        this.password = password;
        this.statusCard = statusCard;
        this.client = client;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(String validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatusCard() {
        return statusCard;
    }

    public void setStatusCard(String statusCard) {
        this.statusCard = statusCard;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

