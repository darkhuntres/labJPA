<%@page import="Entities.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="Controllers.CategoriaJpaController"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../layouts/header.jsp"/>

<div class="container mt-3">
    <form action="/Laboratorio01/JuegoServlet?action=create" method="post">
        <div class="mb-3">
            <label for="nombre">Nombre:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>
        <div class="mb-3">
            <label for="categoria">Categoría: </label>
            <select class="form-control" id="categoria" name="categoria" required>
                 <option value="" selected>Selecciona una opción</option>
                <c:forEach var="categoria" items="${categorias}">
                    <option value="${categoria.getIdcategoria()}">${categoria.getCategoria()}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="precio">Precio: </label>
            <input type="number" class="form-control" id="precio" min="0.00" step="0.01" name="precio" required>
        </div>
        <div class="mb-3">
            <label for="existencias">Existencias:</label>
            <input type="number" class="form-control" id="existencias" name="existencias" min="0" step="1" required>
        </div>
        
       <div class="mb-3">
            <label for="imagen">Direccion de la imagen: </label>
            <input type="text" class="form-control" id="imagen" name="imagen" required>
        </div>
        
        <div class="mb-3">
            <label for="clasificacion">Clasificación</label>
                <select class="form-control" id="clasificacion" name="clasificacion" required>
                    <option value="" selected>Selecciona una opción</option>
                    <option value="EC">Early Childhood</option>
                    <option value="E">Everyone</option>
                    <option value="10+E">Everyone 10+</option>
                    <option value="T">Teen</option>
                    <option value="M">Mature 17+</option>
                    <option value="AO">Adults only 18+</option>
                    <option value="RP">Rating pending</option>
                </select>
        </div>

        <div class="mb-3 row mt-3">
                <div class="offset-sm-5 col-sm-8">
                    <button type="submit" class="btn btn-primary">Agregar</button>
                    <a href="/Laboratorio01/JuegoServlet" class="btn btn-secondary "> Regresar</a>
                </div>
            </div>
    </form>
</div>

<jsp:include page="../layouts/footer.jsp"/>
