package com.example.rest_vid4_ful;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/items")
public class ItemsController {
    private ConcurrentMap<Long,Item> items = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();

    @RequestMapping(value="/", method= RequestMethod.GET)
    public Collection<Item> getItems(){
        return items.values();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public ResponseEntity<Item> getItem(@PathVariable long id){
        Item item = items.get(id);
        if(item != null){
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/", method= RequestMethod.POST)
    public Item newItem(@RequestBody Item item){
        long id = lastId.incrementAndGet();
        item.setId(id);
        items.put(id,item);
        return item;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Item> actualizaItem(@PathVariable long id, @RequestBody Item itemActualizado) {
        Item item = items.get(id);

        if (item != null) {
            itemActualizado.setId(id);
            items.put(id, itemActualizado);
            return new ResponseEntity<>(itemActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Item> deleteItem(@PathVariable long id){
        Item item = items.remove(id);
        if(item != null){
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/", method= RequestMethod.DELETE)
    public Collection<Item> deleteItem(@RequestParam String query){
        String[] parts = query.split(":");
        boolean checked = Boolean.parseBoolean(parts[1]);

        List<Item> removedItems = new ArrayList<>();
        for(Item item: items.values()) {
            if(item.isChecked() == checked){
                items.remove(item.getId());
            }
        }
        return removedItems;
        }

}

