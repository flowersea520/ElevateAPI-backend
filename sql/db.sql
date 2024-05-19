-- 接口信息
-- auto-generated definition
create table interface_info
(
    id              bigint auto_increment comment '主键id'
        primary key,
    name            varchar(256)                       not null comment '接口名称',
    description     varchar(256)                       null comment '接口描述',
    url             varchar(512)                       not null comment '接口地址',
    request_params  text                               null comment '请求参数',
    request_header  text                               null comment '请求头',
    response_header text                               null comment '响应头',
    status          int      default 0                 not null comment '接口状态（0-关闭，1-开启）',
    method          varchar(256)                       not null comment '请求方法',
    user_id         bigint                             not null comment '创建人id',
    create_time     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted      tinyint  default 0                 not null comment '是否删除(0-未删, 1-已删)'
)
    comment '接口信息';


insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ( '范修洁', 'Korn shell', 'www.heike-mosciski.info', '140.46.142.156', '238.66.148.240', 0, 'GET', 5570, '2022-02-03 09:45:00', '2022-12-28 22:02:26', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ( '丁笑愚', 'C++', 'www.chase-kuvalis.net', '171.49.59.69', '120.151.117.173', 0, 'GET', 5765, '2022-10-26 19:18:31', '2022-12-17 12:04:51', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ( '吕鹭洋', 'Common Lisp', 'www.ignacia-price.biz', '5.121.113.52', '13.124.54.18', 0, 'GET', 45564948, '2022-11-16 08:19:14', '2022-10-22 02:56:22', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ( '郭擎苍', 'Haskell', 'www.merle-kub.co', '203.64.74.223', '60.249.244.210', 0, 'GET', 7390846, '2022-05-02 01:02:06', '2022-11-22 05:27:57', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ('姚鹭洋', 'Icon', 'www.teresa-mills.name', '172.81.165.181', '84.108.253.43', 0, 'GET', 73380, '2022-01-26 16:21:31', '2022-03-08 01:27:52', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ( '史鹏煊', 'NATURAL', 'www.cheri-runte.com', '197.92.63.5', '36.157.51.67', 0, 'GET', 720, '2022-12-17 19:05:06', '2022-02-03 02:48:06', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ('崔雪松', 'Elixir', 'www.son-mitchell.name', '232.156.59.19', '129.99.199.241', 0, 'GET', 905404, '2022-11-11 18:07:44', '2022-10-24 18:50:16', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ('尹思淼', 'bc', 'www.stefanie-hand.org', '114.215.194.7', '78.85.77.49', 0, 'GET', 66217857, '2022-02-13 11:22:20', '2022-04-27 20:59:42', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ( '郭峻熙', 'SQL', 'www.carolina-hermann.biz', '38.142.204.28', '166.173.218.221', 0, 'GET', 627179008, '2022-11-16 23:18:56', '2022-09-21 05:56:48', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ( '沈擎宇', 'LabVIEW', 'www.mathew-kling.com', '255.44.206.95', '227.65.145.63', 0, 'GET', 23278770, '2022-12-12 03:29:58', '2022-08-07 18:45:24', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ( '余嘉熙', 'Stata', 'www.collette-kub.co', '211.179.12.123', '157.116.117.148', 0, 'GET', 9607, '2022-03-05 09:08:51', '2022-07-29 20:46:05', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ('戴志泽', 'CLIPS', 'www.gwenn-feil.info', '90.144.237.165', '115.35.192.28', 0, 'GET', 224596, '2022-12-01 02:15:31', '2022-09-14 17:26:55', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ( '唐立辉', 'Groovy', 'www.robt-oreilly.biz', '15.88.243.156', '14.69.147.178', 0, 'GET', 6, '2022-01-22 22:28:41', '2022-05-09 09:36:57', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ('熊立轩', 'RPG', 'www.beatriz-halvorson.co', '52.224.214.230', '181.83.50.3', 0, 'GET', 56, '2022-04-16 06:15:15', '2022-12-26 23:43:49', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ('毛锦程', 'PostScript', 'www.grazyna-treutel.co', '110.92.183.160', '210.53.62.160', 0, 'GET', 2834214, '2022-02-08 20:01:42', '2022-05-01 10:50:24', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ( '叶旭尧', 'Julia', 'www.valencia-gibson.info', '147.5.111.222', '54.186.79.22', 0, 'GET', 52072, '2022-11-30 01:07:46', '2022-07-13 01:25:45', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ('谭子涵', 'ABAP', 'www.beverlee-schultz.info', '44.60.236.116', '248.89.166.152', 0, 'GET', 75718, '2022-09-25 16:31:35', '2022-12-12 09:56:07', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ('洪凯瑞', 'Logo', 'www.russell-schamberger.io', '206.220.125.15', '202.89.229.42', 0, 'GET', 593750795, '2022-06-04 00:19:18', '2022-04-30 01:18:12', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ('高雨泽', 'WebAssembly', 'www.miles-kunde.biz', '29.70.229.205', '180.95.60.100', 0, 'GET', 0, '2022-10-20 08:49:19', '2022-06-15 20:22:38', 0);
insert into elevateapi.`interface_info` (`name`, `description`, `url`, `request_header`, `response_header`, `status`, `method`, `user_id`, `create_time`, `update_time`, `is_deleted`) values ( '周嘉懿', 'Bash', 'www.oscar-fisher.co', '103.17.54.142', '62.139.58.228', 0, 'GET', 39936, '2022-12-24 00:58:25', '2022-11-24 06:17:38', 0);