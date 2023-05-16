package com.exterro.ForumDemo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exterro.ForumDemo.Dao.UserDao;
import com.exterro.ForumDemo.entity.SecurityUser;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	private final UserDao userDao;

	public JpaUserDetailsService(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDao.findByUserEmail(username).map(SecurityUser::new)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found : " + username));
	}

}
