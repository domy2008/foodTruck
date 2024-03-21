/************************************************************************************************
 * @author: cong
 * @comment: This class is the main class for the foodtruck application. It is entry.
 * 
 * ************************************************************************************************/

package com.foodtruck.search;

// 导入必要的类

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodtruckApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(FoodtruckApplication.class, args);

		 // 读取 CSV 文件
		 try {
			//boolean ret = true;
			//InsertTabe.InsertDB();
		 }catch(Exception e){

		 }
	}
	//give more 

}
