package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.EmprestimoDAO;
import model.EmprestimoModel;
import util.LocalDateTimeAdapter;

import java.time.LocalDateTime;
import java.util.List;

import static spark.Spark.*;

public class EmprestimoController {

    private static final EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    private static final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
        .create();


    public static void rotas() {

        // CREATE - CRIAR EMPRÉSTIMO (regra: data atual)
        post("/emprestimos", (req, res) -> {
            EmprestimoModel e = gson.fromJson(req.body(), EmprestimoModel.class);

            System.out.println("ID Cliente recebido: " + e.getid_cliente());
    System.out.println("ID Livro recebido: " + e.getid_livro());

            emprestimoDAO.inserir(e);
            res.status(201);
            return "Empréstimo registrado com sucesso!";
        });

        // READ - LISTAR TODOS
        get("/emprestimos", (req, res) -> {
            List<EmprestimoModel> lista = emprestimoDAO.listar();
            res.type("application/json");
            return gson.toJson(lista);
        });

        // READ - POR ID
        get("/emprestimos/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            EmprestimoModel e = emprestimoDAO.buscarPorId(id);

            if (e == null) {
                res.status(404);
                return "Empréstimo não encontrado!";
            }

            return gson.toJson(e);
        });

        // UPDATE (DEVOLUÇÃO)
        put("/emprestimos/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            EmprestimoModel e = emprestimoDAO.buscarPorId(id);

            if (e == null) {
                res.status(404);
                return "Empréstimo não encontrado!";
            }

            emprestimoDAO.atualizar(e);

            return "Devolução registrada com sucesso!";
        });

        // DELETE
        delete("/emprestimos/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            emprestimoDAO.deletar(id);
            return "Empréstimo removido com sucesso!";
        });
    }
}

