package sn.samaneCorporation.springSenForageMvcFinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sn.samaneCorporation.springSenForageMvcFinal.entities.Abonnement;
import sn.samaneCorporation.springSenForageMvcFinal.entities.Client;
import sn.samaneCorporation.springSenForageMvcFinal.entities.Personne;
import sn.samaneCorporation.springSenForageMvcFinal.repository.IAbonnementRepository;
import sn.samaneCorporation.springSenForageMvcFinal.repository.IClientRepository;
import sn.samaneCorporation.springSenForageMvcFinal.repository.IVillageRepository;

@Controller
public class AbonnementController {

    @Autowired
    private IAbonnementRepository abonnementRepository;

    @Autowired
    private IClientRepository clientRepository;

    @RequestMapping("/Abonnement/add")
    private String addVillage(Model model){

        model.addAttribute("clients", clientRepository.findAll());
        return "abonnement/add";
    }

    @RequestMapping(value = "/Abonnement/save", method = RequestMethod.POST)
    private String save(Model model, String dateAbonnement, String description, int client, @RequestParam(defaultValue = "0", name = "id") int id){

        try {

            Abonnement v = new Abonnement();
            v.setId(id);
            v.setDateAbonnement(dateAbonnement);
            v.setDescription(description);
            v.setEtat(false);
            v.setClient(clientRepository.findById(client).get());

            abonnementRepository.save(v);
            model.addAttribute("abonnements", abonnementRepository.findAll());
            return "abonnement/liste";
        } catch(Exception ex){
            model.addAttribute("error", "0");
            return "abonnement/add";
        }
    }


}
