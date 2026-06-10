package src;

import java.util.HashMap;

public enum CastleAction {
    SHORT, LONG;

    private static final HashMap<String, CastleAction> castleActionsMap = new HashMap<>();
    static {
        castleActionsMap.put("/lc", LONG);
        castleActionsMap.put("/longcastle", LONG);
        castleActionsMap.put("lc", LONG);
        castleActionsMap.put("O-O-O", LONG);
        castleActionsMap.put("0-0-0", LONG);
        castleActionsMap.put("/sc", SHORT);
        castleActionsMap.put("sc", SHORT);
        castleActionsMap.put("O-O", SHORT);
        castleActionsMap.put("0-0", SHORT);
    }

    public static CastleAction getCastleAction(String input) {
        return castleActionsMap.get(input);
    }
    public static boolean inMap(String input){
        return castleActionsMap.containsKey(input);
    }
}
