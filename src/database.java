import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.mysql.cj.xdevapi.Statement;

import net.proteanit.sql.DbUtils;

public class database {

	
	public database()
	{
		
	}
	
	
	public static Connection getConnection() throws Exception{
		
		
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/portfel";
			String username = "Kamil";
			String password = "kamilod11";
			
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			
			return conn;
		}catch(Exception e) {
			System.out.println(e+ "kurwa");
			
		}
		
		
		return null;
		
		
	}

	public static void postZapisy(float a, float b, String c, String d, String f) throws Exception{
		
		
		
		try {
			Connection con = getConnection();
			PreparedStatement posted = con.prepareStatement("INSERT into zapisy(data,wydatki,pozostalo,rodzaj, miesiac) VALUES ('"+d+"','"+a+"','"+b+"','"+c+"','"+f+"')" );
			posted.executeUpdate();
		}catch (Exception e) {System.out.println(e);}

		
		
	}
	public static void getDataBases(String a) throws Exception{
		
		
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT Data, Wydatki,Pozostalo,Rodzaj from Zapisy WHERE Data like ('"+a+"')");
			ResultSet result = statement.executeQuery();
		
			
			Tabela tab = new Tabela();
			tab.createTable(result);
			

				
			
			
			

			con.close();

		}catch (Exception e) { System.out.println(e+" Niestety");
			e.printStackTrace();}

		

		
	}
	public static float getActuallyExpenses(String a) throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT Wydatki from Zapisy WHERE Miesiac like ('"+a+"') ORDER BY idZapisy DESC LIMIT 1");
			ResultSet result = statement.executeQuery();
			result.next();
			float exp = result.getInt(1);
			con.close();
			return exp;
		}catch(Exception e) {System.out.println(e);}
		return 0;
	}
	public static float getActuallyIncome(String a) throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT Pozostalo from Zapisy WHERE Miesiac like ('"+a+"') ORDER BY idZapisy DESC LIMIT 1");
			ResultSet result = statement.executeQuery();
			result.next();
			float exp = result.getInt(1);
			con.close();
			return exp;
		}catch(Exception e) {System.out.println(e);}
		return 0;
	}


}
