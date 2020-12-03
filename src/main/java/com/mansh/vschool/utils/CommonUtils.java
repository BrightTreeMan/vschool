package com.mansh.vschool.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Random;

@Component
@Slf4j
public class CommonUtils {

    /***
     * 获取散列路径，防止同一文件夹下面放入太多的文件导致失效
     * @param fileId
     * @return
     */
    public static String getHashPath(String fileId) {
        //返回的散列路径
        String path = "";
        String pathNameStr = "000000000000000000" + fileId;
        //取18位字符串，按照这个字符串散列路径
        pathNameStr = pathNameStr.substring(pathNameStr.length() - 18);
        char[] pathNamecs = pathNameStr.toCharArray();
        for (int i = 0; i < pathNamecs.length; i++) {
            if (i > 14) {
                break;
            }
            path += pathNamecs[i];
            if ((i + 1) % 3 == 0) {
                path += "/";
            }
        }
        return path;
    }


    /**
     * 获取文件的后缀名称
     * @param fileName
     * @return
     */
    public static String getFileExt(String fileName) {
        String result = "";
        int extTag = fileName.lastIndexOf(".");
        if (extTag != -1) {
            int endTag = fileName.lastIndexOf("?");
            if (endTag == -1 || endTag <= extTag) {
                endTag = fileName.length();
            }
            result = fileName.substring(fileName.lastIndexOf(".") + 1, endTag);
        }
        return result;
    }

    /**
     * exception 转换成String
     * 预防runtimeException获取message为null
     * @param t
     * @return
     */
    public static String exceptionToString(Throwable t) {
        StringBuilder buf = new StringBuilder();
        if (t.getCause() != null) {
            buf.append(exceptionToString(t.getCause()));
        }

        buf.append("Exception class : " + t.getClass().getName());
        buf.append("\r\n");
        if (StrUtil.isNotBlank(t.getMessage())) {
            buf.append(t.getMessage());
            buf.append("\r\n");
        }
        for (int i = 0; i < t.getStackTrace().length; i++) {
            buf.append(t.getStackTrace()[i].toString()).append("\r\n");
        }
        return buf.toString();

    }

    /**
     * 获取request中携带的token
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request){
        return request.getHeader("x-auth-token");
    }

    /**
     * 获取IP
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ip = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    ip = "";
                    log.error(exceptionToString(e));
                }
            }
        }
        return ip;
    }

    /**
     * 绘制验证码土偶按
     * @param image
     * @param width
     * @param height
     * @return
     */
    public static String drawImage(BufferedImage image, int width, int height){
        StringBuffer sb = new StringBuffer();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(getRandomColor());
        graphics.fillRect(0,0,width,height);
        graphics.setFont(new Font("微软雅黑",Font.BOLD,30));
        Random random = new Random();
        //文字初始位置
        int x = 20;
        for(int i=0;i<4;i++){
            int type = random.nextInt(3);
            String letter = "";
            switch(type){
                case 0:
                    //数字
                    letter = String.valueOf(random.nextInt(10));
                    break;
                case 1:
                    //大写
                    letter = String.valueOf((char)(random.nextInt(26) + 65));
                    break;
                case 2:
                    //小写
                    letter = String.valueOf((char)(random.nextInt(26) + 97));
                    break;
            }

            graphics.setColor(getRandomColor());
            //偏差角度
            int angle = random.nextInt() % 30;
            //正向旋转
            graphics.rotate(angle * Math.PI / 180,x,45);
            graphics.drawString(letter,x,30);
            //反向旋转
            graphics.rotate(-angle * Math.PI / 180,x,45);

            x += 30;
            sb.append(letter);
        }

        //干扰线
        for(int i=0;i<6;i++){
            graphics.setColor(getRandomColor());
            graphics.drawLine(random.nextInt(width),random.nextInt(height),random.nextInt(width),random.nextInt(height));
        }

        //噪点
        for(int i=0;i<30;i++){
            graphics.setColor(getRandomColor());
            graphics.fillRect(random.nextInt(width),random.nextInt(height),2,2);
        }

        return sb.toString();
    }

    /**
     * 获取随机颜色
     * @return
     */
    public static Color getRandomColor(){
        Random random = new Random();
        return new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
    }

    /**
     * 日期偏移
     * @param now
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date expireDate(Date now, int year, int month, int day, int hour, int minute, int second){
        if(now == null){
            now = new Date();
        }
        if(year != 0){
            now = DateUtil.offset(now, DateField.YEAR,year);
        }
        if(month != 0){
            now = DateUtil.offset(now, DateField.MONTH,month);
        }
        if(day != 0){
            now = DateUtil.offset(now, DateField.DAY_OF_MONTH,day);
        }
        if(hour != 0){
            now = DateUtil.offset(now, DateField.HOUR_OF_DAY,hour);
        }
        if(minute != 0){
            now = DateUtil.offset(now, DateField.MINUTE,minute);
        }
        if(second != 0){
            now = DateUtil.offset(now, DateField.SECOND,second);
        }
        return now;
    }
}
