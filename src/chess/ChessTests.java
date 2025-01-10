package chess;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import chess.views.console.ConsoleView;

class ChessTests {

    private GameController controller;
    private ChessView view;

    @BeforeEach
    void setUp() {
        controller = new GameController();
        view = new ConsoleView(controller); // Utilisation de ConsoleView comme implémentation concrète
        controller.start(view);
    }

    @Test
    void testStartGame() {
        assertDoesNotThrow(() -> controller.start(view));
    }

    @Test
    void testMoveValid() {
        assertTrue(controller.move(0, 1, 0, 2));
    }

    @Test
    void testMoveInvalid() {
        assertFalse(controller.move(0, 1, 0, 4));
    }

    @Test
    void testMoveOutOfBounds() {
        assertFalse(controller.move(0, 1, 0, 8));
    }

    @Test
    void testCapturePiece() {
        controller.move(3, 1, 3, 3); // Déplacement d'un pion
        controller.move(4, 6, 4, 4); // Déplacement d'un pion adverse
        assertTrue(controller.move(3, 3, 4, 4)); // Capture
    }

    @Test
    void testNewGame() {
        assertDoesNotThrow(() -> controller.newGame());
    }

    @Test
    void testPawnPromotion() {
        controller.move(6, 1, 6, 3);
        controller.move(7, 6, 7, 4);
        controller.move(6, 3, 6, 4);
        controller.move(7, 4, 7, 3);
        controller.move(6, 4, 6, 5);
        controller.move(7, 3, 7, 2);
        controller.move(6, 5, 6, 6);
        controller.move(7, 2, 7, 1);
        controller.move(6, 6, 6, 7);
        // Promotion simulée
        assertDoesNotThrow(() -> controller.promotePawn(controller.getBoard().getSquare(6, 7)));
    }

    @Test
    void testCastling() {
        controller.move(4, 1, 4, 3);
        controller.move(4, 6, 4, 4);
        controller.move(5, 0, 4, 1);
        controller.move(5, 7, 4, 6);
        controller.move(6, 0, 5, 2);
        controller.move(6, 7, 5, 5);
        assertTrue(controller.move(4, 0, 6, 0)); // Petit roque
        controller.newGame(); // Réinitialisation
        assertTrue(controller.move(4, 0, 2, 0)); // Grand roque
    }

    @Test
    void testKingInCheck() {
        controller.move(4, 1, 4, 3);
        controller.move(3, 6, 3, 4);
        controller.move(5, 0, 2, 3);
        assertTrue(controller.isKingInCheck(PlayerColor.BLACK));
    }

    @Test
    void testCheckmate() {
        controller.move(5, 1, 5, 3);
        controller.move(4, 6, 4, 4);
        controller.move(6, 1, 6, 3);
        controller.move(3, 7, 7, 3);
        assertTrue(controller.isKingInCheck(PlayerColor.WHITE));
    }
}
