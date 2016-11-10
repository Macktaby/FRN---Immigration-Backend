package com.services;

import java.util.ArrayList;
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
import com.beans.DesignerReviewBean;
import com.beans.EnvogueBean;
import com.beans.FavoriteProductBean;
import com.beans.HouseBean;
import com.beans.ProductBean;
import com.beans.ProductImagesBean;
import com.beans.ProductReviewBean;
import com.beans.PromotionLocationBean;
import com.beans.PromotionWishlistBean;
import com.beans.ReportBean;
import com.beans.ReservationBean;
import com.beans.ShowRoomBean;
import com.beans.SponsorBean;
import com.beans.UserBean;
import com.models.Brand;
import com.models.Catalog;
import com.models.Category;
import com.models.Designer;
import com.models.DesignerReview;
import com.models.Envogue;
import com.models.FavoriteProduct;
import com.models.House;
import com.models.Product;
import com.models.ProductReview;
import com.models.PromotionLocation;
import com.models.PromotionWishlist;
import com.models.Report;
import com.models.Reservation;
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
			@FormParam("location") String location) {

		UserBean ub = new UserBean();
		User user = ub.addUser(userName, password, nickName, email, website, phone, false, location);

		return JSONBuilder.convertUserToJSON(user).toJSONString();
	}

	@POST
	@Path("/updateProfile")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProfile(@FormParam("userID") int id, @FormParam("uname") String userName,
			@FormParam("pass") String password, @FormParam("nickname") String nickName,
			@FormParam("website") String website, @FormParam("phone") String phone,
			@FormParam("location") String location) {

		UserBean ub = new UserBean();
		String state = ub.updateUser(id, userName, password, nickName, website, phone, location);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/updatePassword")
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePassword(@FormParam("userID") int id, @FormParam("pass") String password) {

		UserBean ub = new UserBean();
		Boolean state = ub.updatePassword(id, password);

		return JSONBuilder.convertStateToJSON(state + "").toJSONString();
	}

	@POST
	@Path("/checkEmail")
	@Produces(MediaType.TEXT_PLAIN)
	public String checkEmail(@FormParam("email") String email) {

		UserBean ub = new UserBean();
		Boolean state = ub.checkEmail(email);

		return JSONBuilder.convertStateToJSON(state + "").toJSONString();
	}

	@POST
	@Path("/checkUserName")
	@Produces(MediaType.TEXT_PLAIN)
	public String checkUserName(@FormParam("username") String username) {

		UserBean ub = new UserBean();
		Boolean state = ub.checkUserName(username);

		return JSONBuilder.convertStateToJSON(state + "").toJSONString();
	}

	@POST
	@Path("/getUser")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser(@FormParam("userID") int id) {

		UserBean ub = new UserBean();
		User user = ub.getUserByID(id);

		return JSONBuilder.convertUserToJSON(user).toJSONString();
	}

	@POST
	@Path("/getUserReservations")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUserReservations(@FormParam("userID") int id) {

		ReservationBean rb = new ReservationBean();
		ArrayList<Reservation> reservations = rb.getUserReservedProducts(id);

		return JSONBuilder.convertReservationsToJSON(reservations).toJSONString();
	}

	@POST
	@Path("/getUserWishlist")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUserWishlist(@FormParam("userID") int id) {

		ProductBean pb = new ProductBean();
		ArrayList<Product> products = pb.getUserFavoriteProducts(id);

		return JSONBuilder.convertProductsToJSON(products).toJSONString();
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

	@POST
	@Path("/searchCatalogs")
	@Produces(MediaType.TEXT_PLAIN)
	public String searchCatalogs(@FormParam("catalogName") String catalogName) {

		CatalogBean cb = new CatalogBean();
		ArrayList<Catalog> catalogs = cb.searchProducts(catalogName);

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

	@POST
	@Path("/filterProductsGroup")
	@Produces(MediaType.TEXT_PLAIN)
	public String filterProductsGroup(@FormParam("brands") List<Integer> brands,
			@FormParam("categories") List<Integer> categories, @FormParam("showrooms") List<Integer> showrooms) {

		ProductBean pb = new ProductBean();
		ArrayList<Product> products = pb.filterProductsGroup(brands, categories, showrooms);

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

	@POST
	@Path("getProductQuantity")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProductQuantity(@FormParam("productID") int productID) {

		ProductBean pb = new ProductBean();
		int quantity = pb.getProductQuantity(productID);

		return JSONBuilder.convertQuantityToJSON(quantity).toJSONString();
	}

	@POST
	@Path("reserveProduct")
	@Produces(MediaType.TEXT_PLAIN)
	public String reserveProduct(@FormParam("productID") int productID, @FormParam("userID") int userID,
			@FormParam("quantity") int quantity, @FormParam("userName") String userName,
			@FormParam("productName") String productName) {

		ProductBean pb = new ProductBean();
		String state = pb.reserveProductQuantity(productID, quantity);

		if (state.equals("true")) {
			Reservation reservation = new Reservation(0, userID, userName, productID, productName, quantity);
			ReservationBean reservationBean = new ReservationBean();
			reservation = reservationBean.addReservation(reservation);

			if (reservation != null) {
				Report report = new Report(0, "reserve", userID, userName, productID, productName, 0, "");
				ReportBean rb = new ReportBean();
				report = rb.addReport(report);
			}

			return JSONBuilder.convertReservationToJSON(reservation).toJSONString();
		}

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("cancelReservation")
	@Produces(MediaType.TEXT_PLAIN)
	public String cancelReservation(@FormParam("reservationID") int reservationID) {

		ReservationBean reservationBean = new ReservationBean();

		// Get the reservation details using reservation id
		Reservation reservation = reservationBean.getReservationDetails(reservationID);
		if (reservation == null)
			return JSONBuilder.convertStateToJSON("Reservation NOT Found !!!").toJSONString();

		// Delete Reservation record
		String state = reservationBean.cancelReservation(reservationID);
		if (!state.equals("true"))
			return JSONBuilder.convertStateToJSON(state).toJSONString();

		// Add reserved quantity of product to available one
		ProductBean pb = new ProductBean();
		state = pb.addProductQuantity(reservation.getProduct().getProductID(), reservation.getQuantity());
		if (!state.equals("true"))
			return JSONBuilder.convertStateToJSON(state).toJSONString();

		// Add report about reservation cancellation
		Report report = new Report(0, "cancel", reservation.getUserID(), reservation.getUserName(),
				reservation.getProduct().getProductID(), reservation.getProduct().getName(), 0, "");
		ReportBean rb = new ReportBean();
		report = rb.addReport(report);
		if (report == null)
			return JSONBuilder.convertStateToJSON("ERROR adding the Report!!!").toJSONString();
		return JSONBuilder.convertReportToJSON(report).toJSONString();
	}

	@POST
	@Path("/addProductReview")
	@Produces(MediaType.TEXT_PLAIN)
	public String addProductReview(@FormParam("userID") int userID, @FormParam("productID") int productID,
			@FormParam("review") String review, @FormParam("rating") int rating) {

		ProductReview pr = new ProductReview(productID, userID, review, rating);

		ProductReviewBean prb = new ProductReviewBean();
		pr = prb.addReview(pr);

		return JSONBuilder.convertProductReviewToJSON(pr).toJSONString();
	}

	@POST
	@Path("/getProductReviews")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProductReviews(@FormParam("productID") int productID) {

		ProductReviewBean prb = new ProductReviewBean();
		ArrayList<ProductReview> reviews = prb.getReviews(productID);

		return JSONBuilder.convertProductReviewsToJSON(reviews).toJSONString();
	}

	@POST
	@Path("/addFavorite")
	@Produces(MediaType.TEXT_PLAIN)
	public String addFavorite(@FormParam("userID") int userID, @FormParam("productID") int productID) {

		FavoriteProduct fp = new FavoriteProduct(0, userID, productID);

		FavoriteProductBean fpb = new FavoriteProductBean();
		String state = fpb.addFavorite(fp);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/removeFavorite")
	@Produces(MediaType.TEXT_PLAIN)
	public String removeFavorite(@FormParam("userID") int userID, @FormParam("productID") int productID) {

		FavoriteProduct fp = new FavoriteProduct(0, userID, productID);

		FavoriteProductBean fpb = new FavoriteProductBean();
		String state = fpb.removeFavorite(fp);

		return JSONBuilder.convertStateToJSON(state).toJSONString();
	}

	@POST
	@Path("/getProductPrice")
	@Produces(MediaType.TEXT_PLAIN)
	public String getProductPriceForCertainUser(@FormParam("productID") int productID, @FormParam("userID") int userID,
			@FormParam("location") String location, @FormParam("price") double price) {

		PromotionLocationBean promLocBean = new PromotionLocationBean();
		PromotionWishlistBean promWishlistBean = new PromotionWishlistBean();

		PromotionLocation locationPromotion = promLocBean.getPromotion(productID, location);
		PromotionWishlist wishlistPromotion = promWishlistBean.getPromotion(productID, userID);

		int discount = 0;
		if (locationPromotion != null)
			discount += locationPromotion.getDiscount();
		if (wishlistPromotion != null)
			discount += wishlistPromotion.getDiscount();

		price -= ((discount) * 0.01 * price);

		return JSONBuilder.convertPriceToJSON(price).toJSONString();
	}

	/************************ ShowRoom Tab ************************/

	@POST
	@Path("getShowRoomProducts")
	@Produces(MediaType.TEXT_PLAIN)
	public String getShowRoomProducts(@FormParam("showroomID") int showRoomID) {

		ProductBean pb = new ProductBean();
		ArrayList<Product> products = pb.getFilteredProducts(0, 0, showRoomID);

		return JSONBuilder.convertProductsToJSON(products).toJSONString();
	}

	/************************ ShowRooms Tab ************************/

	@POST
	@Path("/getShowRooms")
	@Produces(MediaType.TEXT_PLAIN)
	public String getShowrooms() {

		ShowRoomBean sb = new ShowRoomBean();
		ArrayList<ShowRoom> showrooms = sb.getShowRooms();

		return JSONBuilder.convertShowRoomsToJSON(showrooms).toJSONString();
	}

	@POST
	@Path("searchShowrooms")
	@Produces(MediaType.TEXT_PLAIN)
	public String searchShowrooms(@FormParam("name") String name) {

		ShowRoomBean shb = new ShowRoomBean();
		ArrayList<ShowRoom> showrooms = shb.searchShowRooms(name);

		return JSONBuilder.convertShowRoomsToJSON(showrooms).toJSONString();
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
	@Path("/searchDesigners")
	@Produces(MediaType.TEXT_PLAIN)
	public String searchProducts(@FormParam("name") String name) {

		DesignerBean pb = new DesignerBean();
		ArrayList<Designer> designers = pb.getFilteredDesigners(name);

		return JSONBuilder.convertDesignersToJSON(designers).toJSONString();
	}

	@POST
	@Path("/contactDesigner")
	@Produces(MediaType.TEXT_PLAIN)
	public String contactDesigner(@FormParam("userID") int userID, @FormParam("designerID") int designerID,
			@FormParam("userName") String userName, @FormParam("designerName") String designerName) {

		Report report = new Report(0, "contact", userID, userName, 0, "", designerID, designerName);
		ReportBean rb = new ReportBean();
		report = rb.addReport(report);
		return JSONBuilder.convertReportToJSON(report).toJSONString();
	}

	@POST
	@Path("getDesignerDesigns")
	@Produces(MediaType.TEXT_PLAIN)
	public String getDesignerDesigns(@FormParam("designerID") int designerID) {

		DesignerImagesBean pb = new DesignerImagesBean();
		ArrayList<String> images = pb.getDesignerImages(designerID);

		return JSONBuilder.convertImagesToJSON(images).toJSONString();
	}

	@POST
	@Path("/addDesignerReview")
	@Produces(MediaType.TEXT_PLAIN)
	public String addDesignerReview(@FormParam("userID") int userID, @FormParam("designerID") int designerID,
			@FormParam("review") String review, @FormParam("rating") int rating) {

		DesignerReview dr = new DesignerReview(designerID, userID, review, rating);

		DesignerReviewBean drb = new DesignerReviewBean();
		dr = drb.addReview(dr);

		return JSONBuilder.convertDesignerReviewToJSON(dr).toJSONString();
	}

	@POST
	@Path("/getDesignerReviews")
	@Produces(MediaType.TEXT_PLAIN)
	public String getDesignerReviews(@FormParam("designerID") int designerID) {

		DesignerReviewBean drb = new DesignerReviewBean();
		ArrayList<DesignerReview> reviews = drb.getReviews(designerID);

		return JSONBuilder.convertDesignerReviewsToJSON(reviews).toJSONString();
	}

	/************************ Houses Tab ************************/

	@POST
	@Path("/getHouses")
	@Produces(MediaType.TEXT_PLAIN)
	public String getHouses() {

		HouseBean hb = new HouseBean();
		ArrayList<House> houses = hb.getHouses();

		return JSONBuilder.convertHousesToJSON(houses).toJSONString();
	}

	@POST
	@Path("/searchHouses")
	@Produces(MediaType.TEXT_PLAIN)
	public String searchHouses(@FormParam("name") String name) {

		HouseBean hb = new HouseBean();
		ArrayList<House> houses = hb.searchHouses(name);

		return JSONBuilder.convertHousesToJSON(houses).toJSONString();
	}

	/************************ Envogues Tab ************************/

	@POST
	@Path("/getEnvogues")
	@Produces(MediaType.TEXT_PLAIN)
	public String getEnvogues() {

		EnvogueBean eb = new EnvogueBean();
		ArrayList<Envogue> envogues = eb.getEnvogues();

		return JSONBuilder.convertEnvoguesToJSON(envogues).toJSONString();
	}

	@POST
	@Path("/searchEnvogues")
	@Produces(MediaType.TEXT_PLAIN)
	public String searchEnvogues(@FormParam("name") String name) {

		EnvogueBean eb = new EnvogueBean();
		ArrayList<Envogue> envogues = eb.searchEnvogues(name);

		return JSONBuilder.convertEnvoguesToJSON(envogues).toJSONString();
	}

	/************************ For test ONLY ************************/

	@POST
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Hello after editing";
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
	}
}
