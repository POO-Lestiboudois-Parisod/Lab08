package chess.pieces;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */

import chess.ChessView;
import chess.PieceType;

/**
 * Représente les pièces qui peuvent être choisies par l'utilisateur lors de la promotion d'un pion.
 * Cette classe implémente l'interface {@link ChessView.UserChoice} pour fournir un choix utilisateur lors de la promotion.
 */
public class PromotablePiece implements ChessView.UserChoice {

    private final PieceType pieceType;


    /**
     * Constructeur pour créer une nouvelle pièce avec un type donné.
     *
     * @param pieceType le prochain type de la pièce promue (ex. : Reine, Tour, Fou, Cavalier).
     */
    public PromotablePiece(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    /**
     * Retourne la valeur textuelle du type de la pièce promue, qui est le nom de la pièce dans la classe {@link PieceType}.
     *
     * @return le nom du type de la pièce promue.
     */
    @Override
    public String textValue() {
        return pieceType.name();
    }

    /**
     * Retourne le type de la pièce promue.
     *
     * @return le type de la pièce promue, qui est un membre de l'énumération {@link PieceType}.
     */
    public PieceType getPieceType() {
        return pieceType;
    }
}
