package sample;

public class Coordinate {

    private int hor;
    private int vert;

    Coordinate(int hor, int vert){
        this.hor = hor;
        this.vert = vert;
    }

    @Override
    public boolean equals(Object obj) {
        return (((Coordinate)obj).hor == this.hor) && (((Coordinate)obj).vert == this.vert);
    }

    @Override
    public int hashCode() {
        return this.vert + 100 * this.vert;
    }

    public Coordinate sum(Coordinate coordinate){
        int newHor = this.hor + coordinate.hor;
        int newVert = this.vert + coordinate.vert;
        return new Coordinate(newHor, newVert);
    }

    public void validate(int boardSize) {
        if(this.hor > boardSize){
            this.hor = 1;
        }
        if(this.hor <= 0){
            this.hor = boardSize;
        }
        if(this.vert > boardSize){
            this.vert = 1;
        }
        if(this.vert <= 0){
            this.vert = boardSize;
        }
    }
}
