package com.gpt.gptdev.service;

import com.gpt.gptdev.entity.User;


import java.util.List;

public interface UserService {
    public List<User> getUser(String user_id);

}
