package OrderSubsystem;



public class Address 
{
	private String firstName= null;
	private String lastName = null;
	private String street = null;
	private String state = null;
	private String city = null;
	private String zipCode = null;
	private String address = null;
	
	public Address( String first, String last, String street, String state, String city, String zip)
	{
		this.firstName = first;
		this.lastName = last;
		this.street = street;
		this.state = state;
		this.city = city;
		this.zipCode = zip;
	}
	public Address( String first, String last, String zip, String address)
	{
		this.firstName = first;
		this.lastName = last;
		this.zipCode = zip;
		this.address =address;
	}
	
	public Address(String address)
	{
		this.address = address;
	}
	
	
	public String getFirstName() { return firstName; }
	public String getLastName()  { return lastName; }
	public String getStreet()	 { return street; }
	public String getState()	 { return state; }
	public String getCity()		 { return city; }
	public String getZip()		 { return zipCode; }
	
	public String getFullName()	 { return this.firstName + " " + this.lastName; }
	public String getAddress()   { return this.street + " " + this.state +" " + this.city + " " + this.zipCode; }
	public String getAddressForDB()  { return this.address; }
	
	public String toString()	 { return getFullName() + " " + getAddress() ;	}
	
	
	
	
	
	
	
	
	
}
