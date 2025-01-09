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
            board.removeCapturedPiece(end);
            board.movePiece(this, end);
            setSquare(end);
        } else {
            throw new IllegalArgumentException("Mouvement invalide.");
        }
    }

    public int distanceX(Square square){
        int x = square.getX();

        int currentX = this.square.getX();


        // Calcul de la distance
        return Math.abs(x - currentX);
    }

    public int distanceY(Square square){
        int y = square.getY();

        int currentY = this.square.getY();


        // Calcul de la distance
        return Math.abs(y - currentY);
    }



    public boolean isNotSameColor(Piece piece) {
        return !this.color.equals(piece.color);
    }

    public boolean canMove(Board board, Square start, Square end){
        if(end.isOccupied()) {
            return isNotSameColor(end.getPiece());
        }

        return true;

    }

    public interface MoveStrategy {
        boolean isValid(Board board, Square start, Square end);

        void execute(Board board, Square start, Square end);
    }
}