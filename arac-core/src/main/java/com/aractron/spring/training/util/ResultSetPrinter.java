package com.aractron.spring.training.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetPrinter {

	public void printResultSet(ResultSet rs) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();

			printColTypes(rsmd);
			System.out.println("");

			int numberOfColumns = rsmd.getColumnCount();

			for (int i = 1; i <= numberOfColumns; i++) {
				if (i > 1)
					System.out.print(",  ");
				String columnName = rsmd.getColumnName(i);
				System.out.print(columnName);
			}
			System.out.println("");

			while (rs.next()) {
				for (int i = 1; i <= numberOfColumns; i++) {
					if (i > 1)
						System.out.print(",  ");
					String columnValue = rs.getString(i);
					System.out.print(columnValue);
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			System.out.println("****** printResultSet() ERRROR ******");
			e.printStackTrace();
		}
	}

	private void printColTypes(ResultSetMetaData rsmd) {
		int columns = 0;

		try {
			columns = rsmd.getColumnCount();

			for (int i = 1; i <= columns; i++) {
				int jdbcType = rsmd.getColumnType(i);
				String name = rsmd.getColumnTypeName(i);
				System.out.print("Column " + i + " is JDBC type " + jdbcType);
				System.out.println(", which the DBMS calls " + name);
			}
		} catch (SQLException e) {
			System.out.println("****** printColTypes() ERRROR ******");
			e.printStackTrace();
		}
	}
}
