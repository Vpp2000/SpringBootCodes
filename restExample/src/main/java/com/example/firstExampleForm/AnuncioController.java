package com.example.firstExampleForm;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AnuncioController {

    private Map<Long,Anuncio> anuncios = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();

    @RequestMapping(value="/anuncios/",method = RequestMethod.GET)
    public Collection<Anuncio> anuncios(){
        return anuncios.values();
    }

    @RequestMapping(value = "/anuncios/",method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Anuncio anuncios(@RequestBody Anuncio anuncio){
        long id = lastId.incrementAndGet();
        anuncio.setId(id);
        anuncios.put(id,anuncio);
        return anuncio;
    }

    @RequestMapping("/gatito")
    public void gato(HttpServletRequest request , HttpServletResponse response) throws IOException {

        response.addHeader("Content-type","image/jpeg");
        IOUtils.copy(new FileInputStream("C:\\Users\\deepp\\Pictures\\hl2.jpg"),response.getOutputStream());

    }

    @RequestMapping(value = "/anuncios/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Anuncio> actualizaAnuncio(@PathVariable long id, @RequestBody Anuncio anuncioActualizado){
        Anuncio anuncio = anuncios.get(id);

        if(anuncio!=null){
            anuncioActualizado.setId(id);
            anuncios.put(id,anuncioActualizado);
            return new ResponseEntity<>(anuncioActualizado,HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
