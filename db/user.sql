SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zs', 11);
INSERT INTO `user` VALUES (2, 'ls', 15);
INSERT INTO `user` VALUES (3, 'zs', 22);
INSERT INTO `user` VALUES (4, 'ww', 15);
INSERT INTO `user` VALUES (5, 'ls', 18);
INSERT INTO `user` VALUES (6, 'ww', 16);
INSERT INTO `user` VALUES (7, 'zs', 11);
INSERT INTO `user` VALUES (8, 'ls', 19);
INSERT INTO `user` VALUES (9, 'ww', 16);

SET FOREIGN_KEY_CHECKS = 1;
