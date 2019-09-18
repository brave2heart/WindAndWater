package com.tongcheng.soothsay.other.calendar.entities;

/**
 * 日历数据实体
 * 封装日历绘制时需要的数据
 * 
 * Entity of calendar
 *
 * @author AigeStudio 2015-03-26
 */
public class DPInfo {
    public String note;    //今天的记事标题
    public String strG;    //公历
    public String strF;    //农历
    public int noteFlag = 0;   //1为记事  2为提醒
    public boolean isHoliday;       //假日
    public boolean isChoosed;
    public boolean isToday;         //今天
    public boolean isWeekend;       //周末
    public boolean isSolarTerms;    //节气
    public boolean isFestival;      //节日
    public boolean isDeferred;
    public boolean isDecorBG;
    public boolean isDecorTL, isDecorT, isDecorTR, isDecorL, isDecorR;
}