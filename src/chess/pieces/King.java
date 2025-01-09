package chess.pieces;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */

import chess.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un roi dans un jeu d'échecs.
 * Le roi peut effectuer des mouvements standards d'une case dans toutes les directions
 * ou des roques dans certaines conditions.
 */
public class King extends SpecialFirstMovePiece {
    /**
     * Liste des stratégies de mouvement du roi, incluant les mouvements standards et le roque.
     */
    private final List<MoveStrategy> moveStrategies = new ArrayList<>();

    /**
     * Initialise un roi avec une couleur spécifiée.
     *
     * @param color la couleur du joueur à laquelle appartient le roi.
     */
    public King(PlayerColor color) {
        super(color, PieceType.KING);
        // Ajouter les stratégies de mouvement
        moveStrategies.add(new StandardKingMove());
        moveStrategies.add(new CastlingMove());
    }

    /**
     * Vérifie si le roi peut se déplacer d'une case à une autre selon ses règles de mouvement.
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
                return strategy instanceof CastlingMove || super.canMove(board, start, end);
            }
        }
        return false;
    }

    /**
     * Exécute un mouvement pour le roi sur l'échiquier.
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
                if (!hasMoved()) {
                    markAsMoved();
                }
                return;
            }
        }
        throw new IllegalArgumentException("Mouvement invalide pour le roi.");
    }

    /**
     * Stratégie interne pour gérer le roque.
     */
    private class CastlingMove implements MoveStrategy {
        /**
         * Vérifie si un roque est valide dans l'état actuel du jeu.
         *
         * @param board l'échiquier.
         * @param start la case de départ du roi.
         * @param end la case d'arrivée du roi.
         * @return {@code true} si le roque est valide, {@code false} sinon.
         */
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

        /**
         * Exécute le roque en déplaçant le roi et la tour sur l'échiquier.
         *
         * @param board l'échiquier.
         * @param start la case de départ du roi.
         * @param end la case d'arrivée du roi.
         */
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

    /**
     * Stratégie interne pour gérer le mouvement standard du roi.
     */
    private class StandardKingMove implements MoveStrategy {

        /**
         * Vérifie si le mouvement standard du roi est valide (une case dans toutes les directions).
         *
         * @param board l'échiquier.
         * @param start la case de départ.
         * @param end la case d'arrivée.
         * @return {@code true} si le mouvement est valide, {@code false} sinon.
         */
        @Override
        public boolean isValid(Board board, Square start, Square end) {
            int deltaX = King.this.distanceX(end);
            int deltaY = King.this.distanceY(end);

            // Le roi peut se déplacer d'une case dans toutes les directions
            return deltaX <= 1 && deltaY <= 1;
        }

        /**
         * Exécute le mouvement standard du roi.
         *
         * @param board l'échiquier.
         * @param start la case de départ.
         * @param end la case d'arrivée.
         */
        @Override
        public void execute(Board board, Square start, Square end) {
            Piece king = board.getPiece(start.getX(), start.getY());
            board.movePiece(king, end);
        }
    }
}
