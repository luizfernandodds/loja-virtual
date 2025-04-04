package com.dev.loja_virtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.loja_virtual.model.SystemUser;
import com.dev.loja_virtual.repository.SystemUserRepository;

@Service
public class ImplementationUserDetailsService implements UserDetailsService {

	@Autowired
	private SystemUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		SystemUser user = userRepository.findUserByLogin(username);

		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}

		return new User(user.getLogin(), user.getPassword(), user.getAuthorities());
	}

}
