package renderer;

import board.Board;
import board.Placement;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class PlacementCoordinatesRenderer implements BoardRenderer {
    @Override
    public void render(@Nonnull Board board, @Nonnull OutputStream outputStream) throws IOException {
        var writer = new OutputStreamWriter(outputStream);
        StringBuilder builder = new StringBuilder();
        board.getPlacements().forEach(placement -> builder.append(format(placement)).append('\n'));
        writer.write(builder.toString());
        writer.flush();
    }

    private String format(Placement placement) {
        return String.format("<%d,%d>", placement.getX(), placement.getY());
    }
}
