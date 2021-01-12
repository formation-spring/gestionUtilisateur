package com.formation.formationspring.controllers;

import com.formation.formationspring.dto.UserDto;
import com.formation.formationspring.handlers.ResponseHandler;
import com.formation.formationspring.services.roleService.RoleService;
import com.formation.formationspring.services.userService.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@CrossOrigin
@Api(tags = "Users")

public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success",response = UserDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Servor Error"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public ResponseEntity createAuthenticationToken(@RequestBody UserDto accountDto){
        try{
            userService.createUser(accountDto);
        }catch(Exception e){
            //  return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "error", null);

        }
        //return new ResponseEntity<>(HttpStatus.CREATED);
        return ResponseHandler.generateResponse(HttpStatus.CREATED,  "success", null);

    }
    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody UserDto userDto){
        try{
            userService.updateUser(userDto);
        }catch(Exception e){
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "error", null);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, "success", null);
    }
    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    public ResponseEntity getAllUsers(){
        List<UserDto> users;

        try{
            users=new ArrayList<>(userService.getAllUsers());
        }catch(Exception e){
            //  return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "error", null);

        }
        //return new ResponseEntity<>(HttpStatus.CREATED);
        return ResponseHandler.generateResponse(HttpStatus.OK,  "success", users);

    }
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity getUserById(@RequestParam(value="id") UUID id){
        UserDto user;
        try{
            user=userService.getUserById(id);
        }catch(Exception e){
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, "success", user);
    }
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@RequestParam(value="id") UUID id){
         try{
           userService.deleteUser(id);
        }catch(Exception e){
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "error", null);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, "success", null);
    }


}
