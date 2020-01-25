package com.example.firstExampleForm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class AnuncioController {

    @Autowired
    private Usuario usuario;

    private Map<Integer,Anuncio> anuncios = new ConcurrentHashMap<>();
    private AtomicInteger id = new AtomicInteger();

    @RequestMapping("/guardarAnuncio")
    public String guardarAnuncio(Model model, Anuncio anuncio){

        int anuncioId = id.getAndIncrement();
        anuncio.setId(anuncioId);
        anuncios.put(anuncioId,anuncio);
        usuario.setNombre(anuncio.getNombre());
        return "anuncio_guardado";
    }

    @RequestMapping("/")
    public String anuncios(Model model, HttpSession sesion){
        model.addAttribute("anuncios",anuncios.values());
        model.addAttribute("bienvenido",sesion.isNew());
        return "anuncios";
    }

    @RequestMapping("/anuncio/{id}")
    public String anuncio(Model model, @PathVariable int id){
        System.out.println("Anuncio "+ id);
        Anuncio anuncio = anuncios.get(id);
        model.addAttribute("anuncio",anuncio);
        return "anuncio";
    }

    @RequestMapping("/enviarAnuncio")
    public String enviarAnuncio(Model model){
        String nombre = usuario.getNombre();
        if (nombre == null) {
            nombre = " ";
        }
        model.addAttribute("nombre",nombre);
        return "enviarAnuncio";
    }
}
