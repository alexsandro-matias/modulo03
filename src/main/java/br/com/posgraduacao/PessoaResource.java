package br.com.posgraduacao;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("pessoa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


public class PessoaResource {
    @GET
    public List<Pessoa> getPessoas() {
        return Pessoa.listAll();
    }
    @GET
    @Path("ano")
    public List<Pessoa> findByAnoNascimento(@QueryParam("anoNascimento") int anoNascimento) {
        return Pessoa.findByAnoNascimento(anoNascimento);
    }

    @POST
    @Transactional
    public Pessoa createPessoa(Pessoa pessoa) {
        // atributo herdado do panache.
        // Mas ainda não é suficiente por que estamos gerando um objeto
        // do tipo pessoa e retornando um tipo pessoa que deveria ser
        // consumido como MediaType.APPLICATION_JSON), ou seja, serialização deste objeto.
        // por isso iremos adicionar uma dependência para esta conversão. - "quarkus-rest-jsonb"
        pessoa.id = null;
        pessoa.persist();
        return pessoa;
    }

    @PUT
    @Transactional
    public Pessoa updatePessoa(Pessoa pessoaPesquisada) {
        Pessoa pessoa = Pessoa.findById(pessoaPesquisada.id);
        pessoa.nome = pessoaPesquisada.nome;
        pessoa.anoNascimento = pessoaPesquisada.anoNascimento;
        pessoa.persist();
        return pessoa;
    }

    @DELETE
    @Transactional
    public void deletePessoa(int id) {
        Pessoa.deleteById(id);
    }


}
