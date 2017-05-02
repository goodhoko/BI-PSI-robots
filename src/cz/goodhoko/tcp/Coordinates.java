package cz.goodhoko.tcp;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Coordinates {

    public Integer x;
    public Integer y;



    Coordinates(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }



    Boolean atDestination(){
        return x == 0 && y == 0;
    }



    public static Coordinates parse(String in){
        if(in.length() > 10)
            return null;

        Pattern p = Pattern.compile("OK\\s(-?\\d+)\\s(-?\\d+)");
        Matcher m = p.matcher(in);
        if (!m.matches()) {
            return null;
        }

        return new Coordinates(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
    }



    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}
