package com.tongcheng.soothsay.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
/**
 * 线条圆圈控件(仿华为系统天气预报UI)
 * @author zhangyu http://blog.csdn.net/chen_zhang_yu/article/details/51111125
 * @date 2016-4-1
 */
public class LineCircle extends View {

	private static final String TAG = "LineCircle";
	private float centerX;
	private float centerY;
	private int viewWidth;
	private int viewHeight;
	private Paint linePaint;
	private TextPaint whitePaint;
	// 有渐变颜色的旋转起止角度
	private float startAngle = 21;
	private float stopAngle = 123;
	// 圆半径 线长度
	private float r;
	private float l;
	private Shader shader;
	private Shader shaderWhite;
	//起止温度
	private int startTem = 19;
	private int stopTem = 24;
	//中心温度
	private int centerTemp = 0;


	public LineCircle(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public LineCircle(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public LineCircle(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {

		linePaint = new Paint();
		linePaint.setStrokeWidth(3);
		linePaint.setAntiAlias(true);

		whitePaint = new TextPaint();
		whitePaint.setColor(Color.WHITE);
		whitePaint.setStrokeWidth(4);
		whitePaint.setAntiAlias(true);
	}

	/**
	 * 设置开始角度
	 * 开始与结束角度内显示为渐变彩色，反映当前温度范围.
	 * @param startAngle 开始角度
	 * @return 设置成功返回true 否则返回false
	 */
	public boolean setStartAngle(float startAngle){
		if(startAngle >= 0 && startAngle <= 360){
			this.startAngle = startAngle;
			invalidate();
			return true;
		}else 
			return false;
	}
	
	/**
	 * 设置结束角度
	 * 开始与结束角度内显示为渐变彩色，反映当前温度范围.
	 * @param stopAngle 结束角度
	 * @return 设置成功返回true 否则返回false
	 */
	public boolean setStopAngle(float stopAngle){
		if(stopAngle >= 0 && stopAngle <= 360){
			this.stopAngle = stopAngle;
			invalidate();
			return true;
		}else 
			return false;
	}

	/**
	 * 设置中心温度
	 */
	public void setCenterTemp(String temp){
		centerTemp = Integer.valueOf(temp);
		invalidate();
	}


	/**
	 * 设置起始温度
	 * @param startTem
	 */
	public void setStartTem(int startTem){
		this.startTem = startTem;
		invalidate();
	}
	
	/**
	 * 设置截止温度
	 * @param stopTem
	 */
	public void setStopTem(int stopTem){
		this.stopTem = stopTem;
		invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {

		for (double angle = 0; angle <= 360d; angle += 3.0d) {
			float xStart = calculateX(r, angle);
			float xStop = calculateX(r - l, angle);

			float yStart = calculateY(r, angle);
			float yStop = calculateY(r - l, angle);

			if (angle <= stopAngle && angle >= startAngle) {
				linePaint.setShader(shader);
			} else
				linePaint.setShader(shaderWhite);

			if (angle == 204 || angle == 156) {
				linePaint.setStrokeWidth(2);
				float xStartL = calculateX(r * 1.05f, angle);
				float xStopL = calculateX((r - l), angle);

				float yStartL = calculateY(r * 1.05f, angle);
				float yStopL = calculateY((r - l), angle);
				canvas.drawLine(xStartL, yStartL, xStopL, yStopL, linePaint);		//底部两条较长的线
			} else if (!(angle < 204 && angle > 156)) {
				linePaint.setStrokeWidth(3);
				canvas.drawLine(xStart, yStart, xStop, yStop, linePaint);		//	画短线
			}
			Log.d(TAG, "angle = " + angle + ",xStart = " + xStart + "，xStop = " + xStop + "，yStart = " + yStart + "，yStop = " + yStop);
		}

		drawCenterTem(canvas);
		drawStartTem(canvas);
		drawStopTem(canvas);
		super.onDraw(canvas);
	}

	/**
	 * 根据半径和角度计算x坐标
	 */
	private float calculateX(float r, double angle) {
		angle = angle * ((2 * Math.PI) / 360);
		Log.i(TAG, "angle = " + angle + ",Math.sin(angle) = " + Math.sin(angle));
		double x = r * Math.sin(angle);

		double xFinal = centerX + x;
		return (float) xFinal;
	}

	/**
	 * 根据半径和角度计算y坐标
	 */
	private float calculateY(float r, double angle) {
		angle = angle * ((2 * Math.PI) / 360);
		Log.i(TAG, "angle = " + angle + ",Math.cos(angle) = " + Math.cos(angle));
		double y = r * Math.cos(angle);

		double yFinal = centerY - y;
		return (float) yFinal;
	}

	/**
	 * 画中心位置温度
	 * 
	 * @param canvas
	 */
	private void drawCenterTem(Canvas canvas) {
		whitePaint.setTextSize(r * 0.7f);
		whitePaint.setTypeface(Typeface.DEFAULT);

        //因为如果是零下温度（-21）的话，按理说就是小于10的，所以也是从指定x坐标画起，就会超出圆圈，不好看，获取绝对值即可解决。
        int absCenterTemp = Math.abs(centerTemp);

        if(absCenterTemp < 10){
			canvas.drawText(centerTemp + "°", (centerX - r * 0.25f), (centerY + r * 0.25f), whitePaint);

		}else{
			canvas.drawText(centerTemp + "°", (centerX - r * 0.42f), (centerY + r * 0.25f), whitePaint);
		}
	}

	/**
	 * 画起始温度
	 */
	private void drawStartTem(Canvas canvas) {
		whitePaint.setTextSize(r * 0.1f);
		canvas.drawText(startTem + "°", calculateX(r * 1.05f, startAngle), calculateY(r * 1.05f, startAngle), whitePaint);
	}

	/**
	 * 画截至温度
	 */
	private void drawStopTem(Canvas canvas) {
		whitePaint.setTextSize(r * 0.1f);
		canvas.drawText(stopTem + "°", calculateX(r * 1.05f, stopAngle), calculateY(r * 1.05f, stopAngle), whitePaint);
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		viewWidth = getWidth();
		viewHeight = getHeight();

		centerX = viewWidth / 2f;
		centerY = viewHeight / 2f;
		r = viewWidth * 0.4f;
		l = viewWidth * 0.05f;
		Log.v(TAG, "centerX = " + centerX + ",centerY = " + centerY);

		/* 设置渐变色 */
		shader = new SweepGradient(centerX, centerY, new int[] { Color.parseColor("#C8DB3D"), Color.parseColor("#F19F51") }, null);
		shaderWhite = new SweepGradient(centerX, centerY, new int[] { Color.WHITE, Color.WHITE }, null);
		Matrix matrix = new Matrix();
		// 使用matrix改变渐变色起始位置，默认是在90度位置
		matrix.setRotate(45, centerX, centerY);
		shader.setLocalMatrix(matrix);
		linePaint.setShader(shader);

		invalidate();
		super.onWindowFocusChanged(hasWindowFocus);
	}
}