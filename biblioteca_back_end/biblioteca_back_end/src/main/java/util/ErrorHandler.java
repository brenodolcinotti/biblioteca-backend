package util;

import static spark.Spark.*;
import com.google.gson.Gson;

public class ErrorHandler {

    private static final Gson gson = new Gson();

    public static void configure() {

        // 404 NOT FOUND
        notFound((req, res) -> {
            res.type("application/json");
            return gson.toJson(new HttpResponse(false, "Rota não encontrada", null));
        });

        // 500 INTERNAL SERVER ERROR
        internalServerError((req, res) -> {
            res.type("application/json");
            return gson.toJson(new HttpResponse(false, "Erro interno do servidor", null));
        });

        // Tratamento global de exceções
        exception(Exception.class, (e, req, res) -> {
            res.status(500);
            res.type("application/json");
            res.body(gson.toJson(
                new HttpResponse(false, "Erro: " + e.getMessage(), null)
            ));
            e.printStackTrace(); // log no console
        });

        // Configuração padrão de content type
        before((req, res) -> {
            res.type("application/json");
        });
    }
}
