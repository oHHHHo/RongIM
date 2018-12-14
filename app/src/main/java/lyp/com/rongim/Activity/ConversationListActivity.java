package lyp.com.rongim.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Window;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;
import lyp.com.rongim.R;

/**
 * Created by liyp on 18-12-14.
 */

public class ConversationListActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.conversationlist);
        initConversationList();
    }

    /**
     * 封装的代码加载融云的会话列表的 fragment
     * @return
     */
    private void initConversationList() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        ConversationListFragment conversationlistFragment = (ConversationListFragment) supportFragmentManager.findFragmentById(R.id.conversationlist);
        conversationlistFragment.getActivity();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                .build();
        conversationlistFragment.setUri(uri);
    }
}
