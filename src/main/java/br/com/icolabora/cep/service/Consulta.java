package br.com.icolabora.cep.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.icolabora.cep.db.EnderecoDAO;

import com.google.gson.Gson;


@Path( "/consulta" )
public class Consulta {
	
	@GET
	@Produces("application/json")
	@Path("/{cep}")
	public String doSearch(@PathParam("cep") String cep)
	{
		Gson gson = new Gson();
		return gson.toJson(new EnderecoDAO().find(cep));
	}
}
