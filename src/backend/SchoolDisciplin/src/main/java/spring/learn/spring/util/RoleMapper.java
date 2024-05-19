package spring.learn.spring.util;

import spring.learn.spring.model.Role;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class RoleMapper {

    public static List<Role> map(Page<Role> rolePage) {
        List<Role> dtos = new ArrayList<Role>();
        for (Role projet : rolePage) {
            dtos.add(projet);
        }
        return dtos;
    }
}
