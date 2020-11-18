create table oauth_client_details (
    `client_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用标识',
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '应用密钥(bcyt) 加密',
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '范围',
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '回调地址 ',
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限',
  `access_token_validity` int(11) DEFAULT NULL COMMENT 'access_token有效期',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT 'refresh_token有效期',
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '{}' COMMENT '{}',
  `autoapprove` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'true' COMMENT '是否自动授权 是-true'
);
INSERT INTO oauth_client_details
    (client_id, client_secret, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity,
    refresh_token_validity, additional_information, autoapprove)
VALUES
    ('user-client', '$2a$10$o2l5kA7z.Caekp72h5kU7uqdTDrlamLq.57M1F6ulJln9tRtOJufq', 'all',
    'authorization_code,refresh_token,password', null, null, 3600, 36000, null, true);

INSERT INTO oauth_client_details
    (client_id, client_secret, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity,
    refresh_token_validity, additional_information, autoapprove)
VALUES
    ('order-client', '$2a$10$GoIOhjqFKVyrabUNcie8d.ADX.qZSxpYbO6YK4L2gsNzlCIxEUDlW', 'all',
    'authorization_code,refresh_token,password', null, null, 3600, 36000, null, true);