package com.arcanus.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.arcanus.todolist.user.repository.UserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

  @Autowired
  private UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    var servletPath = request.getServletPath();

    if (servletPath.startsWith("/tasks")) {
      // Buscar autenticação. Utilizando Basic Auth
      var authorization = request.getHeader("Authorization");
      var authEncoded = authorization.substring("Basic".length()).trim();
      byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
      String authString = new String(authDecoded);

      String[] credentials = authString.split(":");
      System.out.println("CREDENTIAKS ++++++++++ " + credentials);
      String username = credentials[0];
      System.out.println("username ++++++++++ " + username);
      String password = credentials[1];
      System.out.println("username ++++++++++ " + password);

      // Validar usuário
      var user = this.userRepository.findByUsername(username);
      if (user.isPresent() == false) {
        response.sendError(401);
        // throw new UserUnauthorizedException("Usuário não possui autorização.");
      } else {
        // Validar senha
        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.get().getPassword());
        if (passwordVerify.verified) {
          request.setAttribute("userId", user.get().getId());
          filterChain.doFilter(request, response);
        } else {
          response.sendError(401);
        }
      }
    } else {
      filterChain.doFilter(request, response);
    }

  }
}
