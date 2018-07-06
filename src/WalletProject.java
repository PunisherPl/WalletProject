import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mysql.cj.xdevapi.Statement;
import com.sun.jdi.Value;


public class WalletProject extends JFrame{
	private static JComboBox expensesBox, daysBox, monthBox;
	private static JComboBox yearsBox;
	
	WalletProject()
	{
		JTextField expensesField, incomeField;
		JButton expensesButton, saveButton, lookButton;
		
		JLabel value,value2, value3, value4, haha;
		Calculate calc = new Calculate();
		Calendar data = Calendar.getInstance();
		database call = new database();
		int a,b,c;
		
		
		
		
		setSize(400,300);
		setResizable(false);
		setLayout(null);
		

		
		
		value2 = new JLabel("Pozostalo:");
		value2.setBounds(170, 50, 130, 20);
		add(value2);
		

			
	
		value = new JLabel();
		value.setBounds(245, 30, 50, 20);
		add(value);
		
		value4= new JLabel("0");
		
		
		
		expensesField = new JTextField(" ");
		expensesField.setBounds(15, 100, 150, 20);
		add(expensesField);
		expensesField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				
				
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				value.setText(expensesField.getText());
				
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
		
			
			
			
			
		
		
		incomeField = new JTextField(" ");
		incomeField.setBounds(15, 30, 150, 20);
		add(incomeField);

		
		value3 = new JLabel("Miesiecznie: ");
		value3.setBounds(170, 30, 130, 20);
		add(value3);
		
		
		

		daysBox = new JComboBox();
		daysBox.setBounds(15, 150, 100, 20);
		for (int i = 1; i<32;i++)
		{
		daysBox.addItem(i);
		}
		add(daysBox);
		
		
		int dayDate = data.get(Calendar.DAY_OF_MONTH);
		daysBox.setSelectedItem((Object)dayDate);



		
		monthBox = new JComboBox();
		monthBox.setBounds(115, 150, 100, 20);
		monthBox.addItem("Styczen");
		monthBox.addItem("Luty");
		monthBox.addItem("Marzec");
		monthBox.addItem("Kwiecien");
		monthBox.addItem("Maj");
		monthBox.addItem("Czerwiec");
		monthBox.addItem("Lipiec");
		monthBox.addItem("Sierpien");
		monthBox.addItem("Wrzesien");
		monthBox.addItem("Pazdziernik");
		monthBox.addItem("Listopad");
		monthBox.addItem("Grudzien");
		
		add(monthBox);
		data.getTime();
		int monthDate = data.get(Calendar.MONTH);
		monthBox.setSelectedIndex(monthDate);
		monthBox.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String m = monthBox.getSelectedItem().toString();
				try
				{
					
					value.setText(String.valueOf(getActuallyExpenses()));
					value4.setText(String.valueOf(getActuallyExpenses()));
					
					value2.setText(String.valueOf(getActuallyIncome()));
					incomeField.setText(String.valueOf(getActuallyIncome()));
				}catch(Exception e) {System.out.println(e);}

			
			
			
			
			}});
		
		

		
		yearsBox = new JComboBox();
		yearsBox.setBounds(225, 150, 100, 20);
		for(int i = 2018; i<2031; i++)
		{
		yearsBox.addItem(i);
		}
		add(yearsBox);
	int yearDate = data.get(Calendar.YEAR);
	yearsBox.setSelectedItem((Object)yearDate);
		haha = new JLabel();
		haha.setBounds(100, 200, 200, 100);
		add(haha);
		
		expensesBox = new JComboBox();
		expensesBox.setBounds(15, 70, 100, 20);
		expensesBox.addItem("Zakupy");
		expensesBox.addItem("Op³aty");
		expensesBox.addItem("Rozrywka");
		add(expensesBox);
		
		
		
		
		expensesButton = new JButton("OK");
		expensesButton.setBounds(165,100,80,20);
		add(expensesButton);
		expensesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			
			try {
			
			data.set(getYear(),getMonth(),getDay());
			
				value2.setText( String.valueOf(calc.money(Double.parseDouble(value.getText()), Double.parseDouble(incomeField.getText()))));
				value.setText(String.valueOf(calc.expenses(Double.parseDouble(value4.getText()), Double.parseDouble(expensesField.getText()))));
				incomeField.setText(value2.getText());
				
				
				
				


				
value4.setText(value.getText());
			}catch(Exception e) {
				JOptionPane a = new JOptionPane();
				
				JOptionPane.showMessageDialog(null, "Wpisz poprawn¹ liczbê", "B³¹d", JOptionPane.PLAIN_MESSAGE);
				;}
		
				
				
				
			}
			
			
			
			
			
		});
		saveButton = new JButton("Zapisz");
		saveButton.setBounds(245, 100, 80, 20);
		add(saveButton);
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Date d = data.getTime();
				
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String today = formatter.format(d);
				haha.setText(today);
				String monthBase;
				monthBase = monthBox.getSelectedItem().toString();
				try {
					
					call.postZapisy(Float.parseFloat(expensesField.getText()),Float.parseFloat(value2.getText()),expensesBox.getSelectedItem().toString(),today,monthBase);
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			
			
		});
		lookButton = new JButton("Poka¿");
		lookButton.setBounds(150,200,100,50);
		add(lookButton);
		lookButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					data.set(getYear(),getMonth(),getDay());
					Date d = data.getTime();
					
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					String today = formatter.format(d);
					call.getDataBases(today);
				
				} catch (Exception e) {
					System.out.println(e+ "haha");
				}
				
			}
			
			
			
			
		});

		String m = monthBox.getSelectedItem().toString();
		try
		{
			
			value.setText(String.valueOf(getActuallyExpenses()));
			value4.setText(String.valueOf(getActuallyExpenses()));
			
			value2.setText(String.valueOf(getActuallyIncome()));
			incomeField.setText(String.valueOf(getActuallyIncome()));
		}catch(Exception e) {System.out.println(e);}}

		
	

		

	
	public static float getActuallyExpenses()
	{
		String m = monthBox.getSelectedItem().toString();
		database call = new database();
		float ActuallyExp;
		try {
			ActuallyExp = call.getActuallyExpenses(m);
			return ActuallyExp;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return 0;
		
	}
	public static float getActuallyIncome()
	{
		String m = monthBox.getSelectedItem().toString();
		database call = new database();
		float ActuallyIn;
		try {
			ActuallyIn = call.getActuallyIncome(m);
			return ActuallyIn;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return 0;
		
	}
	public static int getDay()
	{
		int c = (int) daysBox.getSelectedItem();
		return c;
	}
	public static int getMonth()
	{
		
		int b = (int)monthBox.getSelectedIndex();
		return b;
	}
	public static int getYear()
	{
		int a = (int)yearsBox.getSelectedItem();
		return a;
	}

	public static void main(String[] args) {
		
		WalletProject window = new WalletProject();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

	}





}
