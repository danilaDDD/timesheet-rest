package ru.gbdanila.timesheetrest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbdanila.timesheetrest.model.User;
import ru.gbdanila.timesheetrest.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findUserByLogin(String login){
        return userRepository.findByLogin(login);
    }
}
