package ru.gb.danila.timesheetrest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.danila.timesheetrest.model.User;
import ru.gb.danila.timesheetrest.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findUserByLogin(String login){
        return userRepository.findByLogin(login);
    }
}
