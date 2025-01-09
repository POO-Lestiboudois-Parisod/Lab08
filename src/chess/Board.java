package chess;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */

import chess.pieces.King;
import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente l'échiquier du jeu d'échecs.
 * Contient les cases, les pièces, et les fonctionnalités permettant de manipuler les pièces et
 * de vérifier des états spécifiques comme les cases sous attaque.
 */
public class Board {


    private static final int NB_SQUARES_PER_LINE = 8;
    private final Square[][] board = new Square[NB_SQUARES_PER_LINE][NB_SQUARES_PER_LINE];
    private GameController gameController; // Référence au contrôleur

    /**
     * Constructeur de la classe Board.
     * Initialise l'échiquier avec des cases vides.
     */
    public Board() {
        reset();
    }

    /**
     * Définit le contrôleur du jeu pour ce plateau.
     *
     * @param gameController l'instance du contrôleur de jeu.
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Obtient le contrôleur de jeu associé au plateau.
     *
     * @return l'instance du contrôleur de jeu.
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Récupère une pièce à une position donnée sur l'échiquier.
     *
     * @param x la position horizontale (0-7).
     * @param y la position verticale (0-7).
     * @return la pièce à la position donnée, ou null si la case est vide.
     * @throws IllegalArgumentException si les coordonnées sont hors limites.
     */
    public Piece getPiece(int x, int y) {
        if (x < 0 || x >= NB_SQUARES_PER_LINE || y < 0 || y >= NB_SQUARES_PER_LINE) {
            throw new IllegalArgumentException("Les coordonnées sont hors du plateau.");
        }
        return board[x][y].getPiece();
    }

    /**
     * Déplace une pièce sur l'échiquier vers une destination donnée.
     *
     * @param piece la pièce à déplacer.
     * @param destination la case cible.
     */
    public void movePiece(Piece piece, Square destination) {
        Square currentSquare = piece.getSquare();
        currentSquare.setPiece(null);
        destination.setPiece(piece);
    }

    /**
     * Supprime une pièce capturée de la case donnée.
     *
     * @param destination la case où une pièce est capturée.
     */
    public void removeCapturedPiece(Square destination) {
        if (destination.isOccupied()) {
            destination.setPiece(null);
        }
    }

    /**
     * Réinitialise l'échiquier en vidant toutes les cases.
     */
    public void reset() {
        for (int i = 0; i < NB_SQUARES_PER_LINE; ++i) {
            for (int j = 0; j < NB_SQUARES_PER_LINE; ++j) {
                board[i][j] = new Square(i, j);
            }
        }
    }

    /**
     * Récupère une case spécifique à une position donnée.
     *
     * @param x la position horizontale (0-7).
     * @param y la position verticale (0-7).
     * @return la case à la position donnée.
     * @throws IllegalArgumentException si les coordonnées sont hors limites.
     */
    public Square getSquare(int x, int y) {
        if (x < 0 || x >= NB_SQUARES_PER_LINE || y < 0 || y >= NB_SQUARES_PER_LINE) {
            throw new IllegalArgumentException("Les coordonnées sont hors du plateau.");
        }
        return board[x][y];
    }

    /**
     * Vérifie si une case est sous attaque par une pièce ennemie.
     *
     * @param square la case à vérifier.
     * @param color la couleur du joueur qui défend la case.
     * @return true si la case est attaquée, false sinon.
     */
    public boolean isSquareUnderAttack(Square square, PlayerColor color) {
        List<Piece> enemyPieces = getAllPiecesOfColor(color.opposite());

        for (Piece piece : enemyPieces) {
            if (piece.canMove(this, piece.getSquare(), square)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Récupère toutes les pièces d'une couleur donnée, sauf les rois.
     *
     * @param color la couleur des pièces à récupérer.
     * @return une liste contenant toutes les pièces de la couleur donnée.
     */
    private List<Piece> getAllPiecesOfColor(PlayerColor color) {
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