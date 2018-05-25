package cn.partytime.model.danmu;

import cn.partytime.baseModel.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

/**
 * Created by dm on 2017/5/16.
 */

@Document(collection = "danmu_log")
public class DanmuLog extends BaseModel {


    private String id;

    /**弹幕编号*/
    private String danmuId;

    /**
     * 模板编号
     */
    private String templateId;

    /**
     * 模板Key
     */
    private String templateIdKey;

    /**
     * 弹幕内容
     */
    private Map<String,Object> content;



    //弹幕池
    private String danmuPoolId;

    /**审核员编号*/
    private String checkUserId;

    /***弹幕来源 管理员:0,微信用户:1*/
    private int danmuSrc;

    /**0:非语音 1：语音弹幕*/
    private int type;

    /**
     * 开始时刻（当前时间-电影开始时间）
     */
    private int time;
    /**屏蔽状态*/
    private Boolean isBlocked = false;
    /**是否查看状态*/
    private boolean viewFlg=false;


    /**
     * 发送状态 0：未发送 1：已发送  2：发送成功
     */
    private int sendStatus = 0;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDanmuId() {
        return danmuId;
    }

    public void setDanmuId(String danmuId) {
        this.danmuId = danmuId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }

    public String getDanmuPoolId() {
        return danmuPoolId;
    }

    public void setDanmuPoolId(String danmuPoolId) {
        this.danmuPoolId = danmuPoolId;
    }

    public String getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId;
    }

    public int getDanmuSrc() {
        return danmuSrc;
    }

    public void setDanmuSrc(int danmuSrc) {
        this.danmuSrc = danmuSrc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isViewFlg() {
        return viewFlg;
    }

    public void setViewFlg(boolean viewFlg) {
        this.viewFlg = viewFlg;
    }

    public String getTemplateIdKey() {
        return templateIdKey;
    }

    public void setTemplateIdKey(String templateIdKey) {
        this.templateIdKey = templateIdKey;
    }

    public int getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(int sendStatus) {
        this.sendStatus = sendStatus;
    }
}
