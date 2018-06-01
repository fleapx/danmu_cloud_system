package cn.partytime.rpc;

import cn.partytime.model.wechat.WechatUser;
import cn.partytime.model.wechat.WechatUserInfo;
import cn.partytime.service.wechat.WechatUserInfoService;
import cn.partytime.service.wechat.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dm on 2017/7/10.
 */
@RestController
@RequestMapping("/rpcWechat")
public class RpcWechatService {

    @Autowired
    private WechatUserInfoService wechatUserInfoService;

    @Autowired
    private WechatUserService wechatUserService;

    @RequestMapping(value = "/findByWechatId" ,method = RequestMethod.GET)
    public WechatUserInfo findByWechatId(@RequestParam String wechatId){
        return wechatUserInfoService.findByWechatId(wechatId);
    }

    @RequestMapping(value = "/findByOpenId" ,method = RequestMethod.GET)
    public WechatUser findByOpenId(@RequestParam String openId) {
        return wechatUserService.findByOpenId(openId);
    }


    @RequestMapping(value = "/findByUserId" ,method = RequestMethod.GET)
    public WechatUser findByUserId(@RequestParam String userId) {
        return wechatUserService.findByUserId(userId);
    }

}
