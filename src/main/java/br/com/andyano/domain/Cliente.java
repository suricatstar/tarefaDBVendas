package main.java.br.com.andyano.domain;

/**
 * @author anderson.salviano
 * Criado em: 15/10/2025
 */
public class Cliente {

    private Long id;
    private String codigo;
    private String nome;
    private String num_telefone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNum_telefone() {
        return num_telefone;
    }

    public void setNum_telefone(String num_telefone) {
        this.num_telefone = num_telefone;
    }
}
