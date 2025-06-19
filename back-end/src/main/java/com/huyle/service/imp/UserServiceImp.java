package com.huyle.service.imp;

import com.huyle.config.JwtProvider;
import com.huyle.model.User;
import com.huyle.repository.UserRepository;
import com.huyle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserbyJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = findUserbyEmail(email);
        return user;
    }

    @Override
    public User findUserbyEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserById(Long id) throws Exception {
        User user = userRepository.findById(id).orElse(null);

        if(user == null) {
            throw new Exception("User not found with id: " + id);
        }
        return user;
    }
}
