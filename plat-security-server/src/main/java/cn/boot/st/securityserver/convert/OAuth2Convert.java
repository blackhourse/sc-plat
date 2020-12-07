package cn.boot.st.securityserver.convert;

import cn.boot.st.security.dto.OAuth2AccessTokenRespDTO;
import cn.boot.st.securityserver.dataobject.oauth.OAuth2AccessTokenDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OAuth2Convert {

    OAuth2Convert INSTANCE = Mappers.getMapper(OAuth2Convert.class);

    @Mapping(source = "id", target = "accessToken")
    OAuth2AccessTokenRespDTO convert(OAuth2AccessTokenDO bean);

}
