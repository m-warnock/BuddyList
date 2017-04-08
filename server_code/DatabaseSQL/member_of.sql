CREATE TABLE `member_of`(
	UserID int(12) unsigned NOT NULL,
    GroupID int(12) unsigned NOT NULL,
    PRIMARY KEY (UserID, GroupID)
)

ENGINE=INNODB DEFAULT CHARSET =utf8;