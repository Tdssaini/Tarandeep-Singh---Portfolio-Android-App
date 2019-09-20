package com.tarandeep.app.Utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;

import com.tarandeep.app.R;


public class TDSDrawable {

	public static final String IOS_NEW_BUTTON = "iOS_New_Button";
	public static final String RED_BUTTON="RED_BUTTON";
	public static final String TRANSPARENT="TRANSPARENT";
	public static final String BLUE_BUTTON="BLUE_BUTTON";
	public static final String GREEN_BUTTON="GREEN_BUTTON";
	public static final String ROWCOLOR_BUTTON="ROWCOLOR_BUTTON";
	public static final String IOS_DEFAULT_BUTTON="IOS_DEFAULT_BUTTON";



	public static Drawable getButtonDrawableTheam(Context context, String colorCode){
		StateListDrawable states = new StateListDrawable();
		states.addState(new int[] {android.R.attr.state_pressed}, getPressedButtonStyle(context,colorCode));
		states.addState(new int[] {android.R.attr.state_focused},  getPressedButtonStyle(context,colorCode));
		states.addState(new int[] {android.R.attr.state_selected},  getPressedButtonStyle(context,colorCode));
		states.addState(new int[] {android.R.attr.state_checked},  getPressedButtonStyle(context,colorCode));
		states.addState(new int[] { },  getNormalButtonStyle(context,colorCode));
		return states;
	}
	public static  Drawable getNormalButtonStyle(Context context, String colorCode){
		int[] colors=new int[2];
		GradientDrawable child1=null;
		if(colorCode==RED_BUTTON){
			colors= new int[]{context.getResources().getColor(R.color.darkRed), context.getResources().getColor(R.color.mediumRed)};
		}else if(colorCode==IOS_NEW_BUTTON){
			colors = new int[]{context.getResources().getColor(R.color.iOSnewButton),context.getResources().getColor(R.color.iOSnewButton)};
		}else if(colorCode==IOS_DEFAULT_BUTTON){
			colors= new int[]{context.getResources().getColor(R.color.iOSDefaultDark), context.getResources().getColor(R.color.iOSDefaultMedium)};
		}else  if(colorCode==ROWCOLOR_BUTTON){
			colors= new int[]{context.getResources().getColor(R.color.rowDark), context.getResources().getColor(R.color.rowMedium)};
		}else  if(colorCode==BLUE_BUTTON){
			colors= new int[]{context.getResources().getColor(R.color.blueDark), context.getResources().getColor(R.color.blueMedium)};
		}else if(colorCode==GREEN_BUTTON){
			colors= new int[]{context.getResources().getColor(R.color.greenDark), context.getResources().getColor(R.color.greenMedium)};
		}else if(colorCode==TRANSPARENT){
			colors= new int[]{context.getResources().getColor(R.color.tabTransparent), context.getResources().getColor(R.color.tabTransparent)};
		}else{
			colors= new int[]{context.getResources().getColor(R.color.blackDark), context.getResources().getColor(R.color.blackMedium)};
		}
		child1 = new GradientDrawable(Orientation.BOTTOM_TOP,colors);
		child1.setCornerRadius(5);
		LayerDrawable child2=(LayerDrawable)context.getResources().getDrawable(R.drawable.button_buttom);
		LayerDrawable ld = new LayerDrawable(new Drawable[]{child1,child2}); 
		return ld;
	}
	public static  Drawable getPressedButtonStyle(Context context, String colorCode){
		int[] colors=new int[2];
		GradientDrawable child1 =null;
		if(colorCode==RED_BUTTON){
			colors= new int[]{context.getResources().getColor(R.color.mediumRed), context.getResources().getColor(R.color.mediumRed)};
		}else if(colorCode==IOS_DEFAULT_BUTTON){
			colors= new int[]{context.getResources().getColor(R.color.iOSDefaultMedium), context.getResources().getColor(R.color.iOSDefaultMedium)};
		}else  if(colorCode==ROWCOLOR_BUTTON){
			colors= new int[]{context.getResources().getColor(R.color.rowMedium), context.getResources().getColor(R.color.rowMedium)};
		}else  if(colorCode==BLUE_BUTTON){
			colors= new int[]{context.getResources().getColor(R.color.tabTransparent), context.getResources().getColor(R.color.blueMedium)};
		}else if(colorCode==GREEN_BUTTON){
			colors= new int[]{context.getResources().getColor(R.color.tabTransparent), context.getResources().getColor(R.color.greenMedium)};
		}else if(colorCode==TRANSPARENT){
			colors= new int[]{context.getResources().getColor(android.R.color.holo_blue_light), context.getResources().getColor(android.R.color.holo_blue_light)};
		}else{
			colors= new int[]{context.getResources().getColor(R.color.blackMedium), context.getResources().getColor(R.color.blackMedium)};
		}
		if(child1==null)
			child1 = new GradientDrawable(Orientation.BOTTOM_TOP,colors);
		LayerDrawable ld = new LayerDrawable(new Drawable[]{child1}); 
		return ld;
	}

	public static  StateListDrawable getSelectorDefault(Context context, String image){
		StateListDrawable states = new StateListDrawable();
		states.addState(new int[] {android.R.attr.state_pressed}, getDrawableByImgName(context,image+"_press"));
		states.addState(new int[] {android.R.attr.state_focused},  getDrawableByImgName(context,image+"_press"));
		states.addState(new int[] {android.R.attr.state_checked},  getDrawableByImgName(context,image+"_press"));
		states.addState(new int[] {android.R.attr.state_selected},  getDrawableByImgName(context,image+"_press"));
		states.addState(new int[] { },  getDrawableByImgName(context,image+"_default"));
		return states;
	}

	public static Drawable getDrawableByImgName(Context context,String imgName){
		return context.getResources().getDrawable(getDrawableId(context, imgName));
	}
	public static GradientDrawable getRoundedCornerDrawable(int  colorCode, float[] radii){
		GradientDrawable gradientDrawable = new GradientDrawable(Orientation.BOTTOM_TOP,new int[]{});
		gradientDrawable.setStroke(2, Color.parseColor("#BDBDBD"));
		gradientDrawable.setColor(colorCode);
		gradientDrawable.setCornerRadii(radii);
		return gradientDrawable;
	}

	public static int getDrawableId(Context context, String resName){
		return context.getResources().getIdentifier(resName, "drawable", context.getPackageName());
	}

	
}
