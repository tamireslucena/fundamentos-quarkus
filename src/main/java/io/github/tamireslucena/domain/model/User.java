package io.github.tamireslucena.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name= "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


/*
------------- ANOTAÇÕES -------------

    caso os nomes da tabela ou das colunas esteja diferente no db:
    necessário fazer o depara

    @Table(name= "users")
    @Column(name= "nome")

    @GeneratedValue(strategy = GenerationType.IDENTITY) // é um autoincrement

 */