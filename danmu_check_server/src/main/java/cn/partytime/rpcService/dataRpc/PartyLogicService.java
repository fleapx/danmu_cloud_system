package cn.partytime.rpcService.dataRpc;

import cn.partytime.model.PartyDTO;
import cn.partytime.rpcService.dataRpc.impl.PartyLogicServiceHystrix;
import cn.partytime.common.util.ServerConst;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by dm on 2017/7/6.
 */

@FeignClient(value = ServerConst.SERVER_NAME_DATASERVER,fallback = PartyLogicServiceHystrix.class)
public interface PartyLogicService {

    @RequestMapping(value = "/rpcParty/findAddressIdListByPartyId" ,method = RequestMethod.GET)
    public List<String> findAddressIdListByPartyId(@RequestParam(value = "partyId") String partyId);


    @RequestMapping(value = "/rpcParty/getPartyByAddressId" ,method = RequestMethod.GET)
    public PartyDTO getPartyId(@RequestParam(value = "addressId") String addressId);
}
