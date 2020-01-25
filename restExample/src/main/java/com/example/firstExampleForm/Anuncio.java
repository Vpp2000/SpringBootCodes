package com.example.firstExampleForm;

public class Anuncio {

    private long id;
    private String nombre;
    private String asunto;
    private String comentario;

    public Anuncio(){

    }

    public Anuncio(String nombre, String asunto, String comentario, long id){
        this.nombre = nombre;
        this.asunto = asunto;
        this.comentario = comentario;
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getAsunto(){
        return this.asunto;
    }

    public void setAsunto(String asunto){
        this.asunto = asunto;
    }

    public String getComentario(){
        return this.comentario;
    }

    public void setComentario(String comentario){
        this.comentario = comentario;
    }
}
