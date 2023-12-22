package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;
import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        // TODO: Implement the logic to add a sales contract
        String query = """
                INSERT INTO sales_contracts (contract_id, VIN, sale_date, price)
                VALUES (?, ?, ?, ?)""";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, salesContract.getContractId());
            preparedStatement.setString(2, salesContract.getVin());
            preparedStatement.setDate(3, Date.valueOf(salesContract.getSaleDate()));
            preparedStatement.setDouble(4, salesContract.getPrice());

            int rows = preparedStatement.executeUpdate();
            System.out.println("Sales contract added! Rows updated in the database: " + rows);

            carSold(salesContract.getVin());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void carSold(String vin) {
        String query = "UPDATE vehicles SET SOLD = ? WHERE VIN = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, vin);

            preparedStatement.executeUpdate();
            System.out.println("waaaaaa! congrats on using money");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
