package chess;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */

import chess.views.console.ConsoleView;
import chess.views.gui.GUIView;

public class Main {
    public static void main(String[] args) {
        ChessController controller = new GameController();
        ChessView view = new GUIView(controller);
        //ChessView view = new ConsoleView(controller); //mode console
        controller.start(view);
    }
}
