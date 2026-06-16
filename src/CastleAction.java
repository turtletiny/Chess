package src;

import java.util.HashMap;

public enum CastleAction {
    SHORT, LONG, WHITESHORT, WHITELONG, BLACKSHORT, BLACKLONG;

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

    public static CastleAction getCastleAction(String input, Colour colour) {
        if (castleActionsMap.get(input) == CastleAction.SHORT) {
            if (colour.isWhite()) {
                return CastleAction.WHITESHORT;
            } else {
                return CastleAction.BLACKSHORT;
            }
        } else {
            if (colour.isWhite()) {
                return CastleAction.WHITELONG;
            } else {
                return CastleAction.BLACKLONG;
            }
        }
    }

    public static boolean inMap(String input) {
        return castleActionsMap.containsKey(input);
    }
}
