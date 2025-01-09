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
                if (!hasMoved()) {
                    markAsMoved();
                }
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
            int deltaX = end.getX() - start.getX();
            if (Math.abs(deltaX) != 2 || start.getY() != end.getY()) {
                return false;
            }

            int rookX = deltaX > 0 ? 7 : 0;
            Piece rook = board.getPiece(rookX, start.getY());


            if (!(rook instanceof Rook) || !((Rook) rook).canParticipateInCastling() || hasMoved()) {
                return false;
            }

            for (int x = Math.min(start.getX(), rookX) + 1; x < Math.max(start.getX(), rookX); x++) {
                if (board.getSquare(x, start.getY()).isOccupied()) {
                    return false;
                }
            }

            return true;
        }

        @Override
        public void execute(Board board, Square start, Square end) {
            int deltaX = end.getX() - start.getX();
            int rookX = deltaX > 0 ? 7 : 0;
            int rookDestinationX = start.getX() + (deltaX > 0 ? 1 : -1);

            Piece king = board.getPiece(start.getX(), start.getY());
            Rook rook = (Rook) board.getPiece(rookX, start.getY());

            rook.participatedInCastling();


            board.movePiece(king, end);
            board.movePiece(rook, board.getSquare(rookDestinationX, start.getY()));
            board.getGameController().removePiece(rookX, start.getY());
            board.getGameController().setPiece(rook.getSquare().getX(), rook.getSquare().getY());

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
