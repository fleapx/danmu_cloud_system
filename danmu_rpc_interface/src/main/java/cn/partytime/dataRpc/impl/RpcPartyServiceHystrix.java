package cn.partytime.dataRpc.impl;

import cn.partytime.dataRpc.RpcPartyService;
import cn.partytime.model.PartyLogicModel;
import cn.partytime.model.PartyModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class RpcPartyServiceHystrix implements RpcPartyService {

    @Override
    public PartyModel findByMovieAliasOnLine(String command) {
        return null;
    }

    @Override
    public List<String> findAddressIdListByPartyId(String partyId) {
        return null;
    }

    @Override
    public PartyLogicModel findPartyByAddressId(String addressId) {
        return null;
    }
    @Override
    public PartyLogicModel findFilmByAddressId(String addressId) {
        return null;
    }

    @Override
    public PartyLogicModel findTempPartyByAddressId(String addressId) {
        return null;
    }

    @Override
    public PartyModel getPartyByPartyId(String partyId) {
        return null;
    }

    @Override
    public void deleteParty(String partyId) {

    }

    @Override
    public boolean checkPartyIsOver(PartyModel partyModel) {
        return false;
    }

    @Override
    public PartyLogicModel findPartyByLonLat(Double longitude, Double latitude) {
        return null;
    }

    @Override
    public PartyLogicModel findTemporaryParty(String addressId) {
        return null;
    }

    @Override
    public int getPartyDmDensity(String addressId, String partyId) {
        return 0;
    }

    @Override
    public List<PartyModel> findByAddressIdAndStatus(String addressId, Integer status) {
        return null;
    }

    @Override
    public List<PartyModel> findByAddressIdAndType(@RequestParam(name = "addressId") String addressId, @RequestParam(name = "type") Integer type) {
        return null;
    }

    @Override
    public List<PartyModel> findByTypeAndStatus(Integer type, Integer status) {
        return null;
    }

    @Override
    public PartyModel saveParty(PartyModel party) {
        return null;
    }

    @Override
    public boolean findCurrentParyIsInProgress(PartyModel party) {
        return false;
    }




}
