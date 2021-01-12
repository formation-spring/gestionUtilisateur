package com.formation.formationspring.services.userService;

import com.formation.formationspring.dao.IRoleRepository;
import com.formation.formationspring.dao.IUserRepository;
import com.formation.formationspring.dto.RoleDto;
import com.formation.formationspring.dto.UserDto;
import com.formation.formationspring.entities.Roles;
import com.formation.formationspring.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    IRoleRepository iRoleRepository;
      public List<UserDto>getAllUsers(){
        return iUserRepository.findAll().stream().map(user-> new UserDto(user.getId(),
                user.getLastName(),
                user.getFirstName(),
                user.getEmail(),
                user.getPassword(),
                user.getPseudo(),
                user.getRoles().stream().map(role->new RoleDto(role.getId(),role.getLabel()))
                        .collect(Collectors.toList()))).collect(Collectors.toList());
    }
    public  UserDto getUserById(UUID userId){
        Optional<User> user=iUserRepository.findById(userId);
        UserDto userDto=new UserDto();
        userDto.setId(user.get().getId());
        userDto.setLastName(user.get().getLastName());
        userDto.setFirstName(user.get().getFirstName());
        userDto.setEmail(user.get().getEmail());
        userDto.setPassword(user.get().getPassword());
        userDto.setPseudo(user.get().getPseudo());
        userDto.setRoles(user.get().getRoles().stream().map(role->new RoleDto(role.getId(),role.getLabel()))
                .collect(Collectors.toList()));
        return userDto ;
    }
    public void createUser(UserDto userDto) {
        User user=new User();
        user.setId(UUID.randomUUID());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setPseudo(userDto.getPseudo());

        user.setRoles(userDto.getRoles().stream().map(role -> new Roles(role.getId(),role.getLabel())).collect(Collectors.toList()));
        user.setRoles(new ArrayList<Roles>());//pour que la liste des roles ne soit pas null
        for(RoleDto role : userDto.getRoles()){
            Roles userRoles=iRoleRepository.findById(role.getId()).get();

            user.getRoles().add(userRoles);
        }
        iUserRepository.save(user);
    }
      @Transactional
    public void updateUser(UserDto userDto) {
        User user=iUserRepository.findById(userDto.getId()).get();

        if(userDto.getFirstName()!=null){

            user.setFirstName(userDto.getFirstName());
        }
        if(userDto.getLastName()!=null){

            user.setLastName(userDto.getLastName());
        }
        if(userDto.getPassword()!=null){

            user.setPassword(userDto.getPassword());
        }
        if(userDto.getEmail()!=null){

            user.setEmail(userDto.getEmail());}

        if(userDto.getPseudo()!=null){

            user.setPseudo(userDto.getPseudo());
        }

        user.setRoles(new ArrayList<>());
        for(RoleDto role : userDto.getRoles()){
            Roles userRoles=iRoleRepository.findById(role.getId()).get();

            user.getRoles().add(userRoles);
        }
        iUserRepository.save(user);
    }
    public void deleteUser(UUID id){
        User user=iUserRepository.findById(id).get();
        iUserRepository.delete(user);
    }
}
