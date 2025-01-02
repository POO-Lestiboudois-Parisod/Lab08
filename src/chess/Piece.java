package chess;

import java.awt.*;

public abstract class Piece {
    private final PlayerColor color;
    private PieceType type;
    private Square square;

    protected Piece(PlayerColor color, PieceType type, Square square) {
        this.color = color;
        this.type = type;
    }

    public abstract boolean canMove(Board board, int startX, int startY, int endX, int endY);

    public PieceType getPieceType(){
        return type;
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