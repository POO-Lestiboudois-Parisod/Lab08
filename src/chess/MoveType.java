package chess;

/**
 * Interface MoveType pour définir et valider les types de déplacements en échecs.
 */
public interface MoveType {
    /**
     * Vérifie si un mouvement est valide pour un type donné.
     *
     * @param deltaX La différence en X (colonnes) entre la position de départ et d'arrivée.
     * @param deltaY La différence en Y (lignes) entre la position de départ et d'arrivée.
     * @return true si le mouvement est valide, false sinon.
     */
    boolean isValid(int deltaX, int deltaY);

    /**
     * Enumération pour les types de mouvements standard en échecs.
     */
    enum Standard implements MoveType {
        DIAGONAL {
            @Override
            public boolean isValid(int deltaX, int deltaY) {
                return deltaX == deltaY && deltaX > 0;
            }
        },
        LINEAR {
            @Override
            public boolean isValid(int deltaX, int deltaY) {
                return (deltaX == 0 && deltaY > 0) || (deltaY == 0 && deltaX > 0);
            }
        },
        L_SHAPE {
            @Override
            public boolean isValid(int deltaX, int deltaY) {
                return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
            }
        },
        NONE {
            @Override
            public boolean isValid(int deltaX, int deltaY) {
                return false;
            }
        };
    }
}
