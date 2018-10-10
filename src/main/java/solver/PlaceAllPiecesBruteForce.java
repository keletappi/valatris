package solver;

import board.Board;
import board.Coordinate;
import board.Piece;
import board.Placement;
import renderer.AsciiBoardRenderer;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;

public class PlaceAllPiecesBruteForce implements SolutionStrategy {

    @Override
    public boolean solve(@Nonnull Board board, @Nonnull List<Piece> pieces) {
        if (pieces.size() == 0) {
            // Obvious solution - no pieces, so game is complete
            return true;
        }

        return findPlacement(board, pieces.get(0), pieces.subList(1, pieces.size()));
    }

    private boolean findPlacement(@Nonnull Board board,
                                  @Nonnull Piece piece,
                                  @Nonnull List<Piece> remainingPieces) {
        var slots = board.getFreeSlots();
        for (Coordinate slot : slots) {
            Placement placement = new Placement(piece, slot);

            if (board.apply(placement)) {
                if (remainingPieces.isEmpty()) {
                    return true;
                } else {
                    if (findPlacement(board,
                            remainingPieces.get(0),
                            remainingPieces.subList(1, remainingPieces.size()))) {
                        return true;
                    } else {
                        board.undo();
                    }
                }
            }
        }

        return false;
    }

}
