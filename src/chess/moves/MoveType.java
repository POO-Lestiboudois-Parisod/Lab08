package chess.moves;

public enum MoveType {
    DIAGONAL {
        @Override
        public boolean isValid(int deltaX, int deltaY) {
            return deltaX == deltaY && deltaX > 0;
        }
    },
    HORIZONTAL {
        @Override
        public boolean isValid(int deltaX, int deltaY) {
            return deltaY == 0 && deltaX > 0;
        }
    },
    VERTICAL {
        @Override
        public boolean isValid(int deltaX, int deltaY) {
            return deltaX == 0 && deltaY > 0;
        }
    },
    L_SHAPE {
        @Override
        public boolean isValid(int deltaX, int deltaY) {
            return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
        }
    };

    public abstract boolean isValid(int deltaX, int deltaY);
}
