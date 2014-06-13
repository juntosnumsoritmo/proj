package br.com.copa.juntosnumsoritmo.listener;

import br.com.copa.juntosnumsoritmo.facade.IApostadorFacade;
<<<<<<< HEAD
import br.com.copa.juntosnumsoritmo.facade.IAtletaFacade;
import br.com.copa.juntosnumsoritmo.facade.ISelecaoFacade;
import br.com.copa.juntosnumsoritmo.model.Apostador;
import br.com.copa.juntosnumsoritmo.model.Atleta;
import br.com.copa.juntosnumsoritmo.model.Selecao;
import br.com.copa.juntosnumsoritmo.util.Constantes;
import java.util.HashSet;
import java.util.Set;
=======
import br.com.copa.juntosnumsoritmo.model.Apostador;
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MongoListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        final WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        final IApostadorFacade apostadorFacade = context.getBean(IApostadorFacade.class);
<<<<<<< HEAD
        final IAtletaFacade atletaFacade = context.getBean(IAtletaFacade.class);
        final ISelecaoFacade selecaoFacade = context.getBean(ISelecaoFacade.class);
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
        final MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);

        if (!mongoTemplate.collectionExists(Apostador.class)) {
            final Apostador apostador = new Apostador();
            apostador.setNome("Administrador");
            apostador.setLogin("admin");
            apostador.setSenha("pipoca");
            apostador.setAdmin(true);

            apostadorFacade.salvar(apostador);
<<<<<<< HEAD

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
=======
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
