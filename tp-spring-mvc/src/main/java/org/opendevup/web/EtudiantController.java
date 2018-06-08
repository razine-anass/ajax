package org.opendevup.web;

import java.util.List;

import org.opendevup.Dao.EtudiantRepository;
import org.opendevup.entitee.Etudiant;
import org.opendevup.entitee.Personne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


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
		return etudiantRepository.findAll();
	}
	@RequestMapping(value="/supprimer/{id}", method = RequestMethod.GET)
	
	public String supprimerEtudiant(@PathVariable("id") Long id){
		LOG.info("getting user with id: {}", id);
		
		System.out.println("supprimer"+id);
		etudiantRepository.deleteById(id);
		return "redirect:/index";
	}
	
	@RequestMapping(value="/editer/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void modifierEtudiant(@PathVariable("id") int id){
		LOG.info("getting user with id: {}", id);
		
		System.out.println("editer"+id);
		
	}

}
