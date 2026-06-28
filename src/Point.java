//Represents a (x,y) point on the board
// Can be used as a vector

package src;

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

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public void setPoint(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    // Points can be treated as math vectors
    public Point addValues(int x, int y) {
        return new Point(this.getX() + x, this.getY() + y);
    }

    public static Point addValues(Point p, int x, int y) {
        return new Point(p.getX() + x, p.getY() + y);
    }

    public static Point addPoints(Point a, Point b) {
        return new Point(a.getX() + b.getX(), a.getY() + b.getY());
    }

    public static Point subtractPoints(Point a, Point b) {
        return new Point(a.getX() - b.getX(), a.getY() - b.getY());
    }

}
