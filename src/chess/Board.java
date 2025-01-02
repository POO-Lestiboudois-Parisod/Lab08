package chess;

import java.util.List;
import chess.Square;

public class Board {
    private final int NB_SQUARE_BY_LINE = 8;
    private List<List<Square>> board;


    public Board() {

        for(int i = 0; i < NB_SQUARE_BY_LINE; ++i){
            for(int j = 0; j < NB_SQUARE_BY_LINE; ++j){
                board.get(i).set(j,new Square(i, j));
            }
        }
    }
}
