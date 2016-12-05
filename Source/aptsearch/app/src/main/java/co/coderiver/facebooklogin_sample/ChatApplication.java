package co.coderiver.facebooklogin_sample;

/**
 * Created by polavarapu on 12/4/2016.
 */


        import com.firebase.client.Firebase;

/**
 * @author Jenny Tong (mimming)
 * @since 12/5/14
 *
 * Initialize Firebase with the application context. This must happen before the client is used.
 */
public class ChatApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
