package sn.samaneCorporation.springSenForageMvcFinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sn.samaneCorporation.springSenForageMvcFinal.entities.Village;
import sn.samaneCorporation.springSenForageMvcFinal.repository.IVillageRepository;

@Controller
public class VillageController {

    @Autowired
    private IVillageRepository villageRepository;

    @RequestMapping("/Village/add")
    private String addVillage(){
        return "village/add";
    }

    @RequestMapping(value = "/Village/save", method = RequestMethod.POST)
    private String save(Model model, String libelle, @RequestParam(defaultValue = "0", name = "id") int id){

        try {

            Village v = new Village();
            v.setId(id);
            v.setLibelle(libelle);
            villageRepository.save(v);
            model.addAttribute("villages", villageRepository.findAll());
            return "village/liste";
        } catch(Exception ex){
            model.addAttribute("error", "0");
            return "village/add";
        }
    }


}
