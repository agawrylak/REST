package com.agawrylak.REST.controller;

import com.agawrylak.REST.POJO.Pizza;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PizzaController {

    private static Map<Integer, Pizza> pizzaRep = new HashMap<>();

    static{

        Pizza pizza1 = new Pizza();
        pizza1.setId(1);
        pizza1.setTitle("Hawajska");
        pizza1.setDescription("Pizza z ananasem jest genialna!");

        Pizza pizza2 = new Pizza();
        pizza2.setId(2);
        pizza2.setTitle("Margarita");
        pizza2.setDescription("Pizza hawajska to świętokradztwo! Tylko prawdziwa włoska Margarita!");

        pizzaRep.put(pizza1.getId(), pizza1);
        pizzaRep.put(pizza2.getId(), pizza2);

    }

    @PostMapping(value = "/notes/create")
    public ResponseEntity<Object> createPizza(@RequestBody Pizza pizza){
        pizzaRep.put(pizza.getId(), pizza);
        return new ResponseEntity<>("Pizza is created successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/notes/{id}")
    public ResponseEntity<Object> updatePizza(@PathVariable("id") Integer id, @RequestBody Pizza pizza){
        pizzaRep.remove(id);
        pizza.setId(id);
        pizzaRep.put(id, pizza);
        return new ResponseEntity<>("Pizza is successfully updated",HttpStatus.OK);
    }

    @DeleteMapping(value = "/notes/{id}")
    public ResponseEntity<Object> deleteSinglePizza(@PathVariable("id") Integer id){
        pizzaRep.remove(id);
        return new ResponseEntity<>("Pizza is deleted successfully",HttpStatus.OK);
    }

    @GetMapping(value = "/notes/{id}")
    public ResponseEntity<Object> getSinglePizza(@PathVariable("id") Integer id){
        return new ResponseEntity<>(pizzaRep.get(id), HttpStatus.OK);
    }

    @GetMapping(value = "/notes")
    public ResponseEntity<Object> getAllNotes(){
        return new ResponseEntity<>(pizzaRep.values(), HttpStatus.OK);
    }

}
