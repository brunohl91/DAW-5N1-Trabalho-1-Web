/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.servlets;

import br.edu.ifsul.dao.OperadoraDAO;
import br.edu.ifsul.dao.TarifaDAO;
import br.edu.ifsul.modelo.Tarifa;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruno
 */
@WebServlet(name = "ServletTarifa", urlPatterns = {"/tarifa/ServletTarifa"})
public class ServletTarifa extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TarifaDAO dao = (TarifaDAO) request.getSession().getAttribute("tarifaDao");
        if (dao == null) {
            dao = new TarifaDAO();
        }
        OperadoraDAO daoOperadora = (OperadoraDAO) request.getSession().getAttribute("operadoraDao");
        if (daoOperadora == null) {
            daoOperadora = new OperadoraDAO();
        }
        String tela = "";
        String acao = request.getParameter("acao");
        if (acao == null) {
            tela = "listar.jsp";
        } else if (acao.equals("incluir")) {
            dao.setObjetoSelecionado(new Tarifa());
            dao.setMensagem("");
            tela = "formulario.jsp";
        } else if (acao.equals("alterar")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            dao.setObjetoSelecionado(dao.localizar(id));
            dao.setMensagem("");
            tela = "formulario.jsp";
        } else if (acao.equals("excluir")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Tarifa obj = dao.localizar(id);
            if (obj != null) {
                dao.remover(obj);
            }
            tela = "listar.jsp";
        } else if (acao.equals("salvar")) {
            Integer id = null;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (Exception e) {
                dao.setMensagem("Erro ao converter o ID");
            }
            Integer idOperadora = null;
            try {
                idOperadora = Integer.parseInt(request.getParameter("idOperadora"));
            } catch (Exception e) {
                dao.setMensagem("Erro ao converter o ID da Operadora");
            }
            dao.getObjetoSelecionado().setId(id);
            try {
                Calendar dataFimVigencia = Calendar.getInstance();
                Calendar dataIniVigencia = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                dataFimVigencia.setTime(sdf.parse(request.getParameter("dataFimVigencia")));
                dataIniVigencia.setTime(sdf.parse(request.getParameter("dataIniVigencia")));
                dao.getObjetoSelecionado().setFimVigencia(dataFimVigencia);
                dao.getObjetoSelecionado().setInicioVigencia(dataIniVigencia);
                dao.getObjetoSelecionado().setCusto(Double.parseDouble(request.getParameter("custo")));
                dao.getObjetoSelecionado().setRegraTarifacao(Integer.parseInt(request.getParameter("regra")));
            } catch (Exception e) {
                dao.setMensagem("Erro ao converter dados!");
            }
            dao.getObjetoSelecionado().setOperadora(daoOperadora.localizar(idOperadora));
            if (dao.validaObjeto(dao.getObjetoSelecionado())) {
                dao.salvar(dao.getObjetoSelecionado());
                tela = "listar.jsp";
            } else {
                tela = "formulario.jsp";
            }
        } else if (acao.equals("cancelar")){
            dao.setMensagem("");
            tela = "listar.jsp";
        }
        // atualizar o dao na sessão
        request.getSession().setAttribute("tarifaDao", dao);
        request.getSession().setAttribute("operadoraDao", daoOperadora);
        // redireciono para a tela desejada
        response.sendRedirect(tela);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
