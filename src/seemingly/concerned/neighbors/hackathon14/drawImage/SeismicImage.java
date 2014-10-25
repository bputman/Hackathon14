package seemingly.concerned.neighbors.hackathon14.drawImage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import seemingly.concerned.neighbors.hackathon14.R;

public class SeismicImage extends View {

	
	private boolean mShowText = false;
	private int mTextPos = TEXTPOS_LEFT;
	private Rect mRectBounds = new Rect();
	private Rect mLayerBounds = new Rect();
	private Paint mRectPaint = new Paint();
	private Paint mLayerPaint = new Paint();
	private int mRectColor = Color.BLACK;
	private int mLayerColor = Color.BLUE;
	private float mTextWidth;
	
	// Fields that influence the view, that need to be updated based on user inputs
	private float depth;
	private float thickness;
	private float peakFreq;
	private float maxOffset;

    /**
     * Draw text to the left of the image
     */
    public static final int TEXTPOS_LEFT = 0;
    		
	public SeismicImage(Context context) {
		super(context);
		init();
	}
	
    /**
     * Class constructor taking a context and an attribute set. This constructor
     * is used by the layout engine to construct a {@link SeismicImage} from a set of
     * XML attributes.
     *
     * @param context
     * @param attrs   An attribute set which can contain attributes from
     *                {@link seemingly.concerned.neighbors.hackathon14.R.styleable.SeismicImage} as well as attributes inherited
     *                from {@link android.view.View}.
     */
	public SeismicImage(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.getTheme().obtainStyledAttributes(
	        attrs,
	        R.styleable.SeismicImage,
	        0, 0);

	   try {
	       mShowText = a.getBoolean(R.styleable.SeismicImage_showSeisText, false);
	       mTextPos = a.getInteger(R.styleable.SeismicImage_labelPosition, 0);
	   } finally {
	       a.recycle();
	   }
	   
	}
	
	/**
     * Returns true if the text label should be visible.
     *
     * @return True if the text label should be visible, false otherwise.
     */
    public boolean getShowText() {
        return mShowText;
    }

    /**
     * Controls whether the text label is visible or not. Setting this property to
     * false allows the pie chart graphic to take up the entire visible area of
     * the control.
     *
     * @param showText true if the text label should be visible, false otherwise
     */
    public void setShowText(boolean showText) {
        mShowText = showText;
        invalidate();
    }
    
	/**
     * 
     * @return Rect object representing the layer
     */
    public Rect getLayerBounds() {
        return mLayerBounds;
    }

    /**
     * Update the layer based on user inputs
     *
     * @param Rect - the new layer bounds
     */
    public void setLayerBounds(Rect mLayerBounds) {
        this.mLayerBounds = mLayerBounds;
        invalidate();
    }
    
	/**
     * @return Depth of the layer
     */
    public float getDepth() {
        return this.depth;
    }

    /**
     * Update the layer depth based on user inputs
     *
     * @param float - the new layer depth
     */
    public void setDepth(float depth) {
        this.depth = depth;
        invalidate();
    }
    
	/**
     * @return Thickness of the layer
     */
    public float getThickness() {
        return this.thickness;
    }

    /**
     * Update the layer thickness based on user inputs
     *
     * @param float - the new layer thickness
     */
    public void setThickness(float thickness) {
        this.thickness = thickness;
        invalidate();
    }
    
	/**
     * @return peak frequency of the survey
     */
    public float getPeakFreq() {
        return this.peakFreq;
    }

    /**
     * Update the peak frequency based on user inputs
     *
     * @param float - the new peak frequency
     */
    public void setPeakFreq(float peakFreq) {
        this.peakFreq = peakFreq;
        invalidate();
    }
    
	/**
     * @return maximum offset of the survey
     */
    public float getMaxOffset() {
        return this.maxOffset;
    }

    /**
     * Update the maximum offset based on user inputs
     *
     * @param float - the new maximum offset
     */
    public void setMaxOffset(float maxOffset) {
        this.maxOffset = maxOffset;
        invalidate();
    }

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        // Do nothing. Do not call the superclass method--that would start a layout pass
//        // on this view's children. PieChart lays out its children in onSizeChanged().
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the shadow
        //canvas.drawRect(mRectBounds, mRectPaint);

        canvas.drawRect(mLayerBounds, mLayerPaint);
        // Draw the label text
//        if (getShowText()) {
//            canvas.drawText(mData.get(mCurrentItem).mLabel, mTextX, mTextY, mTextPaint);
//        }

        // If the API level is less than 11, we can't rely on the view animation system to
        // do the scrolling animation. Need to tick it here and call postInvalidate() until the scrolling is done.
//        if (Build.VERSION.SDK_INT < 11) {
//            tickScrollAnimation();
//            if (!mScroller.isFinished()) {
//                postInvalidate();
//            }
//        }
    }
    
    /**
     * Update size of the view and the contained views
     */
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //
        // Set dimensions for text, pie chart, etc
        //
        // Account for padding
        int xpad =  (getPaddingLeft() + getPaddingRight());
        int ypad =  (getPaddingTop() + getPaddingBottom());

        // Account for the label
        if (mShowText) xpad += mTextWidth;

        int ww = w - xpad;
        int hh =  h - ypad;

        mRectBounds = new Rect(0,0,ww,hh);

        mRectBounds.offsetTo(getPaddingLeft(), getPaddingTop());
        mRectPaint.setStrokeWidth(2);
        mRectPaint.setColor(mRectColor);
        
        // Just a temporary holder of layer location
        mLayerBounds = new Rect(0,hh/2,ww,hh/10);
        
        mLayerBounds.offsetTo(getPaddingLeft(), getPaddingTop());
        mLayerPaint.setColor(mLayerColor);

        //mPointerY = mTextY - (mTextHeight / 2.0f);
        //float pointerOffset = mPieBounds.centerY() - mPointerY;
    }
    /**
     * Initialize the control. This code is in a separate method so that it can be
     * called from both constructors.
     */
    private void init() {
        
    	mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    	mRectPaint.setColor(mRectColor);
    	
    	mLayerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    	mLayerPaint.setColor(mLayerColor);
    	
    }
}
