/*package chess;

abstract class SpecialFirstMovePiece extends Piece {
    protected boolean hasMoved;

    public boolean hasMoved() {
        return true;//TODO
    }

    protected SpecialFirstMovePiece(PlayerColor color, PieceType type) {
        super(color, type);
    }
}*/

package chess;

abstract class SpecialFirstMovePiece extends Piece {
    private boolean hasMoved = false;

    protected SpecialFirstMovePiece(PlayerColor color, PieceType type) {
        super(color, type);
    }

    // Vérifie si la pièce a déjà été déplacée
    public boolean hasMoved() {
        return hasMoved;
    }

    // Marque la pièce comme ayant été déplacée
    public void markAsMoved() {
        this.hasMoved = true;
    }

    // Réinitialise l'état de la pièce
    public void resetMove() {
        this.hasMoved = false;
    }
}