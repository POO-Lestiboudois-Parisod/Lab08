package chess;

public abstract class Piece {
    private final PlayerColor color;
    private final PieceType type;
    protected Square square;

    protected Piece(PlayerColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public abstract boolean canMove(Board board, int startX, int startY, int endX, int endY);

    public PieceType getPieceType(){
        return type;
    }

    public void setSquare(Square square){
        this.square = square;
    }
    public Square getSquare(){
        return square;
    }
    public void move(Square square){
        this.square = square; //todo
    }
    public PlayerColor getColor(){
        return color;
    }
    public boolean isSameColor(Piece piece){
        return color == piece.color;
    }
    public boolean isAttacking(Board board, Square square){
        return false; //TODO
    }


};