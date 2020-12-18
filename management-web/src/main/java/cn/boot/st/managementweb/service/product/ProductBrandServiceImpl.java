package cn.boot.st.managementweb.service.product;

import cn.boot.st.managementweb.remote.product.ProductBrandRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-17
 **/
@Service
public class ProductBrandServiceImpl implements ProductBrandService {


    @Autowired
    private ProductBrandRemoteService productBrandRemoteService;





}
