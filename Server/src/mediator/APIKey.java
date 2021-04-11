package mediator;

// Kim "1Z19I81E7YM3VHW9"
// Dávid "JI2Z4NFER4CQ52LP","0K7TBQTPK03XHNB0","TD4H3494BLXYYBE1","408T2WU2BSBT1EP1"
// Luka "GB6KGRHXGIJNGXU5"
//
//

public class APIKey {
    private static final String[] KEYS = {"1Z19I81E7YM3VHW9","JI2Z4NFER4CQ52LP","0K7TBQTPK03XHNB0","TD4H3494BLXYYBE1",
            "408T2WU2BSBT1EP1","GB6KGRHXGIJNGXU5"};
    private static int index = 3;

    public static String getKey(){
        if (index>KEYS.length-1){
            index = 0;
        }
        return KEYS[index++];
    }
}
