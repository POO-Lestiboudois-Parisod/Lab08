package chess;

public class Square {
    private final int x;
    private final int y;
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Square getPosition(){
        return new Square(x, y);
    }

    @Override
    public String toString() {
        return "Position: " +x + ", " + y;
    }
}
