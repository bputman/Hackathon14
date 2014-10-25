package seemingly.concerned.neighbors.hackathon14;

import seemingly.concerned.neighbors.hackathon14.drawImage.SeismicImage;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Resources res = getResources();
        
        setContentView(R.layout.activity_main);
        
        //SeismicImage seis = (SeismicImage) this.findViewById(R.id.SeismicIm);
        
        TextView textView_Depth = (TextView) (findViewById(R.id.textView_Depth));
        final TextView t_Depth = new TextView(this);
        
        SeekBar seekBar_Depth = (SeekBar) (findViewById(R.id.seekBar_Depth));
        
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
