package com.apuntes.apuntes.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="apuntes")
public class Apunte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id_apunte")
    private Long idApunte;
    @NotNull(message = "El id_categoria is requerido")
    private Long id_categoria;
    @NotEmpty(message = "El titulo is requerido")
    private String titulo;
    @NotEmpty(message = "El contenido is requerido")
    private String contenido;
    private boolean activo;
    private Long id_usuario;
    @ManyToOne
    @JoinColumn(name = "id_categoria", insertable=false, updatable=false)
    private Categoria categoria;
    @OneToMany(mappedBy = "apunte", fetch = FetchType.EAGER)
    private List<Archivo_Usuario> archivo_usuario;

    public Long getIdApunte() {
        return idApunte;
    }

    public void setIdApunte(Long idApunte) {
        this.idApunte = idApunte;
    }

    public Long getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public List<Archivo_Usuario> getArchivo_usuario() {
        return archivo_usuario;
    }

    public void setArchivo_usuario(List<Archivo_Usuario> archivo_usuario) {
        this.archivo_usuario = archivo_usuario;
    }
}
