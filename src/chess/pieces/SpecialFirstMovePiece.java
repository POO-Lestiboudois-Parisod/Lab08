package chess.pieces;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */


import chess.PieceType;
import chess.PlayerColor;

/**
 * Classe abstraite représentant une pièce qui a un mouvement spécial lors de son premier coup.
 * Les pièces dérivées de cette classe (comme le roi et la tour) peuvent avoir des mouvements spéciaux
 * qui ne peuvent être effectués que si la pièce n'a pas encore bougé, comme le roque.
 */
abstract class SpecialFirstMovePiece extends Piece {
    private boolean hasMoved = false;

    /**
     * Constructeur pour initialiser une pièce avec un type et une couleur.
     *
     * @param color la couleur de la pièce (blanc ou noir).
     * @param type le type de la pièce (par exemple, roi, tour).
     */
    protected SpecialFirstMovePiece(PlayerColor color, PieceType type) {
        super(color, type);
    }

    /**
     * Vérifie si la pièce a déjà été déplacée.
     *
     * @return true si la pièce a été déplacée, false sinon.
     */
    public boolean hasMoved() {
        return hasMoved;
    }

    /**
     * Marque la pièce comme ayant été déplacée.
     * Cette méthode est utilisée après qu'un mouvement ait été effectué sur la pièce,
     * par exemple, après un roque ou tout autre mouvement qui implique cette pièce.
     */
    public void markAsMoved() {
        this.hasMoved = true;
    }

    /**
     * Réinitialise l'état de la pièce pour indiquer qu'elle n'a pas été déplacée.
     * Cette méthode peut être utilisée pour réinitialiser la pièce dans certaines situations
     * (par exemple, lors du retour d'un état du jeu ou d'une autre logique spécifique).
     */
    public void resetMove() {
        this.hasMoved = false;
    }
}