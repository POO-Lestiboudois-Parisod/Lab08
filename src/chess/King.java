package chess;

import java.util.ArrayList;
import java.util.List;

public class King extends SpecialFirstMovePiece implements CastlingPiece {

    private final List<MoveStrategy> moveStrategies = new ArrayList<>();

    public King(PlayerColor color) {
        super(color, PieceType.KING);
        // Ajouter les stratégies de mouvement
        moveStrategies.add(new StandardKingMove());
        moveStrategies.add(new CastlingMove());
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {
        for (MoveStrategy strategy : moveStrategies) {
            if (strategy.isValid(board, start, end)) {
                return strategy instanceof CastlingMove || super.canMove(board, start, end);
            }
        }
        return false;
    }

    @Override
    public void executeMove(Board board, Square start, Square end) {
        for (MoveStrategy strategy : moveStrategies) {
            if (strategy.isValid(board, start, end)) {
                strategy.execute(board, start, end);
                return;
            }
        }
        throw new IllegalArgumentException("Mouvement invalide pour le roi.");
    }

    @Override
    public boolean canCastle() {
        //TODO
        return false;
    }

    private class CastlingMove implements MoveStrategy {

        @Override
        public boolean isValid(Board board, Square start, Square end) {
            Piece rook = board.getPiece(end.getX() < start.getX() ? 0 : 7, start.getY());

            if (!(rook instanceof Rook) || !((Rook) rook).canParticipateInCastling()) {
                return false;
            }

            for (int x = start.getX(); x <= rook.getSquare().getX(); ++x) {
                if ((board.isSquareUnderAttack(board.getSquare(x, start.getY()), getColor()) && x <= end.getX()) ||
                        board.getSquare(x, start.getY()).isOccupied()) {
                    return false;
                }
            }

            return true;
        }

        @Override
        public void execute(Board board, Square start, Square end) {
            Piece king = board.getPiece(start.getX(), start.getY());
            board.movePiece(king, end);

            Piece rook = board.getPiece(end.getX() < start.getX() ? 0 : 7, start.getY());
            board.movePiece(rook, board.getSquare(end.getX() < start.getX() ? end.getX() + 1 : end.getX() - 1, end.getY()));
        }
    }

    // Classe interne pour le mouvement standard du roi
    private class StandardKingMove implements MoveStrategy {

        @Override
        public boolean isValid(Board board, Square start, Square end) {
            int deltaX = King.this.distanceX(end);
            int deltaY = King.this.distanceY(end);

            // Le roi peut se déplacer d'une case dans toutes les directions
            return deltaX <= 1 && deltaY <= 1;
        }

        @Override
        public void execute(Board board, Square start, Square end) {
            Piece king = board.getPiece(start.getX(), start.getY());
            board.movePiece(king, end);
        }
    }
}
