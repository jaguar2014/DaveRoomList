package com.daveslist.demo.controller;

import com.daveslist.demo.model.Room;
import com.daveslist.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/")
    public String showIndex(){
        return "index";
    }

    @GetMapping("/addroom")
    public String addRoom(Model model){

        Room room = new Room();

        model.addAttribute("room",room);


        return "roomform";
    }

    @PostMapping("/addroom")
    public String addRoom(@Valid Room room, BindingResult result){
        if(result.hasErrors()){
            return "roomform";
        }
        roomRepository.save(room);

       return "redirect:/listadmin";
    }

    @GetMapping("/avilableroom")
    public String list(Model model){
       model.addAttribute("rooms",roomRepository.findByRentedTrue());

        return "avilableroom";

    }

    @GetMapping("/detail/{id}")
    public String showFullDetails(@PathVariable("id") long id , Model model){
      model.addAttribute("room",roomRepository.findOne(id));

      return "fulldetails";


    }

    @GetMapping("/listadmin")
    public String listAdmin(Model model){
     model.addAttribute("rooms",roomRepository.findAll());

        return "listadmin";

    }
    @GetMapping("/login")
    public String login(){


        return "login";

    }




}
