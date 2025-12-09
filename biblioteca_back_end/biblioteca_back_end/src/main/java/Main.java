import controller.ClienteController;
import controller.LivroController;
import controller.EmprestimoController;
import util.ErrorHandler;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        port(4567); // Porta padrão do Spark

        // Inicializar rotas dos controllers
        LivroController.rotas();
        ClienteController.rotas();
        EmprestimoController.rotas();

        // Configurar tratamento de erros
        ErrorHandler.configure();

        System.out.println("✅ Servidor rodando em http://localhost:4567");
    }
}
