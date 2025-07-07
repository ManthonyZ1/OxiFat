<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String nombre = (String) session.getAttribute("nombre");
    if (nombre == null) {
        response.sendRedirect("login.html");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Inicio - OxiFat</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="estilos.css">
</head>
<body>
  <script>
    sessionStorage.setItem("nombreUsuario", "<%= nombre %>");
    window.location.href = "index.html?login=cliente";
  </script>
</body>
</html>
