package com.formation.formationspring.controllers;

import com.formation.formationspring.dto.RoleDto;
import com.formation.formationspring.handlers.ResponseHandler;
import com.formation.formationspring.services.roleService.RoleService;
import com.formation.formationspring.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RoleController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @RequestMapping(value = "/createRole", method = RequestMethod.POST)
    public ResponseEntity createRole(@RequestBody RoleDto roleDto){
        try{
            roleService.createRole(roleDto);
        }catch(Exception e){
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "error", null);
        }
        return ResponseHandler.generateResponse(HttpStatus.CREATED, "success", null);
    }
    @RequestMapping(value = "/updateRole", method = RequestMethod.PUT)
    public ResponseEntity updateRole(@RequestBody RoleDto roleDto){
        try{
            roleService.updateRole(roleDto);
        }catch(Exception e){
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "error", null);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, "success", null);
    }
    @RequestMapping(value = "/getAllRoles", method = RequestMethod.GET)
    public ResponseEntity getAllRoles(){
        List<RoleDto> roles;

        try{
            roles=new ArrayList<>(roleService.getAllRoles());
        }catch(Exception e){
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "error", null);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, "success", roles);
    }
    @RequestMapping(value = "/getRole", method = RequestMethod.GET)
    public ResponseEntity getAllRoles(@RequestParam("id")UUID id){
        RoleDto role;
        try{
            role=roleService.getRoleById(id);
        }catch(Exception e){
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "error", null);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, "success", role);
    }
    @RequestMapping(value = "/deleteRole", method = RequestMethod.DELETE)
    public ResponseEntity deleteRole(@RequestParam("id") UUID id){
        try{
            roleService.deleteRole(id);
        }catch(Exception e){
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "error", null);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, "success", null);
    }
}
