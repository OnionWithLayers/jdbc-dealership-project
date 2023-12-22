package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        // TODO: Implement the logic to add a vehicle to the inventory
        String query = "INSERT INTO inventory VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, dealershipId);
            preparedStatement.setString(2, vin);

            int row = preparedStatement.executeUpdate();

            System.out.println("Vehicle successfully added into the inventory! Rows updated in database: " + row);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeVehicleFromInventory(String vin) {
        // TODO: Implement the logic to remove a vehicle from the inventory
        String query = "DELETE FROM inventory WHERE VIN = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, vin);

            int row = preparedStatement.executeUpdate();

            System.out.println("Vehicle successfully removed from the inventory. Rows updated in the database: " + row);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
