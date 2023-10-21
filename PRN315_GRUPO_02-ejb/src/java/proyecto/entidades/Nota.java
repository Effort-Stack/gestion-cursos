/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author johec
 */
@Entity
@Table(name = "Nota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nota.findAll", query = "SELECT n FROM Nota n")
    , @NamedQuery(name = "Nota.findById", query = "SELECT n FROM Nota n WHERE n.id = :id")
    , @NamedQuery(name = "Nota.findByTipo", query = "SELECT n FROM Nota n WHERE n.tipo = :tipo")
    , @NamedQuery(name = "Nota.findByValor", query = "SELECT n FROM Nota n WHERE n.valor = :valor")})
public class Nota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private double valor;
    @JoinTable(name = "Estudiante_Curso_Nota", joinColumns = {
        @JoinColumn(name = "nota_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "estudiante_curso_estudiante_id", referencedColumnName = "estudiante_id")
        , @JoinColumn(name = "estudiante_curso_curso_id", referencedColumnName = "curso_id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<EstudianteCurso> estudianteCursoList;
    @JoinColumn(name = "curso_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso cursoId;

    public Nota() {
    }

    public Nota(Integer id) {
        this.id = id;
    }

    public Nota(Integer id, String tipo, double valor) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @XmlTransient
    public List<EstudianteCurso> getEstudianteCursoList() {
        return estudianteCursoList;
    }

    public void setEstudianteCursoList(List<EstudianteCurso> estudianteCursoList) {
        this.estudianteCursoList = estudianteCursoList;
    }

    public Curso getCursoId() {
        return cursoId;
    }

    public void setCursoId(Curso cursoId) {
        this.cursoId = cursoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nota)) {
            return false;
        }
        Nota other = (Nota) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyecto.entidades.Nota[ id=" + id + " ]";
    }
    
}
