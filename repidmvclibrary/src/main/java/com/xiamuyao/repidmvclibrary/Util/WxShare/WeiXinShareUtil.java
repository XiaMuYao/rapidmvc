package com.xiamuyao.repidmvclibrary.Util.WxShare;//package com.example.xiamuyao.mydevelop.UItil.WxShare;
//
//import android.graphics.Bitmap;
//
//import com.tencent.mm.sdk.modelmsg.SendAuth;
//import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
//import com.tencent.mm.sdk.modelmsg.WXImageObject;
//import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
//import com.tencent.mm.sdk.modelmsg.WXTextObject;
//import com.tencent.mm.sdk.modelmsg.WXVideoObject;
//import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
//import com.tencent.mm.sdk.openapi.IWXAPI;
//
//
///**
// * XiaMuYao
// * XiaMuYaodqx@gmail.com
// * 2016/5/4
// * =========================
// * 说明：
// */
//public class WeiXinShareUtil {
//    private static final int THUMB_SIZE = 150;
//
//    public static void sendtxt(IWXAPI api, String text, Boolean pengyouquan) {
//        WXTextObject textObj = new WXTextObject();
//        textObj.text = text;
//        // 用WXTextObject对象初始化一个WXMediaMessage对象
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = textObj;
//        // 发送文本类型的消息时，title字段不起作用
//        // msg.title = "Will be ignored";
//        msg.description = text;
//        // 构造一个Req
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction("text"); // transaction字段用于唯一标识一个请求
//        req.message = msg;
//        req.scene = pengyouquan ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
//        api.sendReq(req);
//    }
//
//    public static void sendweb(IWXAPI api, String url, Bitmap thumb, Boolean pengyouquan, String title, String content) {
//        try {
//            WXWebpageObject webpage = new WXWebpageObject();
//            webpage.webpageUrl = url;
//            WXMediaMessage msg = new WXMediaMessage(webpage);
//            msg.title = title;
//            msg.description = content;
//            if (thumb != null && !thumb.isRecycled())
//                msg.thumbData = WeiXinsUtils.bmpToByteArray(thumb, true);
//            SendMessageToWX.Req req = new SendMessageToWX.Req();
//            req.transaction = buildTransaction("webpage");
//            req.message = msg;
//            req.scene = pengyouquan ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
//            api.sendReq(req);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void sendimage(Bitmap bmp, IWXAPI api, Boolean pengyouquan) {
//
//        WXImageObject imgObj = new WXImageObject(bmp);
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = imgObj;
//        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
//        bmp.recycle();
//        msg.thumbData = WeiXinsUtils.bmpToByteArray(thumbBmp, true);  // 设置缩略图
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction("img");
//        req.message = msg;
//        req.scene = pengyouquan ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
//        api.sendReq(req);
//    }
//
//    public static void sendVideo(Bitmap thumb, IWXAPI api, Boolean pengyouquan) {
//        WXVideoObject video = new WXVideoObject();
//        video.videoUrl = "http://www.baidu.com";
//        WXMediaMessage msg = new WXMediaMessage(video);
//        msg.title = "Video Title Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long";
//        msg.description = "Video Description Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long Very Long";
//        msg.thumbData = WeiXinsUtils.bmpToByteArray(thumb, true);
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction("video");
//        req.message = msg;
//        req.scene = pengyouquan ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
//        api.sendReq(req);
//    }
//
//    public static void weixinLogin(IWXAPI api) {
//        SendAuth.Req req = new SendAuth.Req();
//        req.scope = "snsapi_userinfo";
//        req.state = "diandi_wx_login";
//        api.sendReq(req);
//    }
//
//    public static String buildTransaction(final String type) {
//        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
//    }
//}
//
