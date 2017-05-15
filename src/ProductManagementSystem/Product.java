package ProductManagementSystem;

public class Product 
{

	private String productId;
	private String productName;
	private double productPrice;
	private double productRating;
	private int stockQuantity;
	private int cartQuantity = 0;
	

	public Product(String productId, String productName, double productPrice, int stockQuantity, double productRating) //String productId
	{
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.stockQuantity = stockQuantity;
		this.productRating = productRating;
	}
	
	public Product(String productId, String productName, double productPrice, int stockQuantity) //String productId
	{
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.stockQuantity = stockQuantity;
		//this.productRating = productRating;
	}
	
	public Product(String productIn, String productName, double productPrice)
	{
		this.productId = productIn;
		this.productName = productName;
		this.productPrice = productPrice;
	}
	public void setProductID(String productId)
	{
		this.productId = productId;
	}
	
	public String getProductId()
	{
		return productId;
	}
		
	public String getProductName()
	{
		return productName;
	}
	
	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public double getProductPrice()
	{
		return productPrice;
	}

	public void setProductPrice(double productPrice)
	{
		this.productPrice = productPrice;
	}
	
	public double getProductRating()
	{
		return productRating;
	}
	
	public void setProductRating(double productRating)
	{
		this.productRating = productRating;
	}
	
	public int getStockQuantity()
	{
		return stockQuantity;
	}
	
	public void setProductQuantity(int stockQuantity)
	{
		this.stockQuantity = stockQuantity;
	}
	
	public void setCartQuantity(int cartQuantity)
	{
		this.cartQuantity = cartQuantity;
	}

	public int getCartQuantity()
	{
		return cartQuantity;
	}
	
	public String toFoRealString()
	{
		return  "Product Name:" + getProductName() ;
	}

	public String toString() 
	{ 		
		String s = /*"The product Id is: " + getProductId() +  "\n" +*/ "Name: " +getProductName() + " Quantity: " + getStockQuantity() + "\n" ;//+ "The price of the product is: " +getProductPrice() + "\n" + "The stockQuantity is: " + getStockQuantity() + "\n" + "The rating of the product is: " + getProductRating();
		
		return s;
	}
	
}
