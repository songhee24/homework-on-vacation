package com.company.exercise1;

import java.sql.*;
import java.util.Scanner;

public class Exercise1 {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String user = "postgres";
    private static final String password = "d244224d";
    public static void main(String[] args) throws SQLException {
        Exercise1 DB = new Exercise1();
        Connection connection = DB.connect();
        if(connection != null)connection.close();
        DB.printAllTrainers();
        DB.sumTrainersSalary();
        DB.trainersName();
    }
    public Connection connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("Connected to the PostgreSQL server successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void printAllTrainers(){
        String SQL = "select * \n" +
                "from trainers1 tt\n" +
                "join sport_types1 st on sport_type1 = st.id";

        try
                (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL))
        {
            while (rs.next()){
                String name = rs.getString("name");
                int salary = rs.getInt("salary");
                System.out.println(name +" "+ salary);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public void sumTrainersSalary(){
        String SQL = "select sum(st.salary) \n" +
                "from trainers1 tt\n" +
                "join sport_types1 st on sport_type1 = st.id";

        try
                (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL))
        {
            while (rs.next()){
                int sumSalary = rs.getInt("sum");
                System.out.println(sumSalary);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void trainersName(){
        String SQL = "select length(t2.\"name\") \n" +
                "from trainers1 t2\n" +
                "where length(name)>=6";

        try
                (Connection conn = connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL))
        {
            while (rs.next()){
                int sumSalary = rs.getInt("length");
                System.out.println(sumSalary + " молодец");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}