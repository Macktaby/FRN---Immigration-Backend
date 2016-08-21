package com.services;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.Brand;
import com.models.Catalog;
import com.models.Category;
import com.models.Designer;
import com.models.Product;
import com.models.ShowRoom;
import com.models.Sponsor;
import com.models.User;

public class JSONBuilder {

	/************************ JSON Build Functions ************************/

	@SuppressWarnings("unchecked")
	public static JSONObject convertUserToJSON(User user) {
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
	public static JSONObject convertProductsToJSON(ArrayList<Product> products) {

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
	public static JSONObject convertProductToJSON(Product product) {

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
	public static JSONObject convertSponsorsToJSON(ArrayList<Sponsor> sponsors) {
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
	public static JSONObject convertSponsorToJSON(Sponsor sponsor) {
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
	public static JSONObject convertCatalogsToJSON(ArrayList<Catalog> catalogs) {

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
	public static JSONObject convertCatalogToJSON(Catalog catalog) {

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
	public static JSONObject convertShowRoomsToJSON(ArrayList<ShowRoom> showrooms) {

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
	public static JSONObject convertShowRoomToJSON(ShowRoom showroom) {

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
	public static JSONObject convertCategoriesToJSON(ArrayList<Category> categories) {

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
	public static JSONObject convertCategoryToJSON(Category category) {

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
	public static JSONObject convertBrandsToJSON(ArrayList<Brand> brands) {

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
	public static JSONObject convertBrandToJSON(Brand brand) {

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
	public static JSONObject convertImagesToJSON(ArrayList<String> images) {

		JSONObject json = new JSONObject();
		JSONArray jsonArr = new JSONArray();

		for (String image : images)
			jsonArr.add(new JSONObject().put("image", image));

		json.put("images", jsonArr);
		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertDesignersToJSON(ArrayList<Designer> designers) {

		JSONObject json = new JSONObject();

		if (designers == null) {
			json.put("state", "false");
		} else {

			JSONArray jsonArr = new JSONArray();
			for (Designer designer : designers)
				jsonArr.add(convertDesignerToJSON(designer));

			json.put("designers", jsonArr);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertDesignerToJSON(Designer designer) {

		JSONObject json = new JSONObject();

		if (designer == null)
			json.put("state", "false");
		else {

			json.put("id", designer.getDesignerID());
			json.put("name", designer.getName());
			json.put("email", designer.getEmail());
			json.put("phone", designer.getPhone());
			json.put("address", designer.getAddress());
			json.put("website", designer.getWebsite());
			json.put("rating", designer.getRating());
			json.put("nRating", designer.getnRatingUsers());
			json.put("image", designer.getProfileImage());
		}

		return json;
	}

}
