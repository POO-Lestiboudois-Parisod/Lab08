package chess.pieces;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */

import chess.*;
import chess.moves.MoveType;
import chess.moves.PathValidator;
import chess.moves.DefaultPathValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente la pièce Tour dans le jeu d'échecs.
 * La Tour se déplace horizontalement ou verticalement sur n'importe quelle distance,
 * tant que son chemin est dégagé. La Tour est également impliquée dans le mouvement spécial du roque.
 */
public class Rook extends SpecialFirstMovePiece {

    private final List<MoveStrategy> moveStrategies = new ArrayList<>();

    /**
     * Constructeur pour initialiser la Tour avec sa couleur.
     *
     * @param color la couleur de la Tour (blanc ou noir).
     */
    public Rook(PlayerColor color) {
        super(color, PieceType.ROOK);
        // Ajouter les stratégies de mouvement
        moveStrategies.add(new StandardRookMove());
    }

    /**
     * Vérifie si la Tour peut se déplacer de la case de départ à la case d'arrivée.
     * La Tour peut se déplacer horizontalement ou verticalement,
     * tant que le chemin est dégagé.
     *
     * @param board le plateau de jeu sur lequel le mouvement est effectué.
     * @param start la case de départ.
     * @param end la case d'arrivée.
     * @return true si le mouvement est valide, false sinon.
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
     * Exécute le mouvement de la Tour de la case de départ à la case d'arrivée.
     *
     * @param board le plateau de jeu sur lequel le mouvement est effectué.
     * @param start la case de départ.
     * @param end la case d'arrivée.
     * @throws IllegalArgumentException si le mouvement est invalide.
     */
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

    /**
     * Vérifie si la Tour peut participer au mouvement spécial du roque.
     * La Tour peut participer au roque si elle n'a pas encore bougé.
     *
     * @return true si la Tour peut participer au roque, false sinon.
     */
    public boolean canParticipateInCastling() {
        return !hasMoved();
    }

    /**
     * Marque la Tour comme ayant participé au roque après l'exécution du mouvement.
     */
    public void participatedInCastling() {
        markAsMoved();
    }

    /**
     * Classe interne représentant le mouvement standard de la Tour.
     * La Tour peut se déplacer horizontalement ou verticalement.
     */
    private class StandardRookMove implements MoveStrategy {

        private final PathValidator pathValidator = new DefaultPathValidator();

        /**
         * Vérifie si le mouvement de la Tour de la case de départ à la case d'arrivée est valide.
         *
         * @param board le plateau de jeu.
         * @param start la case de départ.
         * @param end la case d'arrivée.
         * @return true si le mouvement est valide, false sinon.
         */
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
        /**
         * Exécute le mouvement de la Tour de la case de départ à la case d'arrivée.
         *
         * @param board le plateau de jeu.
         * @param start la case de départ.
         * @param end la case d'arrivée.
         */
        @Override
        public void execute(Board board, Square start, Square end) {
            Piece rook = board.getPiece(start.getX(), start.getY());
            board.movePiece(rook, end);
            markAsMoved();
        }
    }
}