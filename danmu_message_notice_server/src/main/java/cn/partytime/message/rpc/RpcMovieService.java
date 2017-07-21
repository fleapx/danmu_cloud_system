package cn.partytime.message.rpc;

import cn.partytime.common.constants.LogCodeConst;
import cn.partytime.message.bean.MessageObject;
import cn.partytime.message.messageHandlerService.MovieTimeAlaramService;
import cn.partytime.message.model.Party;
import cn.partytime.message.proxy.MessageHandlerService;
import cn.partytime.message.rpcService.dataRpcService.PartyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dm on 2017/7/19.
 */
@RestController
@RequestMapping("/rpcMovie")
public class RpcMovieService {

    private static final Logger logger = LoggerFactory.getLogger(RpcMovieService.class);

    @Autowired
    private MovieTimeAlaramService movieTimeAlaramService;

    @Autowired
    private MessageHandlerService messageHandlerService;

    @Autowired
    private PartyService partyService;


    @RequestMapping(value = "/movieTime" ,method = RequestMethod.GET)
    public void movieTime(@RequestParam String partyId,@RequestParam String addressId, @RequestParam long time) {

        Party party = partyService.getPartyByPartyId(partyId);

        MessageObject<Map<String,Object>> mapMessageObject = null;
        Map<String,Object> map = new HashMap<String,Object>();
        if(party!=null && party.getMovieTime()!=0){
            long minute = time/1000/60;
            if(minute<60){
                //触发事件过短
                mapMessageObject = new MessageObject<Map<String,Object>>(LogCodeConst.PartyLogCode.MOVIE_TIME_TOO_SHORT,map);

            }else if(minute >150) {
                //触发时间过
                mapMessageObject = new MessageObject<Map<String,Object>>(LogCodeConst.PartyLogCode.MOVIE_TIME_TOO_SHORT,map);

            }
        }else{
            long movieTime = party.getMovieTime();
            if(time<movieTime){
                //触发事件过短
                mapMessageObject = new MessageObject<Map<String,Object>>(LogCodeConst.PartyLogCode.MOVIE_TIME_TOO_SHORT,map);
            }else if(time >movieTime) {
                //触发时间过
                mapMessageObject = new MessageObject<Map<String,Object>>(LogCodeConst.PartyLogCode.MOVIE_TIME_TOO_SHORT,map);
            }
        }

        sendMessage(mapMessageObject);
    }

    private void sendMessage(MessageObject<Map<String,Object>> map){
        messageHandlerService.messageHandler(movieTimeAlaramService,map);
    }
}