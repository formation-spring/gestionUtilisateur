package com.formation.formationspring.services.roleService;

import com.formation.formationspring.dao.IRoleRepository;
import com.formation.formationspring.dto.RoleDto;
import com.formation.formationspring.entities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleService {
    @Autowired
    IRoleRepository iRoleRepository;
    public List<RoleDto>getAllRoles(){
        return iRoleRepository.findAll().stream().map(roles-> new RoleDto(roles.getId(),
                roles.getLabel())).collect(Collectors.toList());
    }
    public  RoleDto getRoleById(UUID roleId){
         Optional<Roles> role=iRoleRepository.findById(roleId);
        RoleDto roleDto=new RoleDto();
        roleDto.setId(role.get().getId());
        roleDto.setLabel(role.get().getLabel());
        return roleDto ;
    }
    public void createRole(RoleDto roleDto){
        Roles roles=new Roles();
        roles.setId(UUID.randomUUID());
        roles.setLabel(roleDto.getLabel());
        iRoleRepository.save(roles);
    }
    public void updateRole(RoleDto roleDto) {
        Roles role=iRoleRepository.findById(roleDto.getId()).get();
        if(roleDto.getLabel()!=null){
            role.setLabel(roleDto.getLabel());
        }

        iRoleRepository.save(role);
    }
    public void deleteRole(UUID id){
        Roles role =iRoleRepository.findById(id).get();
        iRoleRepository.delete(role);
    }
 }
