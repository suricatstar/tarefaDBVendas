/**
 * 
 */
package main.java.br.com.rpires.services;

import main.java.br.com.rpires.dao.IClienteDAO;
import main.java.br.com.rpires.domain.Cliente;
import main.java.br.com.rpires.exceptions.DAOException;
import main.java.br.com.rpires.exceptions.MaisDeUmRegistroException;
import main.java.br.com.rpires.exceptions.TableException;
import main.java.br.com.rpires.exceptions.TipoChaveNaoEncontradaException;
import main.java.br.com.rpires.services.generic.GenericService;

/**
 * @author rodrigo.pires
 *
 */
public class ClienteService extends GenericService<Cliente, Long> implements IClienteService {
	
	//private IClienteDAO clienteDAO;
	
	public ClienteService(IClienteDAO clienteDAO) {
		super(clienteDAO);
		//this.clienteDAO = clienteDAO;
	}

//	@Override
//	public Boolean salvar(Cliente cliente) throws TipoChaveNaoEncontradaException {
//		return clienteDAO.cadastrar(cliente);
//	}

	@Override
	public Cliente buscarPorCPF(Long cpf) throws DAOException {
		try {
			return this.dao.consultar(cpf);
		} catch (MaisDeUmRegistroException | TableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

//	@Override
//	public void excluir(Long cpf) {
//		clienteDAO.excluir(cpf);
//	}
//
//	@Override
//	public void alterar(Cliente cliente) throws TipoChaveNaoEncontradaException{
//		clienteDAO.alterar(cliente);
//	}

}
