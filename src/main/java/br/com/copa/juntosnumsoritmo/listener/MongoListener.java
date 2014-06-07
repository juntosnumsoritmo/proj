package br.com.copa.juntosnumsoritmo.listener;

import br.com.copa.juntosnumsoritmo.facade.IApostadorFacade;
import br.com.copa.juntosnumsoritmo.facade.IAtletaFacade;
import br.com.copa.juntosnumsoritmo.facade.ISelecaoFacade;
import br.com.copa.juntosnumsoritmo.model.Apostador;
import br.com.copa.juntosnumsoritmo.model.Atleta;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MongoListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        final WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        final IApostadorFacade apostadorFacade = context.getBean(IApostadorFacade.class);
        final IAtletaFacade atletaFacade = context.getBean(IAtletaFacade.class);
        final ISelecaoFacade selecaoFacade = context.getBean(ISelecaoFacade.class);
        final MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);

        if (!mongoTemplate.collectionExists(Apostador.class)) {
            final Apostador apostador = new Apostador();
            apostador.setNome("Administrador");
            apostador.setLogin("admin");
            apostador.setSenha("pipoca");
            apostador.setAdmin(true);

            apostadorFacade.salvar(apostador);

            final Set<Atleta> atletaList = new HashSet<Atleta>(Constantes.EMPTY);

            for (int i = 1; i <= 11; i++) {
                final Atleta atleta = new Atleta(Long.valueOf(i), "Atleta Brasil " + i, i + 20, "Brasil", null, null);

                atletaList.add(atleta);

                atletaFacade.salvar(atleta);
            }

            selecaoFacade.salvar(new Selecao("Brasil", atletaList));

            atletaList.clear();

            for (int i = 1; i <= 11; i++) {
                final Atleta atleta = new Atleta(Long.valueOf(i + 11), "Atleta Argentina " + i, i + 20, "Argentina", null, null);

                atletaList.add(atleta);

                atletaFacade.salvar(atleta);
            }
            
            selecaoFacade.salvar(new Selecao("Argentina", atletaList));
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
