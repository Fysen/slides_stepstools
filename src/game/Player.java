package game;

class Player {
    private int tile;
    private int x;
    private int y;

    Player(){
        this.tile = 1;
    }

    Player(int tile, int x, int y) {
        this.tile = tile;
        this.x = x;
        this.y = y;
    }

    void setTile(int tile){
        this.tile = tile;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    int getTile(){
        return this.tile;
    }

    int getX(){
        return this.x;
    }

    int getY() {
        return this.y;
    }
}
