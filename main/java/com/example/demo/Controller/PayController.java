package com.example.demo.Controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.demo.Entity.Flw;
import com.example.demo.Entity.User;
import com.example.demo.Service.FlwInfoService;
import com.example.demo.Service.UserService;
import com.example.demo.config.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("pay")
public class PayController {
    @Autowired
    private UserService userService;
    @Autowired
    private FlwInfoService flwInfoService;

    @RequestMapping("/index")
    public String payIndex(Model model,HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/user/login1";}
        User user = (User) session.getAttribute("user");
        String user_account = user.getUser_account();
        String id = new SimpleDateFormat("yyyyMMddHHss").format(new Date()) + UUID.randomUUID().toString().replace("-","");
        Flw flw = new Flw();
        flw.setBody("求职加油站会员购买,虚拟服务购买一旦支付无法退款！");
        flw.setId(id);
        flw.setIdName("增值服务购买");
        flw.setSum("98");
        flw.setUser_account(user_account);
        model.addAttribute("flw",flw);
        flwInfoService.addFlw(flw);
        return "payindex1";
    }
    @RequestMapping("/fail")
    public  String reten(HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/user/login1";}
        return "payFail";
    }

    @RequestMapping("/scuec")
    public  String retenPage(HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/user/login1";}
        User user2 = (User) session.getAttribute("user");
        userService.updateUserByID(user2.getUid());
        user2.setVip(true);
        session.setAttribute("user",user2);
        return "payScuec";
    }
    @PostMapping(value ="/goPay",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String pay(@RequestParam("WIDout_trade_no") String out_trade_no,
                      @RequestParam("WIDsubject") String subject,
                      @RequestParam("WIDtotal_amount") String total_amount,
                      String body,
                      HttpServletResponse response,
                      HttpSession session) throws AlipayApiException {
        if(session.getAttribute("user")==null){
            return "redirect:/user/login1";}
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        //输出
        return result;
    }
}
