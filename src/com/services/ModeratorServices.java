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
import com.beans.DesignerBean;
import com.beans.DesignerImagesBean;
import com.beans.EnvogueBean;
import com.beans.HouseBean;
import com.beans.ProductBean;
import com.beans.ProductImagesBean;
import com.beans.PromotionLocationBean;
import com.beans.PromotionWishlistBean;
import com.beans.ShowRoomBean;
import com.models.Brand;
import com.models.Catalog;
import com.models.Category;
import com.models.Designer;
import com.models.Envogue;
import com.models.House;
import com.models.Product;
import com.models.PromotionLocation;
import com.models.PromotionWishlist;
import com.models.ShowRoom;

@Path("/admin")
public class ModeratorServices {

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

		if (product != null)
			addProductImages(product.getProductID(), images);

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
		String state = pb.updateProduct(product);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/deleteProduct")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProduct(@FormParam("id") int productID) {
		ProductBean pb = new ProductBean();
		String state = pb.deleteProduct(productID);
		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("addProductImages")
	@Produces(MediaType.TEXT_PLAIN)
	public String addProductImages(@FormParam("productID") int productID,
			@FormParam("images") List<String> productImages) {

		ProductImagesBean pb = new ProductImagesBean();
		String state = pb.addProductImages(productID, productImages);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
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
		String state = sb.updateShowRoom(showroom);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/deleteShowroom")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteShowRoom(@FormParam("id") int showroomID) {

		ShowRoomBean sb = new ShowRoomBean();
		String state = sb.deleteShowRoom(showroomID);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

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
		String state = cb.updateCategory(category);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/deleteCategory")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCategory(@FormParam("id") int categoryID) {

		CategoryBean cb = new CategoryBean();
		String state = cb.deleteCategory(categoryID);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

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
		String state = bb.updateBrand(brand);
		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/deleteBrand")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBrand(@FormParam("id") int brandID) {

		BrandBean bb = new BrandBean();
		String state = bb.deleteBrand(brandID);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	/*************************** Catalog *********************************/

	@POST
	@Path("/addCatalog")
	@Produces(MediaType.TEXT_PLAIN)
	public String addCatalog(@FormParam("name") String name, @FormParam("desc") String desc,
			@FormParam("month") String month, @FormParam("year") int year, @FormParam("pdfLink") String pdfLink) {

		Catalog catalog = new Catalog(0, name, desc, month, year, pdfLink);

		CatalogBean cb = new CatalogBean();
		catalog = cb.addCatalog(catalog);

		return JSONBuilder.convertCatalogToJSON(catalog).toJSONString();
	}

	@POST
	@Path("/updateCatalog")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCatalog(@FormParam("id") int catalogID, @FormParam("name") String name,
			@FormParam("desc") String desc, @FormParam("month") String month, @FormParam("year") int year,
			@FormParam("pdfLink") String pdfLink) {

		Catalog catalog = new Catalog(catalogID, name, desc, month, year, pdfLink);

		CatalogBean cb = new CatalogBean();
		String state = cb.updateCatalog(catalog);
		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/deleteCatalog")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCatalog(@FormParam("id") int catalogID) {

		CatalogBean cb = new CatalogBean();
		String state = cb.deleteCatalog(catalogID);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	/*************************** Promotions *********************************/

	@POST
	@Path("/addLocationPromotion")
	@Produces(MediaType.TEXT_PLAIN)
	public String addLocationPromotion(@FormParam("productID") int productID, @FormParam("location") String location,
			@FormParam("discount") int discount, @FormParam("start") Timestamp start, @FormParam("end") Timestamp end) {

		PromotionLocation promotion = new PromotionLocation(0, discount, start, end, productID, location);

		PromotionLocationBean plb = new PromotionLocationBean();
		promotion = plb.addLocationPromotion(promotion);

		return JSONBuilder.convertLocationPromotionToJSON(promotion).toJSONString();
	}

	@POST
	@Path("/deleteLocationPromotion")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteLocationPromotion(@FormParam("promotionID") int promotionID) {

		PromotionLocationBean plb = new PromotionLocationBean();
		String state = plb.deleteLocationPromotion(promotionID);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/addWishlistPromotion")
	@Produces(MediaType.TEXT_PLAIN)
	public String addWishlistPromotion(
			@FormParam("productID") int productID, 
			@FormParam("discount") int discount, 
			@FormParam("start") Timestamp start, 
			@FormParam("end") Timestamp end) {

		PromotionWishlist promotion = new PromotionWishlist(0, discount, start, end, productID, 0);

		PromotionWishlistBean pwb = new PromotionWishlistBean();
		promotion = pwb.addWishlistPromotion(promotion);

		if (promotion == null)
			return JSONBuilder.convertStateToJSON("Error adding promotion").toJSONString();

		String state = pwb.addPromotionUsers(promotion.getPromotionID(), discount, productID);

		if (state.equals("true"))
			return JSONBuilder.convertWishlistPromotionToJSON(promotion).toJSONString();

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/deleteWishlistPromotion")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteWishlistPromotion(@FormParam("promotionID") int promotionID) {

		PromotionWishlistBean plb = new PromotionWishlistBean();
		String state = plb.deleteWishlistPromotion(promotionID);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	/*************************** House *********************************/

	@POST
	@Path("/addHouse")
	@Produces(MediaType.TEXT_PLAIN)
	public String addHouse(@FormParam("name") String name, @FormParam("desc") String desc,
			@FormParam("image") String image) {

		House house = new House(0, name, desc, image);
		HouseBean hb = new HouseBean();
		house = hb.addHouse(house);

		return JSONBuilder.convertHouseToJSON(house).toJSONString();
	}

	@POST
	@Path("/updateHouse")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHouse(@FormParam("id") int id, @FormParam("name") String name, @FormParam("desc") String desc,
			@FormParam("image") String image) {

		House house = new House(id, name, desc, image);
		HouseBean hb = new HouseBean();
		String state = hb.updateHouse(house);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/deleteHouse")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHouse(@FormParam("id") int id) {

		HouseBean hb = new HouseBean();
		String state = hb.deleteHouse(id);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	/*************************** Envogue *********************************/

	@POST
	@Path("/addEnvogue")
	@Produces(MediaType.TEXT_PLAIN)
	public String addEnvogue(@FormParam("name") String name, @FormParam("desc") String desc,
			@FormParam("image") String image) {

		Envogue envogue = new Envogue(0, name, desc, image);
		EnvogueBean eb = new EnvogueBean();
		envogue = eb.addEnvogue(envogue);

		return JSONBuilder.convertEnvogueToJSON(envogue).toJSONString();
	}

	@POST
	@Path("/updateEnvogue")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEnvogue(@FormParam("id") int id, @FormParam("name") String name, @FormParam("desc") String desc,
			@FormParam("image") String image) {

		Envogue envogue = new Envogue(id, name, desc, image);
		EnvogueBean eb = new EnvogueBean();
		String state = eb.updateEnvogue(envogue);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/deleteEnvogue")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEnvogue(@FormParam("id") int id) {

		EnvogueBean eb = new EnvogueBean();
		String state = eb.deleteEnvogue(id);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	/*************************** Designer *********************************/

	@POST
	@Path("/addDesigner")
	@Produces(MediaType.TEXT_PLAIN)
	public String addDesigner(@FormParam("name") String name, @FormParam("email") String email,
			@FormParam("phone") String phone, @FormParam("address") String address,
			@FormParam("website") String website, @FormParam("rating") double rating,
			@FormParam("nRating") int nRatingUsers, @FormParam("image") String image,
			@FormParam("designs") List<String> designs) {

		Designer designer = new Designer(0, name, email, phone, address, website, rating, nRatingUsers, image, designs);
		DesignerBean db = new DesignerBean();
		designer = db.addDesigner(designer);

		if (designer != null)
			addDesignerImages(designer.getDesignerID(), designs);

		return JSONBuilder.convertDesignerToJSON(designer).toJSONString();
	}

	@POST
	@Path("/updateDesigner")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDesigner(@FormParam("id") int id, @FormParam("name") String name,
			@FormParam("email") String email, @FormParam("phone") String phone, @FormParam("address") String address,
			@FormParam("website") String website, @FormParam("rating") double rating,
			@FormParam("nRating") int nRatingUsers, @FormParam("image") String image,
			@FormParam("designs") List<String> designs) {

		Designer designer = new Designer(id, name, email, phone, address, website, rating, nRatingUsers, image,
				designs);
		DesignerBean db = new DesignerBean();
		String state = db.updateDesigner(designer);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/deleteDesigner")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDesigner(@FormParam("id") int id) {

		DesignerBean db = new DesignerBean();
		String state = db.deleteDesigner(id);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("addDesignerImages")
	@Produces(MediaType.TEXT_PLAIN)
	public String addDesignerImages(@FormParam("designerID") int designerID,
			@FormParam("images") List<String> designerImages) {

		DesignerImagesBean db = new DesignerImagesBean();
		String state = db.addDesignerImages(designerID, designerImages);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

}
