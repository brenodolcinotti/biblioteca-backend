package model;

import java.time.LocalDateTime;

public class EmprestimoModel {

    private int id;
    private int id_cliente;
    private int id_livro;
    private String localDateEmprestimo;
    private String localDateDevolucao;

    // Construtor vazio
    public EmprestimoModel() {}

    // Construtor com parâmetros
    public EmprestimoModel(int id, int id_cliente, int id_livro,
                      String localDateEmprestimo,
                      String localDateDevolucao) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.id_livro = id_livro;
        this.localDateEmprestimo = localDateEmprestimo;
        this.localDateDevolucao = localDateDevolucao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getid_cliente() {
        return id_cliente;
    }

    public void setid_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getid_livro() {
        return id_livro;
    }

    public void setid_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public String getLocalDateEmprestimo() {
        return localDateEmprestimo;
    }

    public void setLocalDateEmprestimo(String localDateEmprestimo) {
        this.localDateEmprestimo = localDateEmprestimo;
    }

    public String getLocalDateDevolucao() {
        return localDateDevolucao;
    }

    public void setLocalDateDevolucao(String localDateDevolucao) {
        this.localDateDevolucao = localDateDevolucao;
    }
}
