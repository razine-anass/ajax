package org.opendevup.web;

import java.util.List;
import java.util.Optional;

import org.opendevup.Dao.EtudiantRepository;
import org.opendevup.entitee.Etudiant;
import org.opendevup.entitee.Personne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


//Microservice REST:@RestController. Spring sait alors que les réponses aux requêtes
//qu'il vous passe devront être très probablement en format JSON.
//L'auto-configurateur va alors chercher si vous avez dans votre classpath 
//une dépendance capable de transformer un object Java en JSON,
@Controller
public class EtudiantController {
	
	private final Logger LOG = LoggerFactory.getLogger(EtudiantController.class);
	
	@Autowired
	EtudiantRepository etudiantRepository;
	
	
	@RequestMapping(value="/index")
	public String index(Model model){
		Personne p=new Personne("az","az");
		model.addAttribute("persone",p);
		return "listEtudiant";
	}
	
	@RequestMapping(value="/savePersonne",method=RequestMethod.POST)
	public String savePersonne(String nom,String prenom){
		
		System.out.println(nom+""+prenom);
		
		return "redirect:index";
	}
	
	@RequestMapping(value="/list-etudiant" , method = RequestMethod.GET)
	@ResponseBody
	public List<Etudiant> listEtudiants(){
		
		etudiantRepository.findAll().get(0);
		return etudiantRepository.findAll();
	}
	@RequestMapping(value="/supprimer/{id}", method = RequestMethod.GET)
	
	public String supprimerEtudiant(@PathVariable("id") Long id){
		LOG.info("supprimer", id);
		
		System.out.println("supprimer"+id);
		etudiantRepository.deleteById(id);
		return "redirect:/index";
	}
	
	@RequestMapping(value="/modifier/{id}", method = RequestMethod.GET)
	public String modifierEtudiant(@PathVariable("id") Long id,Model model){
		LOG.info("getting user with id: {}", id);
		Optional<Etudiant> etd=etudiantRepository.findById(id);
		model.addAttribute("etudiant",etd);
		return "editer";
	}
	
	@RequestMapping(value="/updateEtudiant",method=RequestMethod.POST)
	public String modifierEtudiant(Etudiant etudiant){
		
		LOG.info("etudiant à mettre à jour: "+etudiant);
//		//etudiant existe dans le contexte de persistence
//		etudiantRepository.save(etudiant);
//		// on vide le context dans la base de donné
//		etudiantRepository.flush();
//		//etudiant est toujours dans le context donc le nom sera 3arbi
//		etudiant.setNom("3arbi");
		
		Etudiant etd= etudiantRepository.getOne(etudiant.getId());
		etd.setNom(etudiant.getNom());
		etd.setEmail(etudiant.getEmail());
		etd.setDateNaissance(etudiant.getDateNaissance());
		etudiantRepository.save(etd);
		return "redirect:/index";
	}
	
	@RequestMapping(value="/visualiser/{id}")
	@ResponseBody
	public String visualiserEtudiant(@PathVariable("id")Long id){
		
		return "razine";
	}

}
