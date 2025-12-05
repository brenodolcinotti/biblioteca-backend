package model;

public class LivroModel {

    private int id;
    private String nome;
    private String autor;

    // Construtor vazio
    public LivroModel() {}

    // Construtor com parâmetros
    public LivroModel(int id, String nome, String autor) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
