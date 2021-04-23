package model;
import java.sql.*;
import java.sql.Date;

import common.DBConnection;


public class Research {
	private Connection con = DBConnection.getConnection();
	public String insertResearch(String researchcode, String researchercode, String topic, String price, String createdOn)
	{
		String output = "";
		try
		{
			
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = " insert into researchs(`researchID`,`researchCode`,`researcherCode`,`researchTopic`,`researchPrice` ,`createdOn`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, researchcode);
			preparedStmt.setString(3, researchercode);
			preparedStmt.setString(4, topic);
			preparedStmt.setDouble(5, Double.parseDouble(price));

			java.util.Date date=new java.util.Date();
			
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
	
			preparedStmt.setDate(6,sqlDate);
			
			preparedStmt.execute();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the research.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String readResearchs()
	{
		String output = "";
		try
		{
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			// Prepare the html table to be displayed
			output = "<table border='1'><tr>" + 
					"<th>Research Code</th>"+ 
					"<th>Researcher Code</th>" +
					"<th>Research Topic</th>" +
					"<th>Research Price</th>" +
					"<th>Created On</th>" +
					"<th>Update</th>" + 
					"<th>Remove</th></tr>";

			String query = "select * from researchs";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String researchID = Integer.toString(rs.getInt("researchID"));
				String researchCode = rs.getString("researchCode");
				String researcherCode = rs.getString("researcherCode");
				String researchTopic = rs.getString("researchTopic");
				String researchPrice = Double.toString(rs.getDouble("researchPrice"));
				String createdOn = rs.getDate("createdOn").toString();
				// Add into the html table
				output += "<tr><td>" + researchCode + "</td>";
				output += "<td>" + researcherCode + "</td>";
				output += "<td>" + researchTopic + "</td>";
				output += "<td>" + researchPrice + "</td>";
				output += "<td>" + createdOn + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
								+ "<td><form method='post' action='researchs.jsp'>"
								+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
										+ "<input name='researchID' type='hidden' value='" + researchID
										+ "'>" + "</form></td></tr>";
			}
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the researchs.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String updateResearch(String ID, String researchcode, String researchercode, String topic, String price, String createdOn)

	{
		String output = "";
		try
		{
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE researchs SET researchCode=?, researcherCode=?, researchTopic=?,researchPrice=?,createdOn=? WHERE researchID=?";
							PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, researchcode);
			preparedStmt.setString(2, researchercode);
			preparedStmt.setString(3, topic);
			preparedStmt.setDouble(4, Double.parseDouble(price));
			Date date = Date.valueOf(createdOn);
			preparedStmt.setDate(5, date);
			preparedStmt.setInt(6, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the research.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String deleteResearch(String researchID)
	{
		String output = "";
		try
		{
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from researchs where researchID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(researchID));
			// execute the statement
			preparedStmt.execute();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the research.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}