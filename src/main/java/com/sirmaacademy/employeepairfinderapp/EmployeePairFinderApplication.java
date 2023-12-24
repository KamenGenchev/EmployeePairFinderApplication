package com.sirmaacademy.employeepairfinderapp;

import com.sirmaacademy.employeepairfinderapp.csvreader.CsvDataPopulation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.*;

@SpringBootApplication
public class EmployeePairFinderApplication {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "akame");
            statement = connection.createStatement();
            statement.executeQuery("SELECT count(*) FROM pg_database WHERE datname ='pairfinderdb' ");
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count < 0) {
                statement.executeUpdate("CREATE DATABASE pairfinderdb");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
            }
        }

        ConfigurableApplicationContext context = SpringApplication.run(EmployeePairFinderApplication.class, args);
        CsvDataPopulation csvDataPopulation = context.getBean(CsvDataPopulation.class);
        csvDataPopulation.getData();
    }

}
