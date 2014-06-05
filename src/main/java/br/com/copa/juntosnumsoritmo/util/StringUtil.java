package br.com.copa.juntosnumsoritmo.util;

public final class StringUtil {

    private StringUtil() {
        super();
    }

    public static boolean isBlank(String str) {
        int tamanho = -1;
        if (str == null || (tamanho = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < tamanho; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

}
