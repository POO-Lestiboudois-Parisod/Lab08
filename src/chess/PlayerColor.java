package chess;

/**
 * @modified by Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */

public enum PlayerColor {
    WHITE, BLACK;

    /**
     * Retourne la couleur opposée.
     *
     * @return PlayerColor opposé (WHITE devient BLACK et vice-versa).
     */
    public PlayerColor opposite() {
        return this == WHITE ? BLACK : WHITE;
    }
}
