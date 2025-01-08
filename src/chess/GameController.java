package chess;

import chess.views.gui.GUIView;

public class GameController implements ChessController {

    private Board board;
    private ChessView view;
    private Move lastMove; // Attribut pour garder en mémoire le dernier coup joué

    public GameController() {
        board = new Board();
        lastMove = null; // Initialisation du dernier coup joué
    }

    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
        this.newGame();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        Square fromSquare = board.getSquare(fromX, fromY);
        Square toSquare = board.getSquare(toX, toY);

        // Vérification des conditions de déplacement
        if (fromSquare.isOccupied()) {
            Piece piece = fromSquare.getPiece();
            if (piece.canMove(board, fromSquare, toSquare)) {
                board.movePiece(piece, toSquare);

                // Mise à jour du dernier coup joué
                lastMove = new Move(fromSquare, toSquare, piece);

                view.removePiece(fromX, fromY);
                view.putPiece(toSquare.getPiece().getType(), toSquare.getPiece().getColor(), toX, toY);
                return true;
            }
        }
        return false;
    }

    @Override
    public void newGame() {
        board.reset();  // Réinitialisation de l'échiquier

        // Placement des pièces blanches
        for (int i = 0; i < 8; i++) {
            board.getSquare(i, 1).setPiece(new Pawn(PlayerColor.WHITE));
        }
        board.getSquare(0, 0).setPiece(new Rook(PlayerColor.WHITE));
        board.getSquare(7, 0).setPiece(new Rook(PlayerColor.WHITE));
        board.getSquare(1, 0).setPiece(new Knight(PlayerColor.WHITE));
        board.getSquare(6, 0).setPiece(new Knight(PlayerColor.WHITE));
        board.getSquare(2, 0).setPiece(new Bishop(PlayerColor.WHITE));
        board.getSquare(5, 0).setPiece(new Bishop(PlayerColor.WHITE));
        board.getSquare(3, 0).setPiece(new Queen(PlayerColor.WHITE));
        board.getSquare(4, 0).setPiece(new King(PlayerColor.WHITE));

        // Placement des pièces noires
        for (int i = 0; i < 8; i++) {
            board.getSquare(i, 6).setPiece(new Pawn(PlayerColor.BLACK));
        }
        board.getSquare(0, 7).setPiece(new Rook(PlayerColor.BLACK));
        board.getSquare(7, 7).setPiece(new Rook(PlayerColor.BLACK));
        board.getSquare(1, 7).setPiece(new Knight(PlayerColor.BLACK));
        board.getSquare(6, 7).setPiece(new Knight(PlayerColor.BLACK));
        board.getSquare(2, 7).setPiece(new Bishop(PlayerColor.BLACK));
        board.getSquare(5, 7).setPiece(new Bishop(PlayerColor.BLACK));
        board.getSquare(3, 7).setPiece(new Queen(PlayerColor.BLACK));
        board.getSquare(4, 7).setPiece(new King(PlayerColor.BLACK));

        // Mise à jour de la vue
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = board.getSquare(x, y).getPiece();
                if (piece != null) {
                    view.putPiece(piece.getType(), piece.getColor(), x, y);
                }
            }
        }
    }

    // Classe interne pour représenter un coup
    public static class Move {
        private final Square from;
        private final Square to;
        private final Piece piece;

        public Move(Square from, Square to, Piece piece) {
            this.from = from;
            this.to = to;
            this.piece = piece;
        }

        public Square getFrom() {
            return from;
        }

        public Square getTo() {
            return to;
        }

        public Piece getPiece() {
            return piece;
        }
    }

    public Move getLastMove() {
        return lastMove;
    }
}