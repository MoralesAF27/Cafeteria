<%-- 
    Document   : header
    Created on : 29/06/2025, 9:25:42 a. m.
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${param.title} - ${appName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <c:if test="${not empty param.css}">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/${param.css}.css">
    </c:if>
</head>
<body>
    <header>
        <div class="logo">
            <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo Cafetería">
            <h1>${appName}</h1>
        </div>
        <div class="user-info">
            <c:if test="${not empty usuario}">
                <span>Bienvenido, ${usuario.nombre}</span>
                <a href="${pageContext.request.contextPath}/usuario?action=logout" class="btn btn-sm btn-danger">Cerrar Sesión</a>
            </c:if>
        </div>
    </header>
