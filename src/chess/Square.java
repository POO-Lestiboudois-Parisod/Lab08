package chess;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */

import chess.pieces.Piece;

/**
 * Représente une case sur l'échiquier.
 * Chaque case est définie par ses coordonnées (x, y) et peut contenir une pièce.
 */
public class Square {
    private final int x;
    private final int y;
    private Piece piece;

    /**
     * Constructeur d'une case à des coordonnées spécifiques.
     *
     * @param x Coordonnée X de la case
     * @param y Coordonnée Y de la case
     */
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null;
    }

    /**
     * Vérifie si la case est occupée par une pièce.
     *
     * @return true si une pièce occupe la case, false sinon.
     */
    public boolean isOccupied() {
        return piece != null;
    }

    /**
     * Obtient la pièce sur la case.
     *
     * @return La pièce présente ou null si la case est vide.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Place une pièce sur la case.
     * Met également à jour la case associée à la pièce.
     *
     * @param piece La pièce à placer (null pour vider la case).
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece != null) {
            piece.setSquare(this);
        }
    }

    /**
     * Obtient la coordonnée X de la case.
     *
     * @return La coordonnée X.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtient la coordonnée Y de la case.
     *
     * @return La coordonnée Y.
     */
    public int getY() {
        return y;
    }
}
