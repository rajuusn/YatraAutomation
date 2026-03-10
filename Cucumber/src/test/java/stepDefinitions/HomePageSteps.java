package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

import utilities.DataReader;



public class HomePageSteps {
	
	WebDriver driver;
	HomePage hp;
	AccountRegistrationPage arp;
	Properties p;
	 List<HashMap<String, String>> datamap; //Data driven
	@Given("the user is on the login page")
	public void homePageLogin() throws IOException {
		
		BaseClass.getLogger().info("go to my account and click on login");
		hp = new HomePage(BaseClass.getDriver());
		
}
	
	@When("user enters email as {string} and password as {string}")
	public void navigateToPage(String uname, String password) throws IOException {
		
		 p=BaseClass.getProperties();
		 
			hp = new HomePage(BaseClass.getDriver());
			hp.username(uname);
			hp.password(password);
		   
		   }
	
	@When("the user clicks on the Login button")
	public void clickButton() {
		    hp = new HomePage(BaseClass.getDriver());
			hp.clickButton();
		   
		   }
	
	@Then("the user should be redirected to the My Account page")
	public void redirectAccountPage() {
		arp=new AccountRegistrationPage(BaseClass.getDriver());
		arp.dropdown();
	}
	
	@Then("the user should see a welcome message")
	public void steplogOut() {
		arp=new AccountRegistrationPage(BaseClass.getDriver());
		arp.logout();
	}
	

    
    
 
   
    //*******   Data Driven test **************
    @Then("user enters the login page by using username and password with excel row as {string}")
    
    public void check_datadriven (String rows)
    {
        datamap=DataReader.data(System.getProperty("user.dir")+"\\testData\\Opencart_LoginData.xlsx", "Sheet1");

        int index=Integer.parseInt(rows)-1;
        String email= datamap.get(index).get("username");
        String pwd= datamap.get(index).get("password");
        String exp_res= datamap.get(index).get("res");

        hp = new HomePage(BaseClass.getDriver());
        hp.username(email);
		hp.password(pwd);
//		hp.clickButton();
        
		arp=new AccountRegistrationPage(BaseClass.getDriver());
		arp.dropdown();
		arp.logout();
        

          
       
      }
 
	
	 
	
	
	
}
