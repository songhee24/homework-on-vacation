package com.company.exercise3;

import java.sql.*;
import java.util.Scanner;

class Exercise3 {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "d244224d";

    public static void main(String[] args) throws SQLException {
        Exercise3 DB = new Exercise3();
        Connection connection = DB.connect();
        if (connection != null) connection.close();
        DB.printCityWithLetterC();
        DB.printAllPopulation();
        DB.addLanguage();
        connection.close();

    }

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void printCityWithLetterC() {
        String SQL = "select * from cities ci\n" +
                "join countries c2 on country_id = c2.id\n" +
                "where ci.\"name\" like 'C%'";
        try
                (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                String K = rs.getString("name");
                System.out.println(K);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printAllPopulation(){
        String SQL = "select sum(c2.population) from countries c2;";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                int population = rs.getInt("sum");
                System.out.println(population);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showCountries(){
        String SQL = "select * from countries";
        try
                (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                String K = rs.getString("language_county");
                System.out.println(K);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addLanguage() throws SQLException {
        String SQL = "update countries c2 \n" +
                "set language_county = 'rus' \n" +
                "where c2.id = 2;";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
        } catch (SQLException e) {
            Exercise3 DB = new Exercise3();
            Connection connection = DB.connect();
            DB.showCountries();
            connection.close();
        }
    }

}
