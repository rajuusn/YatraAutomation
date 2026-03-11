package com.yatra.automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class YatraAutomationScript {

	@Test
	
	public void yatra() {
		ChromeOptions options=new ChromeOptions();
		
		options.addArguments("--disable-notifications");
		options.addArguments("--start-maximized");
	
		WebDriver driver=new ChromeDriver(options);
		WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(20));
		driver.get("https://www.yatra.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("(//div[@id='__next']//div//img[@alt='cross'])[1]")).click();
		try {
		WebElement button=driver.findElement(By.xpath("//div[@aria-label='Departure Date inputbox']"));
	
		mywait.until(ExpectedConditions.visibilityOf(button)).click();
	}
	catch(TimeoutException e) {
		System.out.println("popup not displayed");
	}
		
		
		
		
		//By monthLocator=By.xpath("//div[@class='react-datepicker__month-container']");
	
	//List<WebElement> months=mywait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(monthLocator));
		

		// WebElement march=months.get(0);//current month
		WebElement current=selectMonthFromCalander(mywait, 0);
		WebElement next=selectMonthFromCalander(mywait, 1);
		String currResult=lowPrice(current);
		String nextResult=lowPrice(next);
		 System.out.println("current month lowest price is "+currResult);
         System.out.println("next month lowest price is "+nextResult);
         compareTwoMonthsPrices(currResult,nextResult);
		 
}
	
	//for switching months
	 public static WebElement selectMonthFromCalander(WebDriverWait mywait, int index) {
		 
	        By monthLocator=By.xpath("//div[@class='react-datepicker__month-container']");
		List<WebElement> months=mywait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(monthLocator));
			 WebElement monthElement=months.get(index);//current month
			 return monthElement;

}
	 
	 public static String lowPrice(WebElement month) {
		 By dateLocator=By.xpath(".//div[contains(@class,'react-datepicker__day react-datepicker__day')]");
		 List<WebElement> dates=month.findElements(dateLocator);
		 By priceLocator=By.xpath(".//span[contains(@class,'custom-day-content')]");
		 List<WebElement> prices=month.findElements(priceLocator);
		// mywait.until(ExpectedConditions(dates));

		 WebElement priceElement=null;
		 int lowestPrice=Integer.MAX_VALUE;
		 for(WebElement price:prices) {
		
			 String priceString=price.getText().replaceAll("₹", "").replaceAll(",","");

	//for getting lowest price 
			if(!priceString.isEmpty()) {
				 int priceVal=Integer.parseInt(priceString);
				 if(priceVal<lowestPrice) {
					 lowestPrice=priceVal;
					 priceElement=price;
				 }
			}
			
		
			
			 
		
		
	} 
		 WebElement date=priceElement.findElement(By.xpath(".//../.."));
		 //System.out.println("price is"+lowestPrice);
      String dateString=date.getAttribute("aria-label");
		 String result=dateString+" ---- price is ----rs"+lowestPrice;
		 return result;
	 }
	
	public static void compareTwoMonthsPrices(String current, String next) {
		int currentIndex=current.indexOf("rs");
		int nextIndex=next.indexOf("rs");
		String currentPrice=current.substring(currentIndex+2);
		String nextPrice=next.substring(nextIndex+2);
		
		int curr=Integer.parseInt(currentPrice);
		int nex=Integer.parseInt(nextPrice);
		if(curr<nex) {
			System.out.println("the lowestprice of two months is "+curr);
		}
		else if(curr==nex) {
			System.out.println("price is same for both months choose whatever you prefer!!");
		}
		else {
			System.out.println("the lowestprice of two months is "+nex);
		}
	}
}
