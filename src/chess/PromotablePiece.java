package chess;

public class PromotablePiece implements ChessView.UserChoice {

    private PieceType pieceType;

    public PromotablePiece(PieceType pieceType) {
        this.pieceType = pieceType;
    }
    @Override
    public String textValue() {
        return pieceType.name();
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
