/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.br.controle;

import br.edu.ifsul.dao.RamalDAO;
import br.edu.ifsul.modelo.Ramal;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Bruno
 */
@ManagedBean
@SessionScoped
public class ControleRamal implements Serializable {
    
    private RamalDAO<Ramal> dao;
    private Ramal objeto;

    public ControleRamal() {
        dao = new RamalDAO<>();
    }
    
public String listar () {
        return "/privado/ramal/listar?faces-redirect=true";
    }
    
    public void novo () {
        objeto = new Ramal();
    }
    
    public void salvar () {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        }
        else {
            persistiu = dao.merge(objeto);
        }
        
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        }
        else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public void editar (Integer id) {
        try {
            objeto = dao.localizar(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void remover (Integer id) {
        try {
            objeto = dao.localizar(id);
            if (dao.remover(objeto)) {
                Util.mensagemInformacao(dao.getMensagem());
            }
            else {
                Util.mensagemErro(dao.getMensagem());
            }
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public RamalDAO<Ramal> getDao() {
        return dao;
    }

    public void setDao(RamalDAO<Ramal> dao) {
        this.dao = dao;
    }

    public Ramal getObjeto() {
        return objeto;
    }

    public void setObjeto(Ramal objeto) {
        this.objeto = objeto;
    }
    
}
