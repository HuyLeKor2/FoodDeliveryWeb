package com.huyle.service;

import com.huyle.model.User;

public interface UserService {
    public User findUserbyJwtToken(String jwt) throws Exception;

    public User findUserbyEmail(String email) throws Exception;

    public User findUserById(Long id) throws Exception;

}
