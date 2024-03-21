/**********************************************************************************
 * @authro: cong
 * @comment: This is a test class for the FoodtruckController class
 **********************************************************************************/
import com.foodtruck.search.FoodtruckApplication;
import com.foodtruck.search.controller.*;
import com.foodtruck.search.service.ServiceDB;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


@SpringBootTest(classes = {FoodtruckApplication.class,FoodtruckController.class, ServiceDB.class})
public class MyServiceTests {
    @Autowired  // Injects mocks into the controller
    private FoodtruckController controller;

    @Test
    public void testGetFoodtrucks() {
        // Test the controller
       String ret = controller.getAllFoodtrucks();
       assertNotEquals(ret.length(), 0);
    }

    @Test
    public void testGetAllMatchedFood() {
        // Test the controller
       String ret = controller.getAllMatchedFood("Chicken");
       assertNotEquals(ret.length(), 0);
    }
    @Test
    public void testSomeFood() {
        // Test the controller
       String ret = controller.getAllMatchedFood("Crab");
       assertNotEquals(ret.length(), 0);
    }

    @Test
    public void testGetFoodtruckById() {
        // Test the controller
       String ret = controller.getFoodtruckById(1L);
       assertNotEquals(ret.length(), 0);
    }
    @Test
    public void testCreateFoodtruck() {
        // Test the controller
       String ret = controller.createFoodtruck("Chicken");
       assertNotEquals(ret.length(), 0);
    }

}
