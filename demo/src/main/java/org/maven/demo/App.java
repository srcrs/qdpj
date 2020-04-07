package org.maven.demo;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class App {

    public static void main(String[] args) throws Exception {
        int n=20;
        while(n--!=0){
            Long currentTimeMillis = System.currentTimeMillis();
            String str = "\""+ currentTimeMillis +"\"";
            String jsonBody = "{\"BaseRequest\":{\"Uin\":360599249,\"Sid\":\"ie24LHh3pS4RSniX\",\"Skey\":\"@crypt_ac11a6d2_d0bfaa4cf5445d034feb5d7df90dc659\",\"DeviceID\":\"e"+currentTimeMillis+"\"},\"Msg\":{\"Type\":1,\"Content\":"+str+",\"FromUserName\":\"@e90c30065a99ccd1d927062265acf4a359802fc47f71d0db53dbe99240502997\",\"ToUserName\":\"filehelper\",\"LocalID\":\""+currentTimeMillis+"\",\"ClientMsgId\":\""+currentTimeMillis+"\"}}";
            Connection.Response res = Jsoup.connect("https://webpush.wx2.qq.com/cgi-bin/mmwebwx-bin/synccheck?r="+currentTimeMillis+"&skey=%40crypt_ac11a6d2_d0bfaa4cf5445d034feb5d7df90dc659&sid=ie24LHh3pS4RSniX&uin=360599249&deviceid=e984927812198809&synckey=1_705994426|2_705994886|3_705994870|11_705994812|19_210|201_1586259513|202_1586259357|203_1586257254|206_3|1000_1586257171|1001_1586257814&_="+currentTimeMillis)
                    .userAgent("Mozilla/5.0 (Linux; Android 9.0; BKL-AL20 Build/HUAWEIBKL-AL20; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044409 Mobile Safari/537.36 wxwork/2.7.2 MicroMessenger/6.3.22 NetType/WIFI Language/zh")
  //                  .requestBody(jsonBody)
                    .cookie("Cookie","_ga=GA1.2.1851665345.1578559629; Qs_lvt_323937=1578559629; Qs_pv_323937=3864376329340056600; pgv_pvid=5825823624; pgv_pvi=6083340288; RK=2bTpfdPyfB; ptcz=c347686ec372fdc57355ac246a6879f36b9cbeb673fddbc21bd83e741a9f0141; tvfe_boss_uuid=110d0cc27763bbae; sd_userid=69461581601524617; sd_cookie_crttime=1581601524617; webwxuvid=35703aab7007963535ae72632d4c927e833ffb596827dcd593ffb0eb936fc7385dacc198b98f59d8840ce3e243d3a04e; ptui_loginuin=531238678; XWINDEXGREY=0; eas_sid=H1s588C6G143v1O8h8U226v261; pac_uid=1_531238678; o_cookie=531238678; wxuin=360599249; pgv_info=ssid=s5298514763; pgv_si=s1732111360; mm_lang=zh_CN; MM_WX_NOTIFY_STATE=1; MM_WX_SOUND_STATE=1; wxsid=ie24LHh3pS4RSniX; webwx_data_ticket=gSfGyFMVSb2JeEu/0dRHbv64; webwx_auth_ticket=CIsBEKmlipQEGoAB8wV+HrL86zLDBHncl8zCtTpBsX2ga6qeesGDkrB+h99vvbc998UahfQGwdqD41mWSROmmOqd21ATRSKqzX/qvbhsafcxk5xFH1mevoPpbqHwjEBoJFhmOdK+/L6aA34fm5/ui4idOljnpHNyAFbzzZxTRrZEV6eKBEvHMA5yp4w=; wxloadtime=1586250026_expired; wxpluginkey=1586246425")
//                .header("Accept","application/json, text/plain, */*")
//                .header("Accept-Encoding","gzip, deflate, br")
//                .header("Accept-Language","zh-CN,zh;q=0.9,en-AS;q=0.8,en;q=0.7")
//                .header("Connection","keep-alive")

                    .header("Content-Type","application/json;charset=UTF-8")
                    .method(Connection.Method.GET)
                    .execute();
            System.out.println(res.body());
            if(n%2!=0){
                Connection.Response rew = Jsoup.connect("https://wx2.qq.com/cgi-bin/mmwebwx-bin/webwxsendmsg")
                        .userAgent("Mozilla/5.0 (Linux; Android 9.0; BKL-AL20 Build/HUAWEIBKL-AL20; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044409 Mobile Safari/537.36 wxwork/2.7.2 MicroMessenger/6.3.22 NetType/WIFI Language/zh")
                        .cookie("Cookie","_ga=GA1.2.1851665345.1578559629; Qs_lvt_323937=1578559629; Qs_pv_323937=3864376329340056600; pgv_pvid=5825823624; pgv_pvi=6083340288; RK=2bTpfdPyfB; ptcz=c347686ec372fdc57355ac246a6879f36b9cbeb673fddbc21bd83e741a9f0141; tvfe_boss_uuid=110d0cc27763bbae; sd_userid=69461581601524617; sd_cookie_crttime=1581601524617; webwxuvid=35703aab7007963535ae72632d4c927e833ffb596827dcd593ffb0eb936fc7385dacc198b98f59d8840ce3e243d3a04e; ptui_loginuin=531238678; XWINDEXGREY=0; eas_sid=H1s588C6G143v1O8h8U226v261; pac_uid=1_531238678; o_cookie=531238678; wxuin=360599249; pgv_info=ssid=s5298514763; pgv_si=s1732111360; mm_lang=zh_CN; MM_WX_NOTIFY_STATE=1; MM_WX_SOUND_STATE=1; wxsid=ie24LHh3pS4RSniX; webwx_data_ticket=gSfGyFMVSb2JeEu/0dRHbv64; webwx_auth_ticket=CIsBEKmlipQEGoAB8wV+HrL86zLDBHncl8zCtTpBsX2ga6qeesGDkrB+h99vvbc998UahfQGwdqD41mWSROmmOqd21ATRSKqzX/qvbhsafcxk5xFH1mevoPpbqHwjEBoJFhmOdK+/L6aA34fm5/ui4idOljnpHNyAFbzzZxTRrZEV6eKBEvHMA5yp4w=; wxloadtime=1586250026_expired; wxpluginkey=1586246425")
                        .header("Content-Type","application/json;charset=UTF-8")
                        .method(Connection.Method.POST)
                        .requestBody(jsonBody)
                        .execute();
                System.out.println(rew.body());
            }
            Thread.sleep(15000);
        }
    }
}
