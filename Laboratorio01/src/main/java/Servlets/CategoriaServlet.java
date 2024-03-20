/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Controllers.CategoriaJpaController;
import Controllers.JuegoJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Entities.Categoria;
import Entities.Juego;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author martinsanabria
 */
@WebServlet(name = "CategoriaServlet", urlPatterns = {"/CategoriaServlet"})
public class CategoriaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CategoriaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoriaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           if(request.getParameter("action")!=null){
                if(request.getParameter("action").equals("edit")){
                int id=Integer.parseInt(request.getParameter("id"));
                CategoriaJpaController categorieController = new CategoriaJpaController();
                 //obtener los datos
                Categoria categorie = (Categoria) categorieController.findCategoria(id);
                request.setAttribute("categorieEdit", categorie);
                 //falta traer la persona de la base de datos
                 //y pasarlo como atributo
                RequestDispatcher dispatcher2=request.getRequestDispatcher("/Categories/edit.jsp");
                dispatcher2.forward(request,response);
                }
           }
           
           
           
           
            CategoriaJpaController categorieController = new CategoriaJpaController();
            //ir al modelo para acceder a los datos
            //obtener los datos
            List<Categoria> categorie = categorieController.findCategoriaEntities();
            
            //pasarlos a la vista
            request.setAttribute("categorias", categorie);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Categories/categorieView.jsp");

            // Envía la solicitud al dispatcher.
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); //
        }
        
           
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("action") != null){
            
            if(request.getParameter("action").equals("create")){
                
                  CategoriaJpaController categorieController = new CategoriaJpaController();
                 Categoria cat=new Categoria(request.getParameter("nombre"), request.getParameter("image"));
                
                try {
                    categorieController.create(cat);
                } catch (Exception ex) {
                    Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                 String successMessage = "Categoria agregada satisfactoriamente";

                request.setAttribute("successMessage", successMessage);
                 
            } else if(request.getParameter("action").equals("update")){
                
                int cateogireId=Integer.parseInt(request.getParameter("id"));
                CategoriaJpaController categorieController = new CategoriaJpaController();
                //obtener los datos
                Categoria categorie = (Categoria) categorieController.findCategoria(cateogireId);
                categorie.setCategoria(request.getParameter("nombre"));
                categorie.setImagenCat(request.getParameter("image"));
                try {
                    categorieController.edit(categorie);
                } catch (Exception ex) {
                    Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                 String successMessage = "Categoria actualizada satisfactoriamente";

                request.setAttribute("successMessage", successMessage);
                //ir al modelo para acceder a los datos
                //obtener los datos


           }else if(request.getParameter("action").equals("delete")){
                  int idcategoria = Integer.parseInt(request.getParameter("id"));
                CategoriaJpaController categorieController = new CategoriaJpaController();
                JuegoJpaController game = new JuegoJpaController();

                // Consultar productos por categoría
                List<Juego> games = game.findJuegoEntities();
                Categoria categoria = new Categoria();
                for (Juego gameD : games) {
                   int id = gameD.getIdcategoria().getIdcategoria();
                    if (idcategoria != id) {
                       try {
                           // No hay productos asociados, se puede eliminar la categoría
                           categorieController.destroy(idcategoria);
                       } catch (IllegalOrphanException ex) {
                           Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
                       } catch (NonexistentEntityException ex) {
                           Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
                       }
                    String successMessage = "Categoria eliminada satisfactoriamente";
                    request.setAttribute("successMessage", successMessage);
                } else if (idcategoria == id){
                    // Hay productos asociados, no se puede eliminar la categoría
                    String errorMessage = "Accion denegada, existen juegos en esta categoria.";
                    request.setAttribute("errorMessage", errorMessage);
                }
                }
               
            }
        }
            
       
          doGet(request,response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
