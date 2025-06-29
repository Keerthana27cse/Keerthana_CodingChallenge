package com.example.codingchallenge.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.codingchallenge.model.Role;
import com.example.codingchallenge.model.User;
import com.example.codingchallenge.repo.UserRepo;

@Service
public class MyDbUserDetailsService implements UserDetailsService 
{
   @Autowired UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        
		User user=userRepo.findById(name).orElse(null);
		
		if(user==null)
		{
			System.out.println("user not found");
			throw new UsernameNotFoundException("User Not Found");
		}
		UserDetails principles=new  UserDetails() {
			
			@Override
			public Collection<? extends GrantedAuthority>getAuthorities()
			{
			SimpleGrantedAuthority sga=new SimpleGrantedAuthority("ROLE_"+user.getRole());
			Collection<SimpleGrantedAuthority>collectionObj=new ArrayList<SimpleGrantedAuthority>();
			collectionObj.add(sga);
			return collectionObj;
			/* return user.getRoles().stream()
			   .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
			   .collect(Collectors.toList()); */
		}
			@Override
			public String getUsername() {
				// TODO Auto-generated method stub
				return user.getName();
			}
			
			@Override
			public String getPassword() {
				// TODO Auto-generated method stub
				return user.getPassword();
			}
			
		};
		return principles;
		
}
}
