package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.AuthenticationResponse;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Response;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.User;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.UserRole;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto.RegistrationRequestDTO;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.DuplicatedEntitiesException;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.repository.UserRepository;

@Service
public class AuthenticationService {
     @Autowired
   private UserRepository userRepository;
   @Autowired
   private PasswordEncoder passwordEncoder;
   @Autowired
   private JwtService jwtService;

   @Autowired
   private AuthenticationManager authenticationManager;

   public AuthenticationResponse register(RegistrationRequestDTO request) {
      Optional<User> user = userRepository.findByUsername(request.username());
      if (user.isPresent()) {
         throw new DuplicatedEntitiesException(
               "User com o nome de " + request.username() + " ja foi cadastrado no sistema");
      }
      User User = new User();
      User.setFirstName(request.firstName());
      User.setLastName(request.lastName());
      User.setUsername(request.username());
      User.setPassword(passwordEncoder.encode(request.password()));
      User.addRole(new UserRole(request.role()));

      User = userRepository.save(User);
      String token = jwtService.generateToken(User);
      return new AuthenticationResponse(token);
   }

   public Response authenticate(User request) {

      try {
         var usernamePassword = new UsernamePasswordAuthenticationToken(
               request.getUsername(),
               request.getPassword());

         var auth = this.authenticationManager.authenticate(usernamePassword);
         User User = userRepository.findByUsername(request.getUsername()).orElseThrow();
         String token = jwtService.generateToken(User);
         UserDetails userDetails = (UserDetails) auth.getPrincipal();
         Response response = new Response();
         Set<UserRole> roles = ((User) auth.getPrincipal()).getRole();
         response.setFirstName(User.getFirstName());
         response.setLastName(User.getLastName());
         response.setUsername(userDetails.getUsername());
         response.setRoles(roles);
         response.setToken(token);
         return response;
      } catch (BadCredentialsException e) {
         throw new BadCredentialsException("User ou senha invalido!");
      }
      

   }
}
