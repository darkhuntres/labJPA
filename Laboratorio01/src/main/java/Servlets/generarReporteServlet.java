import Controllers.JuegoJpaController;
import Entities.Juego;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "generarReporteServlet", urlPatterns = {"/GenerarReporteServlet"})
public class generarReporteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/pdf");
        try {
            ServletOutputStream out = response.getOutputStream();

            // Cargar datos desde la base de datos
            JuegoJpaController juegoController = new JuegoJpaController();
            List<Juego> juegos = juegoController.findJuegoEntities();

            // Crear una fuente de datos para JasperReports
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(juegos);

            // Parámetros opcionales, si los tienes
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("titulo", "Reporte de Juegos");

            // Ruta al archivo compilado .jasper
            String reportPath = getServletContext().getRealPath("../reportes/reporteprueba.jasper");

            // Generar el reporte en PDF
            byte[] bytes = JasperRunManager.runReportToPdf(reportPath, parametros, dataSource);

            // Escribir el PDF en la respuesta
            response.setContentLength(bytes.length);
            out.write(bytes, 0, bytes.length);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Generador de reportes en PDF";
    }
}
