package product;

import java.util.ArrayList;

public class ProductReponsitory {
	
	private ArrayList<Product> pdList = new ArrayList<Product>();
	
	public ProductReponsitory (){
		
	Product iphone = new Product("iPhone6", "4.7inch, 1334X750 Renina HD display" + 
			"8-megapixel iSight Camera", null, "Apple", "smartprhone", 1000, 8000000);

	
	Product gram = new Product("gram", "13.3-inch, IPS LED display, 5rd Generation"
			+ "Intel Core processors", null, "LG", "notebook", 1000, 8000000);

	
	Product galaxy = new Product("galaxy", "4.7inch, 1334X750 Renina HD display" + 
			"8-megapixel iSight Camera", null, "Apple", "smartprhone", 1000, 8000000);
	
	
	pdList.add(iphone);
	pdList.add(gram);
	pdList.add(galaxy);
	
	
	}
}
