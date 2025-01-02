package chess;

public class Bishop extends Piece {

    public Bishop(PlayerColor color, PieceType type, Square square) {
        super(color, type, square);
    }

    @Override
    public boolean canMove() {
        return false;//TODO
    }

    @Override
    public boolean canMove(Board board, int startX, int startY, int endX, int endY) {
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);

        // Vérifier le mouvement en diagonale via un énumérateur MoveType
        if (!MoveType.Standard.DIAGONAL.isValid(deltaX, deltaY)) {
            return false;
        }

        // Vérifier que le chemin est libre
        int stepX = (endX - startX) / deltaX; // +1 ou -1
        int stepY = (endY - startY) / deltaY;

        int x = startX + stepX;
        int y = startY + stepY;

        while (x != endX && y != endY) {
            if (board.getPiece(x, y) != null) {
                return false; // Une pièce bloque le chemin
            }
            x += stepX;
            y += stepY;
        }

        // Vérifier que la destination est valide (case vide ou pièce ennemie)
        Piece targetPiece = board.getPiece(endX, endY);
        return targetPiece == null || targetPiece.getColor() != this.getColor();
    }
}
