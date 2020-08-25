package br.com.alura.forum.security.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.forum.model.User;
import br.com.alura.forum.repository.UserRepository;

@Service
public class UsersService implements UserDetailsService {

	private UserRepository userRepository;
	
	UsersService(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
		Optional<User> possibleUser = userRepository.findByEmail(username);
		return possibleUser
				.orElseThrow(() -> 
				new UsernameNotFoundException("Não foi possível autenticar"));
		
	}
	
	
	public UserDetails loadUserById (Long userId) {
		Optional<User> possibleUser = userRepository.findById(userId);
		return possibleUser.orElseThrow(() -> new UsernameNotFoundException("Não foi possível autenticar"));
	}


}
