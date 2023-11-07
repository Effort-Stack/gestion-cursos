package proyecto.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "Nota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nota.findAll", query = "SELECT n FROM Nota n"),
    @NamedQuery(name = "Nota.findById", query = "SELECT n FROM Nota n WHERE n.id = :id"),
    @NamedQuery(name = "Nota.findByNotaExamen", query = "SELECT n FROM Nota n WHERE n.notaExamen = :notaExamen"),
    @NamedQuery(name = "Nota.findByNotaTarea", query = "SELECT n FROM Nota n WHERE n.notaTarea = :notaTarea"),
    @NamedQuery(name = "Nota.findByNotaLaboratorio", query = "SELECT n FROM Nota n WHERE n.notaLaboratorio = :notaLaboratorio"),
})
public class Nota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "notaExamen")
    private double notaExamen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "notaTarea")
    private double notaTarea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "notaLaboratorio")
    private double notaLaboratorio;
    @Column(name = "notaFinal")
    private double notaFinal;
    
    @JoinColumn(name = "curso_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso cursoId;
    
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante estudianteId;

    public Nota() {
    }

    public Nota(Integer id) {
        this.id = id;
    }

    public Nota(Integer id, double notaExamen, double notaTarea, double notaLaboratorio) {
        this.id = id;
        this.notaExamen = notaExamen;
        this.notaTarea = notaTarea;
        this.notaLaboratorio = notaLaboratorio;
        calcularNotaFinal();
    }

    public Integer getId() {
    return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getNotaExamen() {
        return notaExamen;
    }

    public void setNotaExamen(double notaExamen) {
        this.notaExamen = notaExamen;
    }

    public double getNotaTarea() {
        return notaTarea;
    }

    public void setNotaTarea(double notaTarea) {
        this.notaTarea = notaTarea;
    }

    public double getNotaLaboratorio() {
        return notaLaboratorio;
    }

    public void setNotaLaboratorio(double notaLaboratorio) {
        this.notaLaboratorio = notaLaboratorio;
    }
    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public Curso getCursoId() {
        return cursoId;
    }

    public void setCursoId(Curso cursoId) {
        this.cursoId = cursoId;
    }

    public Estudiante getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Estudiante estudianteId) {
        this.estudianteId = estudianteId;
    }

    public void calcularNotaFinal() {
        double examenPonderado = this.notaExamen * 0.5;
        double tareaPonderada = this.notaTarea * 0.2;
        double laboratorioPonderado = this.notaLaboratorio * 0.3;
        this.notaFinal = examenPonderado + tareaPonderada + laboratorioPonderado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
