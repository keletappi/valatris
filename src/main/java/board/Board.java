package board;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.google.common.base.Predicates.not;


public class Board {
    private final int width;
    private final int height;

    private final Stack<Placement> placements;

    /**
     * Creates a new empty board.
     * @param width width of the board
     * @param height height of the board
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.placements = new Stack<>();
    }

    /**
     * Try to apply a piece on the board.
     * @param placement Placement to try out
     * @return true if placement is successful, false if not.
     */
    public boolean apply(@Nonnull Placement placement) {
        if (isValidPlacement(placement)) {
            placements.push(placement);
            return true;
        }

        return false;
    }

    /**
     * Undo most recent successfully applied piece placement.
     */
    public void undo() {
        placements.pop();
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

    public List<Coordinate> getFreeSlots() {
        var result = new ArrayList<Coordinate>();
        for (int x=0 ; x < width ; x++) {
            for (int y=0 ; y < height ; y++) {
                result.add(new Coordinate(x, y));
            }
        }
        return result.stream().filter(not(this::overlapsExistingPlacements)).collect(Collectors.toList());
    }

    public Set<Placement> getPlacements() {
        return new HashSet<>(placements);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Piece getPieceAt(int x, int y) {
        final Coordinate slot = new Coordinate(x, y);
        return placements.stream()
                .filter(p -> p.coordinates().contains(slot))
                .map(Placement::getPiece)
                .findAny()
                .orElse(null);
    }
}
