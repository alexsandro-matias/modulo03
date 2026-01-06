package br.com.posgraduacao;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDateTime;

@Path("/unips")
// A ideia do uso da notação "Produces" é para informar o tipo dos dados
// que estão sendo trafegados pelos métodos REST
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

//No exemplo acima, esta API seguirá um padrão de "produzir" um conteúdo em texto
// puro e "consumirá" conteúdo em texto pleno para que seja aplicado um padrão dos formatos
public class UniPSResource {
    private int i = 5;

    @GET
    public int getI() {
        return i;
    }

//    provocando um erro - No caso teremos dois métodos GET para o mesmo Endpoint
//    @GET
//    public int getIDiferente() {
//        return LocalDateTime.now().getNano();
//    }

    @PUT
    public void setI(int i) {
        this.i = i;
    }

    // Para corrigir, precisamos definir outro endpoint. - (root-cause first)
    @GET
    @Path("/geti")
    public int getIDiferente() {
        return LocalDateTime.now().getYear();
    }

    @POST
    public void addI() {
        i++;
    }

    @DELETE
    public void removeI() {
        i++;
    }
}
