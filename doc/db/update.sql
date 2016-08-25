ALTER TABLE `jbpress_comments` CHANGE `comment_post_ID` `comment_post_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0';
ALTER TABLE `jbpress_comments` CHANGE `comment_ID` `comment_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0';
ALTER TABLE `jbpress_comments` CHANGE `comment_author_IP` `comment_author_ip` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '';