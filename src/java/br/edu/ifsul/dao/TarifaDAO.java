
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Tarifa;
import br.edu.ifsul.util.Util;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 *
 * @author Bruno
 */
public class TarifaDAO {
    
    private Tarifa objetoSelecionado;
    private String mensagem = "";
    private EntityManager em;

    public TarifaDAO() {
        em = EntityManagerUtil.getEntityManager();
    }
    
    public List<Tarifa> getLista () {
        return em.createQuery("from Tarifa order by id").getResultList();
    }
    
    public boolean salvar (Tarifa t) {
        try {
            em.getTransaction().begin();
            if (t.getId() == null) {
                em.persist(t);
            }
            else {
                em.merge(t);
            }
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso!";
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao persistir: " + Util.getMensagemErro(e);
            return false;
        }
    }
    
    public boolean remover (Tarifa u) {
        try {
            em.getTransaction().begin();
            em.remove(u);
            em.getTransaction().commit();
            mensagem = "Objeto removido com sucesso!";
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "Erro ao remover: " + Util.getMensagemErro(e);
            return false;
        }
    }
    
    public Tarifa localizar (Integer id) {
        return em.find(Tarifa.class, id);
    }
    
    public boolean validaObjeto (Tarifa u) {
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Tarifa>> erros = validador.validate(u);
        if (erros.size() > 0) {
            mensagem = "<div class='alert alert-danger' role='alert'>";
            for (ConstraintViolation<Tarifa> erro : erros) {
                mensagem += "<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span><span class='sr-only'>Erro:</span> " + erro.getMessage() + "<br/>";
            }
            mensagem += "</div>";
            return false;
        }
        else {
            return true;
        }
    }

    public Tarifa getObjetoSelecionado() {
        return objetoSelecionado;
    }

    public void setObjetoSelecionado(Tarifa objetoSelecionado) {
        this.objetoSelecionado = objetoSelecionado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
}
