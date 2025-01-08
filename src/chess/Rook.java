/*package chess;

public class Rook extends SpecialFirstMovePiece implements CastlingPiece {
    public Rook(PlayerColor color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public boolean canMove(Board board, int startX, int startY, int endX, int endY) {
        return false;
    }

    public boolean canCastle(){
        return true;//TODO
    }

    public boolean canMove(Square square){


        if(square.isOccupied()){
            if(square.getPiece().getColor().equals(this.getColor())){
                return false;
            }
        }

        // Calcul de la distance
        int deltaX = Math.abs(square.getX() - this.square.getX());
        int deltaY = Math.abs(square.getY() - this.square.getY());

        if(deltaX > 0 && deltaY > 0) {
            return false;
        }
        else{
            return true;
        }
    }
};*/

package chess;

import chess.moves.MoveType;
import chess.moves.PathValidator;
import chess.moves.DefaultPathValidator;
import java.util.ArrayList;
import java.util.List;

public class Rook extends SpecialFirstMovePiece {

    private final List<MoveStrategy> moveStrategies = new ArrayList<>();

    public Rook(PlayerColor color) {
        super(color, PieceType.ROOK);
        // Ajouter les stratégies de mouvement
        moveStrategies.add(new StandardRookMove());
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {
        for (MoveStrategy strategy : moveStrategies) {
            if (strategy.isValid(board, start, end)) {
                return true;
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
        throw new IllegalArgumentException("Mouvement invalide pour la tour.");
    }

    public boolean canParticipateInCastling() {
        return !hasMoved();
    }

    // Interface pour les stratégies de mouvement
    private interface MoveStrategy {
        boolean isValid(Board board, Square start, Square end);
        void execute(Board board, Square start, Square end);
    }

    // Classe interne pour le mouvement standard de la tour
    private class StandardRookMove implements MoveStrategy {

        private final PathValidator pathValidator = new DefaultPathValidator();

        @Override
        public boolean isValid(Board board, Square start, Square end) {
            int deltaX = Rook.this.distanceX(end);
            int deltaY = Rook.this.distanceY(end);

            // La tour peut se déplacer horizontalement ou verticalement
            if (!MoveType.HORIZONTAL.isValid(deltaX, deltaY) &&
                    !MoveType.VERTICAL.isValid(deltaX, deltaY)) {
                return false;
            }

            // Vérification du chemin via le PathValidator
            return pathValidator.isPathClear(board, start, end) && Rook.super.canMove(board, start, end);
        }

        @Override
        public void execute(Board board, Square start, Square end) {
            Piece rook = board.getPiece(start.getX(), start.getY());
            board.movePiece(rook, end);
        }
    }
}