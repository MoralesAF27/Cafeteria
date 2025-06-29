<%-- 
    Document   : form
    Created on : 29/06/2025, 9:23:03 a. m.
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo Pedido - ${appName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cliente.css">
</head>
<body>
    <%@include file="../../../WEB-INF/templates/header.jsp" %>
    <div class="container">
        <h1>Realizar Pedido</h1>
        
        <div class="menu-categories">
            <button type="button" class="category-btn active" data-category-id="all">Todos</button>
            <c:forEach items="${categorias}" var="categoria">
                <button type="button" class="category-btn" data-category-id="${categoria.categoriaId}">${categoria.nombre}</button>
            </c:forEach>
        </div>
        
        <form id="order-form" action="${pageContext.request.contextPath}/pedido" method="post">
            <input type="hidden" name="action" value="create">
            
            <div class="products-grid">
                <c:forEach items="${productos}" var="producto">
                    <div class="product-card" data-category-id="${producto.categoriaId}">
                        <img src="${pageContext.request.contextPath}/images/productos/${producto.imagenUrl}" alt="${producto.nombre}">
                        <h3>${producto.nombre}</h3>
                        <p>${producto.descripcion}</p>
                        <span class="price">$${producto.precio}</span>
                        
                        <div class="product-controls">
                            <button type="button" class="btn-add" onclick="addToCart(${producto.productoId}, '${producto.nombre}', ${producto.precio})">Agregar</button>
                        </div>
                    </div>
                </c:forEach>
            </div>
            
            <div class="order-summary">
                <h2>Resumen del Pedido</h2>
                <div id="order-items-container">
                    <!-- Los items se agregarán dinámicamente con JavaScript -->
                </div>
                <div class="order-total">
                    <strong>Total: $<span id="order-total">0.00</span></strong>
                </div>
                
                <div class="form-group">
                    <label for="notes">Notas:</label>
                    <textarea id="notes" name="notes"></textarea>
                </div>
                
                <div class="form-group">
                    <label for="payment-method">Método de Pago:</label>
                    <select id="payment-method" name="payment_method" required>
                        <option value="">Seleccione...</option>
                        <option value="Efectivo">Efectivo</option>
                        <option value="Tarjeta">Tarjeta</option>
                    </select>
                </div>
                
                <button type="submit" class="btn btn-primary">Confirmar Pedido</button>
            </div>
        </form>
    </div>
    
    <script src="${pageContext.request.contextPath}/js/pedidos.js"></script>
    <%@include file="../../../WEB-INF/templates/footer.jsp" %>
</body>
</html>
