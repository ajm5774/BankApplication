package database;

import java.awt.List;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbManager {
	
	public String dbURL = "";
	public String dbUsername = "";
	public String dbPassword ="";
	
	public DbManager(String url, String username, String password)
	{
		dbURL = url;
		dbUsername = username;
		dbPassword = password;
	}
	
	@SuppressWarnings("static-access")
	public DbClass Create(DbClass row)
	{	
		int rowsInserted = 0;
		try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword)) {
			
			Field[] fields = row.getClass().getFields();
			
			String sql = "INSERT INTO ? (?) VALUES (?)";
			 
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, row.TableName);
			statement.setString(2, GetFieldsString(fields, row));
			statement.setString(3, GetValuesString(fields, row));
			 
			rowsInserted = statement.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(rowsInserted > 0)
			return row;
		else
			return null;
	}
	
	public ArrayList<DbClass> Read(String id)
	{
		try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword)) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public DbClass Update(DbClass row)
	{
		try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword)) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return row;
	}
	
	public DbClass Delete(DbClass row)
	{
		try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword)) {

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return row;
	}
	
	//============================Private methods===============================================
	
	public static String GetFieldsString(Field[] fields, DbClass row)
	{
		ArrayList<String> dbFieldNames = new ArrayList<String>();
		Object value;
		
		for(Field field: fields)
		{
			DbField annotation = field.getAnnotation(DbField.class);
			
			value = null;
			
			try {
				value = field.get(row);
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(annotation != null && value != null)
			{
				if(annotation.ColumnName().isEmpty())
					dbFieldNames.add(field.getName());
				else
					dbFieldNames.add(annotation.ColumnName());
			}
		}
		
		String valueString = dbFieldNames.toString();
		
		return valueString.substring(1, valueString.length() - 1);
	}
	
	public static String GetValuesString(Field[] fields, DbClass row)
	{
		ArrayList<String> values = new ArrayList<String>();
		Object value;
		
		for(Field field: fields)
		{
			Annotation annotation = field.getAnnotation(DbField.class);
			
			if(annotation != null)
			{
				value = null;
				
				try {
					value = field.get(row);
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(value != null)
					values.add(value.toString());
			}
		}
		
		String valueString = values.toString();
		
		return valueString.substring(1, valueString.length() - 1);
	}
}
