package sn.samaneCorporation.springSenForageMvcFinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sn.samaneCorporation.springSenForageMvcFinal.entities.Abonnement;
import sn.samaneCorporation.springSenForageMvcFinal.entities.Compteur;
import sn.samaneCorporation.springSenForageMvcFinal.repository.IAbonnementRepository;
import sn.samaneCorporation.springSenForageMvcFinal.repository.IClientRepository;
import sn.samaneCorporation.springSenForageMvcFinal.repository.ICompteurRepository;

@Controller
public class CompteurController {

    @Autowired
    private IAbonnementRepository abonnementRepository;

    @Autowired
    private ICompteurRepository compteurRepository;

    @RequestMapping("/Compteur/add")
    private String addVillage(Model model){

        model.addAttribute("abonnements", abonnementRepository.findAll());
        return "compteur/add";
    }

    @RequestMapping("/Compteur")
    private String listeCompteur(Model model){

        model.addAttribute("compteurs", compteurRepository.findAll());
        return "compteur/liste";
    }

    @RequestMapping(value = "/Compteur/save", method = RequestMethod.POST)
    private String save(Model model, String dateAbonnement, String description, int abonnement, @RequestParam(defaultValue = "0", name = "id") int id){

        try {

            Compteur v = new Compteur();
            v.setId(id);
            v.setDateAttribution(dateAbonnement);
            v.setDescription(description);
            v.setEtat(false);
            v.setAbonnement(abonnementRepository.findById(abonnement).get());

            compteurRepository.save(v);
            model.addAttribute("compteurs", compteurRepository.findAll());
            return "compteur/liste";
        } catch(Exception ex){
            model.addAttribute("error", "0");
            return "compteur/add";
        }
    }


}
