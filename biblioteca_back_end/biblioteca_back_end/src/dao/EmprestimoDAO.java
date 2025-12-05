package dao;

import database.ConnectionFactory;
import model.EmprestimoModel;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    // CREATE
    public void inserir(EmprestimoModel e) {
        String sql = """
            INSERT INTO Emprestimo 
            (id_Cliente, id_Livros, localDate_emprestimo, localDate_devolucao)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, e.getIdCliente());
            stmt.setInt(2, e.getIdLivro());
            stmt.setTimestamp(3, Timestamp.valueOf(e.getLocalDateEmprestimo()));
            stmt.setTimestamp(4, Timestamp.valueOf(e.getLocalDateDevolucao()));

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // READ - LISTAR TODOS
    public List<EmprestimoModel> listar() {
        List<EmprestimoModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM Emprestimo";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EmprestimoModel e = new EmprestimoModel();
                e.setId(rs.getInt("id"));
                e.setIdCliente(rs.getInt("id_Cliente"));
                e.setIdLivro(rs.getInt("id_Livros"));
                e.setLocalDateEmprestimo(rs.getTimestamp("localDate_emprestimo").toLocalDateTime());
                e.setLocalDateDevolucao(rs.getTimestamp("localDate_devolucao").toLocalDateTime());
                lista.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    // READ - POR ID
    public EmprestimoModel buscarPorId(int id) {
        String sql = "SELECT * FROM Emprestimo WHERE id = ?";
        EmprestimoModel e = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                e = new EmprestimoModel();
                e.setId(rs.getInt("id"));
                e.setIdCliente(rs.getInt("id_Cliente"));
                e.setIdLivro(rs.getInt("id_Livros"));
                e.setLocalDateEmprestimo(rs.getTimestamp("localDate_emprestimo").toLocalDateTime());
                e.setLocalDateDevolucao(rs.getTimestamp("localDate_devolucao").toLocalDateTime());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return e;
    }

    // UPDATE
    public void atualizar(EmprestimoModel e) {
        String sql = """
            UPDATE Emprestimo 
            SET id_Cliente = ?, id_Livros = ?, localDate_emprestimo = ?, localDate_devolucao = ?
            WHERE id = ?
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, e.getIdCliente());
            stmt.setInt(2, e.getIdLivro());
            stmt.setTimestamp(3, Timestamp.valueOf(e.getLocalDateEmprestimo()));
            stmt.setTimestamp(4, Timestamp.valueOf(e.getLocalDateDevolucao()));
            stmt.setInt(5, e.getId());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // DELETE
    public void deletar(int id) {
        String sql = "DELETE FROM Emprestimo WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
