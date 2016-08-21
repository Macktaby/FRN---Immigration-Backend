package com.services;

import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

		return JSONBuilder.convertUserToJSON(user).toJSONString();
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

		return JSONBuilder.convertUserToJSON(user).toJSONString();
	}

	/*************************** Home Tab *********************************/

	@POST
	@Path("/getDayProducts")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProductsOfTheDay() {

		ProductBean pb = new ProductBean();
		ArrayList<Product> products = pb.getProductsOfTheDay();

		return JSONBuilder.convertProductsToJSON(products).toJSONString();
	}

	@POST
	@Path("/getSponsors")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSponsors() {

		SponsorBean sb = new SponsorBean();
		ArrayList<Sponsor> sponsors = sb.getSponsors();

		return JSONBuilder.convertSponsorsToJSON(sponsors).toJSONString();
	}

	/*************************** Catalog Tab *********************************/

	@POST
	@Path("/getCatalogs")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCatalogs() {

		CatalogBean cb = new CatalogBean();
		ArrayList<Catalog> catalogs = cb.getCatalogs();

		return JSONBuilder.convertCatalogsToJSON(catalogs).toJSONString();
	}

	/*************************** Products Tab *********************************/

	@POST
	@Path("/getProducts")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProducts() {

		ProductBean pb = new ProductBean();
		ArrayList<Product> products = pb.getAllProducts();

		return JSONBuilder.convertProductsToJSON(products).toJSONString();
	}

	@POST
	@Path("/getShowRooms")
	@Produces(MediaType.TEXT_PLAIN)
	public String getShowrooms() {

		ShowRoomBean sb = new ShowRoomBean();
		ArrayList<ShowRoom> showrooms = sb.getShowRooms();

		return JSONBuilder.convertShowRoomsToJSON(showrooms).toJSONString();
	}

	@POST
	@Path("/getCategories")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCategories() {

		CategoryBean cb = new CategoryBean();
		ArrayList<Category> categories = cb.getCategories();

		return JSONBuilder.convertCategoriesToJSON(categories).toJSONString();
	}

	@POST
	@Path("/getBrands")
	@Produces(MediaType.TEXT_PLAIN)
	public String getBrands() {

		BrandBean bb = new BrandBean();
		ArrayList<Brand> brands = bb.getBrands();

		return JSONBuilder.convertBrandsToJSON(brands).toJSONString();
	}

	@POST
	@Path("/filterProducts")
	@Produces(MediaType.TEXT_PLAIN)
	public String filterProducts(@FormParam("brandID") int brandID, @FormParam("catID") int categoryID,
			@FormParam("showroomID") int showRoomID) {

		ProductBean pb = new ProductBean();
		ArrayList<Product> products = pb.getFilteredProducts(brandID, categoryID, showRoomID);

		return JSONBuilder.convertProductsToJSON(products).toJSONString();
	}

	/************************ Product Page ************************/

	@POST
	@Path("getProductImages")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProductImages(@FormParam("productID") int productID) {

		ProductImagesBean pb = new ProductImagesBean();
		ArrayList<String> images = pb.getProductImages(productID);

		return JSONBuilder.convertImagesToJSON(images).toJSONString();
	}

	/************************ ShowRoom Tab ************************/

	// TODO TO BE specified LATER
	@POST
	@Path("getShowRoomProducts")
	@Produces(MediaType.TEXT_PLAIN)
	public String getShowRoomProducts(@FormParam("showroomID") int showRoomID) {

		ProductBean pb = new ProductBean();
		ArrayList<Product> products = pb.getFilteredProducts(0, 0, showRoomID);

		return JSONBuilder.convertProductsToJSON(products).toJSONString();

	}

	/************************ Designer Tab ************************/
	@POST
	@Path("/getDesigners")
	@Produces(MediaType.TEXT_PLAIN)
	public String getDesigners() {

		DesignerBean pb = new DesignerBean();
		ArrayList<Designer> designers = pb.getDesigners();

		return JSONBuilder.convertDesignersToJSON(designers).toJSONString();
	}

	@POST
	@Path("/filterDesigners")
	@Produces(MediaType.TEXT_PLAIN)
	public String filterProducts(@FormParam("name") String name) {

		DesignerBean pb = new DesignerBean();
		ArrayList<Designer> designers = pb.getFilteredDesigners(name);

		return JSONBuilder.convertDesignersToJSON(designers).toJSONString();
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
