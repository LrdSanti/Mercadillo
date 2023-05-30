package com.mall.mercadillo.security;

import com.mall.mercadillo.model.User;
import com.mall.mercadillo.repository.IMakerRepository;
import com.mall.mercadillo.repository.IProductRepository;
import com.mall.mercadillo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

// RECUERDE QUE VAMOS A REALIZAR ESTE PROCESO CREANDO UNA CLASE DE USUARIO PARA VALIDAR CREDENCIALES
    // Y HACER ESTO CON LOS USUARIOS!!!
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository
                .findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email: "+ email + "no existe."));

                return new UserDetailsImpl(user);

    }
}
