package com.SportyShoes.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.SportyShoes.entity.Admin;
import com.SportyShoes.entity.Brands;
import com.SportyShoes.entity.Categories;
import com.SportyShoes.entity.Purchases;
import com.SportyShoes.entity.Shoes;
import com.SportyShoes.entity.Users;
import com.SportyShoes.repository.AdminRepository;
import com.SportyShoes.repository.BrandsRepository;
import com.SportyShoes.repository.CategoriesRepository;
import com.SportyShoes.repository.PurchasesRepository;
import com.SportyShoes.repository.ShoesRepository;
import com.SportyShoes.repository.UsersRepository;

@Controller
@Transactional
public class MvcController {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	UsersRepository usersRepository;

	@Autowired
	PurchasesRepository purchasesRepository;

	@Autowired
	ShoesRepository shoesRepository;
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
	@Autowired
	BrandsRepository brandsRepository;
	

	public String validate(String username, String password, String page, String newPage) {
		Admin data = adminRepository.findByUsername(username);
		
		if (!(password.equals(data.getPassword()))) {
			page = newPage;
		}
		return page;
	}

	public boolean validate(String username, String password) {
		Admin data = adminRepository.findByUsername(username);

		boolean page = false;
		if (password.equals(data.getPassword())) {
			page = true;
		}
		return page;
	}
	
	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public String showHome(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("firstname") String firstname, ModelMap map) {
		map.addAttribute("firstname", firstname);
		map.addAttribute("username", username);
		map.addAttribute("password", password);
		return "changePassword";
	}

	@RequestMapping(value = "mainScreen", method = RequestMethod.POST)
	public String showMain(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("firstname") String firstname, ModelMap map) {
		
		String page = validate(username, password, "mainScreen", "loginScreen");
		
		map.addAttribute("firstname", firstname);
		map.addAttribute("username", username);
		map.addAttribute("password", password);
		
		return page;

		}
	
	@RequestMapping(value = "change", method = RequestMethod.POST)
	public String isValidUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("firstname") String firstname, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,
			@RequestParam("newPassword2") String newPassword2, ModelMap map) {
		String page = "changePassword";
		if (validate(username,oldPassword) && newPassword.equals(newPassword2)) {
			adminRepository.save(username,newPassword);
			Admin data = adminRepository.findByUsername(username);
			
			map.addAttribute("username", username);
			map.addAttribute("password", data.getPassword());
			map.addAttribute("firstname", firstname);
			
			map.addAttribute("completedMessage", "Password changed successfully. Click to return to Main Screen");				
			
		} else {
			if (!newPassword.equals(newPassword2)) {
				map.addAttribute("errorMessage", "New passwords do not match");	
				map.addAttribute("username", username);
				map.addAttribute("password", password);
				map.addAttribute("firstname", firstname);
			} else {
				map.addAttribute("errorMessage", "Please check your existing password");
				map.addAttribute("username", username);
				map.addAttribute("password", password);
				map.addAttribute("firstname", firstname);
			}
		}
		return page;
	}
	
	@RequestMapping(value = "userList", method = RequestMethod.POST)
	public String viewUserList(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("firstname") String firstname, @RequestParam("userusername") String userusername, @RequestParam("userlastname") String userlastname,
			@RequestParam("userfirstname") String userfirstname, @RequestParam("viewoption") String viewoption, ModelMap map) {
		String page = "loginScreen";
		if (validate(username,password)) {
			List<Users> data = null; 
			if (viewoption.equals("viewall")) {
				data = usersRepository.findAll();
			} 
			if (viewoption.equals("viewbyusername")) {
				data = usersRepository.findByUsername(userusername);
			} 
			if (viewoption.equals("viewbyname")) {
				data = usersRepository.findByName(userfirstname,userlastname);
			}
			
			map.addAttribute("data", data);
			map.addAttribute("username", username);
			map.addAttribute("password", password);
			map.addAttribute("firstname", firstname);
			
			page = "userScreen";
		}
		return page;
	}
	
	@RequestMapping(value = "seePurchaseReports", method = RequestMethod.POST)
	public String viewPurchaseReports(@RequestParam("username") String username, @RequestParam("firstname") String firstname, @RequestParam("password") String password, @RequestParam("purchasedate1") String purchasedate1, @RequestParam("purchasedate2") String purchasedate2, @RequestParam("brandid") String brandid,
			@RequestParam("categoryid") String categoryid, ModelMap map) {
		String page = "loginScreen";
		
		LocalDateTime dateTime1 = null;
		LocalDateTime dateTime2 = null;
		Long brand = null;
		Long category = null;
		
		try {
			dateTime1 = LocalDateTime.parse(purchasedate1);
		} catch (Exception ex){}
		try {
			dateTime2 = LocalDateTime.parse(purchasedate2);
		} catch (Exception ex){}
		try {
			category = Long.parseLong(brandid);
		} catch (Exception ex){}
		try {
			brand = Long.parseLong(categoryid);
		} catch (Exception ex){}
		
		
		if (validate(username,password)) {
			List<Purchases> data = null; 
			if ((dateTime1 == null) && (dateTime2 == null) && (brand == null) && (category == null)) {
				data = purchasesRepository.findAll();
			} 
			else  {
				data = purchasesRepository.findByCriterea(dateTime1, dateTime2, brand, category);
			} 
			
			map.addAttribute("data", data);
			map.addAttribute("username", username);
			map.addAttribute("password", password);
			map.addAttribute("firstname", firstname);
			
			page = "purchasesScreen";
		}
		return page;
	}
	
	@RequestMapping(value = "manageProducts", method = RequestMethod.POST)
	public String manageProducts(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("firstname") String firstname, ModelMap map) {
		map.addAttribute("firstname", firstname);
		map.addAttribute("username", username);
		map.addAttribute("password", password);
		map.addAttribute("listproductsc", "true");

		
		return "manageProducts";
	}
	
	@RequestMapping(value = {"manage"}, method = RequestMethod.POST)
	public String manageProducts(
			@RequestParam("username") String username, 
			@RequestParam("password") String password,
			@RequestParam("firstname") String firstname, 
			@RequestParam("actionchoice") String actionchoice, 
			@RequestParam("submitaction") String submitaction,	
			@RequestParam(required=false, name="name") String name, 
			@RequestParam(required=false, name="updateid") String updateid, 
			@RequestParam(required=false, name="serialno") String serialno, 
			@RequestParam(required=false, name="brandid") Long brandid, 
			@RequestParam(required=false, name="categoryid") Long categoryid, 
			@RequestParam(required=false, name="currentprice") Double currentprice, 
			@RequestParam(required=false, name="displaychoice") String displaychoice, 
			ModelMap map) {
		
		boolean entdisplay = false;

		if (displaychoice != null) {
			if (displaychoice.equals("truechoice")) {
				entdisplay = true;
			}
		}
		
		map.addAttribute("firstname", firstname);
		map.addAttribute("username", username);
		map.addAttribute("password", password);
		map.addAttribute("actionchoice", actionchoice);
		
		List<Shoes> dataProducts = null;
		List<Categories> dataCategories = null;
		List<Brands> dataBrands = null;
			
		switch (actionchoice) {
			case "listproducts": 
				dataProducts = shoesRepository.findAll();
				map.addAttribute("dataProducts", dataProducts);
				map.addAttribute("listproductsc", "true");
				break;
	
			case "listcategories": 
				dataCategories = categoriesRepository.findAll();
				map.addAttribute("dataCategories", dataCategories);
				map.addAttribute("listcategoriesc", "true");
				break;
					
			case "listbrands": 
				dataBrands = brandsRepository.findAll();
				map.addAttribute("dataBrands", dataBrands);
				map.addAttribute("listbrandsc", "true");
				break;
			case "updateproduct": 
				map.addAttribute("updateproductc", "true");
				if (submitaction.equals("Update Product")) {

					String message = shoesRepository.save(Long.valueOf(updateid),serialno, entdisplay, currentprice, brandid, categoryid);
					map.addAttribute("message", message);
				}
				if (submitaction.equals("Retrieve Product to Update")) {

					String message = "amend entries to be updated and submit";
					Shoes dataProduct = shoesRepository.findById(Long.valueOf(updateid));
					if (dataProduct.isDisplayshoe()) {
						map.addAttribute("truechoice", "true");
					} else {
						map.addAttribute("falsechoice", "true");
					}

					map.addAttribute("message", message);
					map.addAttribute("dataProduct", dataProduct);
				}
				dataProducts = shoesRepository.findAll();
				map.addAttribute("dataProducts", dataProducts);
				map.addAttribute("updateid", updateid);
				break;
	
			case "updatecategory": 
				map.addAttribute("updatecategoryc", "true");
				if (submitaction.equals("Update Category")) {
					String message = categoriesRepository.save(Long.valueOf(updateid),name);
					map.addAttribute("message", message);
				}
				if (submitaction.equals("Retrieve Category to Update")) {

					String message = "update category and submit";
					Categories dataCategory = categoriesRepository.findById(Long.valueOf(updateid));

					map.addAttribute("message", message);
					map.addAttribute("dataCategory", dataCategory);
				}
				dataCategories = categoriesRepository.findAll();
				map.addAttribute("dataCategories", dataCategories);
				break;
					
			case "updatebrand": 
				map.addAttribute("updatebrandc", "true");
				if (submitaction.equals("Update Brandname")) {
					String message = brandsRepository.save(Long.valueOf(updateid),name);
					map.addAttribute("message", message);
				} 
				if (submitaction.equals("Retrieve Brand to Update")) {

					String message = "update brand name and submit";
					Brands dataBrand = brandsRepository.findById(Long.valueOf(updateid));
					map.addAttribute("message", message);
					map.addAttribute("dataBrand", dataBrand);
				}
				dataBrands = brandsRepository.findAll();
				map.addAttribute("dataBrands", dataBrands);
				break;
			case "addproduct": 
				map.addAttribute("addproductc", "true");
				if (submitaction.equals("Add Product")) {
					String message = shoesRepository.save(0L,serialno, entdisplay, currentprice, brandid, categoryid);
					map.addAttribute("message", message);
				}
				dataProducts = shoesRepository.findAll();
				map.addAttribute("dataProducts", dataProducts);
				break;
	
			case "addcategory": 
				map.addAttribute("addcategoryc", "true");
				if (submitaction.equals("Add Category")) {
					String message = categoriesRepository.save(0L,name);
					map.addAttribute("message", message);
				}
				dataCategories = categoriesRepository.findAll();
				map.addAttribute("dataCategories", dataCategories);
				break;
					
			case "addbrand": 
				map.addAttribute("addbrandc", "true");
				if (submitaction.equals("Add Brandname")) {
					String message = brandsRepository.save(0L,name);
					map.addAttribute("message", message);
				}
				dataBrands = brandsRepository.findAll();
				map.addAttribute("dataBrands", dataBrands);

				break;
			case "deleteproduct": 
				map.addAttribute("deleteproductc", "true");
				if (submitaction.equals("Delete Product")) {
					String message = shoesRepository.deleteById(Long.valueOf(updateid));
					map.addAttribute("message", message);
				}
				dataProducts = shoesRepository.findAll();
				map.addAttribute("dataProducts", dataProducts);
				break;
	
			case "deletecategory": 
				map.addAttribute("deletecategoryc", "true");
				if (submitaction.equals("Delete Category")) {
					String message = categoriesRepository.deleteById(Long.valueOf(updateid));
					map.addAttribute("message", message);
				}
				dataCategories = categoriesRepository.findAll();
				map.addAttribute("dataCategories", dataCategories);
				break;
					
			case "deletebrand": 
				map.addAttribute("deletebrandc", "true");
				if (submitaction.equals("Delete Brandname")) {
					String message = brandsRepository.deleteById(Long.valueOf(updateid));
					map.addAttribute("message", message);
				}
				dataBrands = brandsRepository.findAll();
				map.addAttribute("dataBrands", dataBrands);
				break;
					
		}

		return "manageProducts";
	}
}
