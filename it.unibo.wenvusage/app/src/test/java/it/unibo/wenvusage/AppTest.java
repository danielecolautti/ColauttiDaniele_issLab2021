/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package it.unibo.wenvusage;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    @Before
    public void setup() {
        System.out.println("setup");
    }

    @Test public void testAppHasAGreeting() {
        System.out.println("testAppHasAGreeting");
        App classUnderTest = new App();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }

    @Test public void testAppCorrectGreeting() {
        System.out.println("testAppCorrectGreeting");
        App classUnderTest = new App();
        assertTrue(classUnderTest.getGreeting().equals("Hello World, Daniele!"));
    }
}
