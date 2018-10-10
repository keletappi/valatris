package renderer;

import board.Board;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.OutputStream;

public interface BoardRenderer {

    void render(@Nonnull Board board,
                @Nonnull OutputStream outputStream) throws IOException;
}
