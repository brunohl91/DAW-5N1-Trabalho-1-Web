/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.br.controle;

import br.edu.ifsul.dao.OperadoraDAO;
import br.edu.ifsul.dao.TarifaDAO;
import br.edu.ifsul.modelo.Operadora;
import br.edu.ifsul.modelo.Tarifa;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Bruno
 */
@ManagedBean
@SessionScoped
public class ControleTarifa implements Serializable {
    
    private TarifaDAO<Tarifa> dao;
    private Tarifa objeto;
    private OperadoraDAO<Operadora> daoOperadora;

    public ControleTarifa() {
        dao = new TarifaDAO<>();
        daoOperadora = new OperadoraDAO<>();
    }
    
    public void relatorioTarifas() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioTarifasjavaBeans", parametros, dao.getListaTodos());
    }
    
    public String listar () {
        return "/privado/tarifa/listar?faces-redirect=true";
    }
    
    public void novo () {
        objeto = new Tarifa();
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
    
    public TarifaDAO<Tarifa> getDao() {
        return dao;
    }

    public void setDao(TarifaDAO<Tarifa> dao) {
        this.dao = dao;
    }

    public Tarifa getObjeto() {
        return objeto;
    }

    public void setObjeto(Tarifa objeto) {
        this.objeto = objeto;
    }

    public OperadoraDAO<Operadora> getDaoOperadora() {
        return daoOperadora;
    }

    public void setDaoOperadora(OperadoraDAO<Operadora> daoOperadora) {
        this.daoOperadora = daoOperadora;
    }
    
}
