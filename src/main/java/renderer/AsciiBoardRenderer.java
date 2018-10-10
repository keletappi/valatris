package renderer;

import board.Board;
import board.Piece;
import com.google.common.base.Strings;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Optional;

public class AsciiBoardRenderer implements BoardRenderer {

    private final static Piece EMPTY = new Piece(".");

    @Override
    public void render(@Nonnull Board board, @Nonnull OutputStream outputStream) throws IOException {

        final int height = board.getHeight();
        final int width = board.getWidth();

        StringBuilder builder = new StringBuilder((width + 3) * (height + 2));

        appendTopBorder(width, builder);
        appendRows(board, builder);
        appendBottomBorder(width, builder);

        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        writer.write(builder.toString());
        writer.flush();

    }

    private void appendRows(@Nonnull Board board, StringBuilder builder) {
        for (int y = board.getHeight() - 1 ; y >= 0 ; y--) {
            appendRow(y, board, builder);
        }
    }

    private void appendRow(int y, Board board, StringBuilder builder) {
        builder.append('\u2502');
        for (int x = 0 ; x < board.getWidth() ; x++) {
            Piece piece = board.getPieceAt(x, y);

            String label = Optional.ofNullable(piece).orElse(EMPTY).getLabel().trim();
            if (Strings.isNullOrEmpty(label)) {
                label = " ";
            }
            builder.append(label);
        }
        builder.append('\u2502').append('\n');
    }

    private void appendTopBorder(int width, StringBuilder builder) {
        builder.append('\u250c');
        for (int i = 0 ; i < width ; i++) {
            builder.append('\u2500');
        }
        builder.append('\u2510').append('\n');

    }

    private void appendBottomBorder(int width, StringBuilder builder) {
        builder.append('\u2514');
        for (int i=0 ; i < width ; i++) {
            builder.append('\u2500');
        }
        builder.append('\u2518').append('\n');
    }
}
