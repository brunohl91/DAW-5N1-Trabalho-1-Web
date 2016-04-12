
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Operadora;
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
public class OperadoraDAO {
    
    private Operadora objetoSelecionado;
    private String mensagem = "";
    private EntityManager em;

    public OperadoraDAO() {
        em = EntityManagerUtil.getEntityManager();
    }
    
    public List<Operadora> getLista () {
        return em.createQuery("from Operadora order by id").getResultList();
    }
    
    public boolean salvar (Operadora u) {
        try {
            em.getTransaction().begin();
            if (u.getId() == null) {
                em.persist(u);
            }
            else {
                em.merge(u);
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
    
    public boolean remover (Operadora u) {
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
    
    public Operadora localizar (Integer id) {
        return em.find(Operadora.class, id);
    }
    
    public boolean validaObjeto (Operadora u) {
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Operadora>> erros = validador.validate(u);
        if (erros.size() > 0) {
            mensagem = "<div class='alert alert-danger' role='alert'>";
            for (ConstraintViolation<Operadora> erro : erros) {
                mensagem += "<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span><span class='sr-only'>Erro:</span> " + erro.getMessage() + "<br/>";
            }
            mensagem += "</div>";
            return false;
        }
        else {
            return true;
        }
    }

    public Operadora getObjetoSelecionado() {
        return objetoSelecionado;
    }

    public void setObjetoSelecionado(Operadora objetoSelecionado) {
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
