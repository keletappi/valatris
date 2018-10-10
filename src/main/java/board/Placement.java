package board;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Represents an attempted placement of a piece on the board
 */
public class Placement {
    private final Piece piece;
    private final Coordinate location;

    public Placement(@Nonnull Piece piece, @Nonnull Coordinate location) {
        this.piece = piece;
        this.location = location;
    }

    public List<Coordinate> coordinates() {
        return piece.absoluteCoordinates(location);
    }

    public int getX() {
        return location.getX();
    }

    public int getY() {
        return location.getY();
    }

    public Piece getPiece() {
        return piece;
    }
}
