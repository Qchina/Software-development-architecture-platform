package org.csu.petstore.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller("verCode")
@Validated
public class VerCodeController {
    @GetMapping("/account/verificationCode")
    public void generateCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException {
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Arial", Font.BOLD, 22));

        String captcha = generateRandomCode(4);
        request.getSession().setAttribute("captcha", captcha);

        g.setColor(Color.BLACK);
        g.drawString(captcha, 15, 22);

        g.dispose();
        response.setContentType("image/png");
        ImageIO.write(image, "png", response.getOutputStream());
    }

    private String generateRandomCode(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
