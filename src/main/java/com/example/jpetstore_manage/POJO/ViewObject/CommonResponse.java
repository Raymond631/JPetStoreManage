package com.example.jpetstore_manage.POJO.ViewObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Raymond Li
 * @create 2023-03-20 18:55
 * @description 统一消息封装类
 * 状态码
 * 消息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private int code;
    private Object data;

    public static CommonResponse success(Object data) {
        return new CommonResponse(200, data);
    }

    public static CommonResponse error(Object data) {
        return new CommonResponse(400, data);
    }
}
