package br.com.icolabora.cep.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.icolabora.cep.pojo.Endereco;

public class EnderecoDAO {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;

	public EnderecoDAO() {
	}

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public List<Endereco> find(String cep) {
		List<Endereco> enderecos = new ArrayList<Endereco>();
		
		try {
			String queryString = "select endereco.nome  	logradouro, " +
								 "       cidade.nome    	cidade,     " +
								 "       endereco.id_uf 	uf,         " +
								 "       bairro.nome    	bairro      " +
								 "from endereco                         " +
								 "inner join cidade on cidade.id_cidade = endereco.id_cidade " +
								 "inner join bairro on bairro.id_bairro = endereco.id_bairro " +
								 "where endereco.cep = ?";
			
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, cep);
			
			resultSet = ptmt.executeQuery();
			
			while (resultSet.next()) {
				Endereco endereco = new Endereco();
				endereco.setLogradouro(resultSet.getString("logradouro"));
				endereco.setCidade    (resultSet.getString("cidade"));
				endereco.setUf        (resultSet.getString("uf"));
				endereco.setBairro    (resultSet.getString("bairro"));
				
				enderecos.add(endereco);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet  != null) resultSet.close();
				if (ptmt       != null) ptmt.close();
				if (connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return enderecos;
	}
}
