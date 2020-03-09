package sample;

public enum Direction {
    DOWN(new Coordinate(1,0)),
    UP(new Coordinate(-1,0)),
    LEFT(new Coordinate(0,-1)),
    RIGHT(new Coordinate(0,1)),
    TOPLEFT(new Coordinate(-1,-1)),
    BOTTOMLEFT(new Coordinate(1,-1)),
    TOPRIGHT(new Coordinate(-1,1)),
    BOTTOMRIGHT(new Coordinate(1,1));

    Coordinate direction;

    private Direction(Coordinate coordinate) {
        this.direction = coordinate;
    }

    public Coordinate getDirection(){
        return direction;
    }

}
