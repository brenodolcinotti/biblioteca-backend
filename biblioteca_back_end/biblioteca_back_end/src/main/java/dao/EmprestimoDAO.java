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
            INSERT INTO emprestimo 
            (id_cliente, id_livro, data_devolucao)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, e.getid_cliente());
            stmt.setInt(2, e.getid_livro());
            stmt.setString(3, e.getLocalDateDevolucao());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // READ - LISTAR TODOS
    public List<EmprestimoModel> listar() {
        List<EmprestimoModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EmprestimoModel e = new EmprestimoModel();
                e.setId(rs.getInt("id"));
                e.setid_cliente(rs.getInt("id_cliente"));
                e.setid_livro(rs.getInt("id_livro"));
                e.setLocalDateEmprestimo(rs.getString("data_emprestimo"));
                e.setLocalDateDevolucao(rs.getString("data_devolucao"));
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
                e.setid_cliente(rs.getInt("id_Cliente"));
                e.setid_livro(rs.getInt("id_Livros"));
                e.setLocalDateEmprestimo(rs.getString("localDate_emprestimo"));
                e.setLocalDateDevolucao(rs.getString("localDate_devolucao"));
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

            stmt.setInt(1, e.getid_cliente());
            stmt.setInt(2, e.getid_livro());
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
