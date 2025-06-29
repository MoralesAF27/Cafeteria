<%-- 
    Document   : dashboard
    Created on : 29/06/2025, 9:23:41 a. m.
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inicio - ${appName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cliente.css">
</head>
<body>
    <%@include file="../../../WEB-INF/templates/header.jsp" %>
    <div class="container">
        <h1>Bienvenido, ${usuario.nombre}</h1>
        
        <div class="dashboard-sections">
            <div class="recent-orders">
                <h2>Mis Últimos Pedidos</h2>
                <c:choose>
                    <c:when test="${not empty ultimosPedidos}">
                        <table class="order-table">
                            <thead>
                                <tr>
                                    <th>N° Pedido</th>
                                    <th>Fecha</th>
                                    <th>Estado</th>
                                    <th>Total</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${ultimosPedidos}" var="pedido">
                                    <tr>
                                        <td>${pedido.pedidoId}</td>
                                        <td>${pedido.fechaPedido}</td>
                                        <td><span class="status-${pedido.estado.nombre.toLowerCase()}">${pedido.estado.nombre}</span></td>
                                        <td>$${pedido.total}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/cliente/pedidos/detalle?id=${pedido.pedidoId}" class="btn btn-sm">Ver</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <a href="${pageContext.request.contextPath}/cliente/pedidos" class="btn btn-more">Ver todos</a>
                    </c:when>
                    <c:otherwise>
                        <p>Aún no has realizado pedidos.</p>
                        <a href="${pageContext.request.contextPath}/cliente/menu" class="btn btn-primary">Hacer mi primer pedido</a>
                    </c:otherwise>
                </c:choose>
            </div>
            
            <div class="recommendations">
                <h2>Recomendados para ti</h2>
                <div class="recommended-products">
                    <c:forEach items="${productosRecomendados}" var="producto">
                        <div class="product-card">
                            <img src="${pageContext.request.contextPath}/images/productos/${producto.imagenUrl}" alt="${producto.nombre}">
                            <h3>${producto.nombre}</h3>
                            <p class="price">$${producto.precio}</p>
                            <a href="${pageContext.request.contextPath}/cliente/menu" class="btn btn-add">Ordenar</a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <%@include file="../../../WEB-INF/templates/footer.jsp" %>
</body>
</html>
