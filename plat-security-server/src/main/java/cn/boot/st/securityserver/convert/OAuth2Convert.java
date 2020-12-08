package cn.boot.st.securityserver.convert;

import cn.boot.common.framework.dataobject.dto.OAuth2AccessTokenRespDTO;
import cn.boot.common.framework.dataobject.vo.PassportAccessTokenVO;
import cn.boot.st.securityserver.dataobject.domain.OAuth2AccessTokenDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OAuth2Convert {

    OAuth2Convert INSTANCE = Mappers.getMapper(OAuth2Convert.class);

    @Mapping(source = "id", target = "accessToken")
    OAuth2AccessTokenRespDTO convert(OAuth2AccessTokenDO bean);

    @Mapping(source = "id", target = "accessToken")
    PassportAccessTokenVO convertPassToken(OAuth2AccessTokenDO bean);


}
