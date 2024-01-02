package com.cqie.VO;

import com.cqie.pojo.Role;
import com.cqie.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVO implements Serializable {
    public static final long serialVersionUID = 1L;

    private Integer roId;

    private String roName;

    private List<User> users;

    public RoleVO (Role role){
        if(Objects.nonNull(role)){
            this.roId = role.getRoId();
            this.roName = role.getRoName();
        }
    }
}
