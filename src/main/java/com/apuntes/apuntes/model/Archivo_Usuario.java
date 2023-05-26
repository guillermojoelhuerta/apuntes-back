package com.apuntes.apuntes.model;

import net.bytebuddy.implementation.LoadedTypeInitializer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="archivo_usuario")
public class Archivo_Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id_archivo_usuario;
    private Long id_usuario;
    private String nombre_archivo;
    @Column(name = "id_apunte")
    private Long idApunte;

    @Column(name = "id_type_file")
    private int idTypeFile;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_apunte", insertable=false, updatable=false)
    private Apunte apunte;
    public Long getId_archivo_usuario() {
        return id_archivo_usuario;
    }

    public void setId_archivo_usuario(Long id_archivo_usuario) {
        this.id_archivo_usuario = id_archivo_usuario;
    }

    public Long getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }
    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public Long getIdApunte() {
        return idApunte;
    }

    public void setIdApunte(Long idApunte) {
        this.idApunte = idApunte;
    }

    public int getIdTypeFile() {
        return idTypeFile;
    }
    public void setIdTypeFile(int idTypeFile) {
        this.idTypeFile = idTypeFile;
    }

    @Override
    public String toString() {
        return "Archivo_Usuario{" +
                "id_archivo_usuario=" + id_archivo_usuario +
                ", id_usuario=" + id_usuario +
                ", nombre_archivo='" + nombre_archivo + '\'' +
                ", idApunte=" + idApunte +
                ", apunte=" + apunte +
                '}';
    }
}
