package com.msglearning.javabackend.controllers;

import com.msglearning.javabackend.converters.UserConverter;
import com.msglearning.javabackend.entity.User;
import com.msglearning.javabackend.services.ImageService;
import com.msglearning.javabackend.services.Tokenservice;
import com.msglearning.javabackend.services.UserService;
import com.msglearning.javabackend.to.UserTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({ControllerConstants.API_PATH_USER})
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private static final String ALL_PATH = "/all";
    private static final String ID_PATH = "/id/{id}";
    private static final String EMAIL_PATH = "/email/{email}";
    private static final String NAME_PATH = "/name/{name}";
    private static final String PROFILE_IMAGE = "/image/{id}";
    private static final String ISMANAGER_PATH = "id/{id}/is-manager";
    private static final String DELETE_PATH = "/id/{id}/delete";
    private static final String UPDATE_PATH="/update";

    private static final String NEW_PATH = "/new";


    @Autowired
    UserService userService;

    @Autowired
    Tokenservice tokenService;

    @Autowired
    private Environment env;

    @Autowired
    private ImageService imageService;

    @GetMapping(ALL_PATH)
    public List<UserTO> getAll() {
        return userService.findAll();
    }


    @GetMapping(ID_PATH)
    public UserTO getById(@PathVariable Long id) {
        Optional<User> opUser = userService.findById(id);
        if (opUser.isPresent())
            return UserConverter.convertToTO(opUser.get());
        return null;
    }

    @GetMapping(EMAIL_PATH)
    public Optional<User> getByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping(NAME_PATH)
    public List<UserTO> getByName(@PathVariable String name) {
        return userService.findByName(name);
    }

    @GetMapping(value = PROFILE_IMAGE, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getProfileImage(@PathVariable Long id) throws IOException {
        Optional<String> imageNameOpt = userService.getProfileImage(id);
        if (imageNameOpt.isEmpty()) {
            return new byte[0];
        }
        String profileImageStoragePlace = env.getProperty("profileimage.path");
        return imageService.read(profileImageStoragePlace + "\\" + imageNameOpt.get());
    }

    @GetMapping(ISMANAGER_PATH)
    public boolean isUserManager(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user != null && user.get().getIsManager()) {
            return true;
        }

        return false;
    }

    @DeleteMapping(DELETE_PATH)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PutMapping(UPDATE_PATH)
    public Boolean updateUser(@RequestBody UserTO userTO){
        boolean res = userService.update(userTO);
        System.out.println(res);
        return res;
    }
}
