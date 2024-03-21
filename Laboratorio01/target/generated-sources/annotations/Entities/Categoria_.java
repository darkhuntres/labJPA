package Entities;

import Entities.Juego;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-03-20T19:44:03")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile SingularAttribute<Categoria, String> imagenCat;
    public static volatile CollectionAttribute<Categoria, Juego> juegoCollection;
    public static volatile SingularAttribute<Categoria, String> categoria;
    public static volatile SingularAttribute<Categoria, Integer> idcategoria;

}