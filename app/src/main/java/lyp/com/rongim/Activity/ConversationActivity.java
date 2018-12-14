package lyp.com.rongim.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import lyp.com.rongim.R;

/**
 * Created by liyp on 18-12-14.
 */

public class ConversationActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
    }
}
