package br.com.posgraduacao;

//Criando um consumidor da API - para isso iremos adicionar com o comando:
// .\mvnw quarkus:add-extensions -Dextensions="quarkus-rest-client"

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


//Nomenclatura para consumo de serviço "remoto" - chamamos de "service"
// e como usaremos um método externo ao nosso projeto
@RegisterRestClient(baseUri = "https://swapi.info/api/")
public interface StarWarsService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/starships")
    String getStarShips();

}
