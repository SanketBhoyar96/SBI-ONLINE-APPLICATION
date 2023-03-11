import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SBI_ONLINE_Netbank {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		int option;
		int x = 10;
		Connection con = null;

		Class.forName("com.mysql.cj.jdbc.Driver");

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer", "root", "Sanket@123");

		System.out.println("Welcome to SBI Banking \n");

		System.out.println("Please Choose option from Menu");
		do {
			System.out.println("1.Create Customer");
			System.out.println("2.Delete Customer");
			System.out.println("3.Update Customer");
			System.out.println("4.Read Customer Single Record Only View");
			System.out.println("5.Read Customer All Record View");
			System.out.println("5.Exit");
			System.out.println("======================================");
			System.out.println("Please choose option from Menu");
			option = sc.nextInt();

			switch (option) {

			case 1:
				System.out.println("Plz Enter customerID");
				int customerID = sc.nextInt();
				System.out.println("Plz Enter customerName");
				String customerName = sc.next();
				System.out.println("Plz Enter customerAddress");
				String customerAddress = sc.next();
				System.out.println("Plz Enter customerBalance");
				double customerBalance = sc.nextDouble();
				PreparedStatement ps = con.prepareStatement("INSERT INTO customer_data values(?,?,?,?)");
				ps.setInt(1, customerID);
				ps.setString(2, customerName);
				ps.setString(3, customerAddress);
				ps.setDouble(4, customerBalance);

				int k = ps.executeUpdate();

				if (k > 0) {
					System.out.println("Customer Created Sucessfully.....!");
					System.out.println("==================================================");
				} else {
					System.out.println("Record couldnt Insert DB");
				}

				break;
			case 2:
				System.out.println("Plz Enter CustomerID to Delete Record ");
				int customerID1 = sc.nextInt();
				PreparedStatement ps1 = con.prepareStatement("delete from customer_data where customerID=?");

				ps1.setInt(1, customerID1);

				int k1 = ps1.executeUpdate();

				if (k1 > 0) {
					System.out.println("Customer Record is Deleted Sucessfully....!");
					System.out.println("==================================================");
				} else {
					System.out.println("Customer Record is Not Deleted plz check ......!");
					System.out.println("==================================================");
				}

				break;

			case 3:
				System.out.println("Plz Enter CustomerID");
				int customerID2 = sc.nextInt();
				System.out.println("Plz Enter CustomerBalance Which you Update Record ");
				String customerBalance2 = sc.next();

				PreparedStatement ps2 = con
						.prepareStatement("update customer_data SET customerBalance=? where customerID=?");

				ps2.setString(1, customerBalance2);
				ps2.setInt(2, customerID2);

				int k2 = ps2.executeUpdate();

				if (k2 > 0) {
					System.out.println("Customer Record is UPDATE Sucessfully....!");
					System.out.println("==================================================");
				} else {
					System.out.println("Customer Record is Not UPDATE plz check ......!");
					System.out.println("==================================================");
				}

				break;

			case 4:
				System.out.println("Enter Your CustomerID View only single User Record");
				int customerID3 = sc.nextInt();
				Connection con3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer", "root",
						"Sanket@123");
				PreparedStatement ps3 = con3.prepareStatement("select * from customer_data where customerID=?");
				ps3.setInt(1, customerID3);

				ResultSet rs = ps3.executeQuery();

//				Statement stm = con.createStatement();
//
//				ResultSet rs = stm.executeQuery("SELECT * FROM customer.customer_data");

				System.out.println("CustomerID" + "   " + "CustomerName" + "     " + "CustomerAddress" + "     "
						+ "CustomerBalance");
				while (rs.next()) {
					System.out.println("   " + rs.getInt(1) + "           " + rs.getString(2) + "            "
							+ rs.getString(3) + "               " + rs.getString(4));
				}
				System.out.println("==================================================");

				break;

			case 5:
				Statement stm = con.createStatement();
				ResultSet rs4 = stm.executeQuery("SELECT * FROM customer.customer_data");

				System.out.println("CustomerID" + "   " + "CustomerName" + "     " + "CustomerAddress" + "     "
						+ "CustomerBalance");
				while (rs4.next()) {
					System.out.println("   " + rs4.getInt(1) + "           " + rs4.getString(2) + "            "
							+ rs4.getString(3) + "               " + rs4.getString(4));
				}
				System.out.println("==================================================");

				break;

			case 6:
				System.out.println("==================================================");
				break;

			default:
				System.out.println("plz choose correct Menu");
				System.out.println("===================================================");
				break;

			}
		} while (option != 6);
		System.out.println("Thank You For Using SBI Online Banking, Vist Again....!!!");

	}

}
