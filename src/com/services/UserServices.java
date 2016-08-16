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

import com.beans.CatalogBean;
import com.beans.ProductBean;
import com.beans.SponsorBean;
import com.beans.UserBean;
import com.models.Catalog;
import com.models.Product;
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

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Hello after editing";
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}
}
