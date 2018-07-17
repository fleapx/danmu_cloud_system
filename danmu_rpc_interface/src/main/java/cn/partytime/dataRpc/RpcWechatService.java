package cn.partytime.dataRpc;

import cn.partytime.dataRpc.impl.RpcWechatServiceHystrix;
import cn.partytime.model.WechatUserDto;
import cn.partytime.model.WechatUserInfoDto;
import cn.partytime.model.WechatUserWeekCountDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by dm on 2017/7/10.
 */
@FeignClient(value = "${dataRpcServer}",fallback = RpcWechatServiceHystrix.class)
public interface RpcWechatService {

    @RequestMapping(value = "/rpcWechat/findByOpenId" ,method = RequestMethod.GET)
    public WechatUserDto findByOpenId(@RequestParam(value = "openId") String openId);

    @RequestMapping(value = "/rpcWechat/findByWechatId" ,method = RequestMethod.GET)
    public WechatUserInfoDto findByWechatId(@RequestParam(value = "wechatId") String wechatId);


    @RequestMapping(value = "/rpcWechat/findByUserId" ,method = RequestMethod.GET)
    public WechatUserDto findByUserId(@RequestParam(value = "userId") String userId);



    @RequestMapping(value = "/rpcWechat/findByRegistDateInRange" ,method = RequestMethod.GET)
    public List<WechatUserInfoDto> findByRegistDateInRange(@RequestParam(value = "startDate") Date startDate, @RequestParam(value = "endDate") Date endDate );




    @RequestMapping(value = "/rpcWechat/countNewWechatUser" ,method = RequestMethod.GET)
    public void countNewWechatUser();

}
