package Entities;

import Entities.Categoria;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-03-19T23:12:09")
@StaticMetamodel(Juego.class)
public class Juego_ { 

    public static volatile SingularAttribute<Juego, Integer> existencias;
    public static volatile SingularAttribute<Juego, Float> precio;
    public static volatile SingularAttribute<Juego, Integer> idjuego;
    public static volatile SingularAttribute<Juego, String> nomJuego;
    public static volatile SingularAttribute<Juego, String> imagen;
    public static volatile SingularAttribute<Juego, Categoria> idcategoria;
    public static volatile SingularAttribute<Juego, String> clasificacion;

}