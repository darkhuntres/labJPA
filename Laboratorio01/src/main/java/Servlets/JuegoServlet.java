/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Controllers.CategoriaJpaController;
import Controllers.JuegoJpaController;
import Controllers.exceptions.NonexistentEntityException;
import Entities.Categoria;
import Entities.Juego;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "JuegoServlet", urlPatterns = {"/JuegoServlet"})
public class JuegoServlet extends HttpServlet {

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
            out.println("<title>Servlet JuegoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet JuegoServlet at " + request.getContextPath() + "</h1>");
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
                int idproducto=Integer.parseInt(request.getParameter("id"));
               JuegoJpaController game = new JuegoJpaController();
                 //obtener los datos
                Juego games = (Juego) game.findJuego(idproducto);
                request.setAttribute("gameEdit", games);
               
                 CategoriaJpaController categorieController = new CategoriaJpaController();
                    //ir al modelo para acceder a los datos
                    //obtener los datos

                List<Categoria> categorias = categorieController.findCategoriaEntities();


                request.setAttribute("categorias", categorias);
                 //falta traer la persona de la base de datos
                 //y pasarlo como atributo
                RequestDispatcher dispatcher2=request.getRequestDispatcher("/Games/edit.jsp");
                dispatcher2.forward(request,response);
                } else if (request.getParameter("action").equals("new")){
                    
                    CategoriaJpaController categorieController = new CategoriaJpaController();
                    //ir al modelo para acceder a los datos
                    //obtener los datos

                    List<Categoria> categorias = categorieController.findCategoriaEntities();

                    request.setAttribute("categorias", categorias);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Games/create.jsp");

            // Envía la solicitud al dispatcher.
            dispatcher.forward(request, response);
                }
           }
           
           
           JuegoJpaController game = new JuegoJpaController();
            CategoriaJpaController categorieController = new CategoriaJpaController();
            //ir al modelo para acceder a los datos
            //obtener los datos
            List<Juego> games = game.findJuegoEntities();

         Map<Integer, Map<String, String>> productosData = new HashMap<>();

        for (Juego gameD : games) {
            // Obtener nombre de categoría
            
            Categoria categoria = categorieController.findCategoria(gameD.getIdcategoria().getIdcategoria());
            String nombreCategoria = categoria.getCategoria();

            Map<String, String> productoData = new HashMap<>();
            productoData.put("nombreCategoria", nombreCategoria);

            productosData.put(gameD.getIdjuego(), productoData);
        }

        request.setAttribute("productosData", productosData);
        request.setAttribute("productos", games);


            RequestDispatcher dispatcher = request.getRequestDispatcher("/Games/gameView.jsp");

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
                JuegoJpaController game = new JuegoJpaController();
                CategoriaJpaController categorieController = new CategoriaJpaController();
                if(!request.getParameter("categoria").isEmpty()){
                    Categoria catego = categorieController.findCategoria(Integer.parseInt(request.getParameter("categoria")));
                               
                                
                                Juego productoNuevo = new Juego();
                                productoNuevo.setNomJuego(request.getParameter("nombre"));
                                productoNuevo.setIdcategoria(catego);
                                productoNuevo.setPrecio(Float.parseFloat(request.getParameter("precio")));
                                productoNuevo.setExistencias(Integer.parseInt(request.getParameter("existencias")));
                                productoNuevo.setImagen(request.getParameter("imagen"));
                                productoNuevo.setClasificacion(request.getParameter("clasificacion"));
                                
                            game.create(productoNuevo);

                            String successMessage = "Juego agregado satisfactoriamente";

                            request.setAttribute("successMessage", successMessage);
                       
                     
                } else {
                    String errorMessage = "Error de seleccion de categorias";

                    request.setAttribute("errorMessage", errorMessage);
                }
               
                
            } else if(request.getParameter("action").equals("update")){
                
                int idproductos=Integer.parseInt(request.getParameter("id"));
                JuegoJpaController game = new JuegoJpaController();
                Juego dataGame = game.findJuego(idproductos);
                CategoriaJpaController categorieController = new CategoriaJpaController();
                if(!request.getParameter("categoria").isEmpty()){
                    
                        Categoria catego = categorieController.findCategoria(Integer.parseInt(request.getParameter("categoria")));
        
                                dataGame.setIdcategoria(catego);
                                dataGame.setNomJuego(request.getParameter("nombre"));
                                dataGame.setPrecio(Float.parseFloat(request.getParameter("precio")));
                                dataGame.setExistencias(Integer.parseInt(request.getParameter("existencias")));
                                dataGame.setImagen(request.getParameter("imagen"));
                                dataGame.setClasificacion(request.getParameter("clasificacion"));

                    try {
                        game.edit(dataGame);
                    } catch (Exception ex) {
                        Logger.getLogger(JuegoServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                            String successMessage = "Juego actualizado satisfactoriamente";

                            request.setAttribute("successMessage", successMessage);
                     
                } else {
                    String errorMessage = "Error de seleccion de categorias";

                    request.setAttribute("errorMessage", errorMessage);
                }

           }else if(request.getParameter("action").equals("delete")){
            int idproducto=Integer.parseInt(request.getParameter("id"));
           
            JuegoJpaController game = new JuegoJpaController();
            
                try {
                    game.destroy(idproducto);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(JuegoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            String successMessage = "Juego Eliminado satisfactoriamente";
                 
            request.setAttribute("successMessage", successMessage);
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
