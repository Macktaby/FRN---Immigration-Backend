package com.services;

import java.sql.Timestamp;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.beans.BrandBean;
import com.beans.CatalogBean;
import com.beans.CategoryBean;
import com.beans.ProductBean;
import com.beans.ShowRoomBean;
import com.models.Brand;
import com.models.Catalog;
import com.models.Category;
import com.models.Product;
import com.models.ShowRoom;

@Path("/admin")
public class VendorServices {

	/*************************** Product *********************************/

	@POST
	@Path("/addProduct")
	@Produces(MediaType.TEXT_PLAIN)
	public String addProduct(@FormParam("name") String name, @FormParam("desc") String desc,
			@FormParam("image") String image, @FormParam("quantity") int quantity, @FormParam("price") double price,
			@FormParam("isDayProd") boolean isDayProduct, @FormParam("catID") int categoryID,
			@FormParam("catName") String categoryName, @FormParam("showroomID") int showroomID,
			@FormParam("showroomName") String showroomName, @FormParam("brandID") int brandID,
			@FormParam("brandName") String brandName) {

		Product product = createProduct(name, desc, image, quantity, price, isDayProduct, categoryID, categoryName,
				showroomID, showroomName, brandID, brandName);

		ProductBean pb = new ProductBean();
		product = pb.addProduct(product);

		return JSONBuilder.convertProductToJSON(product).toJSONString();
	}

	private Product createProduct(String name, String desc, String image, int quantity, double price,
			boolean isDayProduct, int categoryID, String categoryName, int showroomID, String showroomName, int brandID,
			String brandName) {

		Product product = new Product();

		product.setName(name);
		product.setDescription(desc);
		product.setImage(image);
		product.setQuantity(quantity);
		product.setPrice(price);
		product.setRating(0);
		product.setNumRatingUsers(0);
		product.setDayProd(isDayProduct);

		product.setCategoryID(categoryID);
		product.setCategoryName(categoryName);
		product.setShowRoomID(showroomID);
		product.setShowRoomName(showroomName);
		product.setBrandID(brandID);
		product.setBrandName(brandName);

		return product;
	}

	/*************************** ShowRoom *********************************/

	@POST
	@Path("/addShowroom")
	@Produces(MediaType.TEXT_PLAIN)
	public String addShowRoom(@FormParam("name") String name, @FormParam("desc") String desc,
			@FormParam("address") String address, @FormParam("location") String location,
			@FormParam("phone") String phone, @FormParam("image") String image) {

		ShowRoom showroom = createShowRoom(name, desc, address, location, phone, image);

		ShowRoomBean sb = new ShowRoomBean();
		showroom = sb.addShowRoom(showroom);

		return JSONBuilder.convertShowRoomToJSON(showroom).toJSONString();
	}

	private ShowRoom createShowRoom(String name, String desc, String address, String location, String phone,
			String image) {
		ShowRoom showroom = new ShowRoom();

		showroom.setName(name);
		showroom.setDescription(desc);
		showroom.setAddress(address);
		showroom.setLocation(location);
		showroom.setPhone(phone);
		showroom.setImage(image);
		return showroom;
	}

	/*************************** Category *********************************/

	@POST
	@Path("/addCategory")
	@Produces(MediaType.TEXT_PLAIN)
	public String addCategory(@FormParam("name") String name, @FormParam("desc") String desc) {

		Category category = createCategory(name, desc);

		CategoryBean cb = new CategoryBean();
		category = cb.addCategory(category);

		return JSONBuilder.convertCategoryToJSON(category).toJSONString();
	}

	private Category createCategory(String name, String desc) {

		Category category = new Category();

		category.setName(name);
		category.setDescription(desc);
		return category;
	}

	/*************************** Brand *********************************/

	@POST
	@Path("/addBrand")
	@Produces(MediaType.TEXT_PLAIN)
	public String addBrand(@FormParam("name") String name, @FormParam("desc") String desc,
			@FormParam("image") String image) {

		Brand brand = createBrand(name, desc, image);

		BrandBean bb = new BrandBean();
		brand = bb.addBrand(brand);

		return JSONBuilder.convertBrandToJSON(brand).toJSONString();
	}

	private Brand createBrand(String name, String desc, String image) {

		Brand brand = new Brand();

		brand.setName(name);
		brand.setDescription(desc);
		brand.setImage(image);
		return brand;
	}

	/*************************** Catalog *********************************/

	@POST
	@Path("/addCatalog")
	@Produces(MediaType.TEXT_PLAIN)
	public String addCatalog(@FormParam("name") String name, @FormParam("desc") String desc,
			@FormParam("date") Timestamp date, @FormParam("pdfLink") String pdfLink) {

		Catalog catalog = createCatalog(name, desc, date, pdfLink);

		CatalogBean cb = new CatalogBean();
		catalog = cb.addCatalog(catalog);

		return JSONBuilder.convertCatalogToJSON(catalog).toJSONString();
	}

	private Catalog createCatalog(String name, String desc, Timestamp date, String pdfLink) {

		Catalog catalog = new Catalog();

		catalog.setName(name);
		catalog.setDescription(desc);
		catalog.setDate(date);
		catalog.setPdfLink(pdfLink);

		return catalog;
	}

}
