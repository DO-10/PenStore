/*
 Navicat Premium Data Transfer

 Source Server         : 软件工程
 Source Server Type    : MySQL
 Source Server Version : 80011 (8.0.11)
 Source Host           : localhost:3306
 Source Schema         : penstore

 Target Server Type    : MySQL
 Target Server Version : 80011 (8.0.11)
 File Encoding         : 65001

 Date: 22/03/2025 17:13:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `address_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `quantity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `added_at` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ischosen` binary(1) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shop_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('0c3a1c97-9e05-4d01-ba9b-517a7e1f4857', 'e', 'f0d288ec-6fb5-4708-9837-3ea1c56ba883', 'af306b72-15e6-496d-a68e-a4f3772dde0f');
INSERT INTO `category` VALUES ('0f81f7d2-9467-48c7-94b9-ae49133bc624', '好', NULL, 'af306b72-15e6-496d-a68e-a4f3772dde0f');
INSERT INTO `category` VALUES ('1', '大笔', NULL, 'af306b72-15e6-496d-a68e-a4f3772dde0f');
INSERT INTO `category` VALUES ('14251a23-36b8-4df2-9b4f-6c3db944e4fd', '挺好的', '376b0cce-567f-40b7-b7c6-f87c4d154b32', 'af306b72-15e6-496d-a68e-a4f3772dde0f');
INSERT INTO `category` VALUES ('2', '中笔', NULL, 'af306b72-15e6-496d-a68e-a4f3772dde0f');
INSERT INTO `category` VALUES ('3', '小笔', NULL, 'af306b72-15e6-496d-a68e-a4f3772dde0f');
INSERT INTO `category` VALUES ('376b0cce-567f-40b7-b7c6-f87c4d154b32', '好', NULL, 'af306b72-15e6-496d-a68e-a4f3772dde0f');
INSERT INTO `category` VALUES ('4', '牛牛', '1', 'af306b72-15e6-496d-a68e-a4f3772dde0f');
INSERT INTO `category` VALUES ('5', '哈哈', '2', 'af306b72-15e6-496d-a68e-a4f3772dde0f');
INSERT INTO `category` VALUES ('6', '嗯嗯', '3', 'af306b72-15e6-496d-a68e-a4f3772dde0f');
INSERT INTO `category` VALUES ('78bcd370-c38f-4285-a2f4-f91250050b53', '太好了', '0f81f7d2-9467-48c7-94b9-ae49133bc624', 'af306b72-15e6-496d-a68e-a4f3772dde0f');
INSERT INTO `category` VALUES ('980c8687-63cf-4127-8236-412d1ee9512c', 'n', 'f0d288ec-6fb5-4708-9837-3ea1c56ba883', 'af306b72-15e6-496d-a68e-a4f3772dde0f');
INSERT INTO `category` VALUES ('c98402dc-08a7-4eb3-82d1-e957d8407084', 'c', 'f0d288ec-6fb5-4708-9837-3ea1c56ba883', 'af306b72-15e6-496d-a68e-a4f3772dde0f');
INSERT INTO `category` VALUES ('d4abe8ac-740f-4b23-9c33-ab1189ef7c68', 'i', 'f0d288ec-6fb5-4708-9837-3ea1c56ba883', 'af306b72-15e6-496d-a68e-a4f3772dde0f');
INSERT INTO `category` VALUES ('f0d288ec-6fb5-4708-9837-3ea1c56ba883', 'nice', NULL, 'af306b72-15e6-496d-a68e-a4f3772dde0f');

-- ----------------------------
-- Table structure for chat_messages
-- ----------------------------
DROP TABLE IF EXISTS `chat_messages`;
CREATE TABLE `chat_messages`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `receiver_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `timestamp` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `is_read` tinyint(4) NULL DEFAULT 0,
  `commentId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of chat_messages
-- ----------------------------
INSERT INTO `chat_messages` VALUES (64, 'af306b72-15e6-496d-a68e-a4f3772dde0f', '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', 'hello', '2025-03-19 14:56:58', 1, NULL);
INSERT INTO `chat_messages` VALUES (65, 'af306b72-15e6-496d-a68e-a4f3772dde0f', '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', '你不能说话', '2025-03-19 14:57:16', 1, NULL);
INSERT INTO `chat_messages` VALUES (66, 'af306b72-15e6-496d-a68e-a4f3772dde0f', '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', 'hello', '2025-03-19 15:08:08', 1, NULL);
INSERT INTO `chat_messages` VALUES (67, 'af306b72-15e6-496d-a68e-a4f3772dde0f', '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', '你好', '2025-03-19 16:04:58', 1, NULL);
INSERT INTO `chat_messages` VALUES (68, '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '你好呀', '2025-03-19 16:06:05', 1, NULL);
INSERT INTO `chat_messages` VALUES (69, 'af306b72-15e6-496d-a68e-a4f3772dde0f', '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', 'good', '2025-03-19 16:06:15', 1, NULL);
INSERT INTO `chat_messages` VALUES (70, 'af306b72-15e6-496d-a68e-a4f3772dde0f', '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', 'hello', '2025-03-19 16:24:04', 1, NULL);
INSERT INTO `chat_messages` VALUES (71, 'af306b72-15e6-496d-a68e-a4f3772dde0f', '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', 'HI', '2025-03-19 16:27:53', 1, NULL);
INSERT INTO `chat_messages` VALUES (72, '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'hi', '2025-03-19 16:28:12', 1, NULL);
INSERT INTO `chat_messages` VALUES (73, 'af306b72-15e6-496d-a68e-a4f3772dde0f', '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', 'lrc', '2025-03-19 16:29:48', 1, NULL);
INSERT INTO `chat_messages` VALUES (74, '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'lrc', '2025-03-19 16:29:54', 1, NULL);
INSERT INTO `chat_messages` VALUES (75, 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '哈哈哈', '2025-03-19 19:07:58', 1, '8fcf5d66-bbda-4dfe-b976-5d009d8711ce');
INSERT INTO `chat_messages` VALUES (76, 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '猪猪侠', '2025-03-19 19:10:19', 1, '2cd94262-1e5a-4ec3-98cf-cb27dc8aecc4');
INSERT INTO `chat_messages` VALUES (77, 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '猪猪侠', '2025-03-19 19:12:15', 1, 'a0278684-814e-45d7-bf01-74556f60a5bb');
INSERT INTO `chat_messages` VALUES (78, 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'good', '2025-03-19 19:12:47', 1, 'e1e4741f-ae37-408f-90f5-59d4d2a57467');
INSERT INTO `chat_messages` VALUES (79, 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'no', '2025-03-19 19:12:58', 1, '3cc26d32-1f41-4578-ad1c-b0f4b6685717');
INSERT INTO `chat_messages` VALUES (80, '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'hi', '2025-03-19 19:34:25', 0, NULL);
INSERT INTO `chat_messages` VALUES (81, 'af306b72-15e6-496d-a68e-a4f3772dde0f', '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', 'hi', '2025-03-19 19:34:46', 1, NULL);
INSERT INTO `chat_messages` VALUES (82, 'af306b72-15e6-496d-a68e-a4f3772dde0f', '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', 'hello', '2025-03-19 19:34:51', 1, NULL);
INSERT INTO `chat_messages` VALUES (83, '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'hi', '2025-03-19 19:35:02', 0, NULL);
INSERT INTO `chat_messages` VALUES (84, '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'I\'m Chen', '2025-03-19 19:38:15', 0, NULL);
INSERT INTO `chat_messages` VALUES (85, '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'I\'m Chen', '2025-03-19 19:40:33', 0, NULL);
INSERT INTO `chat_messages` VALUES (86, '92c8f900-3e12-43bb-ba43-7c23e5a4aea9', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'I,m Chen', '2025-03-19 19:41:08', 0, NULL);
INSERT INTO `chat_messages` VALUES (87, '45c4c2cb-4f2c-44f5-839a-628b39d78469', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'Hi', '2025-03-19 21:25:41', 0, NULL);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pop` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `star` int(11) NULL DEFAULT NULL,
  `comment_at` datetime NULL DEFAULT NULL,
  `images` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isreplied` binary(1) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1c51076c-ac9d-4627-a324-9f6586f9320d', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '1', '123001', 4, '2025-03-19 19:06:22', NULL, NULL, '12呃3', NULL);
INSERT INTO `comment` VALUES ('2c81834d-dce9-4f5c-b106-f567c7566f64', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '1', '121001', 1, '2025-03-15 23:33:49', NULL, 0x31, '差', NULL);
INSERT INTO `comment` VALUES ('2cd94262-1e5a-4ec3-98cf-cb27dc8aecc4', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '1', '123001', 4, '2025-03-19 19:10:18', NULL, NULL, '猪猪侠', NULL);
INSERT INTO `comment` VALUES ('3cc26d32-1f41-4578-ad1c-b0f4b6685717', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '1', '121002', 1, '2025-03-19 19:12:58', NULL, NULL, 'no', NULL);
INSERT INTO `comment` VALUES ('45fb1f5b-1dce-49cc-8897-3eb4e60d3bed', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '2', NULL, NULL, '2025-03-15 23:34:00', NULL, NULL, '差', '2c81834d-dce9-4f5c-b106-f567c7566f64');
INSERT INTO `comment` VALUES ('4addaf99-f9d1-4dba-a55d-d91ba4b10ca9', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '2', NULL, NULL, '2025-03-15 23:34:12', NULL, NULL, '差差', '2c81834d-dce9-4f5c-b106-f567c7566f64');
INSERT INTO `comment` VALUES ('67e2062b-a892-4c1e-93a3-64f61e3db4ff', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '1', '121002', 4, '2025-03-19 19:06:09', NULL, NULL, '呵呵', NULL);
INSERT INTO `comment` VALUES ('8fcf5d66-bbda-4dfe-b976-5d009d8711ce', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '1', '121002', 4, '2025-03-19 19:07:58', NULL, NULL, '哈哈哈', NULL);
INSERT INTO `comment` VALUES ('a0278684-814e-45d7-bf01-74556f60a5bb', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '1', '123001', 4, '2025-03-19 19:12:15', NULL, NULL, '猪猪侠', NULL);
INSERT INTO `comment` VALUES ('e1e4741f-ae37-408f-90f5-59d4d2a57467', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '1', '123001', 5, '2025-03-19 19:12:47', NULL, NULL, 'good', NULL);
INSERT INTO `comment` VALUES ('e52e203c-b5be-4a2b-834c-16da4186d4aa', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '2', NULL, NULL, '2025-03-15 23:33:02', NULL, NULL, '好', 'f21eb4d5-50d8-4778-b06b-a2d1f45ad967');
INSERT INTO `comment` VALUES ('f21eb4d5-50d8-4778-b06b-a2d1f45ad967', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '1', '121001', 5, '2025-03-15 21:02:17', NULL, 0x31, 'hao', NULL);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stock` int(11) NULL DEFAULT NULL,
  `category_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shop_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cost` decimal(10, 2) NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('121001', '晨光文具行家静音书写笔', 8.00, '乘运而来，金榜助力，好运锦鲤文具11件套，笔锋流畅，本子精美，自用送礼皆可', 120, '1', 'pictures/1.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('121002', '孔庙祈福系列晨光2B铅笔六支装', 12.00, '开学必备，马到成功！', 1000, '1', 'pictures/2.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('121003', '小学规范汉字书写：第1册', 5.00, '汉字规范，从小学做起！我奶奶也还在用', 100, '1', 'pictures/3.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('121004', 'MG晨光行家系列签字笔', 15.00, '小贵，但也不一定好用，谢谢！', 10, '1', 'pictures/4.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('121005', '全面屏液晶手写板', 200.00, '学生商务记事本可折叠大屏', 3000, '1', 'pictures/5.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('121006', '黑科技永恒铅笔', 10.00, '不用削不会断！娃儿喜欢的不得了', 1000, '1', 'pictures/6.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('121007', '前苏联苏维埃风记事本', 30.00, '斯大林用过', 100, '1', 'pictures/7.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('121008', '生日礼物！哈利波特羽毛笔礼盒装', 40.00, '高颜值！绝不是花瓶，男回伴手礼', 1000, '1', 'pictures/8.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'available', NULL, NULL);
INSERT INTO `goods` VALUES ('121009', '【国家图书馆】九州山海古典纯铜金属书签', 49.00, '分量十足！颜值极高！国家图书馆官方力荐', 200, '1', 'pictures/9.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'unsold', NULL, NULL);
INSERT INTO `goods` VALUES ('121010', '人民日报金句文案55页小卡片', 33.00, '语文老师用过都说好，作文肯定用得上！但请注意，不要把它带上考场', 100, '1', 'pictures/10.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'available', NULL, NULL);
INSERT INTO `goods` VALUES ('121011', '高材ABS笔盒', 10.00, '全方位保护，更好的收纳空间，同桌看了回想偷！', 200, '1', 'pictures/11.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'available', NULL, NULL);
INSERT INTO `goods` VALUES ('121012', '三菱黑科技笔3', 20.00, '练字专用', 230, '1', 'pictures/12.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'available', NULL, NULL);
INSERT INTO `goods` VALUES ('121013', '乌合之众同款笔by古斯塔夫.勒庞', 30.00, '大众心理学必读', 100, '1', 'pictures/13.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'available', NULL, NULL);
INSERT INTO `goods` VALUES ('121014', '合金笔杆黑科技', 60.00, '可擦可练字可素描皆可', 200, '1', 'pictures/14.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'available', NULL, NULL);
INSERT INTO `goods` VALUES ('121015', '钢笔式毛笔', 5.00, '他可以既是钢笔又是毛笔，也可以既不是毛笔也不是钢笔', 400, '1', 'pictures/15.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'available', NULL, NULL);
INSERT INTO `goods` VALUES ('122001', '毛笔套装初学者', 24.00, '文房四宝成人练字，附带毛笔五天速成教学视频', 100, '2', 'pictures/16.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'available', NULL, NULL);
INSERT INTO `goods` VALUES ('122002', '上林赋全篇手抄字帖', 33.00, '卷轴袖珍珍藏礼盒版！不再问心中悲喜，只问你心中千万缕', 200, '2', 'pictures/17.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'available', NULL, NULL);
INSERT INTO `goods` VALUES ('122003', '《中国书法一本通》', 26.00, '毛笔字临摹。学书法，懂书法，这本书就够了！', 200, '2', 'pictures/18.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'available', NULL, NULL);
INSERT INTO `goods` VALUES ('122004', '毛笔水写布练字布', 30.00, '速干可重复使用，清水练字临摹', 323, '2', 'pictures/19.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'available', NULL, NULL);
INSERT INTO `goods` VALUES ('122005', '宋徽宗瘦金体练字帖', 11.00, '华夏万卷倾情奉献，千字文软笔教程', 2423, '2', 'pictures/20.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'available', NULL, NULL);
INSERT INTO `goods` VALUES ('122006', '行数楷书速成', 23.00, '效果不好请退款！28天速成，大学生适用', 546, '2', 'pictures/21.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'available', NULL, NULL);
INSERT INTO `goods` VALUES ('122007', '兰亭系列（鼠须）考级国展毛笔', 50.00, '更有弹性，更耐用，书写流畅！早知道，还是兰亭鼠须笔！', 46, '2', 'pictures/22.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'available', NULL, NULL);
INSERT INTO `goods` VALUES ('122008', '【翰林系列】瘦金体专用毛笔', 22.00, '瘦金体初学者体质宝宝的毛笔', 646, '2', 'pictures/23.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'available', NULL, NULL);
INSERT INTO `goods` VALUES ('122009', '黑头毛笔', 77.00, '似乎可以去黑头', 666, '2', 'pictures/24.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'unsold', NULL, NULL);
INSERT INTO `goods` VALUES ('122010', '祥云状香薰熏炉安神·1书写用', 11.00, '鹅梨账中安神香，家用室内持久', 675, '2', 'pictures/25.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'unsold', NULL, NULL);
INSERT INTO `goods` VALUES ('122011', '《如何写瘦金》by邱金生', 15.00, '瘦金大师倾情奉献，一本书讲透瘦金', 6774, '2', 'pictures/26.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'unsold', NULL, NULL);
INSERT INTO `goods` VALUES ('122012', '初学书法套装精简版', 60.00, '自备墨汁水写布，大学生适用', 7545, '2', 'pictures/27.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('122013', '仿古釉青瓷存墨书法用品', 17.00, '带盖防干，颜值在线', 123, '2', 'pictures/28.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('122014', '花香持久型墨汁一盒装', 16.00, '家用优雅，手有余香。学生推荐，多种花香类型可选', 1243, '2', 'pictures/29.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('122015', '松烟墨液', 7.00, '国画作品级书法专用墨水', 13432, '2', 'pictures/30.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('123001', '【艺术家之选】专业级水彩画套装', 150.00, '包含所有基本颜色，适合初学者和专业人士使用', 500, '3', 'pictures/31.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('123002', '【画布大师】油画布框', 50.00, '高品质棉布，适合各种油画技法', 200, '3', 'pictures/32.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('123003', '【色彩盛宴】丙烯酸颜料套装', 80.00, '色彩鲜艳，快干，适合室内外绘画', 300, '3', 'pictures/33.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('123004', '【素描专家】素描铅笔套装', 20.00, '从HB到6B，满足不同硬度需求', 400, '3', 'pictures/34.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('123005', '【刀锋画匠】艺术家级油画刀', 35.00, '不锈钢材质，多种尺寸选择', 150, '3', 'pictures/35.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('123006', '【写生伴侣】便携式画板', 25.00, '可折叠设计，方便户外写生', 250, '3', 'pictures/36.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('123007', '【粉彩大师】专业级水粉画颜料', 60.00, '色彩丰富，覆盖力强', 180, '3', 'pictures/37.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('123008', '【稳固之架】画架', 100.00, '可调节高度，稳固耐用', 120, '3', 'pictures/38.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('123009', '【调色大师】调色板', 15.00, '大容量，易于清洗', 500, '3', 'pictures/39.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('123010', '【橡皮卫士】绘画用橡皮擦', 5.00, '柔软耐用，适合各种绘画需求', 1000, '3', 'pictures/40.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('123011', '【笔洗专家】绘画专用洗笔筒', 20.00, '不锈钢材质，耐用易清洗', 300, '3', 'pictures/41.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('123012', '【定色保护】绘画用喷雾定色剂', 18.00, '保护画作，防止颜料脱落', 400, '3', 'pictures/42.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('123013', '【刮刀工艺】绘画用刮刀', 10.00, '多种尺寸，适合不同绘画技巧', 200, '3', 'pictures/43.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('123014', '【画笔艺境】绘画用画笔套装', 40.00, '多种尺寸和形状，适合各种绘画风格', 600, '3', 'pictures/44.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('123015', '【画布精选】绘画用画布', 30.00, '高品质纯棉画布，适合各种颜料', 800, '3', 'pictures/45.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('124001', '香奈儿仙女最爱画眉笔', 5000.00, '喜上眉梢，目如丹凤', 200, '4', 'pictures/46.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('124002', '黑酷防摔转转笔', 80.00, '适合新手使用。力学黄金升级', 150, '4', 'pictures/47.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('124003', '花西子眉笔李佳琪代言', 200.00, '如笔精细勾勒，如粉顺滑柔色', 100, '4', 'pictures/48.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('124004', '全自动金属绘画铅笔', 120.00, '低重心，重手感，不断芯', 300, '4', 'pictures/49.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('124005', '晨光0.5笔芯家用学生用一筒60支装', 60.00, '百年老厂，加量30%不加价', 250, '4', 'pictures/50.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('124006', '仿真玫瑰笔', 99.00, '浪漫与实用共存', 500, '4', 'pictures/51.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('124007', '签字笔的龙头笔夹', 150.00, '至尊高贵，适合商务', 120, '4', 'pictures/52.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('124008', '消字灵修正液新版', 19.00, '修正错字，舍我其谁', 400, '4', 'pictures/53.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('124009', '莫兰迪荧光笔', 10.00, '学生重点标记笔套装', 100, '4', 'pictures/54.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('124010', '莫兰迪荧光笔复色款', 12.00, '全新升级，色调更复古而品味更时尚', 200, '4', 'pictures/55.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('124011', '莫兰迪荧光笔清香款', 14.00, '法国调香师优选，纸留余香，多种款式可选', 300, '4', 'pictures/56.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('124012', '激光笔', 50.00, '有效范围50米，请不要对准眼睛', 150, '4', 'pictures/57.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('124013', '录音笔', 159.00, '多模式按摩，缓解肩颈疲劳', 200, '4', 'pictures/58.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('124014', '谢保华高伏电笔', 199.00, '专业电工可用，旗舰级电笔', 250, '4', 'pictures/59.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('124015', '清香四色笔', 12.00, '红蓝黑紫多功能集成，笔体纤细易于使用', 300, '4', 'pictures/60.jpg', 'af306b72-15e6-496d-a68e-a4f3772dde0f', NULL, NULL, NULL);
INSERT INTO `goods` VALUES ('af306b72-15e6-496d-a68e-a4f3772dde0f', '鸣潮', 344.00, NULL, 1324, NULL, 'images/goods/ff27b70c-3269-4dbc-a77b-682354d5a59d_ce4ba9f048d2e97e1eab6f08bd761b59202763894.jpg@360w_270h_1s.avif', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'unsold', NULL, NULL);
INSERT INTO `goods` VALUES ('af306b72-15e6-496d-a68e-a4f3772dde0f', '鸣潮', 344.00, NULL, 1324, NULL, 'images/goods/f290e94b-7252-4e57-99bf-ec05652edf6c_ce4ba9f048d2e97e1eab6f08bd761b59202763894.jpg@360w_270h_1s.avif', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'unsold', NULL, NULL);
INSERT INTO `goods` VALUES ('af306b72-15e6-496d-a68e-a4f3772dde0f', 'haha', 344.00, NULL, 1324, NULL, 'images/goods/d5f9b0f6-a03f-4096-a266-2bb2c117c82a_1040g3k031esfv1joma005pjqj5n1ou1vd3h3hs0!nd_dft_wlteh_webp_3.png', 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'unsold', NULL, NULL);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shipping_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tracking_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amount` decimal(10, 2) NULL DEFAULT NULL,
  `delivery_time` datetime NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('c8f4c640-1a02-4c10-9802-50c595512e0b', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '中南大学', '待付款', NULL, '2025-03-19 15:16:02', NULL, '8927442915', '66购物', 972.00, NULL, 'XIAO');
INSERT INTO `order` VALUES ('a63f902a-a62e-405f-876a-a0e0605a0f61', '45c4c2cb-4f2c-44f5-839a-628b39d78469', '12北', '待付款', NULL, '2025-03-19 15:37:24', NULL, '18926449335', '121购物', 364.00, NULL, 'Hsiao');
INSERT INTO `order` VALUES ('3121740a-1484-41e3-8ab9-947477bd2587', '45c4c2cb-4f2c-44f5-839a-628b39d78469', '12南', '待付款', NULL, '2025-03-19 18:48:44', NULL, '18926449335', '测试发货等功能', 250.00, NULL, 'Hsiao');

-- ----------------------------
-- Table structure for order_items
-- ----------------------------
DROP TABLE IF EXISTS `order_items`;
CREATE TABLE `order_items`  (
  `order_items_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `quantity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 0) NULL DEFAULT NULL,
  `shop_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_items
-- ----------------------------
INSERT INTO `order_items` VALUES ('162cd048-1c14-4ce8-93b3-fc8729372425', 'c8f4c640-1a02-4c10-9802-50c595512e0b', '121002', '6', 12, 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'completed');
INSERT INTO `order_items` VALUES ('9a1d1995-17a0-49c9-8f86-194c54d5768c', 'c8f4c640-1a02-4c10-9802-50c595512e0b', '123001', '6', 150, 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'completed');
INSERT INTO `order_items` VALUES ('c4620323-ff0d-4c34-a711-5c44a5c23f54', 'a63f902a-a62e-405f-876a-a0e0605a0f61', '121005', '1', 200, 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'completed');
INSERT INTO `order_items` VALUES ('75a67319-09a2-420e-a70b-e772a48bdfd7', 'a63f902a-a62e-405f-876a-a0e0605a0f61', '122009', '2', 77, 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'closed');
INSERT INTO `order_items` VALUES ('a459dc70-1367-4218-b04e-d42c55fed591', 'a63f902a-a62e-405f-876a-a0e0605a0f61', '123013', '1', 10, 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'closed');
INSERT INTO `order_items` VALUES ('48d3632a-3e72-4409-8d41-f0ac959be6a9', '3121740a-1484-41e3-8ab9-947477bd2587', '121001', '1', 8, 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'completed');
INSERT INTO `order_items` VALUES ('c644aa90-6c44-4911-b00b-3c67a6fa2a28', '3121740a-1484-41e3-8ab9-947477bd2587', '121002', '1', 12, 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'completed');
INSERT INTO `order_items` VALUES ('9d45ab82-b6cd-42c0-8264-bc666dc6e98a', '3121740a-1484-41e3-8ab9-947477bd2587', '121003', '1', 5, 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'completed');
INSERT INTO `order_items` VALUES ('9a2a040d-d2ab-4b98-a5fa-6c2a12bf52d4', '3121740a-1484-41e3-8ab9-947477bd2587', '121004', '1', 15, 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'completed');
INSERT INTO `order_items` VALUES ('99c6aa34-7cca-438f-b370-ba92427d5195', '3121740a-1484-41e3-8ab9-947477bd2587', '121005', '1', 200, 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'completed');
INSERT INTO `order_items` VALUES ('eec148e9-e7e7-45e7-8442-6e14c059f32d', '3121740a-1484-41e3-8ab9-947477bd2587', '121006', '1', 10, 'af306b72-15e6-496d-a68e-a4f3772dde0f', 'closed');

-- ----------------------------
-- Table structure for pic
-- ----------------------------
DROP TABLE IF EXISTS `pic`;
CREATE TABLE `pic`  (
  `picid` int(11) NOT NULL AUTO_INCREMENT,
  `picture` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`picid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pic
-- ----------------------------

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `background` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `composition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shopname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shopphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salesman` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `license` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shopid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('1', 'sasuke', '2', '3', '4', '5', '6', '7', 'Genshin', '114514', 'Sasuke', 'let me', '1112', 'af306b72-15e6-496d-a68e-a4f3772dde0f', '停业', 'carousel');
INSERT INTO `shop` VALUES ('2', 'naruto', '2', '3', '4', '5', '6', '7', 'HOK', '456', 'Naruto', 'make me', '222', '2', '1', 'thumbnail');

-- ----------------------------
-- Table structure for transaction_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `transaction_snapshot`;
CREATE TABLE `transaction_snapshot`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `total_price` decimal(10, 2) NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `shop_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `quantity` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of transaction_snapshot
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `clerk` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('af306b72-15e6-496d-a68e-a4f3772dde0f', 'x20205301335@outlook.com', '123', 'XIAO', NULL, 'images/avatar/a19c6101-3fa3-49ba-8af7-c78c7c1f959a_ce4ba9f048d2e97e1eab6f08bd761b59202763894.jpg@360w_270h_1s.avif', NULL);
INSERT INTO `user` VALUES ('1241294', NULL, NULL, '小布丁', NULL, NULL, NULL);
INSERT INTO `user` VALUES ('92c8f900-3e12-43bb-ba43-7c23e5a4aea9', '1378264698@qq.com', '1234565', 'chen', NULL, 'images/avatar/84334f6e-c006-4192-a77c-2bdfcc67c487_微信图片_20240410144808.jpg', NULL);
INSERT INTO `user` VALUES ('123456', '123', '123456', '中南大学出版社', NULL, NULL, NULL);
INSERT INTO `user` VALUES ('45c4c2cb-4f2c-44f5-839a-628b39d78469', '20205301335@proton.me', '1', 'Hsiao', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
