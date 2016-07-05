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
            BufferedImage qrcodeWithLogo = QrcodeUtil.getQrWithLogo("http://cn.bing.com/dict/search?q=%E5%87%A0%E7%82%B9%E7%9D%A1%E4%BA%86%E9%99%84%E8%BF%91%E5%8F%AF%E6%8B%89%E4%BC%B8%E6%9C%BA%E7%9A%84%E5%BC%97%E5%85%B0%E5%85%8B%E5%88%BB%E5%BD%95%E6%9C%BA%E8%BE%A3%E6%A4%92%E7%B2%89%E5%9E%83%E5%9C%BE%E7%9D%A1%E6%88%BF%E9%AA%84%E5%82%B2%E4%BA%86%E5%87%8F%E8%82%A5%E9%98%BF%E6%8B%89%E6%96%AF%E5%8A%A0fl&go=%E6%90%9C%E7%B4%A2&qs=n&form=Z9LH5&pq=%E5%87%A0%E7%82%B9%E7%9D%A1%E4%BA%86%E9%99%84%E8%BF%91%E5%8F%AF%E6%8B%89%E4%BC%B8%E6%9C%BA%E7%9A%84%E5%BC%97%E5%85%B0%E5%85%8B%E5%88%BB%E5%BD%95%E6%9C%BA%E8%BE%A3%E6%A4%92%E7%B2%89%E5%9E%83%E5%9C%BE%E7%9D%A1%E6%88%BF%E9%AA%84%E5%82%B2%E4%BA%86%E5%87%8F%E8%82%A5%E9%98%BF%E6%8B%89%E6%96%AF%E5%8A%A0fl&sc=0-35&sp=-1&sk=&cvid=E3597C62A7A04CB8B1DB419D5F6080E2",830,830, "d:\\test\\logo.jpg");
            ImageIO.write(qrcodeWithLogo, "jpg", response.getOutputStream());
        } catch (IOException e) {
            log.error("createCode exception:" + e);
        }
    }

}
