package com.wlgdo.mq.app.esUserService;


import com.wlgdo.mq.app.model.Users;

import java.util.Optional;

public interface IUserService {
    Users save(Users user);

    Users query(String userId);

    boolean update(Users user);
}
