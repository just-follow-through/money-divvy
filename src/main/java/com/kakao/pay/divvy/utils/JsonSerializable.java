package com.kakao.pay.divvy.utils;

public interface JsonSerializable {
    default String toJson() {
            return JsonUtil.toJson(this);
        }
}
