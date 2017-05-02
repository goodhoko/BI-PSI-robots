package cz.goodhoko.tcp;

public class StateMachine {
    private static final String SERVER_USER =           "100 LOGIN\r\n";         //Výzva k zadání uživatelského jména
    private static final String SERVER_PASSWORD =       "101 PASSWORD\r\n";      //Výzva k zadání uživatelského hesla
    private static final String SERVER_MOVE =           "102 MOVE\r\n";          //Příkaz pro pohyb o jedno pole vpřed
    private static final String SERVER_TURN_RIGHT =     "104 TURN RIGHT\r\n";    //Příkaz pro otočení doprava
    private static final String SERVER_PICK_UP =        "105 GET MESSAGE\r\n";   //Příkaz pro vyzvednutí zprávy
    private static final String SERVER_OK =             "200 OK\r\n";            //Kladné potvrzení
    private static final String SERVER_LOGIN_FAILED =   "300 LOGIN FAILED\r\n";  //Chybné heslo
    private static final String SERVER_SYNTAX_ERROR =   "301 SYNTAX ERROR\r\n";  //Chybná syntaxe zprávy
    private static final String SERVER_LOGIC_ERROR =    "302 LOGIC ERROR\r\n";   //Zpráva odeslaná ve špatné situaci

    private static final String CLIENT_RECHARGING =     "RECHARGING";            //Robot se začal dobíjet a přestal reagovat na zprávy.
    private static final String CLIENT_FULL_POWER =     "FULL POWER";            //Robot doplnil energii a opět příjímá příkazy.

    private static final Integer TIMEOUT =              1;
    private static final Integer TIMEOUT_RECHARGING = 	5;

    private State state;
    private Boolean charging;
    private String buffer;
    private Robot robot;



    StateMachine(){
        this.state = State.INIT;
        this.charging = false;
        this.buffer = "";
        this.robot = null;
    }



    public Integer getTimeout(){
        return 1000 * (charging ? TIMEOUT_RECHARGING : TIMEOUT);
    }



    public Response getNextResponse(String in){
        System.out.println("getNextResponse: '" + StateMachine.unEscapeString(in) + "'");
        in = buffer.concat(in);
        buffer = "";

        if(charging){
            return validateCharging(in);
        }
        if(in.equals(CLIENT_RECHARGING)){
            charging = true;
            return new Response("");
        }

        switch (state){
            case INIT:
                state = State.LOGIN;
                return new Response(SERVER_USER);
            case LOGIN:
                return validateLogin(in);
            case PASSWORD:
                return validatePassword(in);
            case FIRST_COORDINATES:
                return firstCoordinates(in);
            case SECOND_COORDINATES:
                return secondCoordinates(in);
            case NAVIGATING:
                return getNextMove(in);
            case DESTINATION:
                return retrieveMessage(in);
        }
        
        return new Response("");
    }



    private Response validateCharging(String in) {
        if(!in.equals(CLIENT_FULL_POWER))
            return new Response(true, SERVER_LOGIC_ERROR);
        charging = false;
        return new Response("");
    }



    private Response validateLogin(String in) {
        if(!checkLength(in.length())){
            System.out.println("ERROR: Too long username: '" + unEscapeString(in) + "'");
            return new Response(true, SERVER_SYNTAX_ERROR);
        }

        robot = new Robot(in);
        state = State.PASSWORD;
        System.out.println("Username validated: '" + unEscapeString(in) + "'");
        return new Response(SERVER_PASSWORD);
    }



    private Response validatePassword(String in) {
        if(!checkLength(in.length())){
            System.out.println("ERROR: Too long password: '" + unEscapeString(in) + "'");
            return new Response(true, SERVER_SYNTAX_ERROR);
        }

        Integer password = 0;
        try{
            password = Integer.parseInt(in);
        }catch(Exception e){
            System.out.println("ERROR: can't parse Integer from password: '" + unEscapeString(in) + "'");
            return new Response(true, SERVER_SYNTAX_ERROR);
        }

        if(!robot.validatePassword(password)){
            System.out.println("ERROR: wrong password: '" + unEscapeString(in) + "'");
            return new Response(true, SERVER_LOGIN_FAILED);
        }

        state = State.FIRST_COORDINATES;
        System.out.println("Password validated");
        return new Response(SERVER_OK + SERVER_MOVE);
    }



    private Response firstCoordinates(String in) {
        Coordinates coordinates = Coordinates.parse(in);
        if(coordinates == null){
            System.out.println("ERROR: can't parse coordinates: '" + unEscapeString(in) + "'");
            return new Response(true, SERVER_SYNTAX_ERROR);
        }
        if(coordinates.atDestination()){
            System.out.println("Reached destination");
            this.state = State.DESTINATION;
            return new Response(SERVER_PICK_UP);
        }

        System.out.println("Got first coordinates of the robot: " + coordinates);
        robot.setCoordinates(coordinates);
        this.state = State.SECOND_COORDINATES;
        return new Response(SERVER_MOVE);
    }



    private Response secondCoordinates(String in) {
        Coordinates coordinates = Coordinates.parse(in);
        if(coordinates == null){
            System.out.println("ERROR: can't parse coordinates: '" + unEscapeString(in) + "'");
            return new Response(true, SERVER_SYNTAX_ERROR);
        }
        if(coordinates.atDestination()){
            this.state = State.DESTINATION;
            System.out.println("Reached destination");
            return new Response(SERVER_PICK_UP);
        }
        if(!robot.determineOrientation(coordinates)){
            System.out.println("Robot didn't move. Let's do it again.");
            return new Response(SERVER_MOVE);
        }

        System.out.println("Determined orientation, proceeding to navigating");
        robot.setCoordinates(coordinates);
        state = State.NAVIGATING;
        if(robot.getNextMove()){
            robot.rotate();
            return new Response(SERVER_TURN_RIGHT);
        }

        return new Response(SERVER_MOVE);
    }



    private Response getNextMove(String in) {
        Coordinates coordinates = Coordinates.parse(in);
        if(coordinates == null){
            System.out.println("ERROR: can't parse coordinates: '" + unEscapeString(in) + "'");
            return new Response(true, SERVER_SYNTAX_ERROR);
        }
        if(coordinates.atDestination()){
            this.state = State.DESTINATION;
            System.out.println("Reached destination");
            return new Response(SERVER_PICK_UP);
        }

        System.out.println("robot is now on: " + coordinates);
        robot.setCoordinates(coordinates);
        if(robot.getNextMove()){
            robot.rotate();
            return new Response(SERVER_TURN_RIGHT);
        }

        return new Response(SERVER_MOVE);
    }



    private Response retrieveMessage(String in) {
        if(!checkLength(in.length())){
            System.out.println("ERROR: too long message: '" + unEscapeString(in) + "'");
            return new Response(true, SERVER_SYNTAX_ERROR);
        }

        System.out.println("MESSAGE RETRIEVED: " + unEscapeString(in));
        return new Response(true, SERVER_OK);
    }


    //helper function for debuging
    public static String unEscapeString(String s){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); i++)
            switch (s.charAt(i)){
                case '\n': sb.append("\\n"); break;
                case '\t': sb.append("\\t"); break;
                case '\r': sb.append("\\r"); break;
                // ... rest of escape characters
                default: sb.append(s.charAt(i));
            }
        return sb.toString();
    }


    //accepts incomplete message, checks if it is not too long and saves it to the buffer
    public Response preValidate(String in){
        if(checkLength(buffer.length() + in.length())){
            buffer = buffer.concat(in);
            return new Response("");
        }
        return new Response(true, SERVER_SYNTAX_ERROR);
    }


    //checks if availabe is not more than lenght limit of the current expected message
    public boolean checkLength(int available) {
        switch (state){
            case LOGIN:
            case DESTINATION:
                return available <= 98;
            case PASSWORD:
                return available <= 5;
            case FIRST_COORDINATES:
            case SECOND_COORDINATES:
            case NAVIGATING:
                return available <= 10;
        }

        return true;
    }
}
