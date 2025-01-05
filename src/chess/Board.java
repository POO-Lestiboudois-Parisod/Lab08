package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import chess.PlayerColor;
import chess.PieceType;

public class Board {

    private final int NB_SQUARE_BY_LINE = 8;
    private final Square[][] board = new Square[NB_SQUARE_BY_LINE][NB_SQUARE_BY_LINE];


    public Board() {
        reset();
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


    public List<Square> getAllPiecesPosition(){
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

    public void reset() {

        for (int i = 0; i < NB_SQUARE_BY_LINE; ++i) {
            for (int j = 0; j < NB_SQUARE_BY_LINE; ++j) {
                switch (i) {
                    case 0: {
                        Pawn pawn = new Pawn(PlayerColor.WHITE);
                        board[i][j] = new Square(i, j);
                        board[i][j].setPiece(pawn);
                        break;
                    }
                    case 1: {
                        switch (j) {
                            case 0: {
                                Rook rook = new Rook(PlayerColor.WHITE);
                                board[i][j] = new Square(i, j);
                                board[i][j].setPiece(rook);
                                break;
                            }
                            case 1: {
                                Knight knight = new Knight(PlayerColor.WHITE);
                                board[i][j] = new Square(i, j);
                                board[i][j].setPiece(knight);
                            }
                            case 2: {
                                Bishop bishop = new Bishop(PlayerColor.WHITE);
                                board[i][j] = new Square(i, j);
                                board[i][j].setPiece(bishop);
                            }
                            case 3: {
                                Queen queen = new Queen(PlayerColor.WHITE);
                                board[i][j] = new Square(i, j);
                                board[i][j].setPiece(queen);
                            }
                            case 4: {
                                King king = new King(PlayerColor.WHITE);
                                board[i][j] = new Square(i, j);
                                board[i][j].setPiece(king);
                            }
                            case 5: {
                                Bishop bishop = new Bishop(PlayerColor.WHITE);
                                board[i][j] = new Square(i, j);
                                board[i][j].setPiece(bishop);
                            }
                            case 6: {
                                Knight knight = new Knight(PlayerColor.WHITE);
                                board[i][j] = new Square(i, j);
                                board[i][j].setPiece(knight);
                            }
                            case 7: {
                                Queen queen = new Queen(PlayerColor.WHITE);
                                board[i][j] = new Square(i, j);
                                board[i][j].setPiece(queen);
                            }
                        }
                    }
                    case 6: {
                        Pawn pawn = new Pawn(PlayerColor.BLACK);
                        board[i][j] = new Square(i, j);
                        board[i][j].setPiece(pawn);
                        break;
                    }
                    case 7: {
                        switch (j) {
                            case 0: {
                                Rook rook = new Rook(PlayerColor.BLACK);
                                board[i][j] = new Square(i, j);
                                board[i][j].setPiece(rook);
                                break;
                            }
                            case 1: {
                                Knight knight = new Knight(PlayerColor.BLACK);
                                board[i][j] = new Square(i, j);
                                board[i][j].setPiece(knight);
                            }
                            case 2: {
                                Bishop bishop = new Bishop(PlayerColor.BLACK);
                                board[i][j] = new Square(i, j);
                                board[i][j].setPiece(bishop);
                            }
                            case 3: {
                                King king = new King(PlayerColor.BLACK);
                                board[i][j] = new Square(i, j);
                                board[i][j].setPiece(king);

                            }
                            case 4: {
                                Queen queen = new Queen(PlayerColor.BLACK);
                                board[i][j] = new Square(i, j);
                                board[i][j].setPiece(queen);
                            }
                            case 5: {
                                Bishop bishop = new Bishop(PlayerColor.BLACK);
                                board[i][j] = new Square(i, j);
                                board[i][j].setPiece(bishop);
                            }
                            case 6: {
                                Knight knight = new Knight(PlayerColor.BLACK);
                                board[i][j] = new Square(i, j);
                                board[i][j].setPiece(knight);
                            }
                            case 7: {
                                Queen queen = new Queen(PlayerColor.BLACK);
                                board[i][j] = new Square(i, j);
                                board[i][j].setPiece(queen);
                            }
                        }
                    }
                    default: {
                        board[i][j] = new Square(i, j);
                    }
                }
            }
        }
    }

    public List<PromotablePiece> getPromotablePieces() {
        List<PromotablePiece> promotablePieces = new ArrayList<>();
        List<Square> piecesPositions = getAllPiecesPosition();
        for (Square square : piecesPositions) {
            if (square.getPiece() instanceof PromotablePiece) {
                promotablePieces.add((PromotablePiece) square.getPiece());
            }
        }
        return promotablePieces;
    }

    public Piece getPiece(Square square) {
        return square.getPiece();
    }

    public List<Piece> getAllPiecesOfColor(PlayerColor color) {
        List<Piece> pieces = new ArrayList<>();
        for (Square square : getAllPiecesPosition()) {
            if (square.getPiece().getColor().equals(color)) {
                pieces.add(square.getPiece());
            }
        }
        return pieces;
    }

    public void addPiece(Piece piece, Square square) {
        if(square.isOccupied()){
            throw new IllegalArgumentException("Square is occupied");
        }
        square.setPiece(piece);
    }

    public void removePiece(Piece piece, Square square) {
        if(!square.isOccupied()){
            throw new IllegalArgumentException("Square is not occupied");
        }
        square.setPiece(null);
    }
}


