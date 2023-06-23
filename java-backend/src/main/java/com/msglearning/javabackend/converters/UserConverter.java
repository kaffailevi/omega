package com.msglearning.javabackend.converters;

import com.msglearning.javabackend.entity.User;
import com.msglearning.javabackend.to.UserCTO;
import com.msglearning.javabackend.to.UserTO;

public class UserConverter {

    public static final UserTO convertToTO(User entity) {
        return new UserTO(entity.getId(), entity.getFirstName(),
                entity.getLastName(), entity.getEmail(), entity.getPhone(), entity.getOccupation(), false);
    }

    public static final User convertToEntity(UserTO to) {
        return new User(to.getId(),null, to.getFirstName(), to.getLastName(), to.getEmail(), to.getPhone(), null, to.getOccupation(),null,null, false
        );
    }

    public static final User convertToEntity(UserCTO to) {
        return new User(to.getId(), to.getPassword(), to.getFirstName(), to.getLastName(), to.getEmail(), to.getPhone(), null, to.getOccupation()
        ,null,null, false);
    }

}
