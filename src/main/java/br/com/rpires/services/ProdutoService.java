/**
 * 
 */
package main.java.br.com.rpires.services;

import main.java.br.com.rpires.dao.IProdutoDAO;
import main.java.br.com.rpires.domain.Produto;
import main.java.br.com.rpires.services.generic.GenericService;

/**
 * @author rodrigo.pires
 *
 */
public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

	public ProdutoService(IProdutoDAO dao) {
		super(dao);
	}

}
