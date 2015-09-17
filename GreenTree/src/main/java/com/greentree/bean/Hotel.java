package com.greentree.bean;

import java.util.List;

/**
 * Created by yangyongxin on 15/9/7.
 */
public class Hotel {

    /**
     * result : 0
     * message : 接口通信成功！
     * responseData : {"totalItems":"86","totalPage":"18","currentPage":"1","items":[{"id":"010003","name":"格林豪泰北京市清河商务酒店","imageUrl":"http://a3.greentree.cn:8022/UploadFiles/CRS/HotelImg/010003/Logo/Logo_010003_1.jpg","score":"4.6","distance":"","price":"","isFull":"true","longitude":"116.34885675438","latitude":"40.045321458289","service":["41","51","72","91"],"isPromotion":"false"},{"id":"010004","name":"格林豪泰北京市方庄商务酒店","imageUrl":"http://a3.greentree.cn:8022/UploadFiles/CRS/HotelImg/010004/Logo/Logo_010004_1.jpg","score":"4.6","distance":"","price":"195","isFull":"false","longitude":"116.44115151638","latitude":"39.864154221511","service":["72"],"isPromotion":"false"},{"id":"010005","name":"格林豪泰北京市亦庄万源街地铁站商务酒店","imageUrl":"http://a3.greentree.cn:8022/UploadFiles/CRS/HotelImg/010005/Logo/Logo_010005_1.jpg","score":"4.5","distance":"","price":"195","isFull":"false","longitude":"116.51302836911","latitude":"39.805199806955","service":["41","72","91"],"isPromotion":"false"},{"id":"120178","name":"格林豪泰北京市天坛赵公口桥快捷酒店","imageUrl":"http://a3.greentree.cn:8022/UploadFiles/CRS/HotelImg/120178/Logo/Logo_120178_1.jpg","score":"4.5","distance":"","price":"186","isFull":"false","longitude":"116.42212233999","latitude":"39.866676966306","service":["41","51","72"],"isPromotion":"false"},{"id":"120190","name":"格林豪泰北京市安贞鸟巢商务酒店","imageUrl":"http://a3.greentree.cn:8022/UploadFiles/CRS/HotelImg/120190/Logo/Logo_120190_1.jpg","score":"4.4","distance":"","price":"219","isFull":"false","longitude":"116.40147322732","latitude":"39.979566839419","service":["51","72","91"],"isPromotion":"false"}]}
     */

    private String result;
    private String message;
    private ResponseDataEntity responseData;

    @Override
    public String toString() {
        return "SearchHotel{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", responseData=" + responseData +
                '}';
    }

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
         * totalItems : 86
         * totalPage : 18
         * currentPage : 1
         * items : [{"id":"010003","name":"格林豪泰北京市清河商务酒店","imageUrl":"http://a3.greentree.cn:8022/UploadFiles/CRS/HotelImg/010003/Logo/Logo_010003_1.jpg","score":"4.6","distance":"","price":"","isFull":"true","longitude":"116.34885675438","latitude":"40.045321458289","service":["41","51","72","91"],"isPromotion":"false"},{"id":"010004","name":"格林豪泰北京市方庄商务酒店","imageUrl":"http://a3.greentree.cn:8022/UploadFiles/CRS/HotelImg/010004/Logo/Logo_010004_1.jpg","score":"4.6","distance":"","price":"195","isFull":"false","longitude":"116.44115151638","latitude":"39.864154221511","service":["72"],"isPromotion":"false"},{"id":"010005","name":"格林豪泰北京市亦庄万源街地铁站商务酒店","imageUrl":"http://a3.greentree.cn:8022/UploadFiles/CRS/HotelImg/010005/Logo/Logo_010005_1.jpg","score":"4.5","distance":"","price":"195","isFull":"false","longitude":"116.51302836911","latitude":"39.805199806955","service":["41","72","91"],"isPromotion":"false"},{"id":"120178","name":"格林豪泰北京市天坛赵公口桥快捷酒店","imageUrl":"http://a3.greentree.cn:8022/UploadFiles/CRS/HotelImg/120178/Logo/Logo_120178_1.jpg","score":"4.5","distance":"","price":"186","isFull":"false","longitude":"116.42212233999","latitude":"39.866676966306","service":["41","51","72"],"isPromotion":"false"},{"id":"120190","name":"格林豪泰北京市安贞鸟巢商务酒店","imageUrl":"http://a3.greentree.cn:8022/UploadFiles/CRS/HotelImg/120190/Logo/Logo_120190_1.jpg","score":"4.4","distance":"","price":"219","isFull":"false","longitude":"116.40147322732","latitude":"39.979566839419","service":["51","72","91"],"isPromotion":"false"}]
         */

        private String totalItems;
        private String totalPage;
        private String currentPage;
        private List<ItemsEntity> items;

        @Override
        public String toString() {
            return "ResponseDataEntity{" +
                    "totalItems='" + totalItems + '\'' +
                    ", totalPage='" + totalPage + '\'' +
                    ", currentPage='" + currentPage + '\'' +
                    ", items=" + items +
                    '}';
        }

        public void setTotalItems(String totalItems) {
            this.totalItems = totalItems;
        }

        public void setTotalPage(String totalPage) {
            this.totalPage = totalPage;
        }

        public void setCurrentPage(String currentPage) {
            this.currentPage = currentPage;
        }

        public void setItems(List<ItemsEntity> items) {
            this.items = items;
        }

        public String getTotalItems() {
            return totalItems;
        }

        public String getTotalPage() {
            return totalPage;
        }

        public String getCurrentPage() {
            return currentPage;
        }

        public List<ItemsEntity> getItems() {
            return items;
        }

        public static class ItemsEntity {
            /**
             * id : 010003
             * name : 格林豪泰北京市清河商务酒店
             * imageUrl : http://a3.greentree.cn:8022/UploadFiles/CRS/HotelImg/010003/Logo/Logo_010003_1.jpg
             * score : 4.6
             * distance :
             * price :
             * isFull : true
             * longitude : 116.34885675438
             * latitude : 40.045321458289
             * service : ["41","51","72","91"]
             * isPromotion : false
             */

            private String id;
            private String name;
            private String imageUrl;
            private String score;
            private String distance;
            private String price;
            private String isFull;
            private String longitude;
            private String latitude;
            private String isPromotion;
            private List<String> service;

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setIsFull(String isFull) {
                this.isFull = isFull;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public void setIsPromotion(String isPromotion) {
                this.isPromotion = isPromotion;
            }

            public void setService(List<String> service) {
                this.service = service;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public String getScore() {
                return score;
            }

            public String getDistance() {
                return distance;
            }

            public String getPrice() {
                return price;
            }

            public String getIsFull() {
                return isFull;
            }

            public String getLongitude() {
                return longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public String getIsPromotion() {
                return isPromotion;
            }

            public List<String> getService() {
                return service;
            }

            @Override
            public String toString() {
                return "ItemsEntity{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", imageUrl='" + imageUrl + '\'' +
                        ", score='" + score + '\'' +
                        ", distance='" + distance + '\'' +
                        ", price='" + price + '\'' +
                        ", isFull='" + isFull + '\'' +
                        ", longitude='" + longitude + '\'' +
                        ", latitude='" + latitude + '\'' +
                        ", isPromotion='" + isPromotion + '\'' +
                        ", service=" + service +
                        '}';
            }
        }
    }
}
