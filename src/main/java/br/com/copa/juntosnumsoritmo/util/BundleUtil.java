package br.com.copa.juntosnumsoritmo.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class BundleUtil {

    private static final String INTERROG = "???";

    private BundleUtil() {
        super();
    }

    public static String getString(String key, Locale locale, Object... values) {
        String retorno = "";

        final ResourceBundle bundle = ResourceBundle.getBundle(Constantes.BUNDLE_NAME, locale);
        
        if (bundleContainsKey(key, bundle)) {
            retorno = getValue(key, bundle, locale, values);
        } else {
            retorno = (new StringBuilder()).append(INTERROG).append(key).append(INTERROG).toString();
        }

        return retorno;
    }

    private static boolean bundleContainsKey(String key, ResourceBundle bundle) {
        boolean retorno = false;
        try {
            String value = bundle.getString(key);
            retorno = (value != null);
        } catch (MissingResourceException e) {
            retorno = false;
        }
        return retorno;
    }

    private static String getValue(String key, ResourceBundle bundle, Locale locale, Object... parameters) {
        try {
            if (parameters == null || parameters.length == 0) {
                return bundle.getString(key);
            } else {
                MessageFormat formatter = new MessageFormat(bundle.getString(key), locale);
                return formatter.format(parameters);
            }
        } catch (MissingResourceException e) {
            return (new StringBuilder()).append(INTERROG).append(key).append(INTERROG).toString();
        }
    }

}
