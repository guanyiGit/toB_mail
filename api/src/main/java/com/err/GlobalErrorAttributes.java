package com.err;

import com.utils.R;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {


    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);
        Map<String, Object> extMap = (Map<String, Object>) requestAttributes.getAttribute("ext", 0);
        System.err.println(extMap);
//        R.error((Integer) map.get("status"), map.get("error").toString(), null, map.get("message")).putAll(extMap);
        return R.error((Integer) map.get("status"), map.get("error").toString(), null, map.get("message"));
    }
}
