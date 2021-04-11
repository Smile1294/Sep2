package model;

public class TestAPIKeySwitching {
    public static void main(String[] args) {
        for (int x=0;x<20;x++) {
            System.out.println(APIKey.getKey());
        }
    }
}
