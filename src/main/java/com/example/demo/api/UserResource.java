package com.example.demo.api;
import com.example.demo.emailtemplate.EmailTemplate;
import com.example.demo.emailsender.EmailSenderService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.example.demo.users.AppUser;
import com.example.demo.users.UserRole;
import com.example.demo.users.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;
    private final EmailSenderService emailSenderService;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/usersById/{id}")
    public ResponseEntity<AppUser> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userService.getReferenceById(id));
    }

    @GetMapping("/login/{username}")
    public ResponseEntity<AppUser> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getUser(username));
    }

    @DeleteMapping(path = "/users/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/user/save")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/verify").query("token=" + user.getConfirmationToken()).toUriString());
        ResponseEntity<AppUser> createdUser = ResponseEntity.created(uri).body(userService.saveUser(user));
        // send email
        EmailTemplate defaultTemplate = new EmailTemplate();
        defaultTemplate.setEmailTemplateName("Registration Confirmation");
        //<a href=\"" + "#regLink#" + "\">here</a>
        defaultTemplate.setEmailTemplateBody("<p>Dear #newUserName#,</p><p>Please click <a href=\"" + "#regLink#" + "\">here</a>  to verify your account.</p><p><br></p><p>Regards,</p><p>HR Department</p>");

        // formatting
        String templateTitle = defaultTemplate.getEmailTemplateName();
        String templateBody = defaultTemplate.getEmailTemplateBody();

        String recipient = user.getUsername();
        String recipientEmail = user.getEmail();
        // regex patterns
        templateBody = templateBody.replace("#newUserName#",recipient);
        templateBody = templateBody.replace("#regLink#",uri.toString());
        emailSenderService.sendEmail(recipientEmail,templateTitle,templateBody);
        return createdUser;
    }

    @RequestMapping(value="/verify", method= {RequestMethod.GET, RequestMethod.POST}, produces= MediaType.TEXT_HTML_VALUE)
    public String authUsers(@RequestParam("token")String confirmationToken){
        if(userService.verify(confirmationToken)){
            return "<html>\n" +
                    "<body>\n" + "Congratulations!\n" + "Your account has been activated and email is verified\n" +
                    "You may process to login now\n" + "</body>\n" + "</html>";
        }else{
            return "verify_fail";
        }

    }

    @PostMapping("/role/save")
    public ResponseEntity<UserRole> saveRole(@RequestBody UserRole role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> saveRole(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                AppUser user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",
                                user.getUserRoles().stream().map(UserRole::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                // response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
