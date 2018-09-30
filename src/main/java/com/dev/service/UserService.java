package com.dev.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.dto.UserDto;
import com.dev.model.User;
import com.dev.repository.UserRepository;
import com.dev.util.ResponseHandler;
import com.dev.util.devConstants;

@Service(value="userService")
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=userRepository.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("Invalid username or password");
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthority(user));
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(User user)
	{
		Set<SimpleGrantedAuthority> authorities=new HashSet<>();
		user.getRoles().forEach(role->{
			authorities.add(new SimpleGrantedAuthority("ROLE"+role.getName()));
		});
	
			return authorities;
			
	
	}
	
	public ResponseHandler createUser(UserDto userDto)
	{
		//check for existing user
		User foundUser=userRepository.findByUsername(userDto.getUsername());
		if(foundUser==null)
		{
			User tobeCreatedUser=new User(userDto.getUsername(),bcryptEncoder.encode(userDto.getPassword()));
			User newUser=userRepository.save(tobeCreatedUser);
			return new ResponseHandler(HttpStatus.CREATED,devConstants.SUCCESS,false,newUser);
		}
		
		return new ResponseHandler(HttpStatus.BAD_REQUEST,devConstants.FAIL,true,null);
	}
	
	public User getUser(String userId)
	{
		User user=null;
		Optional <User>value=userRepository.findById(Long.parseLong(userId));
		if(value.isPresent())
		{
			user=value.get();
		}
		return user;
	}
	
	
}


