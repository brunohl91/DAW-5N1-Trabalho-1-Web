<%-- 
    Document   : formulario
    Created on : 11/04/2016, 20:42:12
    Author     : Bruno
--%>

<%@page import="br.edu.ifsul.modelo.Operadora"%>
<%@page import="br.edu.ifsul.dao.TarifaDAO"%>
<%@page import="br.edu.ifsul.dao.OperadoraDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="operadoraDao" scope="session" type="OperadoraDAO" />
<jsp:useBean id="tarifaDao" scope="session" type="TarifaDAO" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <title>Edição de Tarifas</title>
        <script>
            function doSalvar() {
                document.getElementById("acao").value = "salvar";
                document.getElementById("form").submit();
            }
            function doCancelar() {
                document.getElementById("acao").value = "cancelar";
                document.getElementById("form").submit();
            }
        </script>
    </head>
    <body>
        <div class="container">
            <h1>Edição de Tarifas</h1>
            <h2><%=tarifaDao.getMensagem()%></h2>
            <form name="form" id="form" action="ServletTarifa" method="POST" class="form-group">
                <div class="form-group">
                    <label>ID</label>
                    <input type="text" class="form-control" name="id" value="<%= tarifaDao.getObjetoSelecionado().getId() == null ? "" : tarifaDao.getObjetoSelecionado().getId() %>" size="6" readonly />
                </div>
                <div class="form-group">
                    <label>Inicio Vigência</label>
                    <input type="date" class="form-control" name="dataIniVigencia" value="<%= tarifaDao.getObjetoSelecionado().getInicioVigencia()== null ? "" : tarifaDao.getObjetoSelecionado().getInicioVigenciaString() %>"/>
                </div>
                <div class="form-group">
                    <label>Fim Vigência</label>
                    <input type="date" class="form-control" name="dataFimVigencia" value="<%= tarifaDao.getObjetoSelecionado().getFimVigencia()== null ? "" : tarifaDao.getObjetoSelecionado().getFimVigenciaString() %>"/>
                </div>
                <div class="form-group">
                    <label>Regra de Tarifação</label>
                    <input type="text" class="form-control" name="regra" value="<%= tarifaDao.getObjetoSelecionado().getRegraTarifacao()== null ? "" : tarifaDao.getObjetoSelecionado().getRegraTarifacao() %>" />
                </div>
                <div class="form-group">
                    <label>Custo</label>
                    <input type="text" class="form-control" name="custo" value="<%= tarifaDao.getObjetoSelecionado().getCusto()== null ? "" : tarifaDao.getObjetoSelecionado().getCusto() %>" />
                </div>
                <div class="form-group">
                    <label>Operadora</label>
                    <select name="idOperadora" id="idOperadora" class="form-control">
                        <%
                            for (Operadora e : operadoraDao.getLista()) {
                                String selected = "";
                                if (tarifaDao.getObjetoSelecionado().getOperadora() != null) {
                                    selected = tarifaDao.getObjetoSelecionado().getOperadora().getId().equals(e.getId()) ? "selected" : "";
                                }
                        %>
                        <option value="<%=e.getId()%>" <%=selected%>><%=e.getNome()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <input type="submit" class="btn btn-success" value="Enviar" name="btnSalvar" onclick="doSalvar()" />
                <input type="submit" class="btn btn-danger" value="Cancelar" name="btnCancelar" onclick="doCancelar()" />
                <input type="hidden" name="acao" id="acao" value="" />
            </form>
        </div>
    <script src="https://code.jquery.com/jquery-2.2.3.min.js"   integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    </body>
</html>
