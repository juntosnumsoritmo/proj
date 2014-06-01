package br.com.copa.juntosnumsoritmo.listener;

import br.com.copa.juntosnumsoritmo.facade.IApostadorFacade;
import br.com.copa.juntosnumsoritmo.model.Apostador;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MongoListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        final WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        final IApostadorFacade apostadorFacade = context.getBean(IApostadorFacade.class);
        final MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);

        if (!mongoTemplate.collectionExists(Apostador.class)) {
            final Apostador apostador = new Apostador();
            apostador.setNome("Administrador");
            apostador.setLogin("admin");
            apostador.setSenha("pipoca");
            apostador.setAdmin(true);

            apostadorFacade.salvar(apostador);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
