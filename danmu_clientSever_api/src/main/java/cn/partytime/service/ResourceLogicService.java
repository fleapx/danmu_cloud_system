package cn.partytime.service;


import cn.partytime.common.util.ListUtils;
import cn.partytime.dataRpc.RpcPartyResourceResultService;
import cn.partytime.dataRpc.RpcPartyService;
import cn.partytime.model.PartyModel;
import cn.partytime.model.PartyResourceResult;
import cn.partytime.model.ResourceFileModel;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ResourceLogicService {

    @Autowired
    private RpcPartyService rpcPartyService;

    @Autowired
    private RpcPartyResourceResultService rpcPartyResourceResultService;


    public List<PartyResourceResult> findFilmResource(){
        List<PartyResourceResult> partyResourceResultList = new ArrayList<>();
        //查询所有的电影
        List<PartyModel> partyList = rpcPartyService.findByTypeAndStatus(1, 2);
        //查询电影下所有的资源
        List<String> partyIdList = new ArrayList<String>();
        if (ListUtils.checkListIsNotNull(partyList)) {
            partyList.stream().forEach(party -> partyIdList.add(party.getId()));
            log.info("partyIdList：{}",JSON.toJSONString(partyIdList));
            Map<String,List<ResourceFileModel>> resourceFileMap =  rpcPartyResourceResultService.findResourceUnderFilm(partyIdList);
            for(PartyModel party : partyList){
                PartyResourceResult partyResourceResult = new PartyResourceResult();
                partyResourceResult.setParty(party);
                if( null != resourceFileMap) {
                    partyResourceResult.setResourceFileList(resourceFileMap.get(party.getId()));
                }
                partyResourceResultList.add(partyResourceResult);
            }
        }

        return partyResourceResultList;
    }

    public List<PartyResourceResult> findPartyResource(String addressId){
        List<PartyResourceResult> partyResourceResultList = new ArrayList<>();
        List<PartyModel> partyList = rpcPartyService.findByAddressIdAndType(addressId,0);

        if(!ListUtils.checkListIsNotNull(partyList)){
            return partyResourceResultList;
        }
        //查询电影下所有的资源
        List<String> partyIdlList = new ArrayList<String>();
        if (ListUtils.checkListIsNotNull(partyList)) {
            partyList.stream().forEach(party -> partyIdlList.add(party.getId()));
            Map<String,List<ResourceFileModel>> resourceFileMap =  rpcPartyResourceResultService.findResourceUnderFilm(partyIdlList);
            for(PartyModel party : partyList){
                PartyResourceResult partyResourceResult = new PartyResourceResult();
                partyResourceResult.setParty(party);
                if( null != resourceFileMap) {
                    partyResourceResult.setResourceFileList(resourceFileMap.get(party.getId()));
                }
                partyResourceResultList.add(partyResourceResult);
            }
        }
        return partyResourceResultList;
    }

}
