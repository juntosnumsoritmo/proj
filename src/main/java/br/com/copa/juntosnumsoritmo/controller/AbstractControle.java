package br.com.copa.juntosnumsoritmo.controller;

import br.com.copa.juntosnumsoritmo.util.BundleUtil;
import static br.com.copa.juntosnumsoritmo.util.Util.createMessage;
import java.io.Serializable;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class AbstractControle implements Serializable {
    
    private static final long serialVersionUID = 3324601526761990721L;

    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public void addMessage(String key) {
        createMessage(FacesMessage.SEVERITY_INFO, key, locale);
    }

    public void addMessageError(String key) {
        createMessage(FacesMessage.SEVERITY_ERROR, key, locale);

        FacesContext.getCurrentInstance().validationFailed();
    }
    
    public String getText(String key, Object... parametros) {
        return BundleUtil.getString(key, locale, parametros);
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        this.locale = new Locale(language);
    }

}
