package solver;

import board.Board;
import board.Piece;

import javax.annotation.Nonnull;
import java.util.List;

public interface SolutionStrategy {

    /**
     * Algorithm to place the pieces legally on board
     * @param board board to place pieces on.
     * @param pieces pieces to place.
     * @return true if valid solution found, false otherwise
     */
    boolean solve(@Nonnull Board board, @Nonnull List<Piece> pieces);
}
