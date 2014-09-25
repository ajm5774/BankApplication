package test;

import java.lang.reflect.Field;
import java.sql.Date;

import database.DbClass;
import database.DbManager;
import domain.AccountOwner;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountOwner owner = new AccountOwner();
		owner.Birthday = new Date(2014, 12, 25);
		owner.FirstName = "Bobby";
		owner.LastName = "Jones";
		owner.Password = "password";
		owner.SSN = "1231231234";
		owner.Username = "bJones38";
		
		Field[] fields = owner.getClass().getFields();
		
		System.out.println(DbManager.GetFieldsString(fields, owner));
		System.out.println(DbManager.GetValuesString(fields, owner));
		
		DbManager manager = new DbManager("jdbc:mysql://mysql.se.rit.edu:3306", "ajm5774", "ohma7Pai3U");
		DbClass sameOwner = manager.Create(owner);
		
		if(sameOwner != owner)
			System.out.println("Create failed");
	}

}
