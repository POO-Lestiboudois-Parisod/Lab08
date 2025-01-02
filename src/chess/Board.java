package chess;

import java.util.ArrayList;
import java.util.List;
import chess.Square;
import java.util.Collection;
import chess.PlayerColor;
import chess.PieceType;

public class Board {
    private final int NB_SQUARE_BY_LINE = 8;
    private final Square[][] board = new Square[NB_SQUARE_BY_LINE][NB_SQUARE_BY_LINE];
    public Board() {

        for(int i = 0; i < NB_SQUARE_BY_LINE; ++i){
            for(int j = 0; j < NB_SQUARE_BY_LINE; ++j) {
                switch (i) {
                    case 0: {
                        Pawn pawn = new Pawn(PlayerColor.WHITE,PieceType.PAWN,board[i][j]);
                    }
                    case 1: {


                    }
                    case 6: {
                        Pawn pawn = new Pawn(PlayerColor.BLACK,PieceType.PAWN,board[i][j]);

                    }
                    case 7: {
                    }
                    default: {
                        board[i][j] = new Square(i, j);
                    }
                }
            }

            }

    }

    public List<Square> getAllPieces(){
        List<Square> pieces = new ArrayList<>();
        for(int i = 0; i < NB_SQUARE_BY_LINE; ++i){
            for(int j = 0; j < NB_SQUARE_BY_LINE; ++j){
                if(board[i][j].isOccupied()){
                    pieces.add(new Square(i, j));
                }
            }
        }
        return pieces;
    }

    public void reset(){
        for(int i = 0; i < NB_SQUARE_BY_LINE; ++i){
            for(int j = 0; j < NB_SQUARE_BY_LINE; ++j){
                new
            }
        }
    }
}
