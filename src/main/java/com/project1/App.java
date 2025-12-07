package com.project1;

import java.util.*;
public class App {
public static void main(String[] args) {
System.out.println("=================================");
System.out.println("E-Commerce Store");
System.out.println("CI/CD Pipeline Project 1");
System.out.println("=================================");
App app = new App();
app.demonstrateFeatures();
}
public void demonstrateFeatures() {
System.out.println("\n
✅
 Key Features:");
System.out.println("1. Add/Update/Delete products");
System.out.println("2. Manage shopping cart");
System.out.println("3. Process orders");
System.out.println("4. Calculate totals with tax and discounts");
System.out.println("5. Track inventory");
System.out.println("\n
🚀
 Application running successfully!");
}
public String getProjectName() {
return "E-Commerce Store";
}
public int getProjectId() {
return 1;
}
public boolean isActive() {
return true;
}
}
// src/test/java/com/project1/AppTest.java
package com.project1;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
public class AppTest {
private App app;
@Before
public void setUp() {
app = new App();
}
@Test
public void testGetProjectName() {
assertEquals("E-Commerce Store", app.getProjectName());
}
@Test
public void testGetProjectId() {
assertEquals(1, app.getProjectId());
}
@Test
public void testIsActive() {
assertTrue("Application should be active", app.isActive());
}
@Test
public void testAppNotNull() {
assertNotNull("App instance should not be null", app);
}
}
