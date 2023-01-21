package brain.security.web;

import brain.security.Dao.UserRepository;
import brain.security.Model.UserLogin;
import brain.security.Model.UserSecurity;
import brain.security.service.MyUserDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static java.security.KeyRep.Type.SECRET;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
@RestController
public class HomeResource {

    private final AuthenticationManager authenticationManager;

    public HomeResource(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /*@GetMapping("/home")
    public String home() {
        return ("<h1>Welcome</h1>");
    }*/
	@GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/userRssource")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }

    @RequestMapping(value="/login", method= RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody UserLogin user) {
   System.out.println("entering login backend with username"+user.getUsername()+"and password="+user.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("login successfull");
            long EXPIRATION_TIME = 24 * 60 * 60 * 1000;
            String SECRET = "yasserPwr";
            String jwt = Jwts.builder()
                    .setSubject(user.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + jwt);
            return new ResponseEntity<>("Successfully logged in",headers, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }
}


