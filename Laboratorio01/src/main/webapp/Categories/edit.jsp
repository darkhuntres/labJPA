<%-- 
    Document   : edit
    Created on : 14 sep. 2023, 08:15:56
    Author     : martinsanabria
--%>

<%@page import="Entities.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../layouts/header.jsp"/>

<div class="container mt-3">
        <form method="post" action="CategoriaServlet?action=update&id=${categorieEdit.getIdcategoria()}">          
            <div class="mb-3 row">
                <label for="nombre" class="col-4 col-form-label">Nombre</label>
                <div class="col-8">
                    <input type="text" value="${categorieEdit.getCategoria()}" class="form-control" name="nombre" id="nombre" required>
                </div>
            </div>  
                
            <div class="mb-3 row">
                <label for="image" class="col-4 col-form-label">Imagen</label>
                <div class="col-8">
                    <input type="text" value="${categorieEdit.getImagencat()}" class="form-control" name="image" id="image" required>
                </div>
            </div>  
                
                
            <div class="mb-3 row">
                <div class="offset-sm-4 col-sm-8 mt-3">
                    <button type="submit" class="btn btn-primary">Actualizar</button>
                     <a href="/Laboratorio01/CategoriaServlet" class="btn btn-secondary "> Regresar</a>
                </div>
            </div>
        </form>
</div>
<jsp:include page="../layouts/footer.jsp"/>