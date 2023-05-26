package com.apuntes.apuntes.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="usuariox")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long usuario_id;

    private String nombre;
    private String apellido_paterno;
    private String usuario;
    private String password;

    public User(String nombre, String apellido_paterno, String usuario, String password) {
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.usuario = usuario;
        this.password = password;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }
}
