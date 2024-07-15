package com.agendaki.scheduling.services.user;

import com.agendaki.scheduling.dtos.request.PreUserToSaveDTO;
import com.agendaki.scheduling.models.user.User;
import com.agendaki.scheduling.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(PreUserToSaveDTO preUserToSaveDTO) {
        User user = new User(preUserToSaveDTO.attributes());
        userRepository.save(user);
    }
}
