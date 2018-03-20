//package com.xiamuyao.repidmvclibrary.UItil.WxShare;
//
//import android.animation.ObjectAnimator;
//import android.widget.PopupWindow;package com.example.xiamuyao.mydevelop.UItil.WxShare;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.drawable.BitmapDrawable;
//import android.os.Handler;
//import android.os.Message;
//import android.text.ClipboardManager;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//
//import com.xiamuyao.repidmvclibrary.AppContext;
//import com.xiamuyao.repidmvclibrary.R;
//import com.xiamuyao.repidmvclibrary.base.BaseActivity;
//
///**
// * ================================================
// * 作    者：夏沐尧  Github地址：https://github.com/XiaMuYaoDQX
// * 版    本：1.0
// * 创建日期： 2016/5/5
// * 描    述：请求url拼接
// * 修订历史：
// * ================================================
// */
//
//public class WeiXinSharePopupWindow extends PopupWindow {
//    View mMenuView;
//    LinearLayout friend, friend_dou, ll_url;
//    LinearLayout ll_popup;
//    BaseActivity mActivity;
//    AppContext app = AppContext.getInstance();
//    TextView tv_quxiao;
//    View view_bg;
//    IWXAPI api;
//    String url = "", content = "", title = "";
//    public Bitmap bitmap;
//    public static boolean isShare = false;
//    public static String mobclickAgent = "";
//
//    public WeiXinSharePopupWindow(BaseActivity activity, final IWXAPI api) {
//        super(activity);
//        this.mActivity = activity;
//        this.api = api;
//        LayoutInflater inflater = (LayoutInflater) app
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mMenuView = inflater.inflate(R.layout.popwindow_view, null);
//        ll_popup = (LinearLayout) mMenuView.findViewById(R.id.ll_popup);
//        view_bg = mMenuView.findViewById(R.id.view_bg);
//        friend = (LinearLayout) mMenuView.findViewById(R.id.friend);
//        friend_dou = (LinearLayout) mMenuView.findViewById(R.id.friend_dou);
//        ll_url = (LinearLayout) mMenuView.findViewById(R.id.ll_url);
//        tv_quxiao = (TextView) mMenuView.findViewById(R.id.tv_quxiao);
//        friend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mActivity.isWeixinAvilible(app)) {
//                if (!TextUtils.isEmpty(mobclickAgent))
//                WeiXinShareUtil.sendweb(api, url, bitmap, false, title, content);
//                new Thread(new MyThread()).start();
//                destory();
//                }else
//                    UIHelper.showToastShort(app,"请安装微信");
//            }
//        });
//
//        friend_dou.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mActivity.isWeixinAvilible(app)) {
//                if (!TextUtils.isEmpty(mobclickAgent))
//                WeiXinShareUtil.sendweb(api, url, bitmap, true, title, content);
//                new Thread(new MyThread()).start();
//                destory();
//                }else
//                    UIHelper.showToastShort(app,"请安装微信");
//            }
//        });
//        ll_url.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                copy(url, mActivity);
//                destory();
//            }
//        });
//        tv_quxiao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                destory();
//            }
//        });
//        this.setContentView(mMenuView);
//
//        this.setBackgroundDrawable(new BitmapDrawable());
//        this.setOutsideTouchable(true);
//        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
//        this.setHeight(ViewGroup.LayoutParams.FILL_PARENT);
//        this.setFocusable(false);
//        this.setAnimationStyle(R.style.anim_null);
//        mMenuView.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View arg0, MotionEvent arg1) {
//                int height = mMenuView.findViewById(R.id.ll_popup).getTop();
//                int y = (int) arg1.getY();
//                if (arg1.getAction() == MotionEvent.ACTION_UP) {
//                    if (y < height) {
//                        destory();
//                    }
//                }
//                return true;
//            }
//        });
//    }
//
//    public void destory() {
//        ObjectAnimator.ofFloat(view_bg, "alpha", 1, 0).setDuration(300).start();
//        Animation animationUtils = AnimationUtils.loadAnimation(app,
//                R.anim.pophidden_anim);
//        ll_popup.startAnimation(animationUtils);
//        animationUtils.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                new Handler().post(new Runnable() {
//                    @Override
//                    public void run() {
//                        dismiss();
//                    }
//                });
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//            }
//        });
//    }
//
//    @Override
//    public void showAtLocation(View parent, int gravity, int x, int y) {
//        super.showAtLocation(parent, gravity, x, y);
//        ll_popup.startAnimation(AnimationUtils.loadAnimation(app,
//                R.anim.popshow_anim));
//        ObjectAnimator.ofFloat(view_bg, "alpha", 0, 1).setDuration(300).start();
//    }
//
//    public class MyThread implements Runnable {
//        @Override
//        public void run() {
//            while (true) {
//                try {
//                    Thread.sleep(500);
//                    Message message = new Message();
//                    message.what = 1;
//                    handler.sendMessage(message);// 发送消息
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            // 要做的事情
//            super.handleMessage(msg);
//        }
//    };
//
//    public void setShard(final Activity activity, final ShareBean share, String mobclickAgent) {
//        if (share.getUrl() != null)
//            this.url = share.getUrl();
//        else
//            url = "";
//        if (share.getTitle() != null)
//            this.title = share.getTitle();
//        else
//            title = "";
//        if (share.getContent() != null)
//            this.content = share.getContent();
//        else
//            content = "";
//        if (mobclickAgent != null)
//            this.mobclickAgent = mobclickAgent;
//        else
//            this.mobclickAgent = "";
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    if (share.getImage() != null) {
//                        bitmap =
//                                Glide.with(activity).load(GlideUtil.getUrl(share.getImage(), GlideUtil.IMAGE_THUMB))
//                                        .asBitmap().into(100, 100).get();
//                    } else {
//                        bitmap = Glide.with(activity).load(R.mipmap.logo)
//                                .asBitmap().into(100, 100).get();
//                    }
//                    isShare = true;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//
//    }
//
//    /**
//     * 实现文本复制功能
//     * add by wangqianzhou
//     *
//     * @param content
//     */
//    public static void copy(String content, Context context) {
//        // 得到剪贴板管理器
//        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//        cmb.setText(content.trim());
//        UIHelper.showToastShort(context, "链接已经复制到您的粘贴板");
//    }
//
//    /**
//     * 实现粘贴功能
//     * add by wangqianzhou
//     *
//     * @param context
//     * @return
//     */
//    public static String paste(Context context) {
//        // 得到剪贴板管理器
//        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//        return cmb.getText().toString().trim();
//    }
//}