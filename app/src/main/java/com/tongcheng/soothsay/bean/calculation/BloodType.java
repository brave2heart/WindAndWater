package com.tongcheng.soothsay.bean.calculation;

import java.io.Serializable;

/**
 * Created by ALing on 2016/12/1 0001.
 */

public class BloodType implements Serializable {

    /**
     * title : A型血男生和A型血女生配对
     * index : 67
     * detailContent : 直性子的O型人不拘小节，粗枝大叶，而A型人办事细致缜密，正可补其不足。反过来，生气勃勃的O型又以其执着专注的行动带领顾虑重重行动过慎的A型共同前进。无论是一起工作还是共同生活，在相互补偏救弊这一点上，O型同A型可以说是最佳匹配了。
     这组关系一般以O型上前台，A型居幕后一担任诸如导演、参谋、教练、助手等职——为佳，这样的搭配是稳当而强有力的。只是当A型在思想上钻牛角尖而欲蛮干时，注重实际的O型也会对其起控制作用。
     “A-O”组的辅助关系一般都以A型为辅助者。所以当他们是上下级关系时，若O型为主，A型则细致周全地辅佐第一线的O型者；若A型执要，他们一般居后扶掖，指导，乃至服务，O型则安心奔走于外。
     从表象也能看出，他们彼此诚悦相待。A型十分欣赏同伴的直爽单纯；由于对方的活动能力正是自己之所短，所以他们甚至怀有近乎憧憬的心情。而O型呢？特别钦佩对方考虑问题全面周到，办事严谨合理的特性，感到自己的伙伴是可靠的。这样，O型的刚愎自用和A型的固执刻板，因相互好感而抵消，所以他们之间较少发生冲突。
     不过，出现以下情况时，“A-O”组合有破裂的危险：当A型长期处于被支配的地位，其思想和行动受到对方蛮横的遏止，而感到不满时；当O型的务实性同A型的信念发生强烈碰撞时；当A型的内心不为O型所理解时；当还不解对方对自己是否怀有好感时等等。O型人同O型人之间具有很强的凝聚力。同时，他们破裂的可能性也很大。
     * instructionTitle : 速配说明
     * titleContent : A型男特质：含蓄、情绪化、直觉敏锐、自尊心强、稍微怯懦、有强烈责任感
     O型女特质：理性、坚定信念、领悟力强、意志坚强、包容性、不义气用事
     * detailTile : 这是一组投手与接手式的关系
     * instructionContent : A型男与O型女都是完美主义者，你们之间常常存在一些小摩擦。不过，所谓“打是亲骂是爱”，在争吵中，你们感情反而不断升温。
     * remindeContent : A型男：你在她面前是一个注重细节的完美主义者，若无法包容她的缺点与不足，你们的感情也就走到了尽头。
     O型女：你在他面前性情飘浮不定，若即若离的样子，会让他受到严惩严重的打击，无法忍受而导致感情的破裂。
     * remindTitle : 重要提醒
     */
    private String boyIndex;
    private String girlIndex;
    private String title;
    private String index;
    private String detailContent;
    private String instructionTitle;
    private String titleContent;
    private String detailTile;
    private String instructionContent;
    private String remindeContent;
    private String remindTitle;

    public String getBoyIndex() {
        return boyIndex;
    }

    public void setBoyIndex(String boyIndex) {
        this.boyIndex = boyIndex;
    }

    public String getGirlIndex() {
        return girlIndex;
    }

    public void setGirlIndex(String girlIndex) {
        this.girlIndex = girlIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }

    public String getInstructionTitle() {
        return instructionTitle;
    }

    public void setInstructionTitle(String instructionTitle) {
        this.instructionTitle = instructionTitle;
    }

    public String getTitleContent() {
        return titleContent;
    }

    public void setTitleContent(String titleContent) {
        this.titleContent = titleContent;
    }

    public String getDetailTile() {
        return detailTile;
    }

    public void setDetailTile(String detailTile) {
        this.detailTile = detailTile;
    }

    public String getInstructionContent() {
        return instructionContent;
    }

    public void setInstructionContent(String instructionContent) {
        this.instructionContent = instructionContent;
    }

    public String getRemindeContent() {
        return remindeContent;
    }

    public void setRemindeContent(String remindeContent) {
        this.remindeContent = remindeContent;
    }

    public String getRemindTitle() {
        return remindTitle;
    }

    public void setRemindTitle(String remindTitle) {
        this.remindTitle = remindTitle;
    }
}
