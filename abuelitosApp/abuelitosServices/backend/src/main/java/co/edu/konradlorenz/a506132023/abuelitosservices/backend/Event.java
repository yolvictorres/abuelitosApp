package co.edu.konradlorenz.a506132023.abuelitosservices.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by astro on 2/05/2017.
 */
@Entity
public class Event {
    @Id
    private String id;
    private String foto;
    private String detalle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detale) {
        this.detalle = detale;
    }
}
