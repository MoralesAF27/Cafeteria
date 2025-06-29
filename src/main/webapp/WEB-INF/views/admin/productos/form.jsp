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
    <title>${empty producto.producto_id ? 'Agregar' : 'Editar'} Producto</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
    <%@include file="../../WEB-INF/templates/header.jsp" %>
    <%@include file="../../WEB-INF/templates/navigation.jsp" %>
    
    <div class="container">
        <h1>${empty producto.producto_id ? 'Agregar' : 'Editar'} Producto</h1>
        
        <form action="${pageContext.request.contextPath}/producto" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="${empty producto.producto_id ? 'insert' : 'update'}">
            <c:if test="${not empty producto.producto_id}">
                <input type="hidden" name="id" value="${producto.producto_id}">
            </c:if>
            
            <div class="form-group">
                <label>Nombre:</label>
                <input type="text" name="nombre" value="${producto.nombre}" class="form-control" required>
            </div>
            
            <div class="form-group">
                <label>Categoría:</label>
                <select name="categoria_id" class="form-control" required>
                    <option value="">Seleccione una categoría</option>
                    <c:forEach items="${categorias}" var="categoria">
                        <option value="${categoria.categoria_id}" ${producto.categoria_id == categoria.categoria_id ? 'selected' : ''}>
                            ${categoria.nombre}
                        </option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <label>Descripción:</label>
                <textarea name="descripcion" class="form-control">${producto.descripcion}</textarea>
            </div>
            
            <div class="form-group">
                <label>Precio:</label>
                <input type="number" name="precio" value="${producto.precio}" step="0.01" min="0" class="form-control" required>
            </div>
            
            <div class="form-group">
                <label>Imagen:</label>
                <input type="file" name="imagen" class="form-control-file">
                <c:if test="${not empty producto.imagenUrl}">
                    <img src="${pageContext.request.contextPath}/images/productos/${producto.imagenUrl}" 
                         alt="${producto.nombre}" width="100" class="mt-2">
                </c:if>
            </div>
            
            <div class="form-check mb-3">
                <input type="checkbox" name="disponible" class="form-check-input" id="disponible" 
                       ${producto.disponible ? 'checked' : ''}>
                <label class="form-check-label" for="disponible">Disponible</label>
            </div>
            
            <div class="form-check mb-3">
                <input type="checkbox" name="personalizable" class="form-check-input" id="personalizable" 
                       ${producto.personalizable ? 'checked' : ''}>
                <label class="form-check-label" for="personalizable">Personalizable</label>
            </div>
            
            <button type="submit" class="btn btn-primary">Guardar</button>
            <a href="${pageContext.request.contextPath}/producto" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
    
    <%@include file="../../WEB-INF/templates/footer.jsp" %>
</body>
</html>
