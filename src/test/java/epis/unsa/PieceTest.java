package epis.unsa;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

@DisplayName("Unit test for Piece class.")
public class PieceTest{
  // You can create data to be used in the your
  // test cases like this. For each run of a test method,
  // a new PieceTest object is created and setUp() is called
  // automatically by JUnit.
  // For example, the code below sets up some
  // pyramid and s pieces in instance variables
  // that can be used in tests.
  private Piece pyr1, pyr2, pyr3, pyr4;
  private Piece s, sRotated;

  @BeforeEach
  public void setUp(){
    pyr1 = Piece.getPieces()[STRS.PYRAMID.ordinal()];
    pyr2 = pyr1.computeNextRotation();
    pyr3 = pyr2.computeNextRotation();
    pyr4 = pyr3.computeNextRotation();

    s = Piece.getPieces()[STRS.S1.ordinal()];
    sRotated = s.computeNextRotation();
  }

  // Here are some sample tests to get you started
  //
  @Test
  @DisplayName("Check size of initial pyr piece")
  public void testPyr1Size() {
    assertEquals(3, pyr1.getWidth());
    assertEquals(2, pyr1.getHeight());
  }

  @Test
  @DisplayName("Check skirt of initial pyr piece1")
  public void testPyr1Skirt(){
    // Note must use assertTrue(Arrays.equals(... as plain .equals does not work
    // right for arrays.
    assertArrayEquals(new int[] {0, 0, 0}, pyr1.getSkirt());
  }

  @Test
  @DisplayName("Trying size with stick original piece")
  public void testStick(){
    Piece l = Piece.getPieces()[STRS.STICK.ordinal()];
    assertEquals(1, l.getWidth());
    assertEquals(4, l.getHeight());
  }

  @Test
  @DisplayName("Testing mirror L1")
  public void testMirrorL1(){
    Piece l1 = Piece.getPieces()[STRS.L1.ordinal()];
    TPoint[] ml1 = new TPoint[4];
    ml1[0] = new TPoint(1, 0);
    ml1[1] = new TPoint(1, 1);
    ml1[2] = new TPoint(1, 2);
    ml1[3] = new TPoint(0, 0);
    assertArrayEquals(ml1, 
        l1.mirror(l1.getBody(), l1.getWidth()));
  }

  @Test
  @DisplayName("Testing mirror Dog1")
  public void testMirrorS1(){
    Piece s1 = Piece.getPieces()[STRS.S1.ordinal()];
    TPoint[] ms1 = new TPoint[4];
    ms1[0] = new TPoint(2, 0);
    ms1[1] = new TPoint(1, 0);
    ms1[2] = new TPoint(1, 1);
    ms1[3] = new TPoint(0, 1);
    assertArrayEquals(ms1, 
        s1.mirror(s1.getBody(), s1.getWidth()));
  }

  @Test
  @DisplayName("Testing size and rotation code after pyr1 rotation")
  public void testPyr2Size(){
    assertEquals(2, pyr2.getWidth());
    assertEquals(3, pyr2.getHeight());
    assertTrue(true);
  }

  // Test the skirt returned by a few pieces
  @Test
  @DisplayName("Testing skirt in pyr3")
  public void testSampleSkirt() {
    assertArrayEquals(new int[] {1, 0, 1}, pyr3.getSkirt());
  }

  @Test
  @DisplayName("Testing skrit in S and rotation")
  public void testSampleSkirtForS(){
    assertArrayEquals(new int[] {0, 0, 1}, s.getSkirt());
    assertArrayEquals(new int[] {1, 0}, sRotated.getSkirt());
  }

}
