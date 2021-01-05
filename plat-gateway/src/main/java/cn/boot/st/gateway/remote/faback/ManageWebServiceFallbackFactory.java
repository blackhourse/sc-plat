package cn.boot.st.gateway.remote.faback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Classname ManageWebServiceFallbackFactory
 * @Description
 * @Date 2021/1/5
 * @Created by maht
 */
@Component
public class ManageWebServiceFallbackFactory implements FallbackFactory<ManageWebServiceFallback> {

    @Override
    public ManageWebServiceFallback create(Throwable throwable) {
        return new ManageWebServiceFallback(throwable);
    }
}
