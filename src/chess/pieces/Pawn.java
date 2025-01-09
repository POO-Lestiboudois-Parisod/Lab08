package chess.pieces;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */

import chess.*;
import chess.moves.DefaultPathValidator;
import chess.moves.PathValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un pion dans un jeu d'échecs.
 * Le pion se déplace d'une case vers l'avant, mais peut se déplacer de deux cases lors de son premier mouvement.
 * Il peut capturer en diagonale, effectuer une prise en passant et être promu lorsqu'il atteint la dernière rangée.
 */
public class Pawn extends SpecialFirstMovePiece {

    private final int direction;
    private final List<MoveStrategy> moveStrategies = new ArrayList<>();
    private final PathValidator pathValidator = new DefaultPathValidator();

    /**
     * Initialise un pion avec une couleur spécifiée.
     *
     * @param color la couleur du joueur à laquelle appartient le pion.
     */
    public Pawn(PlayerColor color) {
        super(color, PieceType.PAWN);
        direction = color == PlayerColor.WHITE ? 1 : -1;
        // Ajouter les stratégies de mouvement
        moveStrategies.add(new StandardPawnMove());
        moveStrategies.add(new DoubleStepMove());
        moveStrategies.add(new EnPassantMove());
    }

    /**
     * Vérifie si le pion peut se déplacer d'une case à une autre selon ses règles de mouvement.
     * Le pion peut avancer d'une case, avancer de deux cases lors de son premier mouvement, ou capturer en diagonale.
     *
     * @param board l'échiquier représentant l'état actuel du jeu.
     * @param start la case de départ.
     * @param end la case d'arrivée.
     * @return {@code true} si le déplacement est valide, {@code false} sinon.
     */
    @Override
    public boolean canMove(Board board, Square start, Square end) {
        for (MoveStrategy strategy : moveStrategies) {
            if (strategy.isValid(board, start, end)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Exécute un mouvement pour le pion sur l'échiquier, avec gestion de la promotion si nécessaire.
     *
     * @param board l'échiquier représentant l'état actuel du jeu.
     * @param start la case de départ.
     * @param end la case d'arrivée.
     * @throws IllegalArgumentException si le mouvement est invalide.
     */
    @Override
    public void executeMove(Board board, Square start, Square end) {
        for (MoveStrategy strategy : moveStrategies) {
            if (strategy.isValid(board, start, end)) {
                strategy.execute(board, start, end);
                markAsMoved();

                // Vérification de la promotion
                if (shouldPromote(end)) {
                    promote(board, end);
                }
                return;
            }
        }
        throw new IllegalArgumentException("Mouvement invalide pour le pion.");
    }

    /**
     * Vérifie si le pion doit être promu (lorsqu'il atteint la dernière ligne).
     *
     * @param end la case d'arrivée.
     * @return {@code true} si le pion doit être promu, {@code false} sinon.
     */
    private boolean shouldPromote(Square end) {
        return (getColor() == PlayerColor.WHITE && end.getY() == 7) || (getColor() == PlayerColor.BLACK && end.getY() == 0);
    }

    /**
     * Promeut le pion en une autre pièce (par exemple, une reine) lorsque cela est nécessaire.
     *
     * @param board l'échiquier représentant l'état actuel du jeu.
     * @param square la case où le pion doit être promu.
     */
    private void promote(Board board, Square square) {
        board.getGameController().promotePawn(square);
    }

    // Mouvement standard d'un pion
    private class StandardPawnMove implements MoveStrategy {
        /**
         * Vérifie si le mouvement standard du pion est valide (avance d'une case ou capture en diagonale).
         *
         * @param board l'échiquier.
         * @param start la case de départ.
         * @param end la case d'arrivée.
         * @return {@code true} si le mouvement est valide, {@code false} sinon.
         */
        @Override
        public boolean isValid(Board board, Square start, Square end) {
            int deltaX = distanceX(end);
            int deltaY = distanceY(end);

            // Vérifier que le pion avance dans la bonne direction
            if ((getColor() == PlayerColor.WHITE && end.getY() <= start.getY()) ||
                    (getColor() == PlayerColor.BLACK && end.getY() >= start.getY())) {
                return false;
            }

            // Avancer d'une case
            return (deltaX == 0 && deltaY == 1 && !end.isOccupied()) ||
                    (deltaX == 1 && deltaY == 1 && end.isOccupied() && isNotSameColor(end.getPiece()));

        }

        /**
         * Exécute le mouvement standard du pion.
         *
         * @param board l'échiquier.
         * @param start la case de départ.
         * @param end la case d'arrivée.
         */
        @Override
        public void execute(Board board, Square start, Square end) {
            Piece pawn = board.getPiece(start.getX(), start.getY());
            board.movePiece(pawn, end);
            markAsMoved();
        }

    }


    private class DoubleStepMove implements MoveStrategy {
        /**
         * Vérifie si le mouvement de deux cases est valide (le premier mouvement du pion).
         *
         * @param board l'échiquier.
         * @param start la case de départ.
         * @param end la case d'arrivée.
         * @return {@code true} si le mouvement est valide, {@code false} sinon.
         */
        @Override
        public boolean isValid(Board board, Square start, Square end) {
            int deltaX = distanceX(end);
            int deltaY = distanceY(end);

            // Avancer de deux cases au premier coup
            return !Pawn.super.hasMoved() && deltaX == 0 && deltaY == 2 && !end.isOccupied()
                    && Pawn.this.pathValidator.isPathClear(board, start, end);
        }

        /**
         * Exécute le mouvement de deux cases.
         *
         * @param board l'échiquier.
         * @param start la case de départ.
         * @param end la case d'arrivée.
         */
        @Override
        public void execute(Board board, Square start, Square end) {
            Piece pawn = board.getPiece(start.getX(), start.getY());
            board.movePiece(pawn, end);
            markAsMoved();
        }
    }

    // Prise en passant
    private class EnPassantMove implements MoveStrategy {

        /**
         * Vérifie si la prise en passant est valide.
         *
         * @param board l'échiquier.
         * @param start la case de départ.
         * @param end la case d'arrivée.
         * @return {@code true} si la prise en passant est valide, {@code false} sinon.
         */
        @Override
        public boolean isValid(Board board, Square start, Square end) {
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
            return deltaX == 1 && deltaY == 1
                    && lastMovedPiece.getColor() != Pawn.this.getColor()
                    && Math.abs(lastTo.getY() - lastFrom.getY()) == 2
                    && lastTo.getX() == end.getX()
                    && lastTo.getY() == start.getY();
        }
        /**
         * Exécute la prise en passant.
         *
         * @param board l'échiquier.
         * @param start la case de départ.
         * @param end la case d'arrivée.
         */
        @Override
        public void execute(Board board, Square start, Square end) {
            GameController.Move lastMove = board.getGameController().getLastMove();
            if (lastMove == null) {
                throw new IllegalStateException("Aucune prise en passant valide");
            }

            Piece pawn = board.getPiece(start.getX(), start.getY());
            board.removeCapturedPiece(board.getSquare(end.getX(), start.getY()));
            board.getGameController().removePiece(end.getX(), start.getY());
            board.movePiece(pawn, end);
        }
    }
}
