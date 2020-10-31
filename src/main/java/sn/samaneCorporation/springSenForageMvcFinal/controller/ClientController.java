package sn.samaneCorporation.springSenForageMvcFinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sn.samaneCorporation.springSenForageMvcFinal.entities.Client;
import sn.samaneCorporation.springSenForageMvcFinal.entities.Personne;
import sn.samaneCorporation.springSenForageMvcFinal.entities.Village;
import sn.samaneCorporation.springSenForageMvcFinal.repository.IClientRepository;
import sn.samaneCorporation.springSenForageMvcFinal.repository.IVillageRepository;

@Controller
public class ClientController {

    @Autowired
    private IVillageRepository villageRepository;

    @Autowired
    private IClientRepository clientRepository;

    @RequestMapping("/Client/add")
    private String addVillage(Model model){

        model.addAttribute("villages", villageRepository.findAll());
        return "client/add";
    }

    @RequestMapping(value = "/Client/save", method = RequestMethod.POST)
    private String save(Model model, String nom, String prenom, String dateNaissance, String lieuNaissance, String telephone, String adresse,int village, @RequestParam(defaultValue = "0", name = "id") int id){

        try {

            Client v = new Client();
            v.setId(id);
            Personne p = new Personne();
            p.setNom(nom);
            p.setPrenom(prenom);
            p.setDateNaissance(dateNaissance);
            p.setLieuNaissance(lieuNaissance);
            p.setTelephone(telephone);
            p.setAdresse(adresse);
            v.setPersonne(p);
            v.setVillage(villageRepository.findById(village).get());

            clientRepository.save(v);
            model.addAttribute("clients", clientRepository.findAll());
            return "client/liste";
        } catch(Exception ex){
            model.addAttribute("error", "0");
            return "client/add";
        }
    }


}
