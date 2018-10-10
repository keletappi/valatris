package parser;

import board.CoordinateTransformation;
import board.Piece;
import com.google.common.base.Strings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class PieceReader {

    public List<Piece> readPieces(File file) throws IOException {
        if (!file.exists()) {
            throw new IllegalArgumentException("File " + file + " not found");
        }
        try (var fr = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            while (true) {
                String line = fr.readLine();
                if (line != null) {
                    lines.add(line);
                } else {
                    break;
                }
            }
            return lines.stream()
                    .filter(not(Strings::isNullOrEmpty))
                    .map(this::parsePiece)
                    .collect(Collectors.toList());
        }
    }

    Piece parsePiece(String line) {
        int labelSeparatorIdx = line.indexOf(':');
        if (labelSeparatorIdx < 0) {
            throw new IllegalArgumentException("Line '" + line + "' does not contain label separator ':'");
        }

        var label = line.substring(0, labelSeparatorIdx);
        var transformations = new ArrayList<CoordinateTransformation>();
        var coordinateTokenizer = new StringTokenizer(line.substring(labelSeparatorIdx + 1), ";");
        while (coordinateTokenizer.hasMoreTokens()) {
            String coordinate = coordinateTokenizer.nextToken();
            int separatorIdx = coordinate.indexOf(',');
            if (separatorIdx < 0) {
                throw new IllegalArgumentException("Line '" + line + "' contains illegal coordinate separator ','");
            }
            int x = Integer.parseInt(coordinate.substring(0, separatorIdx).trim());
            int y = Integer.parseInt(coordinate.substring(separatorIdx + 1).trim());
            transformations.add(new CoordinateTransformation(x, y));
        }
        return new Piece(label, transformations);
    }
}
