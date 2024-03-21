/************************************************************************************************
 * @author: cong
 * @comments:This class is used to insert the data from the CSV file into the SQLite database.
 *          The CSV file is read line by line and the data is inserted into the database using a prepared statement.
 *************************************************************************************************/
package com.foodtruck.search.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTabe {
    public static void InsertDB() throws IOException, SQLException {
        // Replace with your connection details and file path
        String jdbcUrl = "jdbc:sqlite:foodDB.db";
        String csvFile = "data_pure.csv";

        Connection conn = null;
        PreparedStatement ps = null;
        try{
            // Connect to the database
            conn = DriverManager.getConnection(jdbcUrl);
                // Prepare the SQL statement
            String sql = "INSERT INTO food_cart_permits (" +
            "locationid, applicant, facility_type, cnn, location_description, address, blocklot, block, lot, permit, status, food_items, x, y, latitude, longitude, schedule, dayshours, noisent, approved, received, prior_permit, expiration_date, location, fire_prevention_districts, police_districts, supervisor_districts, zip_codes, neighborhoods_old) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            ps = conn.prepareStatement(sql);

        }catch(Exception exception){
            exception.printStackTrace();
        }
        

     

        // Read the CSV file line by line
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into columns
                String[] columns = line.split(",");

                assert columns.length == 30;

                // Set the values for the prepared statement
                for (int i = 0; i < columns.length; i++) {
                    //ps.setString(i, columns[i]);
                    Object object = columns[i];
                    ps.setObject(i + 1, object);
                }

                // Add the record to the database
                ps.executeUpdate();
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }

        // Close the connection and statement
        ps.close();
        conn.close();
    }
}
