package cz.goodhoko.tcp;

public class Response {
    public Boolean closeAfter;
    public String response;



    Response(Boolean closeAfter, String response){
        this.closeAfter = closeAfter;
        this.response = response;
    }



    Response(String response){
        this.closeAfter = false;
        this.response = response;
    }



    @Override
    public String toString(){
        return "RESPONSE: " + closeAfter + " : " + StateMachine.unEscapeString(response);
    }
}
