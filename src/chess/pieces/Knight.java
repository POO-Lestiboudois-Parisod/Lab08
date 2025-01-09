package chess.pieces;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */
import chess.*;
import chess.moves.MoveType;

/**
 * Représente un cavalier dans un jeu d'échecs.
 * Le cavalier se déplace en forme de "L" (2 cases dans une direction et 1 case perpendiculaire, ou inversement).
 */
public class Knight extends Piece {

    /**
     * Initialise un cavalier avec une couleur spécifiée.
     *
     * @param color la couleur du joueur à laquelle appartient le cavalier.
     */
    public Knight(PlayerColor color) {
        super(color, PieceType.KNIGHT);
    }

    /**
     * Vérifie si le cavalier peut se déplacer d'une case à une autre.
     * Le déplacement doit respecter la forme en "L" (2x1 ou 1x2) et les règles générales de mouvement.
     *
     * @param board l'échiquier représentant l'état actuel du jeu.
     * @param start la case de départ.
     * @param end la case d'arrivée.
     * @return {@code true} si le déplacement est valide, {@code false} sinon.
     */
    @Override
    public boolean canMove(Board board, Square start, Square end) {
        return (MoveType.L_SHAPE.isValid(distanceX(end), distanceY(end)) && super.canMove(board, start, end) );
    }
}
