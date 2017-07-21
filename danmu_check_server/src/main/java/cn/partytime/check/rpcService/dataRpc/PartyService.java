package cn.partytime.check.rpcService.dataRpc;

import cn.partytime.check.rpcService.dataRpc.impl.PartyServiceHystrix;
import cn.partytime.check.model.Party;
import cn.partytime.common.util.ServerConst;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dm on 2017/7/6.
 */

@FeignClient(value = ServerConst.SERVER_NAME_DATASERVER,fallback = PartyServiceHystrix.class)
public interface PartyService {

    @RequestMapping(value = "/rpcParty/getPartyByPartyId" ,method = RequestMethod.GET)
    public Party findById(@RequestParam(value = "partyId") String partyId);

    @RequestMapping(value = "/rpcParty/saveParty" ,method = RequestMethod.POST)
    public Party updateParty(Party party);


}