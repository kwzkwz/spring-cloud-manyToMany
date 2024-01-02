package com.cqie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable {
    public static final long serialVersionUID = 1L;

    private Integer usId;

    private Integer roId;
}
