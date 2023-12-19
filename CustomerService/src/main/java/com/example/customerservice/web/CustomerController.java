package com.example.customerservice.web;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/all")
public class CustomerController {
	
	    @Autowired
	    private CustomerRepository CustomerRepo;
	
	    @GetMapping
	   // @PreAuthorize("hasRole('ROLE_client-admin')")
	    public java.util.List<Customer> products(Model model){
	        return CustomerRepo.findAll();
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
