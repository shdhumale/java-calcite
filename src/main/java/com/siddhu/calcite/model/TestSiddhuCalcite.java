package com.siddhu.calcite.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.calcite.adapter.java.ReflectiveSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;

public class TestSiddhuCalcite {
	public static void main(String args[])
	{
		try {

			CompanySchema companySchema = new CompanySchema();
			Department dept1 = new Department("HR", "Human Resource");
			Department dept2 = new Department("MKT", "Marketing");
			Department dept3 = new Department("FIN", "Finance");

			
			Employee emp1 = new Employee("Tom", "1234", "HR");
			Employee emp2 = new Employee("Harry", "39731", "FIN");
			Employee emp3 = new Employee("Danny", "45632", "FIN");
			Employee emp4 = new Employee("Jenny", "78654", "MKT");

			companySchema.DEPARTMENTS = new Department[]{dept1, dept2, dept3};
			companySchema.EMPLOYEES = new Employee[]{emp1, emp2, emp3, emp4};

			Connection connection = DriverManager.getConnection("jdbc:calcite:");
			CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
			SchemaPlus rootSchema = calciteConnection.getRootSchema();
			Schema schema = new ReflectiveSchema(companySchema);
			rootSchema.add("COMPANY", schema);
			Statement statement = calciteConnection.createStatement();
			String query = "select DEPT.DEPTNAME, count(EMP.ID) "
					+ "from COMPANY.EMPLOYEES as EMP "
					+ "join COMPANY.DEPARTMENTS as DEPT "
					+ "on (EMP.DEPTID = DEPT.DEPTID) "
					+ "group by DEPT.DEPTNAME";
			ResultSet resultSet = statement.executeQuery(query);
			long startcurrentmillis = System.currentTimeMillis();
			while (resultSet.next()) {
				System.out.println("Dept Name:" + resultSet.getString(1)
				+ " No. of employees:" + resultSet.getInt(2));
			}
			long endcurrentmillis = System.currentTimeMillis();
			System.out.println("execution time endcurrentmillis: "+endcurrentmillis+" startcurrentmillis:"+startcurrentmillis+":::::::"+ (endcurrentmillis-startcurrentmillis));

			//normal for loop
			int count =0;
			long startForcurrentmillis = System.currentTimeMillis();
			for (int i = 0; i <companySchema.DEPARTMENTS.length ; i++) {
				for (int j = 0; j < companySchema.EMPLOYEES.length ;j++)
				{
					if(companySchema.EMPLOYEES[j].DEPTID.equals(companySchema.DEPARTMENTS[i].DEPTID))
					{
						count ++;                       
						continue;
					}	
				}				
				
				System.out.println("Dept Name ---------"+companySchema.DEPARTMENTS[i].DEPTNAME+" No. of employees ----------:"+count);
				count =0;
				
			}
			long endForcurrentmillis = System.currentTimeMillis();
			System.out.println("execution time endForcurrentmillis: "+endForcurrentmillis+" startForcurrentmillis:"+startForcurrentmillis+":::::::"+ (endForcurrentmillis-startForcurrentmillis));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
