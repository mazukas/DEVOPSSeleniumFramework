package gov.abs.sbs;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Rule;
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
        Assert.assertTrue(false);
	}
	
	@Test
	public void basicTest2() {
		openBrowser(TestUser.TEST_USER_1, "http://www.yahoo.com");
		
		WebElement element = driver.findElement(By.name("p"));

        // Enter something to search for
        element.sendKeys("A Search Term");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();
        Assert.assertEquals("A Search Term - Yahoo Search Results", driver.getTitle());
	}
}
