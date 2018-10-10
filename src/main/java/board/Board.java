package board;

import java.util.Stack;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;


public class Board {
    private final int width;
    private final int height;

    private Stack<Placement> placements;

    /**
     * Creates a new empty board.
     * @param width width of the board
     * @param height height of the board
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        placements = new Stack<>();
    }

    /**
     * Try to apply a piece on the board.
     * @param placement Placement to try out
     * @return true if placement is successful, false if not.
     */
    public boolean apply(Placement placement) {
        if (isValidPlacement(placement)) {
            placements.push(placement);
            return true;
        }

        return false;
    }

    boolean isValidPlacement(Placement placement) {
        return placement.coordinates().stream()
                .allMatch(((Predicate<Coordinate>) this::isOnBoard)
                        .and(not(this::overlapsExistingPlacements)));
    }

    boolean overlapsExistingPlacements(Coordinate coordinate) {
        return placements.stream()
                .anyMatch(placement -> placement.coordinates().contains(coordinate));
    }

    boolean isOnBoard(Coordinate coordinate) {
        return coordinate.getX() >= 0 && coordinate.getX() < width &&
                coordinate.getY() >= 0 && coordinate.getY() < height;
    }

    /**
     * Undo most recent successfully applied piece placement.
     */
    public void undo() {
        placements.pop();
    }
}
