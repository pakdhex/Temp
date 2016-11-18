// Splash screen timer
    SplashScreen mActivity = null;
    private static int SPLASH_TIME_OUT = 3000;
    SmsBroadcastReceiver brReciver;
    String DELIVERED = "SMS_DELIVERED";
    String AMBIL = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mActivity = this;
        //Jalankan Service
        //startService(new Intent(SplashScreen.this, TutorialIntentService.class));

        brReciver = new SmsBroadcastReceiver();

        mActivity.registerReceiver(brReciver, new IntentFilter(DELIVERED));
        mActivity.registerReceiver(brReciver, new IntentFilter(AMBIL));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if (!TrialVersion.Kadaluarsa()){
                    if (new KartuMDL(SplashScreen.this).getTotal() == 0){ DefaultTable.set(SplashScreen.this); }
                    Intent i = new Intent(SplashScreen.this, UtamaActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }else{
                    Toast.makeText(SplashScreen.this, "Apikasi Kadaluarsa", Toast.LENGTH_SHORT).show();
                    //stopService(new Intent(SplashScreen.this, TutorialIntentService.class));
                    onBackPressed();
                }
            }
        }, SPLASH_TIME_OUT);
    }
