package com.arlo.foroApi.controller;

import com.arlo.foroApi.infra.security.DatosJWTtoken;
import com.arlo.foroApi.infra.security.TokenService;
import com.arlo.foroApi.usuarios.DatosAutenticacacionUsuario;
import com.arlo.foroApi.usuarios.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {
    @Autowired
    private AuthenticationManager autenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacacionUsuario datosAutenticacacionUsuario){
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacacionUsuario.login(),
                datosAutenticacacionUsuario.clave());
        var usurarioAutenticado = autenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usurarioAutenticado.getPrincipal());
        System.out.println(JWTtoken);
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }
}
