package seemingly.concerned.neighbors.hackathon14;

import seemingly.concerned.neighbors.hackathon14.drawImage.SeismicImage;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	SeismicImage seismicImage;
	SeekBar seekBar_Depth;
	SeekBar seekBar_Thickness;
	SeekBar seekBar_PeakFreq;
	SeekBar seekBar_MaxOffset;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Resources res = getResources();
        
        setContentView(R.layout.activity_main);
        
        // Set range of seekBar_Depth. True seekBar_Depth is [0,((max-min)/step)]
        final int depth_step = 1;
        final int depth_min = 0;
        final int depth_max = 1000;
        // Set range of seekBar_Thickness. True seekBar_Thickness is [0,((max-min)/step)]
        final int step_Thickness = 1;
        final int min_Thickness = 0;
        final int max_Thickness = 500;
        // Set range of seekBar_PeakFreq. True seekBar_PeakFreq is [0,((max-min)/step)]
        // TODO how do I step a value by a non-integer?
        final int step_PeakFreq = 1;
        final int min_PeakFreq = 1;
        final int max_PeakFreq = 100;
        // Set range of seekBar_MaxOffset. True seekBar_MaxOffset is [0,((max-min)/step)]
        final int step_MaxOffset = 1;
        final int min_MaxOffset = 1; // does not equal 0 in case zero cannot be handled.
        final int max_MaxOffset = 2000;
        
        seismicImage = (SeismicImage) this.findViewById(R.id.SeismicImage);
        //seismicImage.setBackgroundColor(Color.WHITE);
        
        // Depth inputs
        //TextView textView_Depth = (TextView) (findViewById(R.id.textView_Depth));
        final TextView t_Depth = new TextView(this);
        
        
        seekBar_Depth = (SeekBar) (findViewById(R.id.seekBar_Depth));
        
        
        seismicImage.setDepthStep(depth_step);
        seismicImage.setDepthMax(depth_max);
        seismicImage.setDepthMin(depth_min);
    
        seekBar_Depth.setMax((depth_max - depth_min)/depth_step);
        
        // Depth Seekbar Listener
        seekBar_Depth.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				int depth = depth_min + (depth_step*seekBar.getProgress());
				t_Depth.setTextSize(depth);
		        Toast.makeText(getApplicationContext(), String.valueOf(depth),Toast.LENGTH_SHORT).show();
		        
		        seismicImage.setDepth(depth);
			}
        }); 
        
        // Thickness inputs
        final TextView t_Thickness = new TextView(this);
        
        seekBar_Thickness = (SeekBar) (findViewById(R.id.seekBar_Thickness));

        
        seekBar_Thickness.setMax((max_Thickness - min_Thickness)/step_Thickness);
        
        seekBar_Thickness.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				int thickness = min_Thickness + (step_Thickness*seekBar.getProgress());
				seismicImage.setThickness(thickness);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				int thickness = min_Thickness + (step_Thickness*seekBar.getProgress());
				t_Thickness.setTextSize(thickness);
		        Toast.makeText(getApplicationContext(), String.valueOf(thickness),Toast.LENGTH_SHORT).show();
		        
		        seismicImage.setThickness(thickness);
			}
        }); 
        
     // PeakFreq inputs
        final TextView t_PeakFreq = new TextView(this);
        
        seekBar_PeakFreq = (SeekBar) (findViewById(R.id.seekBar_PeakFreq));
        

        seekBar_PeakFreq.setMax((max_PeakFreq - min_PeakFreq)/step_PeakFreq);
        
        seekBar_PeakFreq.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				int peakFreq = min_PeakFreq + (step_PeakFreq*seekBar.getProgress());
				t_PeakFreq.setTextSize(peakFreq);
		        Toast.makeText(getApplicationContext(), String.valueOf(peakFreq),Toast.LENGTH_SHORT).show();
		        
		        seismicImage.setPeakFreq(peakFreq);
			}
        }); 
        
     // MaxOffset inputs
        final TextView t_MaxOffset = new TextView(this);
        
        seekBar_MaxOffset = (SeekBar) (findViewById(R.id.seekBar_MaxOffset));
        

        seekBar_MaxOffset.setMax((max_MaxOffset - min_MaxOffset)/step_MaxOffset);
        
        seekBar_MaxOffset.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				int maxOffset = min_MaxOffset + (step_MaxOffset*seekBar.getProgress());
				t_MaxOffset.setTextSize(maxOffset);
				//Toast toast = new Toast(null);
		        Toast.makeText(getApplicationContext(), String.valueOf(maxOffset),Toast.LENGTH_SHORT).show();
		        
		        seismicImage.setMaxOffset(maxOffset);
			}
        }); 
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
}
