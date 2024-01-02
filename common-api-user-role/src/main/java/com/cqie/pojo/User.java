package com.cqie.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    public static final long serialVersionUID = 1L;

    @TableId("us_id")
    private Integer usId;

    private String usName;
}
