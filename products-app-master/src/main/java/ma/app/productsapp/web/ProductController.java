package ma.app.productsapp.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.app.productsapp.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;


@EnableMethodSecurity
@RequiredArgsConstructor
@Controller
@Slf4j
public class ProductController{

	@Autowired
	OAuth2AuthorizedClientService oauth2ClientService;

	@Autowired
	RestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/")
    //@PreAuthorize("hasRole('client-user')")
    public String index(){
        return "index";
    }
    @GetMapping("/products")
    //@PreAuthorize("hasRole('client-admin')")
    public String products(Model model,@AuthenticationPrincipal OidcUser principal
			) {

		  Authentication authentication =
		  SecurityContextHolder.getContext().getAuthentication();
		  OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
		  authentication;

		  OAuth2AuthorizedClient oauth2Client =
		  oauth2ClientService.loadAuthorizedClient(oauthToken.
		  getAuthorizedClientRegistrationId(), oauthToken.getName());

		  String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
		  System.out.println("jwtAccessToken = " + jwtAccessToken);


		  System.out.println("Principal = " + principal);

		  OidcIdToken idToken = principal.getIdToken();
		  String idTokenValue = idToken.getTokenValue();
		  System.out.println("idTokenValue = " + idTokenValue);
		  model.addAttribute("products",productRepository.findAll());
		  return "products";
    }


	@GetMapping("/suppliers")
	public String suppliers(Model model, @AuthenticationPrincipal OidcUser principal) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

		OAuth2AuthorizedClient oauth2Client = oauth2ClientService.loadAuthorizedClient(
				oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());

		String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
		System.out.println("jwtAccessToken = " + jwtAccessToken);

		System.out.println("Principal = " + principal);

		OidcIdToken idToken = principal.getIdToken();
		String idTokenValue = idToken.getTokenValue();
		System.out.println("idTokenValue = " + idTokenValue);

		String url = "http://localhost:8888/suppliers";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + jwtAccessToken);

		HttpEntity<Void> entity = new HttpEntity<>(headers);

		ResponseEntity<PagedModel<Supplier>> responseEntity = restTemplate.exchange(
				url,
				HttpMethod.GET,
				entity,
				new ParameterizedTypeReference<PagedModel<Supplier>>() {
				});

		PagedModel<Supplier> suppliers =  responseEntity.getBody();
		List<Supplier> pageSuppliers = new ArrayList<>(suppliers.getContent());
		log.info(pageSuppliers.toString());

		model.addAttribute("suppliers", pageSuppliers);
		return "suppliers";
	}


	// customer

	@GetMapping("/customers")
	public String customers(Model model, @AuthenticationPrincipal OidcUser principal) {

		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

		OAuth2AuthorizedClient oauth2Client =
				oauth2ClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());

		String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
		System.out.println("jwtAccessToken = " + jwtAccessToken);

		System.out.println("Principal = " + principal);

		OidcIdToken idToken = principal.getIdToken();
		String idTokenValue = idToken.getTokenValue();
		System.out.println("idTokenValue = " + idTokenValue);

		String url = "http://localhost:8888/customers";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + jwtAccessToken);

		HttpEntity<Void> entity = new HttpEntity<>(headers);

		ResponseEntity<PagedModel<Customer>> responseEntity = restTemplate.exchange(
				url,
				HttpMethod.GET,
				entity,
				new ParameterizedTypeReference<PagedModel<Customer>>() {
				});

		PagedModel<Customer> customers = responseEntity.getBody();
		List<Customer> pageCustomers = new ArrayList<>(customers.getContent());
		log.info(pageCustomers.toString());

		model.addAttribute("customers", pageCustomers);
		return "customers";
	}




	//inventory
	@GetMapping("/inventorys")
	public String inventorys(Model model,@AuthenticationPrincipal OidcUser principal
	) {

		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
		OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
				authentication;

		OAuth2AuthorizedClient oauth2Client =
				oauth2ClientService.loadAuthorizedClient(oauthToken.
						getAuthorizedClientRegistrationId(), oauthToken.getName());

		String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
		System.out.println("jwtAccessToken = " + jwtAccessToken);


		System.out.println("Principal = " + principal);

		OidcIdToken idToken = principal.getIdToken();
		String idTokenValue = idToken.getTokenValue();
		System.out.println("idTokenValue = " + idTokenValue);
		String url = "http://localhost:8888/inventories";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + jwtAccessToken);
		HttpEntity<List<Inventory>> entity = new HttpEntity<>(headers);
		ResponseEntity<PagedModel<Inventory>> responseEntity = restTemplate.exchange(
				url,
				HttpMethod.GET,
				entity,
				new ParameterizedTypeReference<PagedModel<Inventory>>() {
				});

		PagedModel<Inventory> inventorys = responseEntity.getBody();
		List<Inventory> pageInventorys = new ArrayList<>(inventorys.getContent());
		log.info(pageInventorys.toString());
		model.addAttribute("inventorys",pageInventorys);
		return "inventorys";
	}


}
class Supplier{
    private Long id;
    private String name;
    private String email;

	public Supplier() {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", email=" + email + "]";
	}


}

class Customer{
	private Long id;
	private String name;
	private String email;

	public Customer() {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + "]";
	}


}

class Inventory {
	private Long id;
	private String name;
	private Double price;

	public Inventory() {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Inventory [id=" + id + ", name=" + name + ", price=" + price + "]";
	}


}