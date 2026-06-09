class Point {
    private int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    public void setX(int newX){
        this.x = newX;
    }
    public void setY(int newY){
        this.y = newY;
    }

    public void setPoint(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

}
