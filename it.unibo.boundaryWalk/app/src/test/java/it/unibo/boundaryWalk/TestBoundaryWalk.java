package it.unibo.boundaryWalk;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestBoundaryWalk {
    private BoundaryWalk appl;

    @Before
    public void systemSetUp() {
        System.out.println("TestBoundaryWalk | setUp: robot should be at HOME-DOWN ");
        appl = new BoundaryWalk();
    }

    @After
    public void  terminate() {
        System.out.println("%%%  TestBoundaryWalk |  terminates ");
    }

    @Test
    public void test4Wall() {
        System.out.println("TestBoundaryWalk | test4Wall ");
        String feedbacks = appl.walkBoundary("counterclockwise", 250);
        int walls = countWallsFromFeedbacks(feedbacks);
        assertTrue(walls == 4);
    }

    @Test
    public void testCounterclockwiseRotation() {
        System.out.println("TestBoundaryWalk | testCounterclockwiseRotation ");
        String feedbacks = appl.walkBoundary("counterclockwise", 250);
        int leftRotation = countRotationFromFeedbacks("left",feedbacks);
        assertTrue(leftRotation == 4);
    }

    @Test
    public void testClockwiseRotation() {
        System.out.println("TestBoundaryWalk | testClockwiseRotation ");
        String feedbacks = appl.walkBoundary("clockwise", 250);
        int rightRotation = countRotationFromFeedbacks("right",feedbacks);
        assertTrue(rightRotation == 4);
    }

    @Test
    public void testTimeOnOppositeSides() {
        System.out.println("TestBoundaryWalk | testTimeOnOppositeSides ");
        String feedbacks = appl.walkBoundary("counterclockwise", 500);
        int t[] = new int[4];
        for (int i=0; i<4; i++)
            t[i] = sideTime(i,feedbacks);
        assertTrue(t[0] == t[2] && t[1] == t[3]);
    }

    @Test
    public void testRotationAfterWallCounterclockwise() {
        System.out.println("TestBoundaryWalk | testRotationAfterWallCounterclockwise ");
        String feedbacks = appl.walkBoundary("counterclockwise", 500);
        char feedback[] = divideFeedbacks(feedbacks);
        for(int i=0; i<feedback.length; i++) {
            if (feedback[i] == 'w' && i != feedback.length - 1)
                assertTrue(feedback[i+1] == 'l');
        }
    }

    private int countWallsFromFeedbacks(String f) {
        int walls = 0;
        for (int i=0; i<f.length(); i++)
            walls = f.charAt(i) == 'w' ? walls + 1 : walls;
        return walls;
    }

    private int countRotationFromFeedbacks(String direction, String f) {
        char d = direction == "left" ? 'l' : 'r';
        int rotation = 0;
        for (int i=0; i<f.length(); i++)
            rotation = f.charAt(i) == d ? rotation + 1 : rotation;
        return rotation;
    }

    private int sideTime(int side, String f) {
        String sideFeedback = f.split("-")[side];
        System.out.println(sideFeedback);
        int sideTime = 0;
        for (int i=0; i<sideFeedback.length(); i++)
            sideTime = sideFeedback.charAt(i) == 'f' ? sideTime + 1 : sideTime;
        return sideTime;
    }

    private char[] divideFeedbacks(String f) {
        char result[] = new char[f.length()];
        int j = 0;
        for (int i=0; i<f.length(); i++) {
            if (f.charAt(i) != '-') {
                result[j] = f.charAt(i);
                j++;
            }
        }
        return  result;
    }

}
/*
See
http://sqa.fyicenter.com/FAQ/JUnit/What_Is_JUnit_.html
http://sqa.fyicenter.com/FAQ/JUnit/Can_You_Write_a_JUnit_Test_Case_Class_in_10_Minu.html
http://sqa.fyicenter.com/FAQ/JUnit/Can_You_Explain_a_Sample_JUnit_Test_Case_Class_.html
No main required
public static void main(String[] args) {
  junit.textui.TestRunner.run(DirListerTest.class);
}
 */