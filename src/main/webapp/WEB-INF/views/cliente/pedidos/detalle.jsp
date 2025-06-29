<%-- 
    Document   : detalle
    Created on : 29/06/2025, 9:24:09 a. m.
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalle del Pedido - ${appName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cliente.css">
</head>
<body>
    <%@include file="../../WEB-INF/templates/header.jsp" %>
    
    <div class="container">
        <h1>Detalle del Pedido #${pedido.pedido_id}</h1>
        
        <div class="card mb-4">
            <div class="card-header">
                <div class="row">
                    <div class="col-md-6">
                        <strong>Fecha:</strong> 
                        <fmt:formatDate value="${pedido.fecha_pedido}" pattern="dd/MM/yyyy HH:mm"/>
                    </div>
                    <div class="col-md-6 text-right">
                        <strong>Estado:</strong> 
                        <span class="badge 
                            ${pedido.estado.nombre == 'Pendiente' ? 'badge-warning' : 
                              pedido.estado.nombre == 'En preparación' ? 'badge-info' : 
                              pedido.estado.nombre == 'Listo' ? 'badge-success' : 'badge-secondary'}">
                            ${pedido.estado.nombre}
                        </span>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <h5 class="card-title">Productos</h5>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Producto</th>
                            <th>Cantidad</th>
                            <th>Precio Unitario</th>
                            <th>Subtotal</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${pedido.detalles}" var="detalle">
                            <tr>
                                <td>
                                    ${detalle.producto.nombre}
                                    <c:if test="${not empty detalle.personalizaciones}">
                                        <br><small class="text-muted">Personalizaciones: ${detalle.personalizaciones}</small>
                                    </c:if>
                                </td>
                                <td>${detalle.cantidad}</td>
                                <td><fmt:formatNumber value="${detalle.precio_unitario}" type="currency"/></td>
                                <td><fmt:formatNumber value="${detalle.cantidad * detalle.precio_unitario}" type="currency"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="3" class="text-right">Total:</th>
                            <th><fmt:formatNumber value="${pedido.total}" type="currency"/></th>
                        </tr>
                    </tfoot>
                </table>
                
                <div class="mt-3">
                    <h5>Información de Entrega</h5>
                    <p><strong>Dirección:</strong> ${pedido.direccion_entrega}</p>
                    <p><strong>Método de Pago:</strong> ${pedido.metodo_pago}</p>
                    <p><strong>Notas:</strong> ${empty pedido.notas ? 'Ninguna' : pedido.notas}</p>
                </div>
            </div>
        </div>
        
        <a href="${pageContext.request.contextPath}/cliente/pedidos" class="btn btn-secondary">Volver a Mis Pedidos</a>
    </div>
    
    <%@include file="../../WEB-INF/templates/footer.jsp" %>
</body>
</html>
