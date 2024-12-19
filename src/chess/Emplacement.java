package chess;

import java.awt.*;

public class Emplacement {
    private int x;
    private int y;

    public int getx(){
        return x;
    }

    public int gety(){
        return y;
    }

    public Emplacement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Emplacement withAdded(){
        return new Emplacement(x, y);
    }
}
