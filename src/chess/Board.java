package chess;

import java.util.ArrayList;
import java.util.List;

public class Board {


    private static final int NB_SQUARES_PER_LINE = 8;
    private final Square[][] board = new Square[NB_SQUARES_PER_LINE][NB_SQUARES_PER_LINE];
    private GameController gameController; // Référence au contrôleur

    public Board() {
        reset();
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public GameController getGameController() {
        return gameController;
    }

    public Piece getPiece(int x, int y) {
        if (x < 0 || x >= NB_SQUARES_PER_LINE || y < 0 || y >= NB_SQUARES_PER_LINE) {
            throw new IllegalArgumentException("Les coordonnées sont hors du plateau.");
        }
        return board[x][y].getPiece();
    }

    public void movePiece(Piece piece, Square destination) {
        Square currentSquare = piece.getSquare();
        currentSquare.setPiece(null);
        destination.setPiece(piece);
    }

    public void removeCapturedPiece(Square destination) {
        if (destination.isOccupied()) {
            destination.setPiece(null);
        }
    }

    public void reset() {
        for (int i = 0; i < NB_SQUARES_PER_LINE; ++i) {
            for (int j = 0; j < NB_SQUARES_PER_LINE; ++j) {
                board[i][j] = new Square(i, j);
            }
        }
    }

    public Square getSquare(int x, int y) {
        if (x < 0 || x >= NB_SQUARES_PER_LINE || y < 0 || y >= NB_SQUARES_PER_LINE) {
            throw new IllegalArgumentException("Les coordonnées sont hors du plateau.");
        }
        return board[x][y];
    }

    public boolean isSquareUnderAttack(Square square, PlayerColor color) {
        List<Piece> enemyPieces = getAllPiecesOfColor(color.opposite());

        for (Piece piece : enemyPieces) {
            if (piece.canMove(this, piece.getSquare(), square)) {
                return true;
            }
        }
        return false;
    }

    private List<Piece>
    getAllPiecesOfColor(PlayerColor color) {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < NB_SQUARES_PER_LINE; ++i) {
            for (int j = 0; j < NB_SQUARES_PER_LINE; ++j) {
                Piece piece = board[i][j].getPiece();
                if (piece != null && piece.getColor() == color && !(piece instanceof King)) {
                    pieces.add(piece);
                }
            }
        }
        return pieces;
    }
}