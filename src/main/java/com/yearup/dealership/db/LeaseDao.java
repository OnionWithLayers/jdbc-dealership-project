package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        // TODO: Implement the logic to add a lease contract
        String query = """
                INSERT INTO lease_contracts (contract_id, VIN, lease_start, lease_end, monthly_payment)
                VALUES (?, ?, ?, ?, ?)""";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, leaseContract.getContractId());
            preparedStatement.setString(2, leaseContract.getVin());
            preparedStatement.setDate(3, Date.valueOf(leaseContract.getLeaseStart()));
            preparedStatement.setDate(4, Date.valueOf(leaseContract.getLeaseEnd()));
            preparedStatement.setDouble(5, leaseContract.getMonthlyPayment());

            int row = preparedStatement.executeUpdate();

            System.out.println("Lease contract successfully recorded! Rows updated in database: " + row);

            carSold(leaseContract.getVin());
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
