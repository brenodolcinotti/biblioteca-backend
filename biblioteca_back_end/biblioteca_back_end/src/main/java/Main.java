import controller.ClienteController;
import controller.LivroController;
import controller.EmprestimoController;
import util.ErrorHandler;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        port(4567); // Porta padrão do Spark

        // ===== CORS =====
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type,Authorization");
        });

        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });

        // Inicializar rotas dos controllers
        LivroController.rotas();
        ClienteController.rotas();
        EmprestimoController.rotas();

        // Configurar tratamento de erros
        ErrorHandler.configure();

        System.out.println("Servidor rodando em http://localhost:4567");
    }
}
