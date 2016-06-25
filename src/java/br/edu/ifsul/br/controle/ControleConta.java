/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.br.controle;

import br.edu.ifsul.dao.ContaDAO;
import br.edu.ifsul.dao.OperadoraDAO;
import br.edu.ifsul.dao.PrefixoDAO;
import br.edu.ifsul.dao.RamalDAO;
import br.edu.ifsul.dao.SenhaDAO;
import br.edu.ifsul.dao.ServicoDAO;
import br.edu.ifsul.dao.TarifaDAO;
import br.edu.ifsul.modelo.Conta;
import br.edu.ifsul.modelo.Ligacao;
import br.edu.ifsul.modelo.Operadora;
import br.edu.ifsul.modelo.Prefixo;
import br.edu.ifsul.modelo.Ramal;
import br.edu.ifsul.modelo.Senha;
import br.edu.ifsul.modelo.Servico;
import br.edu.ifsul.modelo.ServicoConta;
import br.edu.ifsul.modelo.Tarifa;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author Bruno
 */
@ManagedBean(name = "controleConta")
@ViewScoped
public class ControleConta implements Serializable {
    
    private ContaDAO<Conta> dao;
    private Conta objeto;
    private OperadoraDAO<Operadora> daoOperadora;
    private TarifaDAO<Tarifa> daoTarifa;
    private PrefixoDAO<Prefixo> daoPrefixo;
    private ServicoDAO<Servico> daoServico;
    private SenhaDAO<Senha> daoSenha;
    private RamalDAO<Ramal> daoRamal;
    private ServicoConta servico;
    private Ligacao ligacao;    
    private Boolean novoServico;
    private Boolean novaLigacao;

    public ControleConta () {
        dao = new ContaDAO<>();
        daoOperadora = new OperadoraDAO<>();
        daoTarifa = new TarifaDAO<>();
        daoPrefixo = new PrefixoDAO<>();
        daoServico = new ServicoDAO<>();
        daoSenha = new SenhaDAO<>();
        daoRamal = new RamalDAO<>();
    }
    
    public void relatorioConta(Integer id) {
        objeto = dao.localizar(id);
        List<Conta> listaConta = new ArrayList<>();
        listaConta.add(objeto);
        HashMap parametros = new HashMap();
        parametros.put("listaServicos", objeto.getServicos());
        parametros.put("listaLigacoes", objeto.getLigacoes());
        UtilRelatorios.imprimeRelatorio("relatorioContajavaBeans", parametros, listaConta);
    }
    
    public void atualizaValorTotalConta () {
        Double valorTotal = 0.0;
        for (ServicoConta s : objeto.getServicos()) {
            valorTotal += s.getCustoReal();
        }
        for (Ligacao l : objeto.getLigacoes()) {
            valorTotal += l.getCusto();
        }
        objeto.setCustoTotal(valorTotal);
    }
    
    public void novoServico () {
        servico = new ServicoConta();
        novoServico = true;
    }
    
    public void alterarServico (int index) {
        servico = objeto.getServicos().get(index);
        novoServico = false;
        this.atualizaValorTotalConta();
    }
    
    public void salvarServico () {
        if (novoServico) {
            objeto.adicionarServico(servico);
            this.atualizaValorTotalConta();
        }
        Util.mensagemInformacao("Operação realizada com sucesso!");
    }
    
    public void removerServico (int index) {
        objeto.removerServico(index);
        this.atualizaValorTotalConta();
        Util.mensagemInformacao("Operação realizada com sucesso!: "+index);
    }
    
    public void novaLigacao () {
        ligacao = new Ligacao();
        novaLigacao = true;
    }
    
    public void alterarLigacao (int index) {
        ligacao = objeto.getLigacoes().get(index);
        ligacao.getRamaisLigacao().size();
        novaLigacao = false;
        this.atualizaValorTotalConta();
    }
    
    public void salvarLigacao () {
        if (novaLigacao) {
            objeto.adicionarLigacao(ligacao);
            this.atualizaValorTotalConta();
        }
        Util.mensagemInformacao("Operação realizada com sucesso!");
    }
    
    public void removerLigacao (int index) {
        objeto.removerLigacao(index);
        this.atualizaValorTotalConta();
        Util.mensagemInformacao("Operação realizada com sucesso!");
    }
    
    public String listar () {
        return "/privado/conta/listar?faces-redirect=true";
    }
    
    public void novo () {
        objeto = new Conta ();
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
    
    // getters e setters abaixo

    public void setDao(ContaDAO<Conta> dao) {
        this.dao = dao;
    }

    public Conta getObjeto() {
        return objeto;
    }

    public void setObjeto(Conta objeto) {
        this.objeto = objeto;
    }

    public OperadoraDAO<Operadora> getDaoOperadora() {
        return daoOperadora;
    }

    public void setDaoOperadora(OperadoraDAO<Operadora> daoOperadora) {
        this.daoOperadora = daoOperadora;
    }

    public ServicoConta getServico() {
        return servico;
    }

    public void setServico(ServicoConta servico) {
        this.servico = servico;
    }

    public Ligacao getLigacao() {
        return ligacao;
    }

    public void setLigacao(Ligacao ligacao) {
        this.ligacao = ligacao;
    }

    public Boolean getNovoServico() {
        return novoServico;
    }

    public void setNovoServico(Boolean novoServico) {
        this.novoServico = novoServico;
    }

    public Boolean getNovaLigacao() {
        return novaLigacao;
    }

    public void setNovaLigacao(Boolean novaLigacao) {
        this.novaLigacao = novaLigacao;
    }

    public ContaDAO<Conta> getDao() {
        return dao;
    }

    public TarifaDAO<Tarifa> getDaoTarifa() {
        return daoTarifa;
    }

    public void setDaoTarifa(TarifaDAO<Tarifa> daoTarifa) {
        this.daoTarifa = daoTarifa;
    }

    public PrefixoDAO<Prefixo> getDaoPrefixo() {
        return daoPrefixo;
    }

    public void setDaoPrefixo(PrefixoDAO<Prefixo> daoPrefixo) {
        this.daoPrefixo = daoPrefixo;
    }

    public ServicoDAO<Servico> getDaoServico() {
        return daoServico;
    }

    public void setDaoServico(ServicoDAO<Servico> daoServico) {
        this.daoServico = daoServico;
    }

    public SenhaDAO<Senha> getDaoSenha() {
        return daoSenha;
    }

    public void setDaoSenha(SenhaDAO<Senha> daoSenha) {
        this.daoSenha = daoSenha;
    }

    public RamalDAO<Ramal> getDaoRamal() {
        return daoRamal;
    }

    public void setDaoRamal(RamalDAO<Ramal> daoRamal) {
        this.daoRamal = daoRamal;
    }
    
}
