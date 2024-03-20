<%@page import="Entities.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="Controllers.CategoriaJpaController"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="../layouts/header.jsp"/>


<div class="container mt-3">
    <form action="/Laboratorio01/JuegoServlet?action=update&id=${gameEdit.getIdjuego()}" method="post">
        <div class="mb-3">
            <label for="nombre">Nombre:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="${gameEdit.getNomJuego()}" required>
        </div>
       <div class="mb-3">
            <label for="categoria">Categoría: </label>
            <select class="form-control" id="categoria" name="categoria" required>
                <option value="" selected>Selecciona una opción</option>
                <c:forEach var="categoria" items="${categorias}">
                    <c:choose>
                        <c:when test="${categoria.getIdcategoria() == gameEdit.getIdcategoria().getIdcategoria()}">
                            <option value="${categoria.getIdcategoria()}" selected>${categoria.getCategoria()}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${categoria.getIdcategoria()}">${categoria.getCategoria()}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
      
        <div class="mb-3">
            <label for="precio">Precio: </label>
            <input type="number" class="form-control" id="precio" min="0.00" step="0.01" name="precio" value="${gameEdit.getPrecio()}" required>
        </div>
       

           
        <div class="mb-3">
            <label for="existencias">Existencias: </label>
            <input type="number" class="form-control" id="existencias" name="existencias" min="0" step="1" value="${gameEdit.getExistencias()}" required>
        </div>
        
       <div class="mb-3">
            <label for="imagen">Imagen: </label>
            <input type="text" class="form-control" id="imagen" name="imagen" value="${gameEdit.getImagen()}" required>
        </div>
        
        <div class="mb-3">
            <label for="clasificacion">Clasificación:</label>
            <select class="form-control" id="clasificacion" name="clasificacion" required>
                <c:choose>
                    <c:when test="${gameEdit.getClasificacion().equals('EC')}">
                        <option value="EC" selected>Early Childhood</option>
                    </c:when>
                    <c:otherwise>
                        <option value="EC">Early Childhood</option>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${gameEdit.getClasificacion().equals('E')}">
                        <option value="E" selected>Everyone</option>
                    </c:when>
                    <c:otherwise>
                        <option value="E">Everyone</option>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${gameEdit.getClasificacion().equals('10+E')}">
                        <option value="10+E" selected>Everyone 10+</option>
                    </c:when>
                    <c:otherwise>
                        <option value="10+E">Everyone 10+</option>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${gameEdit.getClasificacion().equals('T')}">
                        <option value="T" selected>Teen</option>
                    </c:when>
                    <c:otherwise>
                        <option value="T">Teen</option>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${gameEdit.getClasificacion().equals('M')}">
                        <option value="M" selected>Mature 17+</option>
                    </c:when>
                    <c:otherwise>
                        <option value="M">Mature 17+</option>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${gameEdit.getClasificacion().equals('AO')}">
                        <option value="AO" selected>Adults only 18+</option>
                    </c:when>
                    <c:otherwise>
                        <option value="AO">Adults only 18+</option>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${gameEdit.getClasificacion().equals('RP')}">
                        <option value="RP" selected>Rating pending</option>
                    </c:when>
                    <c:otherwise>
                        <option value="RP">Rating pending</option>
                    </c:otherwise>
                </c:choose>
            </select>
        </div>
         

        <div class="mb-3 row mt-3">
                <div class="offset-sm-5 col-sm-8">
                    <button type="submit" class="btn btn-primary">Actualizar</button>
                    <a href="/Laboratorio01/JuegoServlet" class="btn btn-secondary "> Regresar</a>
                </div>
            </div>
    </form>
</div>

<jsp:include page="../layouts/footer.jsp"/>
