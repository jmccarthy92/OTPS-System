package OrderSubsystem;

import java.util.Date;

public class CreditCard 
{
	private String CCNumber;
	private String CSVNum;
	private Date expirationDate;
	private CreditCardType CCType;
	
	public CreditCard( String CCNum, String CSVNum, Date expDate, CreditCardType type)
	{
		this.CCNumber = CCNum;
		this.CSVNum = CSVNum;
		this.expirationDate = expDate;
		this.CCType = type;
	}
	
	public String getCCNum()	{ return CCNumber; }
	public String getCSV()		{ return CSVNum; }
	public Date getExpDate()	{ return expirationDate; }
	public String getCCType()	{ return CCType.toString(); }
	
	public void setCCNum(String CC)		{ this.CCNumber = CC;}
	public void setCSV( String CSV)		{ this.CSVNum = CSV;}
	public void setExpDate( Date exp)	{ this.expirationDate = exp;}
	public void setCCType( CreditCardType type)	{this.CCType = type;}
}
