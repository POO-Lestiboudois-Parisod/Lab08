package chess.pieces;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */

import chess.Board;
import chess.PieceType;
import chess.PlayerColor;
import chess.Square;

/**
 * Représente une pièce du jeu d'échecs (roi, reine, fou, cavalier, tour ou pion).
 * Une pièce a une couleur, un type (roi, reine, etc.) et une position sur l'échiquier.
 * Les méthodes de cette classe permettent de vérifier la validité des mouvements, d'exécuter les déplacements,
 * et de gérer les interactions avec d'autres pièces sur l'échiquier.
 */
public abstract class Piece {
    private final PlayerColor color;
    private final PieceType type;
    private Square square;

    /**
     * Constructeur pour initialiser une pièce avec une couleur et un type.
     *
     * @param color la couleur de la pièce (blanc ou noir).
     * @param type le type de la pièce (roi, reine, etc.).
     */
    protected Piece(PlayerColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }
    /**
     * Retourne la couleur de la pièce.
     *
     * @return la couleur de la pièce.
     */
    public PlayerColor getColor() {
        return color;
    }

    /**
     * Retourne le type de la pièce (roi, reine, etc.).
     *
     * @return le type de la pièce.
     */
    public PieceType getType() {
        return type;
    }

    /**
     * Retourne la case actuelle où se trouve la pièce sur l'échiquier.
     *
     * @return la case de la pièce.
     */
    public Square getSquare() {
        return square;
    }

    /**
     * Définit la case sur laquelle se trouve la pièce.
     *
     * @param square la nouvelle case où la pièce sera placée.
     */
    public void setSquare(Square square) {
        this.square = square;
    }

    /**
     * Exécute le mouvement de la pièce si le déplacement est valide.
     * Supprime une pièce capturée et déplace la pièce vers la case d'arrivée.
     *
     * @param board l'échiquier représentant l'état actuel du jeu.
     * @param start la case de départ.
     * @param end la case d'arrivée.
     * @throws IllegalArgumentException si le mouvement est invalide.
     */
    public void executeMove(Board board, Square start, Square end) {
        if (canMove(board, start, end)) {
            board.removeCapturedPiece(end);
            board.movePiece(this, end);
            setSquare(end);
        } else {
            throw new IllegalArgumentException("Mouvement invalide.");
        }
    }
    /**
     * Calcule la distance horizontale (sur l'axe X) entre la pièce actuelle et la case donnée en valeur absolue.
     *
     * @param square la case de destination.
     * @return la distance horizontale entre la pièce et la case.
     */
    public int distanceX(Square square){
        int x = square.getX();
        int currentX = this.square.getX();

        // Calcul de la distance
        return Math.abs(x - currentX);
    }

    /**
     * Calcule la distance verticale (sur l'axe Y) entre la pièce actuelle et la case donnée en valeur absolue.
     *
     * @param square la case de destination.
     * @return la distance verticale entre la pièce et la case.
     */
    public int distanceY(Square square){
        int y = square.getY();
        int currentY = this.square.getY();

        // Calcul de la distance
        return Math.abs(y - currentY);
    }

    /**
     * Vérifie si la pièce donnée a une couleur différente de celle de la pièce actuelle.
     *
     * @param piece la pièce à comparer.
     * @return {@code true} si les pièces ont des couleurs différentes, {@code false} sinon.
     */
    public boolean isNotSameColor(Piece piece) {
        return !this.color.equals(piece.color);
    }

    /**
     * Vérifie si la pièce peut se déplacer de la case de départ à la case d'arrivée.
     * Une pièce peut se déplacer vers une case vide ou capturer une pièce adverse.
     *
     * @param board l'échiquier représentant l'état actuel du jeu.
     * @param start la case de départ.
     * @param end la case d'arrivée.
     * @return {@code true} si le déplacement est possible, {@code false} sinon.
     */
    public boolean canMove(Board board, Square start, Square end){
        if(end.isOccupied()) {
            return isNotSameColor(end.getPiece());
        }
        return true;
    }
    /**
     * Interface représentant une stratégie de mouvement pour les pièces.
     * Chaque stratégie définit si un mouvement est valide et comment l'exécuter.
     */
    public interface MoveStrategy {
        /**
         * Vérifie si le mouvement de la pièce selon la stratégie est valide.
         *
         * @param board l'échiquier.
         * @param start la case de départ.
         * @param end la case d'arrivée.
         * @return {@code true} si le mouvement est valide, {@code false} sinon.
         */
        boolean isValid(Board board, Square start, Square end);
        /**
         * Exécute le mouvement de la pièce selon la stratégie.
         *
         * @param board l'échiquier.
         * @param start la case de départ.
         * @param end la case d'arrivée.
         */
        void execute(Board board, Square start, Square end);
    }
}