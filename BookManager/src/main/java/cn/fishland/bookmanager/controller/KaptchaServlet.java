package cn.fishland.bookmanager.controller;

import cn.fishland.bookmanager.tool.WebTool;
import com.google.code.kaptcha.impl.DefaultKaptcha;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 验证码
 *
 * @author xiaoyu
 * @version 1.0
 */
public class KaptchaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DefaultKaptcha defaultKaptcha = WebTool.defaultKaptcha();
        // 生成文字验证码
        String text = defaultKaptcha.createText();
        // 存入session中
        req.getSession().setAttribute("code", text);
        // 生成图片验证码
        OutputStream out = resp.getOutputStream();
        BufferedImage image = defaultKaptcha.createImage(text);
        ImageIO.write(image, "jpg", out);
    }
}
