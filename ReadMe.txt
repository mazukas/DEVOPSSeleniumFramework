Requirements
 - Gradle 2.5 or higher
 - Java 1.7 or higher
 - Tested with Firefox 46.01 (Selenium Web Drive 2.53.0)
 
This framework leverages Gradle, Selenium, and some custom code to allow for automated regression test of 
a user's system.  It will support multiple profiles so that users with different configurations 
(certs, cookies, etc) can be simulated to test different aspects of a user's software. 

When run, at the end of the execution the framework will launch the standard Gradle Test report.  ALSO, if 
one or more tests fail the framework will take a picture of the browser as well as capture the source code 
at the time of failure and tag it in build/reports/tests/state for later review.

Currently this framework has 3 test designed to demonstrate the frameworks capabilities.

To demo this project, in a command window run "gradle clean test".  Make sure you have a version of 
Firefox installed that is compatible with Selenium Web Driver version 2.53.0.
