package com.tongcheng.soothsay.other.calendar.bizs.themes;

import android.graphics.Color;

/**
 * 主题的默认实现类
 * 
 * The default implement of theme
 *
 * @author AigeStudio 2015-06-17
 */
public class DPBaseTheme extends DPTheme {

    @Override
    public int colorNote() {
        return Color.parseColor("#999999");
    }

    @Override
    public int colorNoteBg() {
        return Color.parseColor("#38C735");
    }

    @Override
    public int colorWranBg() {
        return Color.parseColor("#A9D9F5");
    }

    @Override
    public int colorBG() {
        return 0xFFFFFFFF;
    }

    @Override
    public int colorBGCircle() {
        return Color.parseColor("#66dddddd");
    }

    @Override
    public int colorTitleBG() {
        return 0xFFF37B7A;
    }

    @Override
    public int colorTitle() {
        return 0xEEFFFFFF;
    }

    @Override
    public int colorToday() {
        return Color.parseColor("#e14d33");
    }

    @Override
    public int colorG() {
        return 0xEE666666;
    }

    @Override
    public int colorF() {
        return Color.parseColor("#E5B073");
    }

    @Override
    public int colorWeekend() {
        return Color.parseColor("#e14d33");
    }

    @Override
    public int colorHoliday() {
        return Color.parseColor("#e14d33");
    }

	@Override
	public int colorTodayText() {
		return 0xEEFF733A;
	}

	@Override
	public int colorChooseText() {
		return 0x00000000;
	}
}
