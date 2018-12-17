package com.janu.location.controllers;

import com.janu.location.Repositories.LocationRepository;
import com.janu.location.entities.*;
import com.janu.location.service.LocationService;
import com.janu.location.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ComponentScan(basePackages = {"com.janu.location.controllers","com.janu.location.util"})
@Configuration
@ContextConfiguration
public class  LocationController {

    @Autowired
    LocationService service;

    @Autowired
    LocationRepository repository;

    //@Autowired
    EmailUtil emailUtil;

    @RequestMapping("/showCreate")
    public String showCreate(){

        return "createLocation";
    }

    @RequestMapping("/saveLoc")
    public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap){
        Location locationSaved = service.saveLocation(location);
        String msg = "Location saved with id: "+locationSaved.getId();
        emailUtil.sendEmail("subhadratesting@gmail.com","Location Saved","Location Saved Successfully and to return a response!");
        modelMap.addAttribute("msg",msg);
        return "createLocation";
    }

    @RequestMapping("/displayLocations")
    public String displayLocations(ModelMap modelMap){
        List<Location> locations = service.getAllLocations();
        modelMap.addAttribute("locations", locations);
        return "displayLocations";
    }

    @RequestMapping("/deleteLocation")
    public String deleteLocations(@RequestParam("id") int id, ModelMap modelMap){
        //Location location = service.getLocationById(id);//
        Location location = new Location();
        location.setId(id);
        service.deleteLocation(location);
        displayLocations(modelMap);
        return "displayLocations";
    }

    @RequestMapping("/showUpdate")
    public String showUpdate(@RequestParam("id") int id, ModelMap modelMap) {
        Location location = service.getLocationById(id).get();
        modelMap.addAttribute("location", location);
        return "updateLocation";

    }

    @RequestMapping("/updateLoc")
    public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
        service.updateLocation(location);
        List<Location> locations = service.getAllLocations();
        modelMap.addAttribute("locations", locations);
        return "displayLocations";


    }
}
