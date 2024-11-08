package ru.gb.danila.timesheetrest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.danila.timesheetrest.model.Role;
import ru.gb.danila.timesheetrest.repository.RoleRepository;
import ru.gb.danila.timesheetrest.repository.UserRoleRepository;
import ru.gb.danila.timesheetrest.model.UserRole;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    public List<Role> findRolesByUserId(Long userId){
        List<Long> roleIds = userRoleRepository.findAllByUserId(userId).stream()
                .filter(userRole -> userRole.getRoleId() != null)
                .map(UserRole::getRoleId)
                .toList();

        return roleRepository.findAllByIdIn(roleIds);

    }
}
