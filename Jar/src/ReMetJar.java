import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ReMetJar {
	
	public static void signInMM(String userName, String passWord, WebDriver webDriverObject) throws InterruptedException {
		webDriverObject.get("https://case.s2.movemaestro.com");
		webDriverObject.manage().window().maximize();
		webDriverObject.findElement(By.id("cred_userid_inputtext")).sendKeys(userName);
		webDriverObject.findElement(By.id("cred_password_inputtext")).sendKeys(passWord);
		Thread.sleep(2000);
		webDriverObject.findElement(By.id("cred_sign_in_button")).click();
		System.out.println("Successfully signed");
	}
	
	public static void SelectDropDownid(String locatorid, String texttobeFound, WebDriver webDriverObject) {
		WebElement elementName = webDriverObject.findElement(By.id(locatorid));
		Select Selector = new Select(elementName);
		Selector.selectByVisibleText(texttobeFound);
	}

	public static void SelectDropDownxpath(String locatorxpath, String valuetobeFound, WebDriver webDriverObject) {
		WebElement elementName = webDriverObject.findElement(By.xpath(locatorxpath));
		Select Selector = new Select(elementName);
		Selector.selectByValue(valuetobeFound);
		// Selector.selectByText(texttobeFound);
	}
	
	public static void createTransferee(WebDriver obj, String employerDepartment, int employeeID, String position, String rank, String firstName, String middleName, String surName, String DOB ) throws InterruptedException
	{
		obj.findElement(By.xpath("//a[@class = 'affixShowArrowIcon' and @data-toggle = 'collapse']")).click();
		Thread.sleep(2000);// Wait for the frame to load
		// TRANSFEREE CREATION
		// clicking on Transferee and filling data
		obj.findElement(By.xpath("//*[@id = 'transfereeCreate']/a")).click();
		Thread.sleep(5000);// Wait for frame to load
		obj.switchTo().frame(0);
		ReMetJar.SelectDropDownid("Employer", employerDepartment, obj);//
		Thread.sleep(2000);
		String eid = Integer.toString(employeeID);
		obj.findElement(By.id("EmployeeNumber")).sendKeys(eid);//
		if(employerDepartment.contains("Defence"))
		{
			ReMetJar.SelectDropDownid("ClientPositions",position, obj);//
			ReMetJar.SelectDropDownid("ClientTitles", rank, obj);//
		}
		else if(employerDepartment.contains("Accenture")||employerDepartment.contains("QLD")||employerDepartment.contains("Cartus")||employerDepartment.contains("Telstra"))
		{
			obj.findElement(By.id("Text1")).sendKeys(position);;//
			Thread.sleep(1000);
			ReMetJar.SelectDropDownid("ClientTitles", rank, obj);//
		}
		else
		{
			System.out.println("The entered Employer Department i.e.," +employerDepartment + "is not yet considered for Testing");
		}
		obj.findElement(By.id("GivenName")).sendKeys(firstName);//
		obj.findElement(By.id("MiddleName")).sendKeys(middleName);//
		obj.findElement(By.id("FamilyName")).sendKeys(surName);//
		obj.findElement(By.id("DateOfBirth")).sendKeys(DOB);//
		obj.findElement(By.id("FamilyName")).click();
		Thread.sleep(1000);
		obj.findElement(By.xpath("//*[text() = 'Create Transferee']")).click();// Redirects to profile page
		Thread.sleep(10000); // Wait after creating Transfree
		System.out.println("Successfully created the Transferee");
	}
	
	public static void addInventory(WebDriver obj, String address, String inventoryName) throws InterruptedException
	{
			// click on Inventory
			obj.findElement(By.xpath("//*[@class = 'inventoryIcon']")).click();
			Thread.sleep(5000);
			// click on "Add new Inventory" and enter data
			obj.findElement(By.id("addInventoryLink")).click();
			Thread.sleep(3000);
			obj.switchTo().frame(0);
			obj.findElement(By.xpath("//*[@class = 'inputPanel']/input[@class = 'form-control input-sm']")).sendKeys(inventoryName);// Inventory name
			Thread.sleep(1000);
			obj.findElement(By.id("EditAddressLookup_undefined")).sendKeys(address);// accepts unique address
			Thread.sleep(3000);// Wait for the dropdown to appear
			obj.findElement(By.id("EditAddressLookup_undefined")).sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			obj.findElement(By.id("btnCreateInventory")).click();// Redirects to the created inventory with form to be displayed
			// Filling up the forms provided by the created Inventory
			Thread.sleep(5000);
			List<WebElement> e1 = obj.findElements(By.xpath(
					"//input[@class = 'form-control' and @maxlength = '4' or @maxlength = '8' or @maxlength = '3']"));
			// for (int i = 0; i < e1.Count; i++)
			for (int i = 0; i < 15; i++) {
				e1.get(i).sendKeys("1");
				obj.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			}
			Thread.sleep(2000);
			// clicking on save icon
			obj.findElement(By.xpath("//a[@class = 'picons saveIcon']")).click();
			Thread.sleep(7000);
			System.out.println("Successfully added the Inventory");
			
	}
	

	
	
	
	
	
	
	
	

}
