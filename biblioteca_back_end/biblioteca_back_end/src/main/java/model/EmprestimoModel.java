package model;

import java.time.LocalDateTime;

public class EmprestimoModel {

    private int id;
    private int idCliente;
    private int idLivro;
    private LocalDateTime localDateEmprestimo;
    private LocalDateTime localDateDevolucao;

    // Construtor vazio
    public EmprestimoModel() {}

    // Construtor com parâmetros
    public EmprestimoModel(int id, int idCliente, int idLivro,
                      LocalDateTime localDateEmprestimo,
                      LocalDateTime localDateDevolucao) {
        this.id = id;
        this.idCliente = idCliente;
        this.idLivro = idLivro;
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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public LocalDateTime getLocalDateEmprestimo() {
        return localDateEmprestimo;
    }

    public void setLocalDateEmprestimo(LocalDateTime localDateEmprestimo) {
        this.localDateEmprestimo = localDateEmprestimo;
    }

    public LocalDateTime getLocalDateDevolucao() {
        return localDateDevolucao;
    }

    public void setLocalDateDevolucao(LocalDateTime localDateDevolucao) {
        this.localDateDevolucao = localDateDevolucao;
    }
}
