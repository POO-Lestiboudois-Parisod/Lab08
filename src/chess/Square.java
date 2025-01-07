/*package chess;

public class Square {
    private final int x;
    private final int y;
    private Piece piece;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null;
    }

    public boolean isOccupied() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        piece.setSquare(this);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}*/

package chess;

public class Square {
    private final int x;
    private final int y;
    private Piece piece;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null;
    }

    public boolean isOccupied() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece != null) {
            piece.setSquare(this);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
