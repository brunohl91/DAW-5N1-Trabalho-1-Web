<%-- 
    Document   : listar
    Created on : 28/03/2016, 20:23:04
    Author     : Bruno
--%>

<%@page import="br.edu.ifsul.modelo.Operadora"%>
<%@page import="br.edu.ifsul.dao.OperadoraDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="operadoraDao"
             scope="session" type="OperadoraDAO"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <title>Listagem de Operadoras</title>
    </head>
    <body>
        <div class="container">
            <h1>Listagem de Operadoras</h1>
            <h2><%=operadoraDao.getMensagem()%></h2>
            <a class="btn btn-primary" href="../index.html"><span class="glyphicon glyphicon-home"></span> Home</a>
            <a class="btn btn-success" href="ServletOperadora?acao=incluir"><span class="glyphicon glyphicon-plus"></span> Incluir Nova Operadora</a>
            <hr/>
            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Tipo</th>
                        <th>Código de Discagem</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Operadora e : operadoraDao.getLista()) {
                    %>
                    <tr>
                        <td><%=e.getId()%></td>
                        <td><%=e.getNome()%></td>
                        <td><%=e.getTipo()%></td>
                        <td><%=e.getCodigoDiscagem()%></td>
                        <td><a class="btn btn-warning" href="ServletOperadora?acao=alterar&id=<%=e.getId()%>"><span class="glyphicon glyphicon-edit"></span></a></td>
                        <td><a class="btn btn-danger" href="ServletOperadora?acao=excluir&id=<%=e.getId()%>"><span class="glyphicon glyphicon-remove"></span></a></td>
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