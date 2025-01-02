package chess;

public class Queen extends Piece {

    public Queen(PlayerColor color, Square square) {
        super(color, PieceType.QUEEN, square);
    }

    @Override
    public boolean canMove(Board board, int startX, int startY, int endX, int endY) {
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);

        // Vérifier les mouvements en ligne droite ou en diagonale
        boolean isDiagonal = MoveType.Standard.DIAGONAL.isValid(deltaX, deltaY);
        boolean isLinear = MoveType.Standard.LINEAR.isValid(deltaX, deltaY);

        if (!isDiagonal && !isLinear) {
            return false;
        }

        // Vérification des obstacles
        int stepX = (endX - startX) == 0 ? 0 : (endX - startX) / Math.max(deltaX, 1);
        int stepY = (endY - startY) == 0 ? 0 : (endY - startY) / Math.max(deltaY, 1);

        int x = startX + stepX;
        int y = startY + stepY;

        while (x != endX || y != endY) {
            if (board.getPiece(x, y) != null) {
                return false; // Une pièce bloque le chemin
            }
            x += stepX;
            y += stepY;
        }

        // Vérifier que la case de destination est vide ou contient une pièce ennemie
        Piece targetPiece = board.getPiece(endX, endY);
        return targetPiece == null || targetPiece.getColor() != this.getColor();
    }
}
