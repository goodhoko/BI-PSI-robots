package cz.goodhoko.tcp;

public class Robot {
    private Coordinates coordinates;
    private Orientation orientation;
    private String name;



    Robot(String name){
        this.name = name;
    }



    public Boolean validatePassword(Integer password){
        Integer val = 0;
        for (int i = 0; i < name.length(); i++){
            val += name.charAt(i);
        }

        return password.equals(val);
    }



    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }



    public Boolean getNextMove(){ //0 -> move, 1 -> rotate
        if(coordinates.x > 0){
            return orientation != Orientation.WEST;
        }
        if(coordinates.x < 0){
            return orientation != Orientation.EAST;
        }
        if(coordinates.y > 0){
            return orientation != Orientation.SOUTH;
        }
        if(coordinates.y < 0){
            return orientation != Orientation.NORTH;
        }

        //just in case we are at destination -> we don't want to move
        return true;
    }



    public void rotate(){
        orientation = orientation.next();
    }



    public Boolean determineOrientation(Coordinates newCoordinates){
        if(!coordinates.x.equals(newCoordinates.x) && !coordinates.y.equals(newCoordinates.y))
            return false;
        if(coordinates.x < newCoordinates.x) {
            this.orientation = Orientation.EAST;
            return true;
        }
        if(coordinates.x > newCoordinates.x) {
            this.orientation = Orientation.WEST;
            return true;
        }
        if(coordinates.y < newCoordinates.y) {
            this.orientation = Orientation.NORTH;
            return true;
        }
        if(coordinates.y > newCoordinates.y) {
            this.orientation = Orientation.SOUTH;
            return true;
        }

        return false;
    }



    @Override
    public String toString(){
        return "ROBOT '" + name + "' " + coordinates + " " + orientation + "\n";
    }
}
