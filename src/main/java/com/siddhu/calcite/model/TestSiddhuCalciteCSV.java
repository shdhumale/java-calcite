package com.siddhu.calcite.model;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestSiddhuCalciteCSV {
	public static String getPath(String model) {
		URL url = ClassLoader.getSystemClassLoader().getResource(model);
		System.out.println("path fetched :" + url.getPath());
		return url.getPath();
	}

	//main method
	public static void main(String[] args) {
		Properties info = new Properties();
		info.put("model", getPath("model.json"));
		try(Connection connection = DriverManager.getConnection("jdbc:calcite:", info)) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from industries.INDUSTRY");
			System.out.println("Column name:"+resultSet.getMetaData().getColumnName(1));
			System.out.println("Total count:"+resultSet.getMetaData().getColumnCount());

			List<String> industriesName = new ArrayList<>();			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("Industry"));
			    
			}

		}
		catch (Exception e) {
            e.printStackTrace();
        }

	}
}
