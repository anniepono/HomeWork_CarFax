package homeWork_June21;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VerifyNewOrderCreation {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumFiles\\browserDrivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//1. Open the Chrome browser
		//2. Go to http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx
		
		driver.navigate().to("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		
		//3. Login using username Tester and password test
		
		WebElement username = driver.findElement(By.id("ctl00_MainContent_username"));
		username.sendKeys("Tester");
		
		WebElement password = driver.findElement(By.id("ctl00_MainContent_password"));
		password.sendKeys("test");
		
		WebElement loginButton = driver.findElement(By.id("ctl00_MainContent_login_button"));
		loginButton.click();
				
		//4. Click on Order link
		
		WebElement order = driver.findElement(By.linkText("Order"));
		order.click();
		
		//5. Enter a random quantity between 1 and 100
		WebElement quantity = driver.findElement(By.xpath("/html/body/form/table/tbody/tr/td[2]/div[2]/table/tbody/tr/td/ol[1]/li[2]/input"));
		quantity.sendKeys(String.valueOf((1+(int)(Math.random()*101))));
		
		//6. Enter Customer Name:
		//   John <Middle Name > Doe.
		//	 Instead of <Middle Name> your code should enter a random string of length 5 every time.
		WebElement customersName = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_txtName\"]"));
		customersName.sendKeys(Utilities.nameGenerator());
		
//		7. Enter street: 8607 Westwood Center Dr
//		8. Enter City: Vienna
//		9. Enter State: Virginia
		
		WebElement street = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox2\"]"));
		street.sendKeys("8607 Westwood Center Dr");
		
		WebElement city = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox3\"]"));
		city.sendKeys("Vienna");
		
		WebElement state = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox4\"]"));
		state.sendKeys("Virginia");
		
//		10. Enter a random 5 digit number to the zip code field
		
		WebElement zip = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox5\"]"));
		zip.sendKeys(Utilities.zipCodGenerator());
		
//		11. Select any card type. Every time your code should select a different type.

		int cardType = 1 + (int)(Math.random()*3);
		String radioButtonID;
		
		switch (cardType) {
		case 1: radioButtonID="ctl00_MainContent_fmwOrder_cardList_0";
				break;
		case 2: radioButtonID="ctl00_MainContent_fmwOrder_cardList_1";
				break;
		case 3: radioButtonID="ctl00_MainContent_fmwOrder_cardList_2";
				break;	
		default:
			 	radioButtonID="";
		}
		
		System.out.println(radioButtonID);
		WebElement creditCardType = driver.findElement(By.id(radioButtonID));
		creditCardType.click();
		
//		12. Enter any card number: 
//		If you selected Visa, card number should start with 4. 
//		If you selected Master, card number should start with 5. 
//		If you selected American Express, card number should start with 3. 
//		New card number should be auto generated every time you run the test. 
//		Card numbers should be 16 digits for Visa and Master, 15 for American Express.	
		
		WebElement creditCardBox = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6"));
		creditCardBox.sendKeys(Utilities.creditCardGenerator(cardType));
	
	
//		13. Enter a valid expiration date 
		
		WebElement ExDateBox = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1"));
		ExDateBox.sendKeys("12/25");
		

//		14. Click on Process
		
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		Thread.sleep(2000);
//		15. Verify that the page contains text “New order has been successfully added”.
		
		String expected = "New order has been successfully added.";
		WebElement message = driver.findElement(By.tagName("strong"));
		String actual = message.getText();
		
		
		Utilities.assertEqualsText(expected, actual);
		
	
	}

}
