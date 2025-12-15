import controller.ClienteController;
import controller.LivroController;
import database.ConnectionFactory;
import controller.EmprestimoController;
import util.ErrorHandler;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        // Testa a conexão com o banco de dados
        ConnectionFactory.testarConexao();
    
        port(4567);

     // Em Main.java, substitua o bloco options("/*", ...)

options("/*", (request, response) -> {
    
    // 1. Permite a ORIGEM
    response.header("Access-Control-Allow-Origin", "*");
    
    // 2. Permite os MÉTODOS que o POST, PUT, etc. precisam
    response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    
    // 3. O PONTO CRÍTICO: Permite os headers que o front-end está enviando.
    // É essencial garantir que o Content-Type esteja na lista.
    String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
    if (accessControlRequestHeaders != null) {
        response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
    }
    
    // Adiciona explicitamente o Content-Type se ele não tiver vindo no request
    // (Ainda que o bloco 'if' acima deva capturar isso, este é um reforço robusto)
    response.header("Access-Control-Allow-Headers", "Content-Type, Authorization, Accept, X-Requested-With, " + (accessControlRequestHeaders != null ? accessControlRequestHeaders : ""));
    
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
