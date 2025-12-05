package controller;

import com.google.gson.Gson;
import dao.LivroDAO;
import model.Livro;

import java.util.List;

import static spark.Spark.*;

public class LivroController {

    private static final LivroDAO livroDAO = new LivroDAO();
    private static final Gson gson = new Gson();

    public static void rotas() {

        // CREATE
        post("/livros", (req, res) -> {
            Livro livro = gson.fromJson(req.body(), Livro.class);
            livroDAO.inserir(livro);
            res.status(201);
            return "Livro cadastrado com sucesso!";
        });

        // READ - LISTAR TODOS
        get("/livros", (req, res) -> {
            List<Livro> livros = livroDAO.listar();
            res.type("application/json");
            return gson.toJson(livros);
        });

        // READ - POR ID
        get("/livros/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Livro livro = livroDAO.buscarPorId(id);

            if (livro == null) {
                res.status(404);
                return "Livro não encontrado!";
            }

            return gson.toJson(livro);
        });

        // UPDATE
        put("/livros/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Livro livro = gson.fromJson(req.body(), Livro.class);
            livro.setId(id);

            livroDAO.atualizar(livro);
            return "Livro atualizado com sucesso!";
        });

        // DELETE
        delete("/livros/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            livroDAO.deletar(id);
            return "Livro removido com sucesso!";
        });
    }
}
