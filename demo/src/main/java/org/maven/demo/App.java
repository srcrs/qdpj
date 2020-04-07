package org.maven.demo;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws Exception {
        int n = 1;
            Long currentTimeMillis = System.currentTimeMillis();
            String str = "\""+ n++ +"\"";
            String jsonBody = "{\"BaseRequest\":{\"Uin\":360599249,\"Sid\":\"QAqBn494pSTeQsui\",\"Skey\":\"@crypt_ac11a6d2_7c81c37a3dd246630e219bacc1baf943\",\"DeviceID\":\"e086635885356567\"},\"Msg\":{\"Type\":1,\"Content\":"+str+",\"FromUserName\":\"@69db04d78cd53bfd91cf902610c0bf3fc19e9b1d2b09154e3cd604fbea58fc22\",\"ToUserName\":\"@@2916d5bd8e966d03c2625a554a6971db3d67ae46070ba8ad5dd2f32f5987a48f\",\"LocalID\":"+currentTimeMillis+",\"ClientMsgId\":"+currentTimeMillis+"}}";
            Connection.Response res = Jsoup.connect("https://wx2.qq.com/cgi-bin/mmwebwx-bin/webwxsendmsg")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36")
                    .requestBody(jsonBody)
                    .cookie("Cookie","_ga=GA1.2.1851665345.1578559629; Qs_lvt_323937=1578559629; Qs_pv_323937=3864376329340056600; pgv_pvid=5825823624; pgv_pvi=6083340288; RK=2bTpfdPyfB; ptcz=c347686ec372fdc57355ac246a6879f36b9cbeb673fddbc21bd83e741a9f0141; tvfe_boss_uuid=110d0cc27763bbae; sd_userid=69461581601524617; sd_cookie_crttime=1581601524617; webwxuvid=35703aab7007963535ae72632d4c927e833ffb596827dcd593ffb0eb936fc7385dacc198b98f59d8840ce3e243d3a04e; ptui_loginuin=531238678; XWINDEXGREY=0; eas_sid=H1s588C6G143v1O8h8U226v261; pac_uid=1_531238678; o_cookie=531238678; wxuin=360599249; pgv_info=ssid=s5298514763; pgv_si=s1732111360; wxsid=QAqBn494pSTeQsui; mm_lang=zh_CN; webwx_data_ticket=gSeu4r6LRr8xEa6omRaPiG+A; webwx_auth_ticket=CIsBEM6j9tgNGoABwxlor01h1lZPTseVxtmCkzpBsX2ga6qeesGDkrB+h99vvbc998UahfQGwdqD41mWSROmmOqd21ATRSKqzX/qvbhsafcxk5xFH1mevoPpbqHwjEBoJFhmOdK+/L6aA34fm5/ui4idOljnpHNyAFbzzZxTRrZEV6eKBEvHMA5yp4w=; MM_WX_NOTIFY_STATE=1; MM_WX_SOUND_STATE=1; wxloadtime=1586226126_expired; wxpluginkey=1586217384")
//                .header("Accept","application/json, text/plain, */*")
//                .header("Accept-Encoding","gzip, deflate, br")
//                .header("Accept-Language","zh-CN,zh;q=0.9,en-AS;q=0.8,en;q=0.7")
//                .header("Connection","keep-alive")
//                .header("Content-Type","application/json;charset=UTF-8")
//                .header("DNT","1")
//                .header("Host","wx2.qq.com")
//                .header("Origin","https://wx2.qq.com")
//                .header("Referer","https://wx.qq.com/")
//                .header("Sec-Fetch-Dest","empty")
//                .header("Sec-Fetch-Mode","cors")
//                .header("Sec-Fetch-Site","same-origin")
                    .method(Connection.Method.POST)
                    .execute();
            System.out.println(res.body());
            Thread.sleep(2000);
    }
}
