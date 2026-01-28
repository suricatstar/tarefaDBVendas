
package main.java.br.com.andyano.dao.generic.jdbc.dao;

import main.java.br.com.andyano.domain.Produto;

import java.util.List;

/**
 * @author anderson.salviano
 *         Criado em: 15/10/2025
 */
public interface IProdutoDAO {

    public Integer cadastrar(Produto produto) throws Exception;

    public Integer atualizar(Produto produto) throws Exception;

    public Produto buscar(String codigo) throws Exception;

    public List<Produto> buscarTodos() throws Exception;

    public Integer excluir(Produto produto) throws Exception;
}
