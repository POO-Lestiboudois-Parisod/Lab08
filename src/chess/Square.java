package chess;

public class Square {
    private final int x;
    private final int y;
    private Piece piece;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        piece = null;
    }

    public Square(int x, int y, Piece piece) {
        this(x, y);
        this.piece = piece;
    }

    Square getPosition(){
        return new Square(x, y);
    }

    public boolean isOccupied(){
        return false; //TODO
    }

    @Override
    public String toString() {
        return "Position: " +x + ", " + y;
    }
}
