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
public class UserVO implements Serializable {
    public static final long serialVersionUID = 1L;

    private Integer usId;

    private String usName;

    private List<RoleVO> roles;

    public UserVO(User user){
        if (Objects.nonNull(user)) {
            this.usId = user.getUsId();
            this.usName = user.getUsName();
        }
    }
}
