<%-- 
    Document   : listar
    Created on : 28/03/2016, 20:23:04
    Author     : Bruno
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.edu.ifsul.modelo.Tarifa"%>
<%@page import="br.edu.ifsul.dao.TarifaDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="tarifaDao"
             scope="session" type="TarifaDAO"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <title>Listagem de Tarifas</title>
    </head>
    <body>
        <div class="container">
            <h1>Listagem de Tarifas</h1>
            <h2><%=tarifaDao.getMensagem()%></h2>
            <a class="btn btn-primary" href="../index.html"><span class="glyphicon glyphicon-home"></span> Home</a>
            <a class="btn btn-success" href="ServletTarifa?acao=incluir"><span class="glyphicon glyphicon-plus"></span> Incluir Nova Tarifa</a>
            <hr/>
            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Início Vigência</th>
                        <th>Fim Vigência</th>
                        <th>Regra</th>
                        <th>Custo</th>
                        <th>Operadora</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Tarifa e : tarifaDao.getLista()) {
                    %>
                    <tr>
                        <td><%=e.getId()%></td>
                        <td><%=e.getInicioVigenciaString()%></td>
                        <td><%=e.getFimVigenciaString()%></td>
                        <td><%=e.getRegraTarifacao()%></td>
                        <td><%=e.getCusto()%></td>
                        <td><%=e.getOperadora().getNome()%></td>
                        <td><a class="btn btn-warning" href="ServletTarifa?acao=alterar&id=<%=e.getId()%>"><span class="glyphicon glyphicon-edit"></span></a></td>
                        <td><a class="btn btn-danger" href="ServletTarifa?acao=excluir&id=<%=e.getId()%>"><span class="glyphicon glyphicon-remove"></span></a></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>            
            </table>
        </div>
    <script src="https://code.jquery.com/jquery-2.2.3.min.js"   integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="   crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    </body>
</html>