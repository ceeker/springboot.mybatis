package org.ceeker.web.sbootm.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.ceeker.web.sbootm.common.util.ImgCodeUtil;
import org.ceeker.web.sbootm.common.util.QrcodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 验证码
 * @author zhangxiaoling01
 * @date  2016年7月4日 下午3:57:24
 * @see
 */
@Controller
@RequestMapping("/code")
public class ImgCodeController extends BaseController {

    @RequestMapping("img")
    public void imgCode(HttpServletResponse response) {
        try {
            ImageIO.write(ImgCodeUtil.getImage(6), "jpg", response.getOutputStream());
        } catch (IOException e) {
            log.error("createCode exception:" + e);
        }
    }

    @RequestMapping("qr")
    public void qrCode(HttpServletResponse response) throws Exception {
        try {
            BufferedImage qrcodeWithLogo = QrcodeUtil.getQrWithLogo("http://cn.bing.com/dict/search?q=123", 430, 430, "d:\\test\\logo.jpg");
            ImageIO.write(qrcodeWithLogo, "jpg", response.getOutputStream());
        } catch (IOException e) {
            log.error("createCode exception:" + e);
        }
    }

}
