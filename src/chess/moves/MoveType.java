package chess.moves;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */

/**
 * Représente les différents types de déplacements possibles pour les pièces d'échecs.
 * Chaque type de déplacement vérifie si les coordonnées fournies respectent ses règles spécifiques.
 */
public enum MoveType {
    /**
     * Déplacement en diagonale.
     * Valide si les deux décalages sont égaux et strictement positifs.
     */
    DIAGONAL {
        @Override
        public boolean isValid(int deltaX, int deltaY) {
            return deltaX == deltaY && deltaX > 0;
        }
    },
    /**
     * Déplacement horizontal.
     * Valide si le décalage vertical est nul et le décalage horizontal est strictement positif.
     */
    HORIZONTAL {
        @Override
        public boolean isValid(int deltaX, int deltaY) {
            return deltaY == 0 && deltaX > 0;
        }
    },
    /**
     * Déplacement vertical.
     * Valide si le décalage horizontal est nul et le décalage vertical est strictement positif.
     */
    VERTICAL {
        @Override
        public boolean isValid(int deltaX, int deltaY) {
            return deltaX == 0 && deltaY > 0;
        }
    },
    /**
     * Déplacement en forme de "L".
     * Valide si les décalages correspondent aux mouvements possibles d'un cavalier (2x1 ou 1x2).
     */
    L_SHAPE {
        @Override
        public boolean isValid(int deltaX, int deltaY) {
            return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
        }
    };
    /**
     * Vérifie si un déplacement est valide pour ce type de mouvement.
     *
     * @param deltaX le décalage horizontal entre la case de départ et la case d'arrivée.
     * @param deltaY le décalage vertical entre la case de départ et la case d'arrivée.
     * @return {@code true} si le déplacement respecte les règles du type de mouvement, {@code false} sinon.
     */
    public abstract boolean isValid(int deltaX, int deltaY);
}
