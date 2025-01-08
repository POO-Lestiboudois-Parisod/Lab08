package chess;

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
