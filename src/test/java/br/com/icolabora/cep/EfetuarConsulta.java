package br.com.icolabora.cep;

import java.util.List;

import br.com.icolabora.cep.db.EnderecoDAO;
import br.com.icolabora.cep.pojo.Endereco;

import org.junit.Test;

import com.google.gson.Gson;

import static org.junit.Assert.assertTrue;

public class EfetuarConsulta
{
	@Test
	public final void doSearch()
    {
    	List<Endereco> enderecos = new EnderecoDAO().find("04302020");
    	
    	Gson gson = new Gson();
    	System.out.println(gson.toJson(enderecos));
    	
    	assertTrue(enderecos.size() == 1);
        assertTrue(enderecos.get(0).getLogradouro().toUpperCase().contains("PARACATU"));
    }
}
