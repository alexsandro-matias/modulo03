package br.com.posgraduacao;

//Criando um consumidor da API - para isso iremos adicionar com o comando:
// .\mvnw quarkus:add-extensions -Dextensions="quarkus-rest-client"

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


//Nomenclatura para consumo de serviço "remoto" - chamamos de "service"
// e como usaremos um método externo ao nosso projeto
@RegisterRestClient(baseUri = "https://swapi.info/api/")

//teste método de fallback
//@RegisterRestClient(baseUri = "https://swapi.inf/")
public interface StarWarsService {
    public static final String MSG_ERROR = "Mensagem de Fallback. \n";


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/starships")
    @Timeout(value = 3000L
//            ,unit = ChronoUnit.SECONDS
    )
//    Colocando um timeout, caso ele ocorra, levantará um exception. Que vai ocorrer da parada da aplicação.
//    Para evitar isso, também outra marcação na FaulttoTolerance que não permite que a aplicação caia.
//    Ela é chamada de fallback.
//    Nele é possível deixar uma configuração de um método para quando a aplicação cair, o método sobrepor a exception.
    @Fallback(fallbackMethod = "getStarShipsFallback")

    // Existe possibilidade de várias aplicações ficarem esperando pelo resultado de algum método que,
    //  por algum motivo, lançou uma exceção. e mesmo com timeout ainda não houve resposta.
    //  Por isso, o padrão de projeto Cuicuit breaker interrompe essa execução de
    //  forma temporária para evitar essa requisições que não obterão resposta.
    @CircuitBreaker(requestVolumeThreshold = 2,      // volume de requisições ou amostragem - quantas requisições serão feitas.
            failureRatio = 0.5,  //           qual percentual de falhas para determinar se vai abrir o circuito.
            delay = 6000L,//intervalo de tempo para aguardar para verificar se o sistemas está reestabelecido.
            successThreshold = 2 // quantas requisições deve ser realizadas com sucesso para que o circuito seja fechado.
    )
    String getStarShips();

    //    Este método tem a seguinte característica: Ter as mesma assinatura do método, ou seja,
    //     Mesmo parâmetros e mesmo retorno.

    default String getStarShipsFallback() {
        return MSG_ERROR;
    }

}
