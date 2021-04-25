package model;
import java.sql.*;
//
public class Funding {
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 

			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		return con; 
	} 
	public String insertFundingRecord(String payment_name, String amount, String payment_date,String research_id,String funder_id) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
			// create a prepared statement
			String query = " insert into fundingbodies (`Payment_ID`,`Payment_Type`,`Amount`,`Payment_Date`,`Research_Id`,`Funder_ID`)" + " values (?, ?, ?, ?, ?,?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, payment_name); 
			preparedStmt.setDouble(3, Double.parseDouble(amount)); 
			preparedStmt.setString(4, payment_date);
			preparedStmt.setInt(5, Integer.parseInt(research_id));
			preparedStmt.setInt(6, Integer.parseInt(funder_id));
			
			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the funds."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String readFundingRecords() 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for reading."; } 
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Payment ID</th><th>Payment Type</th>" +
					"<th>Amount</th>" + 
					"<th>Payment Date</th>" +
					"<th>Research ID</th>" +
					"<th>Buyer ID</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 

			String query = "select * from fundingbodies"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String paymentID = Integer.toString(rs.getInt("Payment_ID")); 
				String paymentType = rs.getString("Payment_Type"); 
				String amount = Double.toString(rs.getDouble("Amount")); 
				String paymentDate = rs.getString("Payment_Date"); 
				String researchID = Integer.toString(rs.getInt("Research_Id")); 
				String buyerID = Integer.toString(rs.getInt("Funder_ID")); 
				// Add into the html table
				output += "<tr><td>" + paymentID + "</td>"; 
				output += "<td>" + paymentType + "</td>"; 
				output += "<td>" + amount + "</td>"; 
				output += "<td>" + paymentDate + "</td>"; 
				output += "<td>" + researchID + "</td>"; 
				output += "<td>" + buyerID + "</td>"; 
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
								+ "<td><form method='post' action='funding.jsp'>"
								+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
										+ "<input name='paymentID' type='hidden' value='" + paymentID + "'>" + "</form></td></tr>"; 
			} 
			con.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the items."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String updateFundingRecord(String ID, String paymentType, String amount,String paymentDate, String researchId, String funderId)
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for updating."; } 
			// create a prepared statement
			String query = "UPDATE fundingbodies SET Payment_Type=?,Amount=?,Payment_Date=?,Research_Id=?,Funder_ID=? WHERE Payment_ID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, paymentType); 
			preparedStmt.setDouble(2, Double.parseDouble(amount)); 
			preparedStmt.setString(3, paymentDate); 
			preparedStmt.setInt(4, Integer.parseInt(researchId)); 
			preparedStmt.setInt(5, Integer.parseInt(funderId)); 
			preparedStmt.setInt(6, Integer.parseInt(ID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while updating the item."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String deleteFundingRecord(String Payment_ID) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for deleting."; } 
			// create a prepared statement
			String query = "delete from fundingbodies where Payment_ID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Payment_ID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Deleted successfully"; 
		}
		catch (Exception e) 
		{ 
			output = "Error while deleting the funds."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}
}