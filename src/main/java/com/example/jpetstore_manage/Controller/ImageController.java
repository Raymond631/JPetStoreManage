package com.example.jpetstore_manage.Controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author Raymond Li
 * @create 2023-03-20 18:48
 * @description 图片处理控制器，包括图片的上传、验证码生成等功能
 */
@Slf4j
@RestController
public class ImageController {
    /**
     * 验证码接口
     */
    @GetMapping("/verificationCode")
    public void verificationCode(HttpSession session, HttpServletResponse resp) throws IOException {
        int width = 60;
        int height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();

        // 画背景
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 画边框
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        // 产生随机验证码
        String chars = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++) {
            int rand = (int) (Math.random() * 32);
            rands[i] = chars.charAt(rand);
        }
        // 存入session
        session.setAttribute("checkCode", new String(rands));

        // 画字符
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));
        g.drawString("" + rands[0], 1, 17);
        g.drawString("" + rands[1], 16, 15);
        g.drawString("" + rands[2], 31, 18);
        g.drawString("" + rands[3], 46, 16);

        // 画干扰点
        for (int i = 0; i < 120; i++) {
            int x = (int) (Math.random() * width);// 坐标
            int y = (int) (Math.random() * height);

            int red = (int) (Math.random() * 255);// 颜色
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            g.setColor(new Color(red, green, blue));

            g.drawOval(x, y, 1, 0);
        }

        // 画完图像后，释放画笔
        g.dispose();

        // 将图像传到客户端
        try (ServletOutputStream sos = resp.getOutputStream(); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            resp.setContentType("image/jpeg");
            // 设置浏览器不要缓存此图片
            resp.setHeader("Pragma", "No-cache");
            resp.setHeader("Cache-Control", "no-cache");
            resp.setDateHeader("Expires", 0);

            ImageIO.write(image, "JPEG", baos);
            byte[] buffer = baos.toByteArray();
            resp.setContentLength(buffer.length);
            sos.write(buffer);
        }
    }

    /**
     * 图片上传接口
     * TODO 开发中，有bug
     */
    @PostMapping("/uploadImage")
    public void uploadImageAjax(HttpServletRequest request) {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multiRequest.getFile("file");
        try {
            if (!multipartFile.isEmpty()) {
                // 根路径，在 resources/static/pet
                String basePath = ResourceUtils.getURL("classpath:").getPath() + "static/image/pet/";
                // 获取文件的名称
                String fileName = multipartFile.getOriginalFilename();
                // 获取文件对象
                File file = new File(basePath, fileName);
                // 完成文件的上传
                multipartFile.transferTo(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
