package model;

public class APIKey {
    private static final String[] KEYS = {"1Z19I81E7YM3VHW9","two","three","four","five"};
    private static int index = 0;

    public static String getKey(){
        if (index>KEYS.length-1){
            index = 0;
        }
        return KEYS[index++];
    }
}
