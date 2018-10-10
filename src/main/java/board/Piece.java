package board;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

public class Piece {
    private final String label;
    private final List<CoordinateTransformation> pieceTransformations;

    public Piece(@Nonnull String label, @Nonnull CoordinateTransformation ... pieceTransformations) {
        this(label, List.of(pieceTransformations));
    }

    public Piece(@Nonnull String label, @Nonnull List<CoordinateTransformation> pieceTransformations) {
        this.label = label;
        this.pieceTransformations = pieceTransformations;
    }

    public List<Coordinate> absoluteCoordinates(@Nonnull Coordinate center) {
        return pieceTransformations.stream()
                .map(transformation -> transformation.apply(center))
                .collect(Collectors.toList());
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "label='" + label + '\'' +
                '}';
    }
}
