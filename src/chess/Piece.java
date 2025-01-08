/*package chess;

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


};*/

package chess;

abstract class Piece {
    private final PlayerColor color;
    private final PieceType type;
    private Square square;

    protected Piece(PlayerColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public PlayerColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public void executeMove(Board board, Square start, Square end) {
        if (canMove(board, start, end)) {
            board.movePiece(this, end);
            setSquare(end);
        } else {
            throw new IllegalArgumentException("Mouvement invalide.");
        }
    }

    public boolean isSameColor(Piece piece) {
        return this.color.equals(piece.color);
    }

    public abstract boolean canMove(Board board, Square start, Square end);

    public interface MoveStrategy {
        boolean isValid(Board board, Square start, Square end);

        void execute(Board board, Square start, Square end);
    }
}