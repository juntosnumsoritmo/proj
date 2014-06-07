package br.com.copa.juntosnumsoritmo.controller;

import br.com.copa.juntosnumsoritmo.model.Atleta;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class AtletaControle extends PersistenciaControle<Atleta> {
    
    private static final long serialVersionUID = -5237236861225398885L;

    @Override
    public void init() {
        setEntidade(new Atleta());
        
        setFiltro(new Atleta());

        listar();
    }

    @Override
    public String preSalvar() {
        setEntidade(new Atleta());
        
        return null;
    }

    @Override
    public String preAtualizar() {
        obter();
        
        return null;
    }
    
}
