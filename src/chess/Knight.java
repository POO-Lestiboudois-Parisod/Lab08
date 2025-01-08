package chess;

import chess.moves.MoveType;

public class Knight extends Piece {

    public Knight(PlayerColor color) {
        super(color, PieceType.KNIGHT);
    }

    // Le cavalier se déplace en forme de L
    @Override
    public boolean canMove(Board board, Square start, Square end) {
        return (MoveType.L_SHAPE.isValid(distanceX(end), distanceY(end)) && super.canMove(board, start, end) );
    }
}
