package dao;

import database.ConnectionFactory;
import model.LivroModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    // CREATE
    public void inserir(LivroModel livro) {
        String sql = "INSERT INTO Livros (nome, autor) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getNome());
            stmt.setString(2, livro.getAutor());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ - LISTAR TODOS
    public List<LivroModel> listar() {
        List<LivroModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM Livros";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                LivroModel livro = new LivroModel();
                livro.setId(rs.getInt("id"));
                livro.setNome(rs.getString("nome"));
                livro.setAutor(rs.getString("autor"));
                lista.add(livro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // READ - POR ID
    public LivroModel buscarPorId(int id) {
        String sql = "SELECT * FROM Livros WHERE id = ?";
        LivroModel livro = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                livro = new LivroModel();
                livro.setId(rs.getInt("id"));
                livro.setNome(rs.getString("nome"));
                livro.setAutor(rs.getString("autor"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livro;
    }

    // UPDATE
    public void atualizar(LivroModel livro) {
        String sql = "UPDATE Livros SET nome = ?, autor = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getNome());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deletar(int id) {
        String sql = "DELETE FROM Livros WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
