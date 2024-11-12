package ru.gbdanila.timesheetrest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbdanila.timesheetrest.model.Role;
import ru.gbdanila.timesheetrest.model.UserRole;
import ru.gbdanila.timesheetrest.repository.RoleRepository;
import ru.gbdanila.timesheetrest.repository.UserRoleRepository;

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
