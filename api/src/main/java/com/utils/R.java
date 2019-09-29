package com.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;


    public R() {
        put("code", 0);
        put("msg", "操作成功");
    }


    public static R error() {
        return error(1, "操作失败", null);
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        return error(code, msg, null);
    }

    public static R error(int code, String msg, Object obj) {
        return error(code, msg, obj, null);
    }

    public static R error(int code, String msg, Object obj, Object extData) {
        return result(code, msg, obj, extData, false);
    }

    public static R result(int code, String msg, Object obj, Object extData, boolean success) {
        R r = new R();
        /**
         * 返回码
         */
        r.put("code", code);

        /**
         * 返回消息
         */
        r.put("msg", msg);

        /**
         * 返回数据
         */
        r.put("obj", obj);

        /**
         * 返回数据-其他
         */
        r.put("ext", extData);

        /**
         * 返回结果
         */
        r.put("success", success);
        return r;
    }


    public static R ok(Map<String, Object> map) {
        return result(200, null, map, null, true);
    }

    public static R ok() {
        return ok("操作成功");
    }

    public static R ok(Object obj) {
        return ok(200, "操作成功", obj, null);
    }

    public static R ok(int code, Object obj) {
        return ok(code, "操作成功", obj, null);
    }

    public static R ok(String msg, Object obj) {
        return ok(200, msg, obj, null);
    }


    public static R ok(String msg) {
        return ok(200, msg, null, null);
    }

    public static R ok(int code, String msg) {
        return ok(code, msg, null, null);
    }

    public static R ok(int code, String msg, Object obj) {
        return ok(code, msg, obj, null);
    }

    public static R ok(int code, String msg, Object obj, Object extData) {
        return result(code, msg, obj, extData, true);
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
