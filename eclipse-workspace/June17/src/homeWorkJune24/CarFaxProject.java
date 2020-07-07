package homeWorkJune24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import utility.Utility;

public class CarFaxProject {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumFiles\\browserDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();	
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); 
		
		//1. Go to carfax.com
		driver.navigate().to("https://www.carfax.com/");
		
		//2. Click on find used cars
		driver.findElement(By.linkText("Find a Used Car")).click();
		
		//3. Choose “Tesla” for  Make.
		Select s = new Select (driver.findElement(By.xpath("//*[@id=\"makeModelPanel\"]/form/div[1]/div/select")));
		s.selectByVisibleText(" Tesla ");
		
		//4. Verify that the Select Model dropdown box contains 3 current Tesla models - (Model 3, Model S, Model X). 
		List <WebElement> actualModels = driver.findElements(By.xpath("//*[@id=\"makeModelPanel\"]//select[@class=\"form-control search-model \"]//option[@class='model-option']"));
		
		List <String> actualModelsString = new ArrayList <String>();
		
		for (WebElement m : actualModels) {
			actualModelsString.add(m.getText());
		}
		
		UtilityClass.checkModelList(actualModelsString); //Utility method
		
		//5. Choose “Model S” for Model.
		for (WebElement model: actualModels) {
			if (model.getText().equals(" Model S ")) {
				model.click();
			}
		}
		
		//6. Enter the zipcode as 22182 and click Next	
		driver.findElement(By.xpath("//*[@id=\"makeModelPanel\"]/form/div[3]/div/div[4]/div/input")).sendKeys("22182");
		driver.findElement(By.id("make-model-form-submit")).click();
		
		//7. Verify that the page has “Step 2 – Show me cars with” text
		Thread.sleep(5000);
		UtilityClass.checkSourceContains("Step 2 - Show me cars with", driver.getPageSource());
		
		
		
		//8. Click on all 4 checkboxes.
		
		List <WebElement> checkBoxes = driver.findElements(By.xpath("//ul[@class='checkbox-list checkbox-list--left checkbox-list--large list-unstyled']//div"));
		
		for (WebElement box: checkBoxes) {
			box.click();
			}

		//9. Save the result of “Show me X Results” button to a variable. In this case it is 6.
		
		String searchResult = driver.findElement(By.xpath("//div[@class='four-pillar-form form-padding']//button//span")).getText();
		System.out.println("Search returned " + searchResult + " options.");
		
		//10. Click on “Show me x Results” button. 
		
		driver.findElement(By.xpath("//div[@class='four-pillar-form form-padding']//button")).click();
		Thread.sleep(2000);
		
		//11. On the next page, verify that the results page has the same number of results as indicated in Step 10 by extracting the number and comparing the result
		
		String searchResultPage2 = driver.findElement(By.id("totalResultCount")).getText();
		
		if (searchResultPage2.equals(searchResult)) {
			System.out.println("Results match on both pages. \rPage 1 showed: " + searchResult + "\rPage 2 showed: " + searchResultPage2);
		} else {System.out.println("Results DO NOT match. \rPage 1 showed: " + searchResult + "\rPage 2 showed: " + searchResultPage2);}
		
		//12. Verify the results also by getting the actual number of results displayed in the page with the number in the Step 10. For this step get the count the number of WebElements related to each result.
		
		List <WebElement> articlesResult = driver.findElements(By.tagName("article"));
		
		int expectedNumOfResults=Integer.parseInt(searchResultPage2); 
		int actualNumOfResults = articlesResult.size();
		
		if (expectedNumOfResults==actualNumOfResults) {
			System.out.println("Number of results matches dispayed car options.\rExpected " +  expectedNumOfResults + "\rShown " + actualNumOfResults);
		} else {
			System.out.println("Number of results DOES NOT match dispayed car options.\rExpected " +  expectedNumOfResults + "\rShown " + actualNumOfResults);
		}
		
		
		//13. Verify that each result contains string “Tesla Model S”.
		
		
		String expectedResultName = "Tesla Model S";
		for (int i = 0; i < articlesResult.size(); i++ ) {
			String articleName = driver.findElement(By.xpath("//*[@id=\"listing_" + i + "\"]//div//h4")).getText();
			System.out.println("Name #" +i+" "+articleName);
			if (articleName.contains(expectedResultName))
			System.out.println("PASSED");
			else System.out.println("FAILED");
		}
		
		
		//14. Get the price of each result and save them into a list in the order of their appearance.
		List <String> priceList = new ArrayList<String>();
		String priceString;
		
		for (int i = 0; i < (articlesResult.size()-1); i++ ) { 
		priceString = driver.findElement(By.xpath("//*[@id=\"listing_" + i + "\"]/div[3]/a/div[1]/div/span")).getText();
		priceList.add(priceString);
		//System.out.println(priceString);
		}
		
		
		//15. Choose “Price - High to Low” option from Sort menu
	
		Collections. sort(priceList);
		Collections.reverse(priceList);
		
		System.out.println(priceList);

		Select selectSort = new Select(driver.findElement(By.xpath("//*[@id=\"ucl-microapp-srp-content\"]/div/div[1]/div/div[2]/div/form/div/select")));
		selectSort.selectByValue("PRICE_DESC");
		
			Thread.sleep(8000); 
			
		List <String> sortedList = new ArrayList<String>();
		for (int i = 0; i < (articlesResult.size()-1); i++ ) {
		String priceStringSorted = driver.findElement(By.xpath( "//*[@id=\"listing_" + i + "\"]/div[3]/a/div[1]/div/span")).getText();
		sortedList.add(priceStringSorted);
		}
		
		System.out.println(sortedList);
		
		
		//16. Verify that the results are displayed from high to low price. 
		if (sortedList.equals(priceList)) {
			System.out.println("Passed");
		} else 
			System.out.println("Failed");
	
		//17. Choose “Mileage - Low to High” option from Sort menu
		
		selectSort.selectByValue("MILEAGE_ASC");
		
		Thread.sleep(5000);

		
		//18. Verify that the results are displayed from low to high mileage. 

		List <String> sortedMiles = new ArrayList<String>();

		for (int i=0; i < (articlesResult.size()-1); i++) {
			String s3 = driver.findElement(By.xpath("//*[@id=\"listing_"
					+ i
					+ "\"]/div[4]/div[2]/span[1]")).getText();
			sortedMiles.add(s3);			
		}
		
		List<Double> d = UtilityClass.getDoubleMiles(sortedMiles);
		List<Double> d1 = UtilityClass.getDoubleMiles(sortedMiles);
		Collections.sort(d);
		System.out.println(d.equals(d1)? "Passed.Miles sorted from high to low":"Failed.Miles are not sorted high to low");
	
		
		//19. Choose “Year - New to Old” option from Sort menu
		
		selectSort.selectByValue("YEAR_DESC");
		Thread.sleep(2000);
		
		List<Integer> yearList = new ArrayList<Integer>();
		List<Integer> yearListCheck = new ArrayList<Integer>();
		for (int i = 0; i < articlesResult.size(); i++ ) {
			String articleName = driver.findElement(By.xpath("//*[@id=\"listing_" + i + "\"]//div//h4")).getText();
			articleName = articleName.substring(0, articleName.indexOf(" "));
			System.out.println(articleName);
			int year = Integer. valueOf(articleName);
			System.out.println(year);
			yearList.add(year);
			yearListCheck.add(year);
		}
		
		
		//20. Verify that the results are displayed from new to old year. 

		Collections.sort(yearList);
		Collections. reverse(yearList); 
		System.out.println(yearList);
		System.out.println(yearListCheck);
		if (yearList.equals(yearListCheck)) 
			System.out.println("Passed.Year Sorted from New to Old");
		else 
			System.out.println("Failed. Not sorted from New to Old");
		
	
		
	}

}
