package cz.goodhoko.tcp;


public enum State {
    INIT, //initial state
    LOGIN, //wating for username
    PASSWORD, //waiting for password
    FIRST_COORDINATES, //waiting for the first coordinates
    SECOND_COORDINATES, //waiting for the second coordinates
    NAVIGATING, //navigating
    DESTINATION; //wating for message
}
