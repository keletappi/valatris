package board;

import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

public class BoardTest {

    public static final Piece IDENTITY_PIECE = new Piece("IDENTITY", new CoordinateTransformation(0, 0));

    @Test
    public void testCoordinateOnBoard() {
        Board onebyOne = new Board(1, 1);
        assertThat(onebyOne.isOnBoard(new Coordinate(0,0)), is(true));
        assertThat(onebyOne.isOnBoard(new Coordinate(1,0)), is(false));
        assertThat(onebyOne.isOnBoard(new Coordinate(0,1)), is(false));
        assertThat(onebyOne.isOnBoard(new Coordinate(-1,-1)), is(false));


        Board twoByFour = new Board(2, 4);
        assertThat(twoByFour.isOnBoard(new Coordinate(0,0)), is(true));
        assertThat(twoByFour.isOnBoard(new Coordinate(1,3)), is(true));
        assertThat(twoByFour.isOnBoard(new Coordinate(1,0)), is(true));
        assertThat(twoByFour.isOnBoard(new Coordinate(-1,-1)), is(false));
        assertThat(twoByFour.isOnBoard(new Coordinate(2,3)), is(false));
        assertThat(twoByFour.isOnBoard(new Coordinate(1,4)), is(false));
        assertThat(twoByFour.isOnBoard(new Coordinate(2,4)), is(false));
    }

    @Test
    public void testOverlapsExistingPlacement() {
        Board board = new Board(3, 3);
        board.apply(new Placement(
                new Piece("X", new CoordinateTransformation(0,0)),
                new Coordinate(1,1)
        ));

        assertThat(board.overlapsExistingPlacements(new Coordinate(1,1)), is(true));
        assertThat(board.overlapsExistingPlacements(new Coordinate(0,0)), is(false));
        assertThat(board.overlapsExistingPlacements(new Coordinate(1,0)), is(false));
        assertThat(board.overlapsExistingPlacements(new Coordinate(0,1)), is(false));
        assertThat(board.overlapsExistingPlacements(new Coordinate(2,2)), is(false));
        assertThat(board.overlapsExistingPlacements(new Coordinate(1,2)), is(false));
        assertThat(board.overlapsExistingPlacements(new Coordinate(2,1)), is(false));

    }


    @Test
    public void testIsValidPlacement() {
        Board board = Mockito.spy(new Board(3, 3));
        doReturn(true).when(board).isOnBoard(any(Coordinate.class));
        doReturn(false).when(board).overlapsExistingPlacements(any(Coordinate.class));

        assertThat(board.isValidPlacement(new Placement(
                        new Piece("X", new CoordinateTransformation(0,0)),
                        new Coordinate(1,1))),
                is(true));

    }

    @Test
    public void testIsValidPlacementOverlappingExisting() {
        Board board = Mockito.spy(new Board(3, 3));
        doReturn(true).when(board).isOnBoard(any(Coordinate.class));
        doReturn(true).when(board).overlapsExistingPlacements(any(Coordinate.class));

        assertThat(board.isValidPlacement(new Placement(
                        new Piece("X", new CoordinateTransformation(0,0)),
                        new Coordinate(1,1))),
                is(false));

    }

    @Test
    public void testIsValidPlacementOutsideBoard() {
        Board board = Mockito.spy(new Board(3, 3));
        doReturn(false).when(board).isOnBoard(any(Coordinate.class));
        doReturn(false).when(board).overlapsExistingPlacements(any(Coordinate.class));

        assertThat(board.isValidPlacement(new Placement(
                        IDENTITY_PIECE,
                        new Coordinate(1,1))),
                is(false));
    }


    @Test
    public void testGetFreeSlots() {
        Board board = new Board(3, 3);

        board.apply(new Placement(IDENTITY_PIECE, new Coordinate(1,1)));
        board.apply(new Placement(IDENTITY_PIECE, new Coordinate(2,1)));
        board.apply(new Placement(IDENTITY_PIECE, new Coordinate(2,2)));

        assertThat(board.getFreeSlots(), containsInAnyOrder(
                new Coordinate(0, 0),
                new Coordinate(0, 1),
                new Coordinate(0, 2),
                new Coordinate(1, 0),
                new Coordinate(1, 2),
                new Coordinate(2, 0)
        ));
    }
}