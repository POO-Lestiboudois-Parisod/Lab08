package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import chess.PlayerColor;
import chess.PieceType;

public class Board {

    private final int NB_SQUARE_BY_LINE = 8;
    private final Square[][] board = new Square[NB_SQUARE_BY_LINE][NB_SQUARE_BY_LINE];


    public Board() {

        for (int i = 0; i < NB_SQUARE_BY_LINE; ++i) {
            for (int j = 0; j < NB_SQUARE_BY_LINE; ++j) {
                switch (i) {
                    case 0: {
                        Pawn pawn = new Pawn(PlayerColor.WHITE, PieceType.PAWN);
                        board[i][j] =  new Square(i,j);
                        board[i][j].setPiece(pawn);
                        break;
                    }
                    case 1: {


                    }
                    case 6: {
                        Pawn pawn = new Pawn(PlayerColor.BLACK, PieceType.PAWN);
                        board[i][j] =  new Square(i,j);
                        board[i][j].setPiece(pawn);
                        break;
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

    public Piece getPiece(int x, int y) {
        // Vérifier si les coordonnées sont valides
        if (x < 0 || x >= NB_SQUARE_BY_LINE || y < 0 || y >= NB_SQUARE_BY_LINE) {
            throw new IllegalArgumentException("Les coordonnées sont hors du plateau.");
        }

        // Récupérer la case correspondante
        Square square = board[x][y];

        // Retourner la pièce occupant cette case (ou null si la case est vide)
        return square.isOccupied() ? square.getPiece() : null;
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
/*
    private static class Square {
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
        }
    }*/
}
