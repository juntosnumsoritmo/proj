package br.com.copa.juntosnumsoritmo.converter;

import br.com.copa.juntosnumsoritmo.model.AbstractDocument;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "selectConverter")
public class SelectConverter implements Converter {

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Object retorno = null;

        if (uic != null) {
            final List<UIComponent> childs = uic.getChildren();
            UISelectItems itens = null;
            
            if (childs != null) {
                for (UIComponent ui : childs) {
                    if (ui instanceof UISelectItems) {
                        itens = (UISelectItems) ui;
                        break;
                    } else if (ui instanceof UISelectItem) {
                        final UISelectItem item = (UISelectItem) ui;
                        
                        try {
                            final AbstractDocument val = (AbstractDocument) item.getItemValue();
                            if (value.equals(val.getId().toString())) {
                                retorno = val;
                                break;
                            }
                        } catch (Exception e) { }
                    }
                }
            }

            if (itens != null) {
                final List<AbstractDocument> values = (List<AbstractDocument>) itens.getValue();
                
                if (values != null) {
                    for (AbstractDocument val : values) {
                        if (value.equals(val.getId().toString())) {
                            retorno = val;
                            break;
                        }
                    }
                }
            }
        }
        
        return retorno;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object obj) {
        String retorno = "";

        if (obj != null && obj instanceof AbstractDocument) {
            final AbstractDocument abstractDocument = (AbstractDocument) obj;
            
            if (abstractDocument.getId() != null) {
                retorno = abstractDocument.getId().toString();
            }
        }

        return retorno;
    }

}
