package lyp.com.rongim.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;
import lyp.com.rongim.R;
import lyp.com.rongim.Utils.Friend;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,RongIM.UserInfoProvider{

    protected static final String TAG = "MainActivity";
    private Button connectServer, updateUserInfo, loadList, loadChat;

    private String mUserId;

    private List<Friend> userIdList;

    private static final String token1 = "Ldg9Yy7wNd16M9I9tbMAghmS0eiAfLkEtqx9yeu9vrrHqbYwnwUtCZWr8zgqrUDw6b/vWuN6gPq8wQ93wwS3YA==";

    private static final String token2 = "DMzd2VnxtlrIm0r8ksjRkdu/wWRueb6HvGCBOVUVrn+EIThHAxrEia/qVdY/W5ZXL/WTwWoVGx3YIk5o8yfeog==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        userIdList = new ArrayList<Friend>();
        userIdList.add(new Friend("10010", "联通", "http://www.51zxw.net/bbs/UploadFile/2013-4/201341122335711220.jpg"));
        userIdList.add(new Friend("10086", "移动", "http://img02.tooopen.com/Download/2010/5/22/20100522103223994012.jpg"));
        RongIM.setUserInfoProvider(this,true);
    }

    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 {#init(Context)} 之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param token    从服务端获取的用户身份令牌（Token）。
     * @return RongIM  客户端核心类的实例。
     */
    private void connectRongServer(String token) {

        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.d("myTag", "--onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("myTag", "--onSuccess" + userid);
                    connectServer.setClickable(false);
                    connectServer.setTextColor(Color.GRAY);
                    Toast.makeText(MainActivity.this, "connet server success", Toast.LENGTH_SHORT).show();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.d("myTag", "--onTokenIncorrect" + errorCode);
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connect_server:
                connectRongServer(token1);
                break;
            case R.id.update_user_info:
                RongIM.getInstance().refreshUserInfoCache(new UserInfo("10086","我曾经是移动",Uri.parse("http://static.yingyonghui.com/screenshots/1657/1657011_4.jpg")));
                break;
            case R.id.load_list:
                Intent intent = new Intent(MainActivity.this,ConversationListActivity.class);
                startActivity(intent);
                break;
            case R.id.load_chat:
                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().startPrivateChat(MainActivity.this,"10086","移动");
                }
                break;
            default:
                break;
        }
    }

    private void init() {
        connectServer = findViewById(R.id.connect_server);
        updateUserInfo = findViewById(R.id.update_user_info);
        loadList = findViewById(R.id.load_list);
        loadChat = findViewById(R.id.load_chat);

        connectServer.setOnClickListener(this);
        updateUserInfo.setOnClickListener(this);
        loadList.setOnClickListener(this);
        loadChat.setOnClickListener(this);
    }

    @Override
    public UserInfo getUserInfo(String s) {
        for (Friend i : userIdList) {
            if (i.getUserId().equals(s)) {
                Log.e(TAG, i.getPortraitUri());
                return new UserInfo(i.getUserId(),i.getUserName(), Uri.parse(i.getPortraitUri()));
            }
        }
        Log.e("MainActivity","UserId is ：" +s );
        return null;
    }
}
