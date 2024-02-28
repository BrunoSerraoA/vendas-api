package brunoserraoa.com.github.vendas.controller;

import brunoserraoa.com.github.vendas.dto.CredentialDto;
import brunoserraoa.com.github.vendas.dto.TokenDto;
import brunoserraoa.com.github.vendas.exception.SenhaInvalidaException;
import brunoserraoa.com.github.vendas.model.entity.Usuario;
import brunoserraoa.com.github.vendas.security.jwt.JwtService;
import brunoserraoa.com.github.vendas.service.usuario.UsuarioServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@Api
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/auth")
    public Usuario save(@RequestBody @Valid Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioService.save(usuario);
    }

    public TokenDto authenticate(@RequestBody CredentialDto credentialDto) {
        try {
            Usuario usuario = Usuario.builder()
                    .login(credentialDto.getLogin())
                    .senha(credentialDto.getSenha())
                    .build();

            UserDetails usuarioAutenticado = usuarioService.authenticate(usuario);
            String token = jwtService.gerarToken(usuario);

            TokenDto tokenDto = new TokenDto();
            tokenDto.setLogin(usuario.getLogin());
            tokenDto.setSenha(token);

            return tokenDto;

        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}