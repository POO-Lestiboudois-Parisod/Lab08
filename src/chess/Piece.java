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
    public boolean canMove(Square square) {
        if (square.isOccupied()) {
            if (square.getPiece().getColor().equals(this.getColor())) {
                return false;
            }
        }
        return true;
    }

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

        if(canMove(square)){
            this.square = square;
        }

    }
    public PlayerColor getColor(){
        return color;
    }

    public boolean isSameColor(Piece piece){
        return color.equals(piece.color);
    }
    public boolean isAttacking(Board board, Square square){
        return false; //TODO
    }

    public double distanceX(Square square){
        int x = square.getX();

        int currentX = this.square.getX();


        // Calcul de la distance
        return Math.abs(x - currentX);
    }

    public double distanceY(Square square){
        int y = square.getY();

        int currentY = this.square.getY();


        // Calcul de la distance
        return Math.abs(y - currentY);
    }


};