package chess.pieces;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */


import chess.Board;
import chess.PieceType;
import chess.PlayerColor;
import chess.Square;
import chess.moves.DefaultPathValidator;
import chess.moves.MoveType;
import chess.moves.PathValidator;

/**
 * Représente la pièce Reine dans le jeu d'échecs.
 * La Reine peut se déplacer sur n'importe quelle case en ligne droite ou en diagonale, sur toute la longueur de la grille,
 * tant que son chemin est libre.
 */
public class Queen extends Piece {

    private final PathValidator pathValidator = new DefaultPathValidator();

    /**
     * Constructeur pour initialiser la Reine avec sa couleur.
     *
     * @param color la couleur de la Reine (blanc ou noir).
     */
    public Queen(PlayerColor color) {
        super(color, PieceType.QUEEN);
    }

    /**
     * Vérifie si la Reine peut se déplacer de la case de départ à la case d'arrivée.
     * La Reine peut se déplacer horizontalement, verticalement ou en diagonale,
     * tant que le chemin est dégagé.
     *
     * @param board le plateau de jeu sur lequel le mouvement est effectué.
     * @param start la case de départ.
     * @param end la case d'arrivée.
     * @return true si le mouvement est valide, false sinon.
     */
    @Override
    public boolean canMove(Board board, Square start, Square end) {
        int deltaX = distanceX(end);
        int deltaY = distanceY(end);

        // La reine peut se déplacer en diagonale, horizontalement, ou verticalement
        if (!MoveType.DIAGONAL.isValid(deltaX, deltaY) &&
                !MoveType.HORIZONTAL.isValid(deltaX, deltaY) &&
                !MoveType.VERTICAL.isValid(deltaX, deltaY)) {
            return false;
        }

        // Vérification du chemin via le PathValidator
        return pathValidator.isPathClear(board, start, end) && super.canMove(board, start, end);
    }

    /**
     * Définit la case où la Reine se trouve actuellement sur le plateau de jeu.
     * Cette méthode est utilisée pour mettre à jour la position de la Reine après un mouvement.
     *
     * @param square la case de destination où la Reine se déplacera.
     */
    @Override
    public void setSquare(Square square) {
        super.setSquare(square);
    }

    // La reine n'a pas de mouvements spéciaux comme le roque ou la promotion, donc pas de SpecialMove à implémenter
}

