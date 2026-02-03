package main.java.br.com.andyano.dao.generic.jdbc.dao;

import main.java.br.com.andyano.dao.generic.jdbc.ConnectionFactory;
import main.java.br.com.andyano.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author anderson.salviano
 *         Criado em: 15/10/2025
 */
public class ProdutoDAO implements IProdutoDAO {

    @Override
    public Integer cadastrar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlInsert();
            stm = connection.prepareStatement(sql);
            adicionarParametrosInsert(stm, produto);

            return stm.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Erro ao cadastrar o produto no banco de dados.", e);
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public Integer atualizar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlUpdate();
            stm = connection.prepareStatement(sql);
            adicionarParametrosUpdate(stm, produto);
            return stm.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Erro ao atualizar o produto no banco de dados.", e);
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public Produto buscar(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Produto produto = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelect();
            stm = connection.prepareStatement(sql);
            adicionarParametrosSelect(stm, codigo);
            rs = stm.executeQuery();

            if (rs.next()) {
                produto = new Produto();
                Long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                String cd = rs.getString("CODIGO");
                String descricao = rs.getString("DESCRICAO");
                Double valor = rs.getDouble("VALOR");
                Integer estoque = rs.getInt("ESTOQUE");
                produto.setId(id);
                produto.setNome(nome);
                produto.setCodigo(cd);
                produto.setDescricao(descricao);
                produto.setValor(valor);
                produto.setEstoque(estoque);
            }

        } catch (SQLException e) {
            throw new Exception("Erro ao buscar o produto no banco de dados.", e);
        } finally {
            closeConnection(connection, stm, rs);
        }
        return produto;
    }

    @Override
    public List<Produto> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Produto> list = new ArrayList<>();
        Produto produto = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelectAll();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                produto = new Produto();
                Long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                String cd = rs.getString("CODIGO");
                String descricao = rs.getString("DESCRICAO");
                Double valor = rs.getDouble("VALOR");
                Integer estoque = rs.getInt("ESTOQUE");
                produto.setId(id);
                produto.setNome(nome);
                produto.setCodigo(cd);
                produto.setDescricao(descricao);
                produto.setValor(valor);
                produto.setEstoque(estoque);
                list.add(produto);
            }

        } catch (SQLException e) {
            throw new Exception("Erro ao buscar produtos no banco de dados.", e);
        } finally {
            closeConnection(connection, stm, rs);
        }
        return list;
    }

    @Override
    public Integer excluir(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlDelete();
            stm = connection.prepareStatement(sql);
            adicionarParametrosDelete(stm, produto);
            return stm.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Erro ao excluir o produto no banco de dados.", e);
        } finally {
            closeConnection(connection, stm, null);
        }
    }

    private String getSqlInsert() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO tb_produto (CODIGO, NOME, DESCRICAO, VALOR, ESTOQUE)");
        sb.append(" VALUES (?, ?, ?, ?, ?)");
        return sb.toString();
    }

    private void adicionarParametrosInsert(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setString(1, produto.getCodigo());
        stm.setString(2, produto.getNome());
        stm.setString(3, produto.getDescricao());
        stm.setDouble(4, produto.getValor());
        stm.setInt(5, produto.getEstoque());
    }

    private String getSqlUpdate() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE tb_produto");
        sb.append(" SET NOME = ?, CODIGO = ?, DESCRICAO = ?, VALOR = ?, ESTOQUE = ?");
        sb.append(" WHERE ID = ?");
        return sb.toString();
    }

    private void adicionarParametrosUpdate(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setString(1, produto.getNome());
        stm.setString(2, produto.getCodigo());
        stm.setString(3, produto.getDescricao());
        stm.setDouble(4, produto.getValor());
        stm.setInt(5, produto.getEstoque());
        stm.setLong(6, produto.getId());
    }

    private String getSqlDelete() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM tb_produto");
        sb.append(" WHERE CODIGO = ?");
        return sb.toString();
    }

    private void adicionarParametrosDelete(PreparedStatement stm, Produto produto) throws SQLException {
        stm.setString(1, produto.getCodigo());
    }

    private String getSqlSelect() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_produto");
        sb.append(" WHERE CODIGO = ?");
        return sb.toString();
    }

    private void adicionarParametrosSelect(PreparedStatement stm, String codigo) throws SQLException {
        stm.setString(1, codigo);
    }

    private String getSqlSelectAll() {
        return "SELECT * FROM tb_produto";
    }

    private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
