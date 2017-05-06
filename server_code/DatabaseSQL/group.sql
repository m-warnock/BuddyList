CREATE TABLE `group` (
    GroupID int(12) unsigned NOT NULL AUTO_INCREMENT,
    GroupName VARCHAR(255) NOT NULL,
    GroupDescription VARCHAR(255),
    CreationDate DATE,
    PRIMARY KEY (GroupID)
)
ENGINE=INNODB DEFAULT CHARSET=utf8;

ALTER TABLE `group`
ADD FOREIGN KEY (GroupID) REFERENCES group_list_data(GroupID);

--write cascade for deleting everybody from the member_of table