package com.xiamuyao.rapidmvc;

import java.util.List;

public class TestBean {

    /**
     * code : 200
     * msg : 请求成功
     * data : [{"userId":1,"userName":"XiaMuYao","password":"123123","phone":"18945709505"},{"userId":1007,"userName":"夏沐尧","password":"123123","phone":"18900000000"},{"userId":1008,"userName":"夏沐尧","password":"123123","phone":"18900000000"},{"userId":1009,"userName":"夏沐尧","password":"123123","phone":"18900000000"},{"userId":1010,"userName":"夏沐尧","password":"123123","phone":"18900000000"},{"userId":1011,"userName":"夏沐尧","password":"123123","phone":"18900000000"},{"userId":1012,"userName":"夏沐尧","password":"123123","phone":"18900000000"},{"userId":1013,"userName":"夏沐尧","password":"123123","phone":"18900000000"},{"userId":1014,"userName":"夏沐尧","password":"123123","phone":"18900000000"},{"userId":1015,"userName":"夏沐尧","password":"123123","phone":"18900000000"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userId : 1
         * userName : XiaMuYao
         * password : 123123
         * phone : 18945709505
         */

        private int userId;
        private String userName;
        private String password;
        private String phone;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
