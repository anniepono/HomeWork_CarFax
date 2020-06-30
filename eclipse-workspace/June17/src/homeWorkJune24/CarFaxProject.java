package homeWorkJune24;

import java.util.ArrayList;
import java.util.Arrays;
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
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
		
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
		
		
		
		
	}

}
