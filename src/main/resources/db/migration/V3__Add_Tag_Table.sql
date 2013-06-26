CREATE TABLE Post_Tag (
  post_id int NOT NULL,
  tags_name varchar(255) NOT NULL,
  KEY FK_TAGS_NAME_KEY (tags_name),
  KEY FK_POST_ID (post_id)
) ENGINE=InnoDB;

CREATE TABLE Tag (
  name varchar(255) NOT NULL,
  PRIMARY KEY (name)
) ENGINE=InnoDB;