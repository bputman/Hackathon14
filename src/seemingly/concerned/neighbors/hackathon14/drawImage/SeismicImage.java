package seemingly.concerned.neighbors.hackathon14.drawImage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import seemingly.concerned.neighbors.hackathon14.R;

public class SeismicImage extends View {

	
	private boolean mShowText = false;
	private int mTextPos = TEXTPOS_LEFT;

    /**
     * Draw text to the left of the image
     */
    public static final int TEXTPOS_LEFT = 0;
    		
	public SeismicImage(Context context) {
		super(context);
		//init();
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
}
