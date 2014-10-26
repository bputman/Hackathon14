package seemingly.concerned.neighbors.hackathon14.drawImage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.*;
import seemingly.concerned.neighbors.hackathon14.R;

public class SeismicImage extends View {

	
	private boolean mShowText = false;
	private Rect mRectBounds = new Rect();
	private Rect mLayerBounds = new Rect();
	private Paint mRectPaint;
	private Paint mLayerPaint;
	private Paint mErrorBarPaint;
	private float[] mErrorBarLines;
	private float mTextWidth;
	
	// Fields that influence the view, that need to be updated based on user inputs
	private float depth;
	private float thickness;
	private float peakFreq;
	private float maxOffset;
	private int depth_Pixels;
	private int thickness_Pixels;
	private int peakFreq_Pixels;
	private int maxOffset_Pixels;
	private float depth_step;
	private float depth_min;
	private float depth_max;
	private VariableChangeListener mVariableChangeListener;
	
	// Standard deviation of upper layer
	//TODO standard devations
	private int stdUpper = 10;

	// Standard deviation of lower layer
	private int stdLower = 20;	
	
    
    /**
     * Interface definition for a callback to be invoked when the current
     * item changes.
     */
    public interface VariableChangeListener {
        void OnVariableChanged(SeismicImage source, float depth);
    }
    
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

		//Default SeismicImage parameters
	   try {
	       depth = a.getFloat(R.styleable.SeismicImage_layerDepth,500);
	       thickness = a.getFloat(R.styleable.SeismicImage_layerThickness,100);
	       peakFreq = a.getFloat(R.styleable.SeismicImage_layerPeakFreq,25);
	       maxOffset = a.getFloat(R.styleable.SeismicImage_layerMaxOffset,1000);
	       
	   } finally {
	       a.recycle();
	   }
	   init();
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
    
//    class Variable extends Observable {
//    	public void setVariable() {
//    		setChanged();
//    		notifyObservers();
//    	}
//    }
//    
//    class VariableObserver implements Observer {
//
//		@Override
//		public void update(Observable observable, Object data) {
//			// TODO Auto-generated method stub
//			//onSizeChanged();
//		}
//    }

    /**
     * Update the layer depth based on user inputs
     *
     * @param float - the new layer depth
     */
    public void setDepth(float depth) {
        //this.depth = depth;
        if (this.depth != depth && mVariableChangeListener != null) {
        	
        	this.mVariableChangeListener.OnVariableChanged(this, depth);
        }
        this.depth = depth;
        invalidate();
    }
    
    /**
     * Register a callback to be invoked when the depth variable changes.
     *
     * @param listener Can be null.
     *                 The variable change listener to attach to this view.
     */
    public void setOnVariableChangeListener(VariableChangeListener listener) {
        this.mVariableChangeListener = listener;
    }
    
	/**
     * @return Depth of the layer
     */
    public float getDepthStep() {
        return this.depth_step;
    }

    /**
     * Update the layer depth based on user inputs
     *
     * @param float - the new layer depth
     */
    public void setDepthStep(float depth_step) {
        this.depth_step = depth_step;
        invalidate();
    }
    
	/**
     * @return Minimum depth where the layer can be located
     */
    public float getDepthMin() {
        return this.depth_min;
    }

    /**
     * Update the Minimum depth where the layer can be located based on user input
     *
     * @param float - the new layer minimum layer depth
     */
    public void setDepthMin(float depth_min) {
        this.depth_min = depth_min;
        invalidate();
    }
    
	/**
     * @return Maximum depth where the layer can be located
     */
    public float getDepthMax() {
        return this.depth_max;
    }

    /**
     * Update the Minimum depth where the layer can be located based on user input
     *
     * @param float - the new layer minimum layer depth
     */
    public void setDepthMax(float depth_max) {
        this.depth_max = depth_max;
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
        
        // Draw the outer rectangle
        canvas.drawRect(mRectBounds, mRectPaint);
        
        // Draw the layer
        canvas.drawRect(mLayerBounds, mLayerPaint);
        
        // Draw error bars
        canvas.drawLines(mErrorBarLines, mErrorBarPaint);
    }
    
    /**
     * Update size of the view and the contained views
     */
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        
        // Set dimensions for outline, layer, errorBars
        
        // Account for padding
        int xpad =  (getPaddingLeft() + getPaddingRight());
        int ypad =  (getPaddingTop() + getPaddingBottom());

        int ww = w - xpad;
        int hh =  h - ypad;
        
        mRectBounds.set(0,0,ww,hh);
        mRectBounds.offsetTo(getPaddingLeft(), getPaddingTop());
        
        //Update depth and thickness in terms of screen pixels
        float canvasToDepthSlope = hh/(depth_max-depth_min);
        depth_Pixels = (int) (canvasToDepthSlope*depth);
        thickness_Pixels = (int) (canvasToDepthSlope*thickness);
        
        // Set updated layer bounds
        mLayerBounds.set(0,0,ww-2,thickness_Pixels);
        mLayerBounds.offsetTo(getPaddingLeft()+1, getPaddingTop()+depth_Pixels);
        //mLayerBounds = new Rect(0,0,ww-2,50);
        //mLayerBounds.offsetTo(getPaddingLeft()+1, getPaddingTop()+50);
        
        
        // Create points for two error bars. This takes 6 lines.
        mErrorBarLines = new float[4*6]; // 6 lines. Each line is x0,y0,x1,y1
        
        int horzPos = 2*ww/3;
        int errorWidth = ww/24;
        int upperLayer = getPaddingTop()+depth_Pixels;
        int lowerLayer = getPaddingTop()+depth_Pixels+thickness_Pixels;
        
        //Upper surface upper horizontal line
        mErrorBarLines[0] = horzPos;
        mErrorBarLines[1] = upperLayer-stdUpper/2;
        mErrorBarLines[2] = horzPos + errorWidth;
        mErrorBarLines[3] = upperLayer-stdUpper/2;
        //Upper surface lower horizontal line
        mErrorBarLines[4] = horzPos;
        mErrorBarLines[5] = upperLayer+stdUpper/2;
        mErrorBarLines[6] = horzPos + errorWidth;
        mErrorBarLines[7] = upperLayer+stdUpper/2;
        //Upper surface vertical line
        mErrorBarLines[8] = horzPos + errorWidth/2;
        mErrorBarLines[9] = upperLayer+stdUpper/2;
        mErrorBarLines[10] = horzPos + errorWidth/2;
        mErrorBarLines[11] = upperLayer-stdUpper/2;
        
        //Lower surface upper horizontal line
        mErrorBarLines[12] = horzPos;
        mErrorBarLines[13] = lowerLayer-stdLower/2;
        mErrorBarLines[14] = horzPos + errorWidth;
        mErrorBarLines[15] = lowerLayer-stdLower/2;
        //Lower surface lower horizontal line
        mErrorBarLines[16] = horzPos;
        mErrorBarLines[17] = lowerLayer+stdLower/2;
        mErrorBarLines[18] = horzPos + errorWidth;
        mErrorBarLines[19] = lowerLayer+stdLower/2;
        //Lower surface vertical line
        mErrorBarLines[20] = horzPos + errorWidth/2;
        mErrorBarLines[21] = lowerLayer+stdLower/2;
        mErrorBarLines[22] = horzPos + errorWidth/2;
        mErrorBarLines[23] = lowerLayer-stdLower/2;
    }
    
    /**
     * Initialize the control. This code is in a separate method so that it can be
     * called from both constructors.
     */
    private void init() { 
    	//this.setWillNotDraw(false);
    	// Set the outer rectangle
    	mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(1);
    	mRectPaint.setColor(Color.BLACK);
    	
    	// Set the layer 
    	mLayerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    	mLayerPaint.setStyle(Paint.Style.FILL);
    	mLayerPaint.setStrokeWidth(1);
    	mLayerPaint.setColor(Color.CYAN);
    	
    	// Set the error bar
    	mErrorBarPaint = new Paint();
    	mErrorBarPaint.setColor(Color.RED);
    	mErrorBarPaint.setStrokeWidth(3);
    }

	public void setDepthMax(int depth_max) {
		// TODO Auto-generated method stub
		this.depth_max = depth_max;
	}

	public void setDepthMin(int depth_min) {
		// TODO Auto-generated method stub
		this.depth_min = depth_min;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onMeasure(int x, int y) {
		// TODO Auto-generated method stub
		setMeasuredDimension(x,y);
	}
}
