package com.example.inventoryservice.web;


import com.example.inventoryservice.entity.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/all")
public class InventoryController {
	
	    @Autowired
	    private InventoryRepository INVRepo;
	
	    @GetMapping
	   // @PreAuthorize("hasRole('ROLE_client-admin')")
	    public java.util.List<Inventory> products(Model model){
	        return INVRepo.findAll();
	    }


	    
	   /* @ExceptionHandler(Exception.class)
	    public String exceptionHandler(Exception e, Model model){
	        model.addAttribute("errorMessage","problème d'autorisation");
	        return "errors";
	    }*/

	    /*@GetMapping("/jwt")
	    @ResponseBody
	    public Map<String,String> map(HttpServletRequest request){
	        KeycloakAuthenticationToken token =(KeycloakAuthenticationToken) request.getUserPrincipal();
	        KeycloakPrincipal principal=(KeycloakPrincipal)token.getPrincipal();
	        KeycloakSecurityContext keycloakSecurityContext=principal.getKeycloakSecurityContext();
	        Map<String,String> map = new HashMap<>();
	        map.put("access_token", keycloakSecurityContext.getTokenString());
	        return map;
	    }*/

}
