package product;

public class Product {
	
	private String prdctName;
	private String details; // 상세정보
	private String code;
	private String prfrm;//성능
	private String category;//분류
	private int stock;
	private int price;
	
	public Product(String prdctName, String details, String code, String prfrm, String category, int stock, int price) {
		this.prdctName = prdctName;
		this.details = details;
		this.code = code;
		this.prfrm = prfrm;
		this.category = category;
		this.stock = stock;
		this.price = price;
	}
	
	public String getPrdctName() {
		return prdctName;
	}
	public void setPrdctName(String prdctName) {
		this.prdctName = prdctName;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPrfrm() {
		return prfrm;
	}
	public void setPrfrm(String prfrm) {
		this.prfrm = prfrm;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}