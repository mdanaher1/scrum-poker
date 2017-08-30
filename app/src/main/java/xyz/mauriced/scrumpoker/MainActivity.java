package xyz.mauriced.scrumpoker;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // The following are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                /*
                 * The following method, "handleShakeEvent(count):" is a stub //
				 * method you would use to setup whatever you want done once the
				 * device has been shook.
				 */
                handleShakeEvent(count);
            }
        });

        fragmentManager = getFragmentManager();

        // Begin the transaction
        FragmentTransaction ft = fragmentManager.beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.main_activity, new MainFragment());
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();

    }

    private void performTransition() {

    }

    public void handleShakeEvent(int count) {
        Toast.makeText(this, "SHAKE IT OFF!!!", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {

        //Fragment previousFragment = fragmentManager.findFragmentById(R.id.main_activity);
        //Fragment nextFragment = CardFragment.newInstance();

        switch (view.getId()) {
            case R.id.button0:
                Toast.makeText(getApplicationContext(), "asdfsadf", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button1:

                // Begin the transaction
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                /*Fade exitFade = new Fade();
                exitFade.setDuration(500);
                previousFragment.setExitTransition(exitFade);

                Fade enterFade = new Fade();
                enterFade.setStartDelay(600);
                enterFade.setDuration(500);
                nextFragment.setEnterTransition(enterFade);*/


                // Replace the contents of the container with the new fragment
                ft.setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out, R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                        .replace(R.id.main_activity, new CardFragment())
                        .addToBackStack("main")
                        .commit();
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
            case R.id.button5:
                break;
            case R.id.button8:
                break;
            case R.id.buttoncoffee:
                break;
            case R.id.buttonq:
                break;
            case R.id.buttonqqq:
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
