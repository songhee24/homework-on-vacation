package com.company.exercise2;

import java.sql.*;
import java.util.Scanner;

 class Exercise2 {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "d244224d";

    public static void main(String[] args) throws SQLException {
        Exercise2 DB = new Exercise2();
        Connection connection = DB.connect();
        if (connection != null) connection.close();
        DB.countStudents();
        DB.printAllGroup();

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

    public void countStudents() {
        String SQL = "select count(*) from students_2";

        try
                (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                String count = rs.getString("count");
                System.out.println("в группе "+count+ " студентов");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printAllGroup(){
        String SQL = "select id,\"name\" from groups_2 g2";
        try (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id");
                System.out.println(id + " " + name);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}

