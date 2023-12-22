package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        // TODO: Implement the logic to add a vehicle
        String addVehicleQuery = """
        INSERT INTO vehicles (VIN, make, model, year, SOLD, color, vehicleType, odometer, price)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)""";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addVehicleQuery)) {
            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setInt(4, vehicle.getYear());
            preparedStatement.setInt(5, 0);
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setString(7, vehicle.getVehicleType());
            preparedStatement.setInt(8, vehicle.getOdometer());
            preparedStatement.setDouble(9, vehicle.getPrice());

            int row = preparedStatement.executeUpdate();

            System.out.println("Rows updated: " + row);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeVehicle(String VIN) {
        // TODO: Implement the logic to remove a vehicle
        String removeVehicleQuery = "DELETE FROM vehicles WHERE VIN = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(removeVehicleQuery)) {
            preparedStatement.setString(1, VIN);

            int rows = preparedStatement.executeUpdate();
            System.out.println("Vehicle successfully removed! Rows updated in database: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        // TODO: Implement the logic to search vehicles by price range

        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);

            boolean carSold;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                do {
                    String vin = resultSet.getString(1);
                    String make = resultSet.getString(2);
                    String model = resultSet.getString(3);
                    int year = resultSet.getInt(4);
                    int sold = resultSet.getInt(5);
                    String color = resultSet.getString(6);
                    String vehicleType = resultSet.getString(7);
                    int odometer = resultSet.getInt(8);
                    double price = resultSet.getDouble(9);
                    if (sold == 0) {
                        carSold = false;
                    } else {
                        carSold = true;
                    }
                    Vehicle vehicle = new Vehicle(vin, make, model, year, carSold, color, vehicleType,
                            odometer, price);
                    vehicles.add(vehicle);
                } while (resultSet.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        // TODO: Implement the logic to search vehicles by make and model

        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE make = ? AND model = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            boolean carSold;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    do {
                        String vin = resultSet.getString(1);
                        String carMake = resultSet.getString(2);
                        String carModel = resultSet.getString(3);
                        int year = resultSet.getInt(4);
                        int sold = resultSet.getInt(5);
                        String color = resultSet.getString(6);
                        String vehicleType = resultSet.getString(7);
                        int odometer = resultSet.getInt(8);
                        double price = resultSet.getDouble(9);
                        if (sold == 0) {
                            carSold = false;
                        } else {
                            carSold = true;
                        }
                        Vehicle vehicle = new Vehicle(vin, carMake, carModel, year, carSold, color, vehicleType,
                                odometer, price);
                        vehicles.add(vehicle);
                    } while (resultSet.next());
                } else {
                    System.out.println("Nothing go away");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        // TODO: Implement the logic to search vehicles by year range

        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, minYear);
            preparedStatement.setDouble(2, maxYear);

            boolean carSold;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    do {
                        String vin = resultSet.getString(1);
                        String make = resultSet.getString(2);
                        String model = resultSet.getString(3);
                        int year = resultSet.getInt(4);
                        int sold = resultSet.getInt(5);
                        String color = resultSet.getString(6);
                        String vehicleType = resultSet.getString(7);
                        int odometer = resultSet.getInt(8);
                        double price = resultSet.getDouble(9);
                        if (sold == 0) {
                            carSold = false;
                        } else {
                            carSold = true;
                        }
                        Vehicle vehicle = new Vehicle(vin, make, model, year, carSold, color, vehicleType,
                                odometer, price);
                        vehicles.add(vehicle);
                    } while (resultSet.next());
                } else {
                    System.out.println("no results found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByColor(String color) {
        // TODO: Implement the logic to search vehicles by color

        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE color = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, color);

            boolean carSold;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    do {
                        String vin = resultSet.getString(1);
                        String make = resultSet.getString(2);
                        String model = resultSet.getString(3);
                        int year = resultSet.getInt(4);
                        int sold = resultSet.getInt(5);
                        String carColor = resultSet.getString(6);
                        String vehicleType = resultSet.getString(7);
                        int odometer = resultSet.getInt(8);
                        double price = resultSet.getDouble(9);
                        if (sold == 0) {
                            carSold = false;
                        } else {
                            carSold = true;
                        }
                        Vehicle vehicle = new Vehicle(vin, make, model, year, carSold, carColor, vehicleType,
                                odometer, price);
                        vehicles.add(vehicle);
                    } while (resultSet.next());
                } else {
                    System.out.println("nope");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        // TODO: Implement the logic to search vehicles by mileage range

        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, minMileage);
            preparedStatement.setDouble(2, maxMileage);

            boolean carSold;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    do {
                        String vin = resultSet.getString(1);
                        String make = resultSet.getString(2);
                        String model = resultSet.getString(3);
                        int year = resultSet.getInt(4);
                        int sold = resultSet.getInt(5);
                        String color = resultSet.getString(6);
                        String vehicleType = resultSet.getString(7);
                        int odometer = resultSet.getInt(8);
                        double price = resultSet.getDouble(9);
                        if (sold == 0) {
                            carSold = false;
                        } else {
                            carSold = true;
                        }
                        Vehicle vehicle = new Vehicle(vin, make, model, year, carSold, color, vehicleType,
                                odometer, price);
                        vehicles.add(vehicle);
                    } while (resultSet.next());
                } else {
                    System.out.println("nope!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByType(String type) {
        // TODO: Implement the logic to search vehicles by type

        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles WHERE vehicleType = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, type);

            boolean carSold;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    do {
                        String vin = resultSet.getString(1);
                        String make = resultSet.getString(2);
                        String model = resultSet.getString(3);
                        int year = resultSet.getInt(4);
                        int sold = resultSet.getInt(5);
                        String color = resultSet.getString(6);
                        String vehicleType = resultSet.getString(7);
                        int odometer = resultSet.getInt(8);
                        double price = resultSet.getDouble(9);
                        if (sold == 0) {
                            carSold = false;
                        } else {
                            carSold = true;
                        }
                        Vehicle vehicle = new Vehicle(vin, make, model, year, carSold, color, vehicleType,
                                odometer, price);
                        vehicles.add(vehicle);
                    } while (resultSet.next());
                } else {
                    System.out.println("nothing here");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }


    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
