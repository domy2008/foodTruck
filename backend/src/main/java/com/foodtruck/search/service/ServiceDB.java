package com.foodtruck.search.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.foodtruck.search.model.FoodCartPermit;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class ServiceDB {
    private String jdbcUrl = "jdbc:sqlite:foodDB.db";
    private Connection conn = null;
    

    public Connection getConnection() throws SQLException {
        try {
            conn = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

public List<FoodCartPermit> getAllMatchedFood(String subItem) {
    List<FoodCartPermit> permits = new ArrayList<>();
    String sql = "SELECT * FROM food_cart_permits WHERE LOWER(food_items) LIKE ?";
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
    
         ps.setString(1, "%" + subItem + "%");  // Set the first parameter with the search term
         ResultSet rs = ps.executeQuery();

        // Process the results
       
        while (rs.next()) {
            // Assuming you have a FoodCartPermit class to map the result set data
            FoodCartPermit permit = new FoodCartPermit();
            
            permit.address = rs.getString("address");
            permit.applicant = rs.getString("applicant");
            permit.cnn = rs.getString("cnn");
            permit.daysHours = rs.getString("daysHours");
            permit.expirationDate = rs.getDate("expiration_date");
            permit.facilityType = rs.getString("facility_type");
            permit.firePreventionDistricts = rs.getString("fire_prevention_districts");
            permit.foodItems = rs.getString("food_items");
            permit.latitude = rs.getDouble("latitude");
            permit.location = rs.getString("location");
            permit.locationDescription = rs.getString("location_description");
            permit.locationId = rs.getLong("locationId");
            permit.longitude = rs.getDouble("longitude");
            permit.lot = rs.getInt("lot");
            permit.neighborhoodsOld = rs.getString("neighborhoods_old");
            permit.noiseNt = rs.getString("noiseNt");
            permit.permit = rs.getString("permit");
            permit.policeDistricts = rs.getString("police_districts");
            permit.priorPermit = rs.getString("prior_permit");
            permit.received = rs.getDate("received");
            permit.schedule = rs.getString("schedule");
            permit.status = rs.getString("status");
            permit.supervisorDistricts = rs.getString("supervisor_districts");
            permit.x = rs.getDouble("x");
            permit.y = rs.getDouble("y");
            permit.zipCodes = rs.getString("zip_codes");

           
            permits.add(permit);
        }
    } catch (SQLException e) {
       e.printStackTrace();  // Or handle the exception appropriately
    }

    return permits;
}
    public String readDB(String sub) {
       // Replace with your connection details and file path
    

        Connection conn = null;
        PreparedStatement ps = null;
        try{
            // Connect to the database
            conn = DriverManager.getConnection(jdbcUrl);
                // Prepare the SQL statement
            String sql = "SELECT * FROM food_cart_permits WHERE food_items LIKE '%" + sub + "%'";

            ps = conn.prepareStatement(sql);

        }catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }
}
