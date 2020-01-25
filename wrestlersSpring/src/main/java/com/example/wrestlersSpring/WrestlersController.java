package com.example.wrestlersSpring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/luchadores")
public class WrestlersController {
    ConcurrentMap<Long,Luchador> luchadores = new ConcurrentHashMap<>();
    AtomicLong id = new AtomicLong();

    @RequestMapping(value="/", method= RequestMethod.GET)
    public Collection<Luchador> luchadores(){
        return luchadores.values();
    }

    @RequestMapping(value="/", method= RequestMethod.POST)
    public ResponseEntity<Luchador> addLuchador(@RequestBody Luchador luchador){
        Long ident = id.incrementAndGet();
        luchador.setId(ident);
        luchadores.put(ident,luchador);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public Luchador getLuchador(@PathVariable Long id){
        Luchador wrestler = luchadores.get(id);
        return wrestler;
    }


}
