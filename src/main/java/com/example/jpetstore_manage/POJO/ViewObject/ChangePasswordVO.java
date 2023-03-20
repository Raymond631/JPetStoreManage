package com.example.jpetstore_manage.POJO.ViewObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Raymond Li
 * @create 2023-03-21 0:15
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordVO {
    private String oldPassword;
    private String newPassword;
    private String rePassword;
    private String vCode;
}
