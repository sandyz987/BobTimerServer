-- 用户表
CREATE TABLE IF NOT EXISTS `user`
(
    `id`            INT UNSIGNED AUTO_INCREMENT,         -- 自增主键 ID
    `user_id`       VARCHAR(20)               NOT NULL,  -- 手机号（可作为查询用）
    `stu_id`        VARCHAR(30)               NOT NULL,  -- 学号
    `school_id`     INT UNSIGNED              NOT NULL,  -- 学校编号
    `nickname`      VARCHAR(30)               NOT NULL,  -- 昵称
    `password`      VARCHAR(255)              NOT NULL,  -- 密码（存储哈希值）
    `register_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 注册时间
    `sex`           ENUM ('男', '女', '保密') NOT NULL,  -- 性别
    `text`          VARCHAR(300)              NOT NULL,  -- 个人介绍
    `avatar_url`    VARCHAR(1000)             NOT NULL,  -- 头像
    `is_admin`      TINYINT(1)               NOT NULL,  -- 是否为管理员
    PRIMARY KEY (`id`),                                  -- 使用自增 ID 作为主键
    UNIQUE KEY (`user_id`),                              -- 为 user_id 字段建立唯一索引
    UNIQUE KEY (`stu_id`),                               -- 为 stu_id 字段建立唯一索引
    INDEX (`school_id`)                                 -- 为 school_id 字段建立索引
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

ALTER TABLE `user`
    AUTO_INCREMENT = 100001;

-- 学校表
CREATE TABLE IF NOT EXISTS `school`
(
    `school_id`         INT UNSIGNED AUTO_INCREMENT, -- 学校编号
    `school_name`       VARCHAR(30)     NOT NULL,    -- 学校名
    `statement`         VARCHAR(3000)   NOT NULL,    -- 学校简介
    `school_avatar_url` VARCHAR(1000)   NOT NULL,    -- 学校头像
    `school_member`     BIGINT UNSIGNED NOT NULL,    -- 学校成员数
    PRIMARY KEY (`school_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 商品表
CREATE TABLE IF NOT EXISTS `good`
(
    `good_id`        INT UNSIGNED AUTO_INCREMENT,         -- 商品编号
    `good_name`      VARCHAR(100)   NOT NULL,             -- 商品名称
    `statement`      VARCHAR(3000)  NOT NULL,             -- 商品描述
    `price`          DECIMAL(15, 2) NOT NULL,             -- 商品价格（精确到两位小数）
    `pic_url`        VARCHAR(10000) NOT NULL,             -- 商品图片（以逗号分隔的 URL）
    `submit_time`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 发布日期
    `from_school_id` INT UNSIGNED   NOT NULL,             -- 来源学校编号
    `from_user_id`   INT UNSIGNED   NOT NULL,             -- 发布人 ID
    `status`         INT UNSIGNED   NOT NULL,             -- 状态（0：未拍下，1：已拍下）
    `get_user_id`    INT UNSIGNED,                        -- 拍下用户 ID（可空，状态为已拍下时填写）
    `stock`          INT UNSIGNED   NOT NULL DEFAULT 0,   -- 库存数量（初始为0，库存数量会随着订单的支付和秒杀活动的进行而减少）
    `sold_count`     INT UNSIGNED   NOT NULL DEFAULT 0,   -- 已售数量（用于快速查询商品的销量）
    PRIMARY KEY (`good_id`),
    INDEX (`from_school_id`),
    INDEX (`from_user_id`),
    INDEX (`submit_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 私信表
CREATE TABLE IF NOT EXISTS `private_message`
(
    `id`              INT UNSIGNED AUTO_INCREMENT,            -- 主键 ID
    `sender_id`       INT UNSIGNED   NOT NULL,                 -- 发送者 ID
    `receiver_id`     INT UNSIGNED   NOT NULL,                 -- 接受者 ID
    `message_type`    INT UNSIGNED  NOT NULL,                 -- 消息类型（0：普通消息，1：系统消息）
    `message_content` VARCHAR(3000) NOT NULL,                 -- 消息内容
    `send_time`       TIMESTAMP    DEFAULT CURRENT_TIMESTAMP, -- 发送时间
    `status`          INT UNSIGNED DEFAULT 0,                 -- 消息状态（0：未读，1：已读）
    PRIMARY KEY (`id`),
    INDEX (`sender_id`),
    INDEX (`receiver_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 帖子表
CREATE TABLE IF NOT EXISTS `article`
(
    `article_id`  INT UNSIGNED AUTO_INCREMENT,         -- 动态 ID
    `user_id`     INT UNSIGNED   NOT NULL,              -- 发布人 ID
    `submit_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 发布时间
    `text`        VARCHAR(3000) NOT NULL,              -- 内容
    `topic`       VARCHAR(100)  NOT NULL,              -- 话题或标签
    PRIMARY KEY (`article_id`),
    INDEX (`user_id`),
    INDEX (`submit_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 评论表（包括一级二级评论）
CREATE TABLE IF NOT EXISTS `comment`
(
    `reply_id`      INT UNSIGNED,                        -- 回复的动态 ID（一级评论 ID）
    `id`            INT UNSIGNED AUTO_INCREMENT,         -- 评论 ID（唯一标识）
    `user_id`       INT UNSIGNED   NOT NULL,              -- 发布人 ID
    `submit_time`   TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 发布时间
    `text`          VARCHAR(3000) NOT NULL,              -- 评论内容
    `which`         INT UNSIGNED,                        -- 评论类型（1：一级评论，2：二级评论）
    `reply_user_id` INT UNSIGNED,                         -- 回复的用户 ID（为空表示二级评论）
    PRIMARY KEY (`id`),                                  -- 组合主键：一级评论 + 二级评论
    INDEX (`reply_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 图片表（多图关联到动态）
CREATE TABLE IF NOT EXISTS `pic`
(
    `article_id` INT UNSIGNED,            -- 所属动态 ID
    `id`     INT UNSIGNED AUTO_INCREMENT, -- 图片 ID
    `pic_url`    VARCHAR(3000) NOT NULL,  -- 图片 URL
    PRIMARY KEY (`id`), -- 每个动态下每个图片唯一
    INDEX (`article_id`) -- 动态 ID 索引
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 关注表（记录用户的关注关系）
CREATE TABLE IF NOT EXISTS `follow`
(
    `user_id`          INT UNSIGNED NOT NULL,    -- 用户 ID
    `followed_user_id` INT UNSIGNED NOT NULL,    -- 被关注的用户 ID
    `follow_time`   TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 关注时间

    PRIMARY KEY (`user_id`, `followed_user_id`) -- 复合主键：用户与关注的用户组合
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 点赞表（记录用户点赞行为）
CREATE TABLE IF NOT EXISTS `praise`
(
    `id`      INT UNSIGNED NOT NULL,       -- 被点赞的对象 ID（动态、评论）
    `user_id` INT UNSIGNED NOT NULL,       -- 用户 ID
    `which`   INT UNSIGNED NOT NULL,       -- 点赞对象类型（0：动态，1：一级评论，2：二级评论）
    `praise_time`   TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 关注时间

    PRIMARY KEY (`id`, `which`, `user_id`) -- 复合主键：对象 ID、类型与用户 ID
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

 -- 订单表
CREATE TABLE IF NOT EXISTS `order`
(
    `order_id`      INT UNSIGNED AUTO_INCREMENT,       -- 订单编号
    `user_id`       INT UNSIGNED NOT NULL,             -- 用户 ID
    `order_date`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 订单创建时间
    `status`        ENUM('待支付', '已支付', '已发货', '已完成', '已取消') NOT NULL, -- 订单状态
    `payment_method` ENUM('支付宝', '微信', '银行卡', '货到付款') NOT NULL, -- 支付方式
    `total_price`   DECIMAL(15, 2) NOT NULL,            -- 订单总金额
    `shipping_address` VARCHAR(500) NOT NULL,           -- 收货地址
    `shipping_status` ENUM('待发货', '已发货', '已签收') NOT NULL, -- 发货状态
    `payment_status` ENUM('未支付', '已支付', '支付失败') NOT NULL, -- 支付状态
    `delivery_time` TIMESTAMP NULL,                     -- 发货时间
    PRIMARY KEY (`order_id`),
    INDEX (`user_id`),                                  -- 用户 ID 索引
    INDEX (`order_date`)                                -- 订单创建时间索引
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 收货地址表
CREATE TABLE IF NOT EXISTS `shipping_address`
(
    `address_id`    INT UNSIGNED AUTO_INCREMENT,      -- 收货地址 ID
    `user_id`       INT UNSIGNED NOT NULL,            -- 用户 ID
    `address`       VARCHAR(1000) NOT NULL,           -- 收货地址（包含省、市、区、详细地址）
    `receiver_name` VARCHAR(100) NOT NULL,            -- 收货人姓名
    `receiver_phone` VARCHAR(20) NOT NULL,            -- 收货人电话
    `is_default`    TINYINT(1) DEFAULT 0,             -- 是否为默认地址（0：不是，1：是）
    `add_time`      TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 地址添加时间
    PRIMARY KEY (`address_id`),
    INDEX (`user_id`)                                -- 用户 ID 索引
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 支付记录表
CREATE TABLE IF NOT EXISTS `payment_history`
(
    `payment_id`    INT UNSIGNED AUTO_INCREMENT,       -- 支付记录 ID
    `order_id`      INT UNSIGNED NOT NULL,             -- 关联的订单 ID
    `user_id`       INT UNSIGNED NOT NULL,             -- 用户 ID
    `payment_method` ENUM('支付宝', '微信', '银行卡', '货到付款') NOT NULL, -- 支付方式
    `payment_amount` DECIMAL(15, 2) NOT NULL,          -- 支付金额
    `payment_time`   TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 支付时间
    `status`         ENUM('未支付', '已支付', '支付失败') NOT NULL, -- 支付状态
    PRIMARY KEY (`payment_id`),
    INDEX (`order_id`),                                -- 订单 ID 索引
    INDEX (`user_id`),                                 -- 用户 ID 索引
    INDEX (`payment_time`)                              -- 支付时间索引
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 退货记录表
CREATE TABLE IF NOT EXISTS `return_history`
(
    `return_id`    INT UNSIGNED AUTO_INCREMENT,        -- 退货记录 ID
    `order_id`     INT UNSIGNED NOT NULL,              -- 关联的订单 ID
    `user_id`       INT UNSIGNED NOT NULL,             -- 用户 ID
    `good_id`      INT UNSIGNED NOT NULL,              -- 退货商品 ID
    `return_reason` VARCHAR(1000) NOT NULL,            -- 退货原因
    `return_status` ENUM('申请中', '已同意', '已退货', '已拒绝') NOT NULL, -- 退货状态
    `return_time`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 退货时间
    PRIMARY KEY (`return_id`),
    INDEX (`order_id`),                                 -- 订单 ID 索引
    INDEX (`good_id`),                                  -- 商品 ID 索引
    INDEX (`user_id`),                                 -- 用户 ID 索引
    INDEX (`return_time`)                               -- 退货时间索引
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 用户反馈
CREATE TABLE IF NOT EXISTS `user_feedback`
(
    `feedback_id`    INT UNSIGNED AUTO_INCREMENT,       -- 反馈 ID
    `user_id`        INT UNSIGNED NOT NULL,             -- 用户 ID
    `feedback_text`  VARCHAR(3000) NOT NULL,            -- 反馈内容
    `feedback_time`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 反馈时间
    `reply_text`     VARCHAR(3000),                     -- 回复内容
    `status`         ENUM('未处理', '处理中', '已处理') NOT NULL, -- 反馈状态
    PRIMARY KEY (`feedback_id`),
    INDEX (`user_id`),                                  -- 用户 ID 索引
    INDEX (`feedback_time`)                              -- 反馈时间索引
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 库存记录表
CREATE TABLE IF NOT EXISTS `stock_history`
(
    `stock_id`      INT UNSIGNED AUTO_INCREMENT,        -- 库存记录 ID
    `good_id`       INT UNSIGNED NOT NULL,              -- 商品 ID
    `quantity`      INT UNSIGNED NOT NULL,              -- 增减数量（负数为出库，正数为入库）
    `operation`     ENUM('入库', '出库') NOT NULL,      -- 操作类型
    `operation_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 操作时间
    PRIMARY KEY (`stock_id`),
    INDEX (`good_id`),                                  -- 商品 ID 索引
    INDEX (`operation_time`)                             -- 操作时间索引
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 商品评价表
CREATE TABLE IF NOT EXISTS `product_review`
(
    `review_id`      INT UNSIGNED AUTO_INCREMENT,      -- 评价 ID
    `good_id`        INT UNSIGNED NOT NULL,            -- 商品 ID
    `user_id`        INT UNSIGNED NOT NULL,            -- 用户 ID
    `rating`         INT UNSIGNED NOT NULL,            -- 评分（1-5）
    `review_text`    VARCHAR(3000),                     -- 评价内容
    `review_time`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 评价时间
    PRIMARY KEY (`review_id`),
    INDEX (`good_id`),                                 -- 商品 ID 索引
    INDEX (`user_id`),                                 -- 用户 ID 索引
    INDEX (`review_time`)                               -- 评价时间索引
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 购物车
CREATE TABLE IF NOT EXISTS `cart`
(
    `cart_id`     INT UNSIGNED AUTO_INCREMENT,        -- 购物车项编号
    `user_id`     INT UNSIGNED NOT NULL,              -- 用户 ID
    `good_id`     INT UNSIGNED NOT NULL,              -- 商品 ID
    `quantity`    INT UNSIGNED NOT NULL,              -- 商品数量
    `add_time`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 添加时间
    PRIMARY KEY (`cart_id`),
    INDEX (`user_id`),                                -- 用户 ID 索引
    INDEX (`good_id`)                                 -- 商品 ID 索引
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- 秒杀表
CREATE TABLE IF NOT EXISTS `seckill`
(
    `seckill_id`    INT UNSIGNED AUTO_INCREMENT,       -- 秒杀活动编号
    `good_id`       INT UNSIGNED NOT NULL,             -- 商品 ID
    `seckill_price` DECIMAL(15, 2) NOT NULL,           -- 秒杀价格
    `seckill_stock` INT UNSIGNED NOT NULL,             -- 秒杀库存
    `start_time`    TIMESTAMP NOT NULL,                -- 秒杀开始时间
    `end_time`      TIMESTAMP NOT NULL,                -- 秒杀结束时间
    `status`        ENUM('未开始', '进行中', '已结束') NOT NULL, -- 活动状态
    PRIMARY KEY (`seckill_id`),
    INDEX (`good_id`),                                 -- 商品 ID 索引
    INDEX (`start_time`),                              -- 秒杀开始时间索引
    INDEX (`status`)                                   -- 秒杀活动状态索引
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

# ============================示例数据=============================
INSERT INTO `user` (`user_id`, `stu_id`, `school_id`, `nickname`, `password`, `register_date`, `sex`, `text`, `avatar_url`, `is_admin`)
VALUES
    ('admin', '00000000000', 1, 'Administrator', '$2a$10$tMcsrD0JKYiW.qjtUX0/RO5SDqww2MWmXqvHGxwujByLtCwvKC2Am', NOW(), '保密', '系统管理员账号', 'https://example.com/avatar/admin.jpg', 1),
    ('13812345678', '20250301', 1, 'Alice', '$2a$10$H9KpqzAp9DnMBs/IjreLLuUuQ9A/F/mkmnXTRXM0A.zvitG1HIz/a', NOW(), '女', '喜欢编程和旅游', 'https://example.com/avatar/alice.jpg', 0),
    ('13987654321', '20250302', 1, 'Bob', '$2a$10$H9KpqzAp9DnMBs/IjreLLuUuQ9A/F/mkmnXTRXM0A.zvitG1HIz/a', NOW(), '男', '足球爱好者', 'https://example.com/avatar/bob.jpg', 0),
    ('13656781234', '20250303', 2, 'Charlie', '$2a$10$H9KpqzAp9DnMBs/IjreLLuUuQ9A/F/mkmnXTRXM0A.zvitG1HIz/a', NOW(), '男', '音乐发烧友', 'https://example.com/avatar/charlie.jpg', 0);


INSERT INTO `school` (`school_name`, `statement`, `school_avatar_url`, `school_member`)
VALUES
    ('重庆邮电大学', '重邮（重庆邮电大学）是一所以信息技术为特色的高校，致力于培养通信、计算机等领域的人才。学校在科研和技术创新方面具有较强实力。', 'https://example.com/school/pku.jpg', 43245),
    ('清华大学', '中国顶尖高校，理工科优势明显', 'https://example.com/school/tsinghua.jpg', 46343),
    ('北京大学', '综合性研究型大学，历史悠久', 'https://example.com/school/pku.jpg', 32524);


INSERT INTO `good` (`good_name`, `statement`, `price`, `pic_url`, `submit_time`, `from_school_id`, `from_user_id`, `status`, `get_user_id`, `stock`, `sold_count`)
VALUES
    ('二手笔记本电脑', '9成新 MacBook Pro，512GB SSD，16GB 内存', 6500.00, 'https://example.com/goods/macbook.jpg', NOW(), 1, 100002, 0, NULL, 2, 1),
    ('PS5 游戏机', '几乎全新，送两款游戏', 3500.00, 'https://example.com/goods/ps5.jpg', NOW(), 2, 100001, 1, 100002, 1, 0);

INSERT INTO `private_message` (`sender_id`, `receiver_id`, `message_type`, `message_content`, `send_time`, `status`)
VALUES
    (100002, 100001, 0, '你好，最近在忙什么？', NOW(), 0),
    (100001, 100002, 0, '没什么，刚买了个新游戏机！', NOW(), 1);

INSERT INTO `article` (`user_id`, `submit_time`, `text`, `topic`)
VALUES
    (100002, NOW(), '今天在学校的实验室做了一个有趣的实验！', '科技'),
    (100001, NOW(), '刚买了PS5，想找人一起玩游戏！', '游戏');

INSERT INTO `comment` (`reply_id`, `user_id`, `submit_time`, `text`, `which`, `reply_user_id`)
VALUES
    (1, 100001, NOW(), '听起来很有趣，什么实验？', 1, NULL),
    (2, 100002, NOW(), '加我好友，一起玩！', 1, NULL);

INSERT INTO `pic` (`article_id`, `pic_url`)
VALUES
    (1, 'https://example.com/article/experiment.jpg'),
    (2, 'https://example.com/article/ps5.jpg');

INSERT INTO `follow` (`user_id`, `followed_user_id`, `follow_time`)
VALUES
    (100002, 100001, NOW()),
    (100001, 100002, NOW());

INSERT INTO `praise` (`id`, `user_id`, `which`, `praise_time`)
VALUES
    (1, 100001, 0, NOW()),  -- Bob 给 Alice 的帖子点赞
    (2, 100002, 0, NOW());  -- Alice 给 Bob 的帖子点赞
