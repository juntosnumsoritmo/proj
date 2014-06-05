package br.com.copa.juntosnumsoritmo.util;

import br.com.copa.juntosnumsoritmo.model.AbstractDocument;
import br.com.copa.juntosnumsoritmo.model.Apostador;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Util {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(Constantes.PATTERN_DATA_BRASIL);
    private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat(Constantes.PATTERN_DATA_HORA_BRASIL);

    private Util() {
        super();
    }

    public static void createMessage(FacesMessage.Severity severity, String key, Locale locale) {
        final String mensagem = BundleUtil.getString(key, locale);
        final FacesMessage message = new FacesMessage(severity, mensagem, null);

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(true);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.
                getCurrentInstance().
                getExternalContext().getRequest();
    }

    public static Apostador recuperarUsarioLogado() {
        final Object usuarioLogado = getSession().getAttribute(Constantes.USUARIO_LOGADO);
        Apostador retorno = null;

        if (usuarioLogado != null && usuarioLogado instanceof Apostador) {
            retorno = (Apostador) usuarioLogado;
        }

        return retorno;
    }

    public static Apostador recuperarUsuarioLogado(HttpServletRequest request) {
        final HttpSession session = request.getSession(true);
        final Object usuarioLogado = session.getAttribute(Constantes.USUARIO_LOGADO);
        Apostador retorno = null;

        if (usuarioLogado != null && usuarioLogado instanceof Apostador) {
            retorno = (Apostador) usuarioLogado;
        }

        return retorno;
    }

    public static String formatarPlacar(Integer resultado1, Integer resultado2) {
        String retorno = "";

        if (resultado1 != null && resultado2 != null) {
            retorno = new StringBuilder().append(resultado1).append(Constantes.SEPARADOR_PLACAR).append(resultado2).toString();
        }

        return retorno;
    }

    public static <T> T obterItem(List<T> list) {
        T retorno = null;

        if (list != null && list.size() >= 1) {
            final Integer index = list.size() == 1 ? 0 : gerarValor(list.size() - 1);

            retorno = list.get(index);
        }

        return retorno;
    }

    public static Integer gerarValor(Integer limite) {
        double retorno = 0d;

        do {
            retorno = Math.random() * limite;
        } while (retorno < 0);

        return (int) retorno;
    }

    public static String formatarData(Date data) {
        return data == null ? "" : DATE_FORMAT.format(data);
    }

    public static String formatarDataHora(Date data) {
        return data == null ? "" : DATE_TIME_FORMAT.format(data);
    }

    public static Date configurarPeriodoInicial(Date date) {
        if (date == null) {
            return null;
        }

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date configurarPeriodoFinal(Date date) {
        if (date == null) {
            return null;
        }

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();
    }

    public static boolean isPeriodoValido(Date dataInicial, Date dataFinal) {
        boolean retorno = false;

        if (dataInicial != null && dataFinal != null) {
            dataInicial = configurarPeriodoInicial(dataInicial);
            dataFinal = configurarPeriodoFinal(dataFinal);

            retorno = dataFinal.getTime() >= dataInicial.getTime();
        }

        return retorno;
    }

    public static List<Long> obterAbstractDocumentIdList(Collection<? extends AbstractDocument> abstractDocumentList) {
        final List<Long> retorno = new ArrayList<Long>(Constantes.EMPTY);

        if (abstractDocumentList != null) {
            for (AbstractDocument document : abstractDocumentList) {
                retorno.add(document.getId());
            }
        }

        return retorno;
    }

    public static List<Long> obterAbstractDocumentIdList(AbstractDocument... abstractDocumentList) {
        final List<Long> retorno = new ArrayList<Long>(Constantes.EMPTY);

        if (abstractDocumentList != null) {
            retorno.addAll(Util.obterAbstractDocumentIdList(Arrays.asList(abstractDocumentList)));
        }

        return retorno;
    }

    public static Date obterDataInicialDefault(Date data) {
        if (data == null) {
            return null;
        }

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        return calendar.getTime();
    }

    public static Date obterDataFinalDefault(Date data) {
        if (data == null) {
            return null;
        }

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        return calendar.getTime();
    }

}
