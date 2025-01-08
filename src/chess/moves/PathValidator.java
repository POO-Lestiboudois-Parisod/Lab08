package chess.moves;

import chess.Board;
import chess.Square;

public interface PathValidator {
    boolean isPathClear(Board board, Square start, Square end);
}