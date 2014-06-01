package br.com.copa.juntosnumsoritmo.controller;

import br.com.copa.juntosnumsoritmo.model.Apostador;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class ApostadorControle extends PersistenciaControle<Apostador> {
    
    private static final long serialVersionUID = -9141081515709898775L;

    @Override
    public void init() {
        setEntidade(new Apostador());
        
        setFiltro(new Apostador());
        
        listar();
    }
    
    @Override
    public String preSalvar() {
        setEntidade(new Apostador());
        
        return null;
    }

    @Override
    public String preAtualizar() {
        obter();
        
        return null;
    }
    
}
