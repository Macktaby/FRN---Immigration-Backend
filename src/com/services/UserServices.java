package com.services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.beans.BrandBean;
import com.beans.CatalogBean;
import com.beans.CategoryBean;
import com.beans.DesignerBean;
import com.beans.ProductBean;
import com.beans.ProductImagesBean;
import com.beans.ShowRoomBean;
import com.beans.SponsorBean;
import com.beans.UserBean;
import com.models.Brand;
import com.models.Catalog;
import com.models.Category;
import com.models.Designer;
import com.models.Product;
import com.models.ShowRoom;
import com.models.Sponsor;
import com.models.User;

@Path("/")
public class UserServices {

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String login(@FormParam("email") String email, @FormParam("pass") String pass) {

		UserBean ub = new UserBean();
		User user = ub.getUser(email, pass);

		return convertUserToJSON(user).toJSONString();
	}

	@POST
	@Path("/signup")
	@Produces(MediaType.TEXT_PLAIN)
	public String signup(@FormParam("uname") String userName, @FormParam("pass") String password,
			@FormParam("nickname") String nickName, @FormParam("email") String email,
			@FormParam("website") String website, @FormParam("phone") String phone,
			@FormParam("isAdmin") Boolean isAdmin) {

		UserBean ub = new UserBean();
		User user = ub.addUser(userName, password, nickName, email, website, phone, isAdmin);

		return convertUserToJSON(user).toJSONString();
	}

	/*************************** Home Tab *********************************/

	@POST
	@Path("/getDayProducts")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProductsOfTheDay() {

		ProductBean pb = new ProductBean();
		ArrayList<Product> products = pb.getProductsOfTheDay();

		return convertProductsToJSON(products).toJSONString();
	}

	@POST
	@Path("/getSponsors")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSponsors() {

		SponsorBean sb = new SponsorBean();
		ArrayList<Sponsor> sponsors = sb.getSponsors();

		return convertSponsorsToJSON(sponsors).toJSONString();
	}

	/*************************** Catalog Tab *********************************/

	@POST
	@Path("/getCatalogs")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCatalogs() {

		CatalogBean cb = new CatalogBean();
		ArrayList<Catalog> catalogs = cb.getCatalogs();

		return convertCatalogsToJSON(catalogs).toJSONString();
	}

	/*************************** Products Tab *********************************/

	@POST
	@Path("/getProducts")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProducts() {

		ProductBean pb = new ProductBean();
		ArrayList<Product> products = pb.getAllProducts();

		return convertProductsToJSON(products).toJSONString();
	}

	@POST
	@Path("/getShowRooms")
	@Produces(MediaType.TEXT_PLAIN)
	public String getShowrooms() {

		ShowRoomBean sb = new ShowRoomBean();
		ArrayList<ShowRoom> showrooms = sb.getShowRooms();

		return convertShowRoomsToJSON(showrooms).toJSONString();
	}

	@POST
	@Path("/getCategories")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCategories() {

		CategoryBean cb = new CategoryBean();
		ArrayList<Category> categories = cb.getCategories();

		return convertCategoriesToJSON(categories).toJSONString();
	}

	@POST
	@Path("/getBrands")
	@Produces(MediaType.TEXT_PLAIN)
	public String getBrands() {

		BrandBean bb = new BrandBean();
		ArrayList<Brand> brands = bb.getBrands();

		return convertBrandsToJSON(brands).toJSONString();
	}

	@POST
	@Path("/filterProducts")
	@Produces(MediaType.TEXT_PLAIN)
	public String filterProducts(@FormParam("brandID") int brandID, @FormParam("catID") int categoryID,
			@FormParam("showroomID") int showRoomID) {

		ProductBean pb = new ProductBean();
		ArrayList<Product> products = pb.getFilteredProducts(brandID, categoryID, showRoomID);

		return convertProductsToJSON(products).toJSONString();
	}

	/************************ Product Page ************************/

	@POST
	@Path("getProductImages")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProductImages(@FormParam("productID") int productID) {

		ProductImagesBean pb = new ProductImagesBean();
		ArrayList<String> images = pb.getProductImages(productID);

		return convertImagesToJSON(images).toJSONString();
	}

	/************************ ShowRoom Tab ************************/

	// TODO TO BE specified LATER
	@POST
	@Path("getShowRoomProducts")
	@Produces(MediaType.TEXT_PLAIN)
	public String getShowRoomProducts(@FormParam("showroomID") int showRoomID) {

		ProductBean pb = new ProductBean();
		ArrayList<Product> products = pb.getFilteredProducts(0, 0, showRoomID);

		return convertProductsToJSON(products).toJSONString();

	}

	/************************ Designer Tab ************************/
	@POST
	@Path("/getDesigners")
	@Produces(MediaType.TEXT_PLAIN)
	public String getDesigners() {

		DesignerBean pb = new DesignerBean();
		ArrayList<Designer> designers = pb.getDesigners();

		return convertDesignersToJSON(designers).toJSONString();
	}

	@POST
	@Path("/filterDesigners")
	@Produces(MediaType.TEXT_PLAIN)
	public String filterProducts(@FormParam("name") String name) {

		DesignerBean pb = new DesignerBean();
		ArrayList<Designer> designers = pb.getFilteredDesigners(name);

		return convertDesignersToJSON(designers).toJSONString();
	}

	
	/************************ JSON Build Functions ************************/

	@POST
	@SuppressWarnings("unchecked")
	public JSONObject convertUserToJSON(User user) {
		JSONObject json = new JSONObject();

		if (user == null)
			json.put("state", "false");
		else {
			json.put("id", user.getUserID());
			json.put("uname", user.getUserName());
			json.put("pass", user.getPassword());
			json.put("nickname", user.getNickName());
			json.put("email", user.getEmail());
			json.put("website", user.getWebsite());
			json.put("phone", user.getPhone());
			json.put("regTime", user.getRegisterTime());
			json.put("actKey", user.getActivationKey());
			json.put("status", user.getUserStatus());
			json.put("isAdmin", user.isAdmin());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject convertProductsToJSON(ArrayList<Product> products) {

		JSONObject json = new JSONObject();

		if (products == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Product product : products)
				jsonArr.add(convertProductToJSON(product));

			json.put("products", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject convertProductToJSON(Product product) {

		JSONObject json = new JSONObject();

		if (product == null)
			json.put("state", "false");
		else {
			json.put("id", product.getProductID());
			json.put("name", product.getName());
			json.put("desc", product.getDescription());
			json.put("image", product.getImage());
			json.put("quantity", product.getQuantity());
			json.put("price", product.getPrice());
			json.put("avgRating", product.getRating());
			json.put("numRating", product.getNumRatingUsers());
			json.put("isDayProd", product.isDayProd());

			json.put("catID", product.getCategoryID());
			json.put("showroomID", product.getShowRoomID());
			json.put("brandID", product.getBrandID());
			json.put("catName", product.getCategoryName());
			json.put("showroomName", product.getShowRoomName());
			json.put("brandName", product.getBrandName());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject convertSponsorsToJSON(ArrayList<Sponsor> sponsors) {
		JSONObject json = new JSONObject();

		if (sponsors == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Sponsor sponsor : sponsors)
				jsonArr.add(convertSponsorToJSON(sponsor));

			json.put("sponsors", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject convertSponsorToJSON(Sponsor sponsor) {
		JSONObject json = new JSONObject();

		if (sponsor == null)
			json.put("state", "false");
		else {
			json.put("id", sponsor.getSponserID());
			json.put("name", sponsor.getName());
			json.put("image", sponsor.getImage());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject convertCatalogsToJSON(ArrayList<Catalog> catalogs) {

		JSONObject json = new JSONObject();

		if (catalogs == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Catalog catalog : catalogs)
				jsonArr.add(convertCatalogToJSON(catalog));

			json.put("catalogs", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject convertCatalogToJSON(Catalog catalog) {

		JSONObject json = new JSONObject();

		if (catalog == null)
			json.put("state", "false");
		else {

			json.put("id", catalog.getCatalogID());
			json.put("name", catalog.getName());
			json.put("desc", catalog.getDescription());
			json.put("date", catalog.getDate().toString());
			json.put("pdf", catalog.getPdfLink());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject convertShowRoomsToJSON(ArrayList<ShowRoom> showrooms) {

		JSONObject json = new JSONObject();

		if (showrooms == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (ShowRoom showroom : showrooms)
				jsonArr.add(convertShowRoomToJSON(showroom));

			json.put("showrooms", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject convertShowRoomToJSON(ShowRoom showroom) {

		JSONObject json = new JSONObject();

		if (showroom == null)
			json.put("state", "false");
		else {
			json.put("id", showroom.getShowRoomID());
			json.put("name", showroom.getName());
			json.put("desc", showroom.getDescription());
			json.put("address", showroom.getAddress());
			json.put("location", showroom.getLocation());
			json.put("phone", showroom.getPhone());
			json.put("image", showroom.getImage());
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject convertCategoriesToJSON(ArrayList<Category> categories) {

		JSONObject json = new JSONObject();

		if (categories == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Category category : categories)
				jsonArr.add(convertCategoryToJSON(category));

			json.put("categories", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject convertCategoryToJSON(Category category) {

		JSONObject json = new JSONObject();

		if (category == null)
			json.put("state", "false");
		else {
			json.put("id", category.getCategoryID());
			json.put("name", category.getName());
			json.put("desc", category.getDescription());
		}

		return json;

	}

	@SuppressWarnings("unchecked")
	public JSONObject convertBrandsToJSON(ArrayList<Brand> brands) {

		JSONObject json = new JSONObject();

		if (brands == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Brand brand : brands)
				jsonArr.add(convertBrandToJSON(brand));

			json.put("brands", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public JSONObject convertBrandToJSON(Brand brand) {

		JSONObject json = new JSONObject();

		if (brand == null)
			json.put("state", "false");
		else {
			json.put("id", brand.getBrandID());
			json.put("name", brand.getName());
			json.put("desc", brand.getDescription());
			json.put("image", brand.getImage());
		}

		return json;

	}

	@SuppressWarnings("unchecked")
	public JSONObject convertImagesToJSON(ArrayList<String> images) {

		JSONObject json = new JSONObject();
		JSONArray jsonArr = new JSONArray();

		for (String image : images)
			jsonArr.add(new JSONObject().put("image", image));

		json.put("images", jsonArr);
		return json;
	}

	private JSONArray convertDesignersToJSON(ArrayList<Designer> designers) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Hello after editing";
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}
}
