/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author martinsanabria
 */
@Entity
@Table(name = "juego")
@NamedQueries({
    @NamedQuery(name = "Juego.findAll", query = "SELECT j FROM Juego j"),
    @NamedQuery(name = "Juego.findByIdjuego", query = "SELECT j FROM Juego j WHERE j.idjuego = :idjuego"),
    @NamedQuery(name = "Juego.findByNomJuego", query = "SELECT j FROM Juego j WHERE j.nomJuego = :nomJuego"),
    @NamedQuery(name = "Juego.findByPrecio", query = "SELECT j FROM Juego j WHERE j.precio = :precio"),
    @NamedQuery(name = "Juego.findByExistencias", query = "SELECT j FROM Juego j WHERE j.existencias = :existencias"),
    @NamedQuery(name = "Juego.findByImagen", query = "SELECT j FROM Juego j WHERE j.imagen = :imagen"),
    @NamedQuery(name = "Juego.findByClasificacion", query = "SELECT j FROM Juego j WHERE j.clasificacion = :clasificacion")})
public class Juego implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idjuego")
    private Integer idjuego;
    @Column(name = "nomJuego")
    private String nomJuego;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Float precio;
    @Column(name = "existencias")
    private Integer existencias;
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "clasificacion")
    private String clasificacion;
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria")
    @ManyToOne(optional = false)
    private Categoria idcategoria;

    public Juego() {
    }

    public Juego(Integer idjuego) {
        this.idjuego = idjuego;
    }

    public Juego(String parameter, Integer idcategoria, double parseDouble, int parseInt, String parameter0, String parameter1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Integer getIdjuego() {
        return idjuego;
    }

    public void setIdjuego(Integer idjuego) {
        this.idjuego = idjuego;
    }

    public String getNomJuego() {
        return nomJuego;
    }

    public void setNomJuego(String nomJuego) {
        this.nomJuego = nomJuego;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getExistencias() {
        return existencias;
    }

    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Categoria getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Categoria idcategoria) {
        this.idcategoria = idcategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idjuego != null ? idjuego.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Juego)) {
            return false;
        }
        Juego other = (Juego) object;
        if ((this.idjuego == null && other.idjuego != null) || (this.idjuego != null && !this.idjuego.equals(other.idjuego))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Juego[ idjuego=" + idjuego + " ]";
    }
    
}
