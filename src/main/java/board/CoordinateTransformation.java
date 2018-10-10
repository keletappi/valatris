package board;

import java.util.Objects;
import java.util.function.Function;

public final class CoordinateTransformation implements Function<Coordinate, Coordinate> {
    private final int dX;
    private final int dY;

    public CoordinateTransformation(int dX, int dY) {
        this.dX = dX;
        this.dY = dY;
    }

    public Coordinate apply(Coordinate absolute) {
        return new Coordinate(absolute.getX() + dX, absolute.getY() + dY);
    }

    @Override
    public String toString() {
        return "CoordinateTransformation{" +
                "dX=" + dX +
                ", dY=" + dY +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoordinateTransformation)) return false;
        CoordinateTransformation that = (CoordinateTransformation) o;
        return dX == that.dX &&
                dY == that.dY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dX, dY);
    }
}
