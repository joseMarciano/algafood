package com.api.algafood.domain.service;

import com.api.algafood.domain.Exception.EntidadeEmUsoException;
import com.api.algafood.domain.Exception.EntidadeNaoEncontradaException;
import com.api.algafood.domain.Exception.NegocioException;
import com.api.algafood.domain.model.Usuario;
import com.api.algafood.domain.repository.UsuarioRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {


    private final String MSG_USUARIO_NAO_ENCONTRADO =
            "Entity 'Usuario' with identifier %d not found";
    private final String MSG_USUARIO_EM_USO =
            "Entity 'Usuario' with identifier %d is in use";

    private final String MSG_EMAIL_EXISTENTE =
            "Email atual já está cadastrado";

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario findById(Long id){
        return repository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(MSG_USUARIO_NAO_ENCONTRADO,id)));
    }

    @Transactional
    public Usuario save(Usuario usuario){
        hasEqualEmail(usuario);
        return repository.save(usuario);
    }

    @Transactional
    public void remove(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_USUARIO_NAO_ENCONTRADO, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_USUARIO_EM_USO, id));
        }
    }

    public Usuario updateSenha(Long id, String senhaAtual, String novaSenha){
        var usuario = findById(id);
        if(!usuario.senhaCoincideCom(senhaAtual)){
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }
        usuario.setSenha(novaSenha);
        return save(usuario);
    }

    public void hasEqualEmail(Usuario usuario){
        repository.detach(usuario); // para desanexar a entidade do contexto do JPA e não dar problema quand for fazer um select dentro de um @Transactional que eu tenha algum update pendeente
        Optional<Usuario> usuarioEmail = repository.findByEmail(usuario.getEmail());
        if(usuarioEmail.isPresent() && !usuarioEmail.get().equals(usuario)){
            throw new NegocioException(
                    String.format("Já existe um usuário cadastrado com o e-mail %s",usuario.getEmail()));
        }
    }

}
