/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author johec
 */
@Embeddable
public class EstudianteCursoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "estudiante_id")
    private int estudianteId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "curso_id")
    private int cursoId;

    public EstudianteCursoPK() {
    }

    public EstudianteCursoPK(int estudianteId, int cursoId) {
        this.estudianteId = estudianteId;
        this.cursoId = cursoId;
    }

    public int getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) estudianteId;
        hash += (int) cursoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudianteCursoPK)) {
            return false;
        }
        EstudianteCursoPK other = (EstudianteCursoPK) object;
        if (this.estudianteId != other.estudianteId) {
            return false;
        }
        if (this.cursoId != other.cursoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyecto.entidades.EstudianteCursoPK[ estudianteId=" + estudianteId + ", cursoId=" + cursoId + " ]";
    }
    
}
