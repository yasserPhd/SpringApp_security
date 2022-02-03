package brain.security.service;









import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import brain.security.Dao.UserRepository;
import brain.security.Model.MyUserDetails;
import brain.security.Model.UserSecurity;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserSecurity> user = userRepository.findByUserName(userName);
        System.out.println(user.get().getUserName());
        System.out.println(user.get().getPassword());
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
      
        return user.map(MyUserDetails::new).get();
    }
}
