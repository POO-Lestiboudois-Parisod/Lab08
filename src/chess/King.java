/*package chess;

import java.awt.*;
import java.util.ArrayList;

public class King extends SpecialFirstMovePiece implements CastlingPiece {

    public King(PlayerColor color) {
        super(color, PieceType.KING);
    }

    @Override
    public boolean canMove(Board board, int startX, int startY, int endX, int endY) {
        return false;
        //TODO
    }

    @Override
    public boolean canMove(Square square){

        super.canMove(square);


        // Calcul de la distance
        double deltaX = distanceX(square);
        double deltaY = distanceY(square);

        // Le roi peut se déplacer d'une case dans n'importe quelle direction
        return (deltaX <= Math.sqrt(2) && deltaY <= Math.sqrt(2));

    }
    public boolean canCastle() {
        return true;//TODO
    }

    //TODO
   /* public void addBeingCheckedBy(Piece piece) {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (Piece otherPiece : pieces) {
            if(otherPiece.isAttacking(this.getSquare())){
                pieces.add(this);
            }
        }
    }///////////////


};*/

package chess;

import chess.moves.MoveType;
import chess.moves.PathValidator;
import chess.moves.DefaultPathValidator;

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
        throw new IllegalArgumentException("Mouvement invalide pour le roi.");
    }

    private class CastlingMove implements MoveStrategy {

        @Override
        public boolean isValid(Board board, Square start, Square end) {
            Piece rook = board.getPiece(end.getX() < start.getX() ? 0 : 7, start.getY());

            if (!(rook instanceof Rook) || !((Rook) rook).canParticipateInCastling()) {
                return false;
            }

            int stepX = (end.getX() - start.getX()) / Math.abs(end.getX() - start.getX());
            for (int x = start.getX(); x != end.getX() + stepX; x += stepX) {
                if (board.isSquareUnderAttack(board.getSquare(x, start.getY()), getColor())) {
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
            int deltaX = Math.abs(end.getX() - start.getX());
            int deltaY = Math.abs(end.getY() - start.getY());

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
