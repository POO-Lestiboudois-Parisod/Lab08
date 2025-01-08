package chess;

import chess.moves.DefaultPathValidator;
import chess.moves.PathValidator;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends SpecialFirstMovePiece implements PromotablePiece {

    private final int direction;
    private final List<MoveStrategy> moveStrategies = new ArrayList<>();
    private final PathValidator pathValidator = new DefaultPathValidator();

    public Pawn(PlayerColor color) {
        super(color, PieceType.PAWN);
        direction = color == PlayerColor.WHITE ? 1 : -1;
        // Ajouter les stratégies de mouvement
        moveStrategies.add(new StandardPawnMove());
        moveStrategies.add(new DoubleStepMove());
        moveStrategies.add(new EnPassantMove());
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

    public MoveStrategy canMoveStrategy(Board board, Square start, Square end) {
        for (MoveStrategy strategy : moveStrategies) {
            if (strategy.isValid(board, start, end)) {
                return strategy;
            }
        }
        return null;
    }

    @Override
    public void executeMove(Board board, Square start, Square end) {
        if(!hasMoved()){
            markAsMoved();
        }

        if(canMove(board, start, end)) {

            // for (MoveStrategy strategy : moveStrategies) {
            MoveStrategy strategy = canMoveStrategy(board, start, end);
            System.out.println("bijour " + strategy);
            strategy.execute(board, start, end);

            // Vérification de la promotion
            if (shouldPromote(end)) {
                promote(board, end);
            }
        }
        //}
       // throw new IllegalArgumentException("Mouvement invalide pour le pion.");
    }

    @Override
    public boolean canPromote() {
        return true;
    }

    private boolean shouldPromote(Square end) {
        return (getColor() == PlayerColor.WHITE && end.getY() == 7) || (getColor() == PlayerColor.BLACK && end.getY() == 0);
    }

    private void promote(Board board, Square square) {
        // Par défaut, promouvoir en reine
        board.getSquare(square.getX(), square.getY()).setPiece(new Queen(getColor()));
    }

    // Mouvement standard d'un pion
    private class StandardPawnMove implements MoveStrategy {
        @Override
        public boolean isValid(Board board, Square start, Square end) {
            System.out.println("gaga");
            int deltaX = distanceX(end);
            int deltaY = end.getY()-start.getY();

            // Avancer d'une case
            if (deltaX == 0 && deltaY == Pawn.this.direction && !end.isOccupied()) {
                return true;
            }

            // Capture diagonale
            if (deltaX == 1 && deltaY == Pawn.this.direction && end.isOccupied() && isNotSameColor(end.getPiece())) {
                return true;
            }

            return false;
        }

        @Override
        public void execute(Board board, Square start, Square end) {
            System.out.println("world");
            Piece pawn = board.getPiece(start.getX(), start.getY());
            board.movePiece(pawn, end);
            markAsMoved();
        }
    }

    // Mouvement de deux cases au premier coup
    private class DoubleStepMove implements MoveStrategy {
        @Override
        public boolean isValid(Board board, Square start, Square end) {
            System.out.println("gogo");
            int deltaX = distanceX(end);
            int deltaY = end.getY()-start.getY();

            // Avancer de deux cases au premier coup
            return !Pawn.super.hasMoved() && deltaX == 0 && deltaY == 2 * Pawn.this.direction && !end.isOccupied()//TODO
                    && Pawn.this.pathValidator.isPathClear(board, start, end);
        }

        @Override
        public void execute(Board board, Square start, Square end) {
            Piece pawn = board.getPiece(start.getX(), start.getY());
            board.movePiece(pawn, end);
            System.out.println("Pawn moved");
            markAsMoved();
        }
    }

    // Prise en passant
    private class EnPassantMove implements MoveStrategy {
        @Override
        public boolean isValid(Board board, Square start, Square end) {
            System.out.println("gugu");
            GameController.Move lastMove = ((GameController) board.getGameController()).getLastMove();
            if (lastMove == null || !(lastMove.getPiece() instanceof Pawn)) {
                return false;
            }

            Piece lastMovedPiece = lastMove.getPiece();
            Square lastFrom = lastMove.getFrom();
            Square lastTo = lastMove.getTo();

            int deltaX = distanceX(end);
            int deltaY = distanceY(end);

            // Vérification des conditions de la prise en passant
            return deltaX == 1 && deltaY == Pawn.this.direction
                    && lastMovedPiece.getColor() != Pawn.this.getColor()
                    && Math.abs(lastTo.getY() - lastFrom.getY()) == 2
                    && lastTo.getX() == end.getX()
                    && lastTo.getY() == start.getY();
        }

        @Override
        public void execute(Board board, Square start, Square end) {
            GameController.Move lastMove = ((GameController) board.getGameController()).getLastMove();
            if (lastMove == null) {
                throw new IllegalStateException("Aucune prise en passant valide");
            }

            Piece pawn = board.getPiece(start.getX(), start.getY());
            board.movePiece(pawn, end);

            // Supprimer le pion capturé en passant
            Square capturedSquare = lastMove.getTo();
            board.getSquare(capturedSquare.getX(), capturedSquare.getY()).setPiece(null);
        }
    }
}
