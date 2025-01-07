package chess;

public class Bishop extends Piece {

    public Bishop(PlayerColor color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public boolean canMove(Board board, int startX, int startY, int endX, int endY) {

        super.canMove(new Square(endX, endY));


        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);

        // Vérifier que le mouvement est en diagonale
        if (!MoveType.Standard.DIAGONAL.isValid(deltaX, deltaY)) {
            return false;
        }

        // Vérification des obstacles
        int stepX = (endX - startX) / deltaX; // +1 ou -1
        int stepY = (endY - startY) / deltaY; // +1 ou -1

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

    @Override
    public boolean canMove(Square square) {
        return false;
    }
}
