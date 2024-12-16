package com.arlo.foroApi.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Table(name = "Topicos")
@Entity(name = "Topico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(nullable = false, length = 50)
    private String status;

    @Column(nullable = false, length = 200)
    private String autor;

    @Column(nullable = false, length = 200)
    private String curso;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fechaCreacion = datosRegistroTopico.fechaCreacion() != null ? datosRegistroTopico.fechaCreacion() : LocalDateTime.now();
        this.status = datosRegistroTopico.status();
        this.autor = datosRegistroTopico.autor();
        this.curso = datosRegistroTopico.curso();
    }

    public Topico() {
    }

    @PrePersist
    public void prePersist() {
        if(this.fechaCreacion == null) {
            this.fechaCreacion = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public String getStatus() {
        return status;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }

    public void actualizarDatos(@Valid DatosActualizarTopico datosTopico) {
        if(datosTopico.titulo() != null) {
            this.titulo = datosTopico.titulo();
        }
        if (datosTopico.mensaje() != null) {
            this.mensaje = datosTopico.mensaje();
        }
        if (datosTopico.fechaCreacion() != null) {
            this.fechaCreacion = datosTopico.fechaCreacion();
        }
        if (datosTopico.status() != null) {
            this.status = datosTopico.status();
        }
        if (datosTopico.autor() != null) {
            this.autor = datosTopico.autor();
        }
        if (datosTopico.curso() != null) {
            this.curso = datosTopico.curso();
        }
    }
}
