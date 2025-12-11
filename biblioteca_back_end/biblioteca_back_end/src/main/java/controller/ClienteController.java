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
        .create();


    public static void rotas() {

        // CREATE
        post("/clientes", (req, res) -> {
            ClienteModel cliente = gson.fromJson(req.body(), ClienteModel.class);
            clienteDAO.inserir(cliente);
            res.status(201);
            return "Cliente cadastrado com sucesso!";
        });

        // READ - LISTAR TODOS
        get("/clientes", (req, res) -> {
            List<ClienteModel> clientes = clienteDAO.listar();
            res.type("application/json");
            return gson.toJson(clientes);
        });

        // READ - POR ID
        get("/clientes/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            ClienteModel cliente = clienteDAO.buscarPorId(id);

            if (cliente == null) {
                res.status(404);
                return "Cliente não encontrado!";
            }

            return gson.toJson(cliente);
        });

        // UPDATE
        put("/clientes/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            ClienteModel cliente = gson.fromJson(req.body(), ClienteModel.class);
            cliente.setId(id);

            clienteDAO.atualizar(cliente);
            return "Cliente atualizado com sucesso!";
        });

        // DELETE
        delete("/clientes/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            clienteDAO.deletar(id);
            return "Cliente removido com sucesso!";
        });
    }
}