package gov.abs.sbs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

/**
 * This is the base test class all automated test cases should extend. It has
 * the list of available profiles to use as well as the web drive to leverage to
 * write your test. At the end of each individual test your browser will be shut
 * down.
 */
public abstract class MasterTest {
	protected WebDriver driver;

	private boolean shutDownBrowserAfterTest = true;
	
	@Rule
	public TestFailRule testFile = new TestFailRule();

	/*
	 * Anyone using this, below are different users you can leverage within the framework.  
	 * Feel free to re-name as required.  By default, all users get a default profile in Fire Fox 
	 * call 'default'.  If you want to learn how to create new profiles...... Google it.
	 */
	public enum TestUser {
		DEFAULT("default"), TEST_USER_1("TestUser1"), TEST_USER_2("TestUser2"), TEST_USER_3(
				"TestUser3"), TEST_USER_4("TestUser4"), TEST_USER_5("TestUser5"), TEST_USER_6(
				"TestUser6"), TEST_USER_7("TestUser7"), TEST_USER_8("TestUser8"), TEST_USER_9(
				"TestUser9"), TEST_USER_10("TestUser10");

		private final String profileName;

		private TestUser(String profileName) {
			this.profileName = profileName;
		}

		public String getProfileName() {
			return profileName;
		}
	}

	/**
	 * This is the class that should be called before each test is run. It will
	 * set up your configuration to be used and executed against.
	 * 
	 * @param user
	 *            - The user that you wish to use to run this test. For now this
	 *            will only determine which profile to load for the test.
	 * @param url
	 *            - The fully qualified (http/https) url you wish to test
	 *            against.
	 */
	public void openBrowser(TestUser user, String url) {
		openBrowser(user, url, true);
	}

	public void openBrowser(TestUser user, String url, boolean shutDownBrowserAfterTest) {
		this.shutDownBrowserAfterTest = shutDownBrowserAfterTest;
		ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile(user.getProfileName());

		driver = new FirefoxDriver(profile);
		driver.get(url);
	}
	
	class TestFailRule implements MethodRule {
		public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
	        return new Statement() {
	            @Override
	            public void evaluate() throws Throwable {
	                try {
	                    statement.evaluate();
	                    if (shutDownBrowserAfterTest)
	                    	driver.quit();
	                } catch (Throwable t) {
	                    captureScreenshot(o.getClass().getName()+"-"+frameworkMethod.getName());
	                    if (shutDownBrowserAfterTest)
	                    	driver.quit();
	                    
	                    //throw t;	// rethrow to allow the failure 
	                    			//to be reported to JUnit, SBS Test team needs to talk about this
	                }
	            }

	            public void captureScreenshot(String fileName) {
	            	System.out.println("A failure was captured and now we'll capture the state");
	    			String baseFilePath = "build/reports/tests/state/";
	    			new File(baseFilePath).mkdirs(); // Insure directory is there

	    			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    			BufferedWriter out = null;
	    			try {
	    				FileUtils.copyFile(scrFile, new File(baseFilePath + fileName + ".png"));
	    				out = new BufferedWriter(new FileWriter(baseFilePath + fileName + ".txt"));
	    				out.write(driver.getPageSource());
	    				Thread.sleep(5000);
	    			} catch (IOException e1) {
	    				e1.printStackTrace();
	    			} catch (InterruptedException e1) {
	    				e1.printStackTrace();
	    			} finally {
	    				if (out != null) {
	    					try {
	    						out.close();
	    					} catch (IOException e1) {
	    						e1.printStackTrace();
	    					}
	    				}
	    			}
	            }
	        };
	    }
	}
}
