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
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Resources res = getResources();
        
        setContentView(R.layout.activity_main);
        
        
        seismicImage = (SeismicImage) this.findViewById(R.id.SeismicImage);
        seismicImage.setBackgroundColor(Color.WHITE);
        
        // Depth inputs
        //TextView textView_Depth = (TextView) (findViewById(R.id.textView_Depth));
        final TextView t_Depth = new TextView(this);
        
        seekBar_Depth = (SeekBar) (findViewById(R.id.seekBar_Depth));
        
        // Set range of seekBar_Depth. True seekBar_Depth is [0,((max-min)/step)]
        final int step_Depth = 1;
        final int min_Depth = 200;
        final int max_Depth = 1000;
        seekBar_Depth.setMax((max_Depth - min_Depth)/step_Depth);
        
        seekBar_Depth.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

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
				int depth = min_Depth + (step_Depth*seekBar.getProgress());
				t_Depth.setTextSize(depth);
		        Toast.makeText(getApplicationContext(), String.valueOf(depth),Toast.LENGTH_SHORT).show();
			}
        }); 
        
        // Thickness inputs
        final TextView t_Thickness = new TextView(this);
        
        SeekBar seekBar_Thickness = (SeekBar) (findViewById(R.id.seekBar_Thickness));
        
        // Set range of seekBar_Thickness. True seekBar_Thickness is [0,((max-min)/step)]
        final int step_Thickness = 1;
        final int min_Thickness = 1;
        final int max_Thickness = 200;
        seekBar_Thickness.setMax((max_Thickness - min_Thickness)/step_Thickness);
        
        seekBar_Thickness.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

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
				int depth = min_Thickness + (step_Thickness*seekBar.getProgress());
				t_Thickness.setTextSize(depth);
		        Toast.makeText(getApplicationContext(), String.valueOf(depth),Toast.LENGTH_SHORT).show();
			}
        }); 
        
     // PeakFreq inputs
        final TextView t_PeakFreq = new TextView(this);
        
        SeekBar seekBar_PeakFreq = (SeekBar) (findViewById(R.id.seekBar_PeakFreq));
        
        // Set range of seekBar_PeakFreq. True seekBar_PeakFreq is [0,((max-min)/step)]
        final int step_PeakFreq = 1;
        final int min_PeakFreq = 1;
        final int max_PeakFreq = 100;
        seekBar_PeakFreq.setMax((max_PeakFreq - min_PeakFreq)/step_PeakFreq);
        
        seekBar_PeakFreq.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

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
				int depth = min_PeakFreq + (step_PeakFreq*seekBar.getProgress());
				t_PeakFreq.setTextSize(depth);
		        Toast.makeText(getApplicationContext(), String.valueOf(depth),Toast.LENGTH_SHORT).show();
			}
        }); 
        
     // MaxOffset inputs
        final TextView t_MaxOffset = new TextView(this);
        
        SeekBar seekBar_MaxOffset = (SeekBar) (findViewById(R.id.seekBar_MaxOffset));
        
        // Set range of seekBar_MaxOffset. True seekBar_MaxOffset is [0,((max-min)/step)]
        final int step_MaxOffset = 1;
        final int min_MaxOffset = 0;
        final int max_MaxOffset = 2000;
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
				int depth = min_MaxOffset + (step_MaxOffset*seekBar.getProgress());
				t_MaxOffset.setTextSize(depth);
				//Toast toast = new Toast(null);
		        Toast.makeText(getApplicationContext(), String.valueOf(depth),1).show();
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
