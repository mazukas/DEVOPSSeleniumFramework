package gov.abs.sbs;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SampleTest extends MasterTest {

	@Test
	public void basicTest1() throws IOException, InterruptedException {
		openBrowser(TestUser.DEFAULT, "http://www.google.com");
		
		WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("A Search Term");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();
        Assert.assertEquals("Google", driver.getTitle());
	}
	
	@Test
	public void basicTest2() throws IOException, InterruptedException {
		openBrowser(TestUser.DEFAULT, "http://www.google.com");
		
		WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("A Search Term");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        //This class is intended to fail to demonstrate how the framework will capture the error.  Once
        //the test have ran check out ./build/reports/tests/state to see the screen shot and source code 
        //of the failed test.
        Assert.assertEquals("NOT Google", driver.getTitle());
	}
	
	@Test
	public void basicTest3() {
		openBrowser(TestUser.DEFAULT, "http://www.yahoo.com");
		
		WebElement element = driver.findElement(By.name("p"));

        // Enter something to search for
        element.sendKeys("A Search Term");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();
        Assert.assertEquals("A Search Term - Yahoo Search Results", driver.getTitle());
	}
}
