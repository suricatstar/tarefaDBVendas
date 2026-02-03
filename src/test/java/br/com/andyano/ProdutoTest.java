package test.java.br.com.andyano;

import main.java.br.com.andyano.dao.generic.jdbc.dao.IProdutoDAO;
import main.java.br.com.andyano.dao.generic.jdbc.dao.ProdutoDAO;
import main.java.br.com.andyano.domain.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author caue.tamiarana
 *         Criado em: 28/01/2026
 */
public class ProdutoTest {

    private IProdutoDAO produtoDAO;

    @Test
    public void deveCadastrarBDTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("P01");
        produto.setNome("Notebook Dell");
        produto.setDescricao("Notebook Dell Inspiron 15");
        produto.setValor(3500.00);

        Integer countCad = produtoDAO.cadastrar(produto);
        assertTrue(countCad == 1);

        Produto produtoBD = produtoDAO.buscar("P01");
        Assert.assertNotNull(produtoBD);
        assertEquals("Devem ter o mesmo código", produto.getCodigo(), produtoBD.getCodigo());
        assertEquals("Devem ter o mesmo nome", produto.getNome(), produtoBD.getNome());
        assertEquals("Devem ter a mesma descrição", produto.getDescricao(), produtoBD.getDescricao());
        assertEquals("Devem ter o mesmo valor", produto.getValor(), produtoBD.getValor());
        assertEquals("Devem ter o mesmo estoque", produto.getEstoque(), produtoBD.getEstoque());

        Integer countDel = produtoDAO.excluir(produtoBD);
        assertTrue(countDel == 1);
    }

    @Test
    public void deveBuscarProdutoTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("P01");
        produto.setNome("Mouse Logitech");
        produto.setDescricao("Mouse sem fio Logitech MX Master");
        produto.setValor(350.00);

        Integer countCad = produtoDAO.cadastrar(produto);
        assertTrue(countCad == 1);

        Produto produtoBD = produtoDAO.buscar("P01");
        Assert.assertNotNull(produtoBD);
        assertEquals("Devem ter o mesmo código", produto.getCodigo(), produtoBD.getCodigo());
        assertEquals("Devem ter o mesmo nome", produto.getNome(), produtoBD.getNome());
        assertEquals("Devem ter o mesmo estoque", produto.getEstoque(), produtoBD.getEstoque());

        Integer countDel = produtoDAO.excluir(produtoBD);
        assertTrue(countDel == 1);
    }

    @Test
    public void deveExcluirProdutoTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("P01");
        produto.setNome("Teclado Mecânico");
        produto.setDescricao("Teclado mecânico RGB");
        produto.setValor(450.00);

        Integer countCad = produtoDAO.cadastrar(produto);
        assertTrue(countCad == 1);

        Produto produtoBD = produtoDAO.buscar("P01");
        Assert.assertNotNull(produtoBD);
        assertEquals("Devem ter o mesmo código", produto.getCodigo(), produtoBD.getCodigo());
        assertEquals("Devem ter o mesmo nome", produto.getNome(), produtoBD.getNome());
        assertEquals("Devem ter o mesmo estoque", produto.getEstoque(), produtoBD.getEstoque());

        Integer countDel = produtoDAO.excluir(produtoBD);
        assertTrue(countDel == 1);
    }

    @Test
    public void deveRetornarTodosTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto1 = new Produto();
        produto1.setCodigo("P01");
        produto1.setNome("Monitor LG");
        produto1.setDescricao("Monitor LG 24 polegadas");
        produto1.setValor(800.00);
        Integer countCad1 = produtoDAO.cadastrar(produto1);
        assertTrue(countCad1 == 1);

        Produto produto2 = new Produto();
        produto2.setCodigo("P02");
        produto2.setNome("Webcam Logitech");
        produto2.setDescricao("Webcam Full HD");
        produto2.setValor(250.00);
        Integer countCad2 = produtoDAO.cadastrar(produto2);
        assertTrue(countCad2 == 1);

        List<Produto> lista = produtoDAO.buscarTodos();
        Assert.assertNotNull(lista);
        assertEquals(2, lista.size());

        int countDel = 0;
        for (Produto prod : lista) {
            produtoDAO.excluir(prod);
            countDel++;
        }
        assertEquals(lista.size(), countDel);

        lista = produtoDAO.buscarTodos();
        assertEquals(0, lista.size());
    }

    @Test
    public void deveAtualizarProdutoTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("P01");
        produto.setNome("Headset Gamer");
        produto.setDescricao("Headset com microfone");
        produto.setValor(300.00);
        Integer countCad = produtoDAO.cadastrar(produto);
        assertTrue(countCad == 1);

        // Busca produto no banco de dados e testa se o retorno não é nulo
        Produto produtoBD = produtoDAO.buscar("P01");
        Assert.assertNotNull(produtoBD);
        assertEquals("Devem ter o mesmo código", produto.getCodigo(), produtoBD.getCodigo());
        assertEquals("Devem ter o mesmo nome", produto.getNome(), produtoBD.getNome());
        assertEquals("Devem ter o mesmo estoque", produto.getEstoque(), produtoBD.getEstoque());

        // Atualiza os dados do produto
        produtoBD.setCodigo("P02");
        produtoBD.setNome("Headset Profissional");
        produtoBD.setDescricao("Headset profissional com cancelamento de ruído");
        produtoBD.setValor(500.00);

        Integer countAtt = produtoDAO.atualizar(produtoBD);
        assertTrue(countAtt == 1);

        // Busca novamente o produto com o código antigo para verificar que não existe
        // mais
        Produto produtoBD2 = produtoDAO.buscar("P01");
        Assert.assertNull("Deve retornar nulo: código foi atualizado", produtoBD2);

        // Busca com o novo código
        produtoBD2 = produtoDAO.buscar("P02");
        Assert.assertNotNull(produtoBD2);
        assertEquals("Devem ter o mesmo código", produtoBD.getCodigo(), produtoBD2.getCodigo());
        assertEquals("Devem ter o mesmo nome", produtoBD.getNome(), produtoBD2.getNome());
        assertEquals("Devem ter a mesma descrição", produtoBD.getDescricao(), produtoBD2.getDescricao());
        assertEquals("Devem ter o mesmo valor", produtoBD.getValor(), produtoBD2.getValor());
        assertEquals("Devem ter o mesmo id", produtoBD.getId(), produtoBD2.getId());

        // Limpa o banco de dados
        List<Produto> produtos = produtoDAO.buscarTodos();
        for (Produto prod : produtos) {
            produtoDAO.excluir(prod);
        }
    }
}
