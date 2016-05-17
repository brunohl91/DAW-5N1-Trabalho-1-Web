/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.br.controle;

import br.edu.ifsul.dao.OperadoraDAO;
import br.edu.ifsul.modelo.Operadora;
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
public class ControleOperadora implements Serializable {
    
    private OperadoraDAO<Operadora> dao;
    private Operadora objeto;

    public ControleOperadora() {
        dao = new OperadoraDAO<>();
    }
    
public String listar () {
        return "/privado/operadora/listar?faces-redirect=true";
    }
    
    public String novo () {
        objeto = new Operadora();
        return "formulario";
    }
    
    public String salvar () {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = dao.persist(objeto);
        }
        else {
            persistiu = dao.merge(objeto);
        }
        
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
            return "listar";
        }
        else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario";
        }
    }
    
    public String cancelar () {
        return "listar";
    }
    
    public String editar (Integer id) {
        try {
            objeto = dao.localizar(id);
            return "formulario";
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
            return "listar";
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
    
    public OperadoraDAO<Operadora> getDao() {
        return dao;
    }

    public void setDao(OperadoraDAO<Operadora> dao) {
        this.dao = dao;
    }

    public Operadora getObjeto() {
        return objeto;
    }

    public void setObjeto(Operadora objeto) {
        this.objeto = objeto;
    }
    
}
