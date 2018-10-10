package board;

import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

public class PieceTest {

    @Test
    public void testAbsoluteCoordinatesIdentity() {
        Piece piece = new Piece("X", new CoordinateTransformation(0,0));
        assertThat(
                piece.absoluteCoordinates(new Coordinate(0,0)),
                contains(new Coordinate(0,0)));
    }

    @Test
    public void testAbsoluteCoordinatesTransformations() {
        Piece piece = new Piece("X", new CoordinateTransformation(0,0),
                new CoordinateTransformation(0,-1),
                new CoordinateTransformation(1,0),
                new CoordinateTransformation(1,1),
                new CoordinateTransformation(1,2)
                );

        assertThat(
                piece.absoluteCoordinates(new Coordinate(0,0)),
                contains(new Coordinate(0, 0),
                        new Coordinate(0,-1),
                        new Coordinate(1,0),
                        new Coordinate(1,1),
                        new Coordinate(1,2)
                        ));
        assertThat(
                piece.absoluteCoordinates(new Coordinate(2,2)),
                contains(new Coordinate(2,2),
                        new Coordinate(2,1),
                        new Coordinate(3,2),
                        new Coordinate(3,3),
                        new Coordinate(3,4)
                        ));
    }
}