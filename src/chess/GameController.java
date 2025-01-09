package chess;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */

import chess.pieces.*;

/**
 * Contrôleur principal pour le jeu d'échecs.
 * Gère les interactions entre le modèle (échiquier et pièces), la vue (interface utilisateur)
 * et la logique de jeu (mouvements, vérification des règles, etc.).
 */
public class GameController implements ChessController {

    private Board board;
    private ChessView view;
    private Move lastMove; // Attribut pour garder en mémoire le dernier coup joué

    /**
     * Constructeur de la classe GameController.
     * Initialise le plateau de jeu et le dernier coup joué.
     */
    public GameController() {
        board = new Board();
        lastMove = null;
        board.setGameController(this);// Initialisation du dernier coup joué
    }

    /**
     * Démarre une nouvelle partie et initialise la vue.
     *
     * @param view l'interface utilisateur pour le jeu.
     */
    @Override
    public void start(ChessView view) {
        this.view = view;
        view.startView();
        this.newGame();
    }

    /**
     * Vérifie si le roi d'une couleur donnée est en échec.
     *
     * @param color la couleur du roi à vérifier.
     * @return true si le roi est en échec, false sinon.
     */
    public boolean isKingInCheck(PlayerColor color) {
        Square kingSquare = findKing(color);
        return board.isSquareUnderAttack(kingSquare, color);
    }

    /**
     * Recherche la position du roi d'une couleur donnée.
     *
     * @param color la couleur du roi à localiser.
     * @return la case où se trouve le roi.
     * @throws IllegalStateException si le roi n'est pas trouvé sur l'échiquier.
     */
    private Square findKing(PlayerColor color) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = board.getSquare(x, y).getPiece();
                if (piece != null && piece.getType() == PieceType.KING && piece.getColor() == color) {
                    return board.getSquare(x, y);
                }
            }
        }
        throw new IllegalStateException("King not found on the board");
    }

    /**
     * Tente de déplacer une pièce d'une case à une autre.
     *
     * @param fromX la coordonnée x de la case de départ.
     * @param fromY la coordonnée y de la case de départ.
     * @param toX la coordonnée x de la case de destination.
     * @param toY la coordonnée y de la case de destination.
     * @return true si le mouvement est valide et exécuté, false sinon.
     */
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        Square fromSquare = board.getSquare(fromX, fromY);
        Square toSquare = board.getSquare(toX, toY);

        if (fromSquare.getPiece() == null || (lastMove == null && fromSquare.getPiece().getColor() != PlayerColor.WHITE) || lastMove != null && !lastMove.getPiece().isNotSameColor(fromSquare.getPiece())) {
            return false;
        }

        // Vérification des conditions de déplacement
        if (fromSquare.isOccupied()) {
            Piece piece = fromSquare.getPiece();
            if (piece.canMove(board, fromSquare, toSquare)) {

                //Simuler le mouvement
                Piece capturedPiece = toSquare.getPiece();
                board.movePiece(piece, toSquare);
                boolean isInCheck = isKingInCheck(piece.getColor());

                //Annuler le mouvement
                board.movePiece(piece, fromSquare);
                toSquare.setPiece(capturedPiece);

                if (!isInCheck) {
                    piece.executeMove(board, fromSquare, toSquare);

                    // Mise à jour du dernier coup joué
                    lastMove = new Move(fromSquare, toSquare, piece);

                    view.removePiece(fromX, fromY);
                    view.putPiece(toSquare.getPiece().getType(), toSquare.getPiece().getColor(), toX, toY);

                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Retire une pièce de la vue à une position donnée.
     *
     * @param x la coordonnée x de la pièce à retirer.
     * @param y la coordonnée y de la pièce à retirer.
     */
    public void removePiece(int x, int y) {
        view.removePiece(x, y);
    }

    /**
     * Place une pièce dans la vue à une position donnée.
     *
     * @param x la coordonnée x de la pièce à placer.
     * @param y la coordonnée y de la pièce à placer.
     */
    public void setPiece(int x, int y) {
        view.putPiece(board.getPiece(x, y).getType(), board.getPiece(x, y).getColor(), x, y);
    }

    /**
     * Démarre une nouvelle partie en réinitialisant le plateau et les pièces.
     */
    @Override
    public void newGame() {
        board.reset();// Réinitialisation de l'échiquier
        setLastMoveAtNull();

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

    /**
     * Gère la promotion d'un pion.
     * Demande à l'utilisateur de choisir une pièce pour remplacer le pion.
     *
     * @param square la case où le pion est promu.
     */
    public void promotePawn(Square square) {
        PromotablePiece[] promotionChoices = {new PromotablePiece(PieceType.QUEEN), new PromotablePiece(PieceType.ROOK), new PromotablePiece(PieceType.BISHOP), new PromotablePiece(PieceType.KNIGHT)};
        PromotablePiece choice = this.view.askUser("Promotion", "Choisissez une pièce pour la promotion :", promotionChoices);
        switch (choice.getPieceType()) {
            case ROOK:
                square.setPiece(new Rook(square.getPiece().getColor()));
                break;
            case BISHOP:
                square.setPiece(new Bishop(square.getPiece().getColor()));
                break;
            case KNIGHT:
                square.setPiece(new Knight(square.getPiece().getColor()));
                break;
            default:
                square.setPiece(new Queen(square.getPiece().getColor()));
                break;
        }
    }

    /**
     * Classe interne représentant un mouvement.
     */
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

    /**
     * Obtient le dernier coup joué.
     *
     * @return le dernier mouvement.
     */
    public Move getLastMove() {
        return lastMove;
    }

    /**
     * Réinitialise le dernier mouvement à null.
     */
    public void setLastMoveAtNull() {
        lastMove = null;
    }
}