package epis.unsa;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

@DisplayName("Unit test for Piece class.")
public class BoardTest {
	Board b;
	Piece pyr1, pyr2, pyr3, pyr4, s, sRotated;

	// This shows how to build things in setUp() to re-use
	// across tests.
	
	// In this case, setUp() makes shapes,
	// and also a 3X6 board, with pyr placed at the bottom,
	// ready to be used by tests.
	
  @BeforeEach
	protected void setUp() throws Exception {
		b = new Board(3, 6);
		
		pyr1 = Piece.getPieces()[STRS.PYRAMID.ordinal()];
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		
    s = Piece.getPieces()[STRS.S1.ordinal()];
		sRotated = s.computeNextRotation();
		
		b.place(pyr1, 0, 0);
	}

  @Test
  @DisplayName("Testing grid pyr1 in 0 0")
  public void testPlacePYR1(){
    assertTrue(b.getGrid(0, 0));
    assertFalse(b.getGrid(0, 1));
    assertFalse(b.getGrid(0, 2));
    assertFalse(b.getGrid(0, 3));
    assertFalse(b.getGrid(0, 4));
    assertFalse(b.getGrid(0, 5));
    assertTrue(b.getGrid(1, 0));
    assertTrue(b.getGrid(1, 1));
    assertFalse(b.getGrid(1, 2));
    assertFalse(b.getGrid(1, 3));
    assertFalse(b.getGrid(1, 4));
    assertFalse(b.getGrid(1, 5));
  }

  @Test	
	@DisplayName("Check the basic width/height/max after the one placement")
	public void testBoardSize() {
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(2, b.getColumnHeight(1));
		assertEquals(2, b.getMaxHeight());
		assertEquals(3, b.getRowWidth(0));
		assertEquals(1, b.getRowWidth(1));
		assertEquals(0, b.getRowWidth(2));
	}
	
  @Test
  @DisplayName("Place sRotated into the board, then check some measures")
	public void testBoardSizeSRotate() {
		b.commit();
		int result = b.place(sRotated, 1, 1);
		assertEquals(Board.PLACE_OK, result);
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(4, b.getColumnHeight(1));
		assertEquals(3, b.getColumnHeight(2));
		assertEquals(4, b.getMaxHeight());
    assertTrue(true);
	}
	
  @Test
  @DisplayName("Testing dropHeight for S1 rotate at column 1")
  /*
   * -X-
   * -XX
   * - X
   * ---
   * -X-
   * XXX
   *
   * skirt = {1, 0}
   * heigths = {1, 2, 1}
   */
  public void testDropHeightS1(){
    assertArrayEquals(new int[] {1, 0}, sRotated.getSkirt());
    assertEquals(1, b.getColumnHeight(0));
    assertEquals(2, b.getColumnHeight(1));
    assertEquals(1, b.getColumnHeight(2));
    assertEquals(1, b.dropHeight(sRotated, 1));
  }
	// Make more tests, by putting together longer series of 
	// place, clearRows, undo, place ... checking a few col/row/max
	// numbers that the board looks right after the operations.
	
	
}
