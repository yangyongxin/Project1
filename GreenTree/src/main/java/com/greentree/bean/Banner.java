package com.greentree.bean;

import java.util.List;

/**
 * Created by yangyongxin on 15/9/16.
 */
public class Banner {
    /**
     * result : 0
     * message : 接口通信成功！
     * responseData : {"bannerList":[{"bannerId":1213,"bannerUrl":"http://a1.greentree.cn:8029/Public/uploadfile/content/2015/08/20150825012947640.jpg"},{"bannerId":1214,"bannerUrl":"http://a1.greentree.cn:8029/Public/uploadfile/content/2015/09/20150908022049602.jpg"},{"bannerId":1226,"bannerUrl":"http://a1.greentree.cn:8029/Public/uploadfile/content/2015/09/20150916113435774.jpg"},{"bannerId":1236,"bannerUrl":"http://a1.greentree.cn:8029/Public/uploadfile/content/2015/09/20150908041646224.jpg"},{"bannerId":1239,"bannerUrl":"http://a1.greentree.cn:8029/Public/uploadfile/content/2015/08/20150817111442641.jpg"},{"bannerId":1242,"bannerUrl":"http://a1.greentree.cn:8029/Public/uploadfile/content/2015/09/20150916100415582.jpg"}]}
     */

    private String result;
    private String message;
    private ResponseDataEntity responseData;

    public void setResult(String result) {
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResponseData(ResponseDataEntity responseData) {
        this.responseData = responseData;
    }

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public ResponseDataEntity getResponseData() {
        return responseData;
    }

    public static class ResponseDataEntity {
        /**
         * bannerList : [{"bannerId":1213,"bannerUrl":"http://a1.greentree.cn:8029/Public/uploadfile/content/2015/08/20150825012947640.jpg"},{"bannerId":1214,"bannerUrl":"http://a1.greentree.cn:8029/Public/uploadfile/content/2015/09/20150908022049602.jpg"},{"bannerId":1226,"bannerUrl":"http://a1.greentree.cn:8029/Public/uploadfile/content/2015/09/20150916113435774.jpg"},{"bannerId":1236,"bannerUrl":"http://a1.greentree.cn:8029/Public/uploadfile/content/2015/09/20150908041646224.jpg"},{"bannerId":1239,"bannerUrl":"http://a1.greentree.cn:8029/Public/uploadfile/content/2015/08/20150817111442641.jpg"},{"bannerId":1242,"bannerUrl":"http://a1.greentree.cn:8029/Public/uploadfile/content/2015/09/20150916100415582.jpg"}]
         */

        private List<BannerListEntity> bannerList;

        public void setBannerList(List<BannerListEntity> bannerList) {
            this.bannerList = bannerList;
        }

        public List<BannerListEntity> getBannerList() {
            return bannerList;
        }

        public static class BannerListEntity {
            /**
             * bannerId : 1213
             * bannerUrl : http://a1.greentree.cn:8029/Public/uploadfile/content/2015/08/20150825012947640.jpg
             */

            private int bannerId;
            private String bannerUrl;

            public void setBannerId(int bannerId) {
                this.bannerId = bannerId;
            }

            public void setBannerUrl(String bannerUrl) {
                this.bannerUrl = bannerUrl;
            }

            public int getBannerId() {
                return bannerId;
            }

            public String getBannerUrl() {
                return bannerUrl;
            }
        }
    }
}
