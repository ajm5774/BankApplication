package domain;

import java.sql.Date;

import database.DbClass;
import database.DbField;

public class AccountOwner extends DbClass {
	public static String TableName = "account_owner";

	
	
	@DbField(ColumnName = "ssn", PrimaryKey = true)
	public String SSN;
	
	@DbField(ColumnName = "first_name")
	public String FirstName;
	
	@DbField(ColumnName = "last_name")
	public String LastName;
	
	@DbField(ColumnName = "birthday")
	public Date Birthday;
	
	@DbField(ColumnName = "password")
	public String Password;
	
	@DbField(ColumnName = "username")
	public String Username;
	
	@DbField(ColumnName = "email")
	public String Email;
	
	
	
	public static DbClass ParseResult()
	{
		return null;
	}
}
