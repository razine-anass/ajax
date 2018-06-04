package org.opendevup.web;

import org.opendevup.entitee.Personne;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class EtudiantController {
	
	
	
	@RequestMapping(value="/index")
	public String index(Model model){
		Personne p=new Personne("az","az");
		model.addAttribute("persone",p);
		return "formulaire";
	}
	
	@RequestMapping(value="/savePersonne",method=RequestMethod.POST)
	public String savePersonne(String nom,String prenom){
		
		System.out.println(nom+""+prenom);
		
		return "redirect:index";
	}

}
