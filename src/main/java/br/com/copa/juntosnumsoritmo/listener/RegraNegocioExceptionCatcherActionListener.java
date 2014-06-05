package br.com.copa.juntosnumsoritmo.listener;

import br.com.copa.juntosnumsoritmo.exception.RegraNegocioException;
import br.com.copa.juntosnumsoritmo.exception.ValidacaoException;
import br.com.copa.juntosnumsoritmo.util.BundleUtil;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import br.com.copa.juntosnumsoritmo.validador.Mensagem;
import com.sun.faces.application.ActionListenerImpl;
import java.util.Locale;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegraNegocioExceptionCatcherActionListener extends ActionListenerImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegraNegocioExceptionCatcherActionListener.class);
    private static final String MSG_LOG_INSTANCE_FACES_EXCEPTION = "This Exception is an instance of FacesException";
    private static final String MSG_LOG_EXCEPTION_WITHOU_MESSAGE = (new StringBuilder())
            .append("BusinessException caught without message.")
            .append("This messege will not be rendered.").toString();

    @Override
    public void processAction(ActionEvent event) {
        logDebugBeginEventProcessing(event);
        try {
            super.processAction(event);
            logDebugEventProcessingWithoutExceptions(event);
        } catch (FacesException e) {
            /*
             * Para todos os testes feitos, e a verificacao da implementacao da
             * versao utilizada 1.2_12 demonstram que o metodo processAction do
             * ActionListenerImpl lanca um dos dois tipos de excecao:
             * EvaluationException ou MethodNotFoundException. Dentro da excecao
             * eh encapsulada a excecao de origem. Esse eh o motivo pelo qual
             * para esse tratamento de excecao, nos pegamos a causa da causa
             * para verificar se eh uma BusinessException
             */
            final Throwable cause = e.getCause().getCause();
            if (cause instanceof ValidacaoException) {
                logDebugEventExceptionsOnEventProcessing(event, cause);
                final ValidacaoException exception = (ValidacaoException) cause;

                for (Mensagem mensagem : exception.getValidadorMessage().getMessages()) {
                    saveException(mensagem.getTextoMensagem(), exception);
                }

                FacesContext.getCurrentInstance().renderResponse();
            } else if (cause instanceof RegraNegocioException) {
                logDebugEventExceptionsOnEventProcessing(event, cause);
                saveException(cause.getMessage(), (RegraNegocioException) cause);
                FacesContext.getCurrentInstance().renderResponse();
//            } else if (cause instanceof ConstraintViolationException) {
//                for (ConstraintViolation<?> value : ((ConstraintViolationException) cause)
//                        .getConstraintViolations()) {
//                    saveValidatorException(value.getMessage(), e);
//                }
            } else {
                /*
                 * Colocar log para avisar que foi encontrado uma
                 * FacesException, cujo o segundo nivel nao era uma
                 * BusinessException, difere do comportamento esperado, e
                 * relanco a excecao para alguem tratar, ou ser capturada pela
                 * barreira de excecao
                 */
                LOGGER.error((new StringBuilder()).append(
                        "Expected BusinessException, but got a ").append(
                                cause.getClass()).toString(), cause);
                throw e;
            }
        }
    }

    private void saveException(String msg, RegraNegocioException ex) {
        if (msg != null) {
            // prepara a mensagem que sera apresentada para o usuario e
            // utilizada pelo log
            FacesMessage facesMessage = getFacesMessage(
                    FacesMessage.SEVERITY_ERROR, msg, ex.getParamametros());
            String message = facesMessage.getSummary();

            FacesMessage mesg = new FacesMessage();
            mesg.getSummary();

            // Pega a mensagem da excecao e adiciona no faces context
            FacesContext.getCurrentInstance().addMessage(
                    Constantes.BUNDLE_NAME, facesMessage);

            FacesContext.getCurrentInstance().validationFailed();

            // loga a mensagem como debug
            LOGGER.debug(MSG_LOG_INSTANCE_FACES_EXCEPTION);
            LOGGER.debug(message, ex);
        } else {
            LOGGER.error(MSG_LOG_EXCEPTION_WITHOU_MESSAGE, ex);
        }
    }

    protected FacesMessage getFacesMessage(FacesMessage.Severity severity, String msg,
            Object... params) {
        final Locale locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
        final String mensagem = BundleUtil.getString(msg, locale);

        return new FacesMessage(severity, mensagem, null);
    }

    private void saveValidatorException(String msg, Throwable ex) {
        if (msg != null) {
            // prepara a mensagem que sera apresentada para o usuario e
            // utilizada pelo log
            FacesMessage facesMessage = new FacesMessage(msg);
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);

            // Pega a mensagem da excecao e adiciona no faces context
            FacesContext.getCurrentInstance().addMessage(
                    Constantes.BUNDLE_NAME, facesMessage);

            FacesContext.getCurrentInstance().validationFailed();

            // loga a mensagem como debug
            LOGGER.debug(MSG_LOG_INSTANCE_FACES_EXCEPTION);
            LOGGER.debug(msg, ex);
        } else {
            LOGGER.error(MSG_LOG_EXCEPTION_WITHOU_MESSAGE, ex);
        }
    }

    private static final void logDebugBeginEventProcessing(ActionEvent event) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug((new StringBuilder()).append(
                    "Beginning to process event from source ").append(
                            event.getSource()).toString());
        }
    }

    private static final void logDebugEventProcessingWithoutExceptions(
            ActionEvent event) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug((new StringBuilder()).append("Event from source ")
                    .append(event.getSource()).append(
                            " processed without exceptions.").toString());
        }
    }

    private static final void logDebugEventExceptionsOnEventProcessing(
            ActionEvent event, Throwable cause) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug((new StringBuilder()).append("Caught a ").append(
                    cause.getClass()).append(
                            " while processing event from source ").append(
                            event.getSource()).append(
                            ". Sending a message to the end-user.").toString());
        }
    }

}
