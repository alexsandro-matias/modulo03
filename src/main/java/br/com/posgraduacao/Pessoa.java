package br.com.posgraduacao;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Pessoa extends PanacheEntity {
    //    o "panache" permite que se deixe os atribuitos como públicos e ele
//    fará o gerencimaneto deste atributos.
    public String nome;
    public int anoNascimento;

    public static List<Pessoa> findByAnoNascimento(int anoNascimento) {
        return find("anoNascimento", anoNascimento).list();
    }
}
