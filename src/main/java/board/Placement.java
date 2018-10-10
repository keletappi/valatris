package board;

import java.util.List;

/**
 * Represents an attempted placement of a piece on the board
 */
public class Placement {
    private final Piece piece;
    private final Coordinate location;

    public Placement(Piece piece, Coordinate location) {
        this.piece = piece;
        this.location = location;
    }

    public List<Coordinate> coordinates() {
        return piece.absoluteCoordinates(location);
    }
}
