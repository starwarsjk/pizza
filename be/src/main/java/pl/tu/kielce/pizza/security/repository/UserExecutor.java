package pl.tu.kielce.pizza.security.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.tu.kielce.pizza.department.dto.FreeManagerDto;
import pl.tu.kielce.pizza.queryHandler.NativeResultQuerySetHandler;
import pl.tu.kielce.pizza.security.dto.UserDto;
import pl.tu.kielce.pizza.security.mapper.UserMapper;
import pl.tu.kielce.pizza.security.model.jpa.Role;
import pl.tu.kielce.pizza.security.model.jpa.User;
import pl.tu.kielce.pizza.security.repository.role.RoleRepository;
import pl.tu.kielce.pizza.security.repository.user.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class UserExecutor {

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final UserMapper userMapper;

    @Autowired
    private final UserRepository userRepository;

    public UserDto save(UserDto userDto) {
        //TODO role będą pobierane z bazy wg parametrów w userDto
        Set<Role> roles = new HashSet<>(roleRepository.findAll());
        User user = userMapper.dtoToEntity(userDto);
        user.setRoles(roles);
        user = userRepository.save(user);
        return userMapper.entityToDto(user);
    }

    public UserDto findByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .map(userMapper::entityToDto)
                .orElse(null);
    }

    public List<FreeManagerDto> freeManagers() {
        return NativeResultQuerySetHandler.resultList(userRepository.findFreeManagers(), FreeManagerDto.class);
    }
}
