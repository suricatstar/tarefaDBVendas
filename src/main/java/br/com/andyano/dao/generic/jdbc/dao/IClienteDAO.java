package br.com.andyano.dao.generic.jdbc.dao;

import br.com.andyano.domain.Cliente;

import java.util.List;

/**
 * @author anderson.salviano
 * Criado em: 15/10/2025
 */
public interface IClienteDAO {

    public Integer cadastrar(Cliente cliente) throws  Exception;

    public Integer atualizar(Cliente cliente)  throws  Exception;

    public Cliente buscar(String codigo) throws Exception;

    public List<Cliente> buscarTodos() throws Exception;

    public Integer excluir(Cliente cliente) throws Exception;
}
