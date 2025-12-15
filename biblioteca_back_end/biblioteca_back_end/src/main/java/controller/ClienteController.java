package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.ClienteDAO;
import model.ClienteModel;
import java.util.List;

import static spark.Spark.*;

public class ClienteController {

    private static final ClienteDAO clienteDAO = new ClienteDAO();
    private static final Gson gson = new GsonBuilder()
        .setPrettyPrinting() // Adicionado para melhor visualização
        .create();


    public static void rotas() {
        
        // 1. PREFIXAÇÃO DA API: Todas as rotas usam o prefixo /api
            
            // 2. CONFIGURAÇÃO CORS (OPTIONS): Trata o PREFLIGHT (requisições OPTIONS)
            // ESTE BLOCO DEVE SER O ÚNICO A TRATAR A REQUISIÇÃO OPTIONS
            options("/*", (request, response) -> {
                
                // Define os métodos e cabeçalhos permitidos para a requisição real
                response.header("Access-Control-Allow-Origin", "*");
                response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                
                String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
                if (accessControlRequestHeaders != null) {
                    response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
                } else {
                    // Reforço de cabeçalhos essenciais (Content-Type)
                    response.header("Access-Control-Allow-Headers", "Content-Type, Authorization, Accept"); 
                }
                
                return "OK";
            });

            // 3. FILTRO BEFORE DA API: Aplica CORS e Content-Type SOMENTE PARA REQUISIÇÕES REAIS
            before("/*", (request, response) -> {
                // IGNORA O FILTRO SE FOR OPTIONS, EVITANDO DUPLICAÇÃO DE HEADERS CORS
                if (request.requestMethod().equalsIgnoreCase("OPTIONS")) {
                    return; 
                }
                
                // Aplica os cabeçalhos CORS e tipo de resposta para GET, POST, PUT, DELETE
                response.header("Access-Control-Allow-Origin", "*");
                response.type("application/json");
            });

            // CREATE -> POST /api/clientes
            post("/clientes", (req, res) -> {
                try {
                    ClienteModel cliente = gson.fromJson(req.body(), ClienteModel.class);
                    clienteDAO.inserir(cliente);
                    res.status(201);
                    return gson.toJson(cliente); // Retorna o objeto em JSON para seguir o padrão REST
                } catch (Exception e) {
                    res.status(400);
                    return "{\"mensagem\": \"Erro ao processar cliente.\"}";
                }
            });

            // READ - LISTAR TODOS -> GET /api/clientes
            get("/clientes", (req, res) -> {
                List<ClienteModel> clientes = clienteDAO.listar();
                return gson.toJson(clientes);
            });

            // READ - POR ID -> GET /api/clientes/:id
            get("/clientes/:id", (req, res) -> {
                try {
                    int id = Integer.parseInt(req.params(":id"));
                    ClienteModel cliente = clienteDAO.buscarPorId(id);

                    if (cliente == null) {
                        res.status(404);
                        return "{\"mensagem\": \"Cliente não encontrado!\"}";
                    }

                    return gson.toJson(cliente);
                } catch (NumberFormatException e) {
                    res.status(400);
                    return "{\"mensagem\": \"ID inválido.\"}";
                }
            });

            // UPDATE -> PUT /api/clientes/:id
            put("/clientes/:id", (req, res) -> {
                try {
                    int id = Integer.parseInt(req.params(":id"));
                    ClienteModel cliente = gson.fromJson(req.body(), ClienteModel.class);
                    cliente.setId(id);

                    clienteDAO.atualizar(cliente);
                    return "{\"mensagem\": \"Cliente atualizado com sucesso!\"}";
                } catch (Exception e) {
                    res.status(400);
                    return "{\"mensagem\": \"Erro ao atualizar cliente.\"}";
                }
            });

            // DELETE -> DELETE /api/clientes/:id
            delete("/clientes/:id", (req, res) -> {
                try {
                    int id = Integer.parseInt(req.params(":id"));
                    clienteDAO.deletar(id);
                    res.status(204);
                    return ""; // 204 No Content retorna vazio
                } catch (Exception e) {
                    res.status(500);
                    return "{\"mensagem\": \"Erro ao remover cliente.\"}";
                }
            }); // Fim do path("/api")
    }
}