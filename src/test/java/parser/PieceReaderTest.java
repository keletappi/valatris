package parser;

import board.Coordinate;
import board.Piece;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class PieceReaderTest {

    @Test
    public void parsePiece() {
        var reader = new PieceReader();
        var piece = reader.parsePiece("foo:1,2;3,3;-1,0");
        assertThat(piece.getLabel(), is("foo"));
        assertThat(piece.absoluteCoordinates(new Coordinate(0,0)),
                containsInAnyOrder(new Coordinate(1, 2),
                        new Coordinate(3,3),
                        new Coordinate(-1, 0)));
    }
}