package org.dsi.web;

import org.dsi.entity.Supplier;
import org.dsi.repo.SupplierRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/all")
public class SupplierController {
	
	    @Autowired
	    private SupplierRepo supplierRepo;
	
	    @GetMapping
	   // @PreAuthorize("hasRole('ROLE_client-admin')")
	    public java.util.List<Supplier> products(Model model){
	        return supplierRepo.findAll();
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
