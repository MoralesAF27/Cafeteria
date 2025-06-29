<%-- 
    Document   : list
    Created on : 29/06/2025, 9:22:57 a. m.
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mis Pedidos - ${appName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cliente.css">
</head>
<body>
    <%@include file="../../../WEB-INF/templates/header.jsp" %>
    <div class="container">
        <h1>Mis Pedidos</h1>
        
        <a href="${pageContext.request.contextPath}/cliente/menu" class="btn btn-primary">Nuevo Pedido</a>
        
        <div class="order-history">
            <c:forEach items="${pedidos}" var="pedido">
                <div class="order-item">
                    <div class="order-header">
                        <span class="order-id">Pedido #${pedido.pedidoId}</span>
                        <span class="order-date">${pedido.fechaPedido}</span>
                        <span class="order-status ${pedido.estado.nombre.toLowerCase()}">${pedido.estado.nombre}</span>
                        <span class="order-total">$${pedido.total}</span>
                    </div>
                    <div class="order-products">
                        <c:forEach items="${pedido.detalles}" var="detalle" end="2">
                            <span>${detalle.cantidad} x ${detalle.producto.nombre}</span>
                        </c:forEach>
                        <c:if test="${pedido.detalles.size() > 3}">
                            <span>+${pedido.detalles.size() - 3} más</span>
                        </c:if>
                    </div>
                    <div class="order-actions">
                        <a href="${pageContext.request.contextPath}/cliente/pedidos/detalle?id=${pedido.pedidoId}" class="btn btn-sm btn-view">Ver Detalle</a>
                        <c:if test="${pedido.estadoId == 1}">
                            <a href="${pageContext.request.contextPath}/pedido?action=repeat&id=${pedido.pedidoId}" class="btn btn-sm btn-repeat">Repetir</a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <%@include file="../../../WEB-INF/templates/footer.jsp" %>
</body>
</html>
