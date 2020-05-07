package ru.ifmo.jjd.utils.net;

import static ru.ifmo.jjd.utils.StringHelper.isNullOrEmpty;

public class IPParser {
    public static boolean isIP(String ipString) {
        if (isNullOrEmpty(ipString)) return false;
        ipString = ipString.trim();
        if (!ipString.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) return false;
        String[] blocks = ipString.split("\\.");
        if (blocks.length != 4) return false;
        for (String block : blocks) {
            try {
                int k = Integer.parseInt(block);
                if (k < 0 || k >= 256) return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    public static String parseIP(String ipString) {
        if (isNullOrEmpty(ipString)) return null;
        ipString = ipString.trim();
        return isIP(ipString) ? ipString : null;
    }
}
