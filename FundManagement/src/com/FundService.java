package com;
import model.Funding;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/Funding")
public class FundService {
	
	Funding fundingServe = new Funding(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readFunding() 
	 { 
		return fundingServe.readFundingRecords();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearch(@FormParam("Payment_Type") String Payment_Type,
			@FormParam("Amount") String Amount,
			@FormParam("Payment_Date") String Payment_Date,
			@FormParam("Research_ID") String Research_ID,
			@FormParam("Buyer_ID") String Buyer_ID)
	{
		String output = fundingServe.insertFundingRecord(Payment_Type, Amount, Payment_Date, Research_ID, Buyer_ID);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFunds(String fundingData)
	{
		//Convert the input string to a JSON object
		JsonObject fundingObject = new JsonParser().parse(fundingData).getAsJsonObject();
		//Read the values from the JSON object
		String paymentId = fundingObject.get("Payment_ID").getAsString();
		String paymentType = fundingObject.get("Payment_Type").getAsString();
		String amount = fundingObject.get("Amount").getAsString();
		String paymentDate = fundingObject.get("Payment_Date").getAsString();
		String researchId = fundingObject.get("Research_ID").getAsString();
		String buyerId = fundingObject.get("Buyer_ID").getAsString();
		
		String output = fundingServe.updateFundingRecord(paymentId, paymentType, amount, paymentDate, researchId, buyerId);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearch(String fundsData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(fundsData, "", Parser.xmlParser());

		//Read the value from the element <fundID>
		String paymentID = doc.select("Payment_ID").text();
		String output = fundingServe.deleteFundingRecord(paymentID);
		return output;
	}
}