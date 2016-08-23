package com.services;

import java.sql.Timestamp;
import java.util.List;

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
			@FormParam("isDayProd") boolean isDayProduct, @FormParam("images") List<String> images,
			@FormParam("catID") int categoryID, @FormParam("catName") String categoryName,
			@FormParam("showroomID") int showroomID, @FormParam("showroomName") String showroomName,
			@FormParam("brandID") int brandID, @FormParam("brandName") String brandName) {

		Product product = new Product(0, name, desc, image, quantity, price, 0, 0, isDayProduct, images, categoryID,
				categoryName, showroomID, showroomName, brandID, brandName);

		ProductBean pb = new ProductBean();
		product = pb.addProduct(product);

		return JSONBuilder.convertProductToJSON(product).toJSONString();
	}

	@POST
	@Path("/updateProduct")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProduct(@FormParam("id") int id, @FormParam("name") String name, @FormParam("desc") String desc,
			@FormParam("image") String image, @FormParam("quantity") int quantity, @FormParam("price") double price,
			@FormParam("isDayProd") boolean isDayProduct, @FormParam("images") List<String> images,
			@FormParam("catID") int categoryID, @FormParam("catName") String categoryName,
			@FormParam("showroomID") int showroomID, @FormParam("showroomName") String showroomName,
			@FormParam("brandID") int brandID, @FormParam("brandName") String brandName) {

		Product product = new Product(id, name, desc, image, quantity, price, 0, 0, isDayProduct, images, categoryID,
				categoryName, showroomID, showroomName, brandID, brandName);

		ProductBean pb = new ProductBean();
		return pb.updateProduct(product);
	}

	@POST
	@Path("/deleteProduct")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProduct(@FormParam("id") int productID) {
		ProductBean pb = new ProductBean();
		return pb.deleteProduct(productID);
	}

	/*************************** ShowRoom *********************************/

	@POST
	@Path("/addShowroom")
	@Produces(MediaType.TEXT_PLAIN)
	public String addShowRoom(@FormParam("name") String name, @FormParam("desc") String desc,
			@FormParam("address") String address, @FormParam("location") String location,
			@FormParam("phone") String phone, @FormParam("image") String image) {

		ShowRoom showroom = new ShowRoom(0, name, desc, address, location, phone, image);

		ShowRoomBean sb = new ShowRoomBean();
		showroom = sb.addShowRoom(showroom);

		return JSONBuilder.convertShowRoomToJSON(showroom).toJSONString();
	}

	@POST
	@Path("/updateShowroom")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateShowRoom(@FormParam("id") int showroomID, @FormParam("name") String name,
			@FormParam("desc") String desc, @FormParam("address") String address,
			@FormParam("location") String location, @FormParam("phone") String phone,
			@FormParam("image") String image) {

		ShowRoom showroom = new ShowRoom(showroomID, name, desc, address, location, phone, image);

		ShowRoomBean sb = new ShowRoomBean();
		return sb.updateShowRoom(showroom);
	}

	@POST
	@Path("/deleteShowroom")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteShowRoom(@FormParam("id") int showroomID) {

		ShowRoomBean sb = new ShowRoomBean();
		return sb.deleteShowRoom(showroomID);
	}

	/*************************** Category *********************************/
	/*************************** Category *********************************/
	@POST
	@Path("/addCategory")
	@Produces(MediaType.TEXT_PLAIN)
	public String addCategory(@FormParam("name") String name, @FormParam("desc") String desc) {

		Category category = new Category(0, name, desc);

		CategoryBean cb = new CategoryBean();
		category = cb.addCategory(category);

		return JSONBuilder.convertCategoryToJSON(category).toJSONString();
	}

	@POST
	@Path("/updateCategory")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCategory(@FormParam("id") int categoryID, @FormParam("name") String name,
			@FormParam("desc") String desc) {

		Category category = new Category(categoryID, name, desc);

		CategoryBean cb = new CategoryBean();
		return cb.updateCategory(category);
	}

	@POST
	@Path("/deleteCategory")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCategory(@FormParam("id") int categoryID) {
		CategoryBean cb = new CategoryBean();
		return cb.deleteCategory(categoryID);
	}

	/*************************** Brand *********************************/
	/*************************** Brand *********************************/

	@POST
	@Path("/addBrand")
	@Produces(MediaType.TEXT_PLAIN)
	public String addBrand(@FormParam("name") String name, @FormParam("desc") String desc,
			@FormParam("image") String image) {

		Brand brand = new Brand(0, name, desc, image);

		BrandBean bb = new BrandBean();
		brand = bb.addBrand(brand);

		return JSONBuilder.convertBrandToJSON(brand).toJSONString();
	}

	@POST
	@Path("/updateBrand")
	@Produces(MediaType.TEXT_PLAIN)

	public String updateBrand(@FormParam("id") int brandID, @FormParam("name") String name,
			@FormParam("desc") String desc, @FormParam("image") String image) {

		Brand brand = new Brand(brandID, name, desc, image);

		BrandBean bb = new BrandBean();
		return bb.updateBrand(brand);
	}

	@POST
	@Path("/deleteBrand")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBrand(@FormParam("id") int brandID) {

		BrandBean bb = new BrandBean();
		return bb.deleteBrand(brandID);
	}

	/*************************** Catalog *********************************/

	@POST
	@Path("/addCatalog")
	@Produces(MediaType.TEXT_PLAIN)
	public String addCatalog(@FormParam("name") String name, @FormParam("desc") String desc,
			@FormParam("date") Timestamp date, @FormParam("pdfLink") String pdfLink) {

		Catalog catalog = new Catalog(0, name, desc, date, pdfLink);

		CatalogBean cb = new CatalogBean();
		catalog = cb.addCatalog(catalog);

		return JSONBuilder.convertCatalogToJSON(catalog).toJSONString();
	}

	@POST
	@Path("/updateCatalog")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCatalog(@FormParam("id") int catalogID, @FormParam("name") String name,
			@FormParam("desc") String desc, @FormParam("date") Timestamp date, @FormParam("pdfLink") String pdfLink) {

		Catalog catalog = new Catalog(catalogID, name, desc, date, pdfLink);

		CatalogBean cb = new CatalogBean();
		return cb.updateCatalog(catalog);
	}

	@POST
	@Path("/deleteCatalog")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCatalog(@FormParam("id") int catalogID) {

		CatalogBean cb = new CatalogBean();
		return cb.deleteCatalog(catalogID);
	}
}
