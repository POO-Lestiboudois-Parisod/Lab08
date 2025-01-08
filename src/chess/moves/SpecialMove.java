package chess.moves;

import chess.Board;
import chess.Square;

public interface SpecialMove {
    boolean isValid(Board board, Square start, Square end);
    void execute(Board board, Square start, Square end);
}