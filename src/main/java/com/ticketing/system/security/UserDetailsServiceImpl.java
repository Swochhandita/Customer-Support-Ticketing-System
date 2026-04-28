package com.ticketing.system.security;

import com.ticketing.system.entity.User;
import com.ticketing.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {// system have email as an identifier so we use email as parameter but username for only spring security convention
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email "+email));
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())//SpringSecurity stores the username, but we use email as identifier
                .password(user.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority("Role_"+ user.getRole().getName().name())))
                .build();
    }
}
