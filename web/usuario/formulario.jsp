<%-- 
    Document   : formulario
    Created on : 11/04/2016, 20:42:12
    Author     : Bruno
--%>

<%@page import="br.edu.ifsul.dao.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="usuarioDao" scope="session" type="UsuarioDAO" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <title>Edição de Usuários</title>
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
            <h1>Edição de Usuários</h1>
            <h2><%=usuarioDao.getMensagem()%></h2>
            <form name="form" id="form" action="ServletUsuario" method="POST" class="form-group">
                <div class="form-group">
                    <label>ID</label>
                    <input type="text" class="form-control" name="id" value="<%= usuarioDao.getObjetoSelecionado().getId() == null ? "" : usuarioDao.getObjetoSelecionado().getId() %>" size="6" readonly />
                </div>
                <div class="form-group">
                    <label>Login</label>
                    <input type="text" class="form-control" name="login" value="<%= usuarioDao.getObjetoSelecionado().getNome()== null ? "" : usuarioDao.getObjetoSelecionado().getLogin() %>" size="30" />
                </div>
                <div class="form-group">
                    <label>Senha</label>
                    <input type="password" class="form-control" name="senha" value="<%= usuarioDao.getObjetoSelecionado().getSenha() == null ? "" : usuarioDao.getObjetoSelecionado().getSenha() %>" size="30"/>
                </div>
                <div class="form-group">
                    <label>Nome</label>
                    <input type="text" class="form-control" name="nome" value="<%= usuarioDao.getObjetoSelecionado().getNome()== null ? "" : usuarioDao.getObjetoSelecionado().getNome() %>" size="50"/>
                </div>
                <div class="form-group">
                    <label>E-mail</label>
                    <input type="text" class="form-control" name="email" value="<%= usuarioDao.getObjetoSelecionado().getEmail()== null ? "" : usuarioDao.getObjetoSelecionado().getEmail() %>" size="70"/>
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
