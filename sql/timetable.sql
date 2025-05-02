create table time_table
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    user_id varchar(50) NOT NULL,
    name varchar(255) DEFAULT 'untitled',
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    is_main BOOLEAN DEFAULT false
);

create table course
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    user_id varchar(50) NOT NULL,
    title varchar(255) NOT NULL,
    instructor varchar(255),
    color varchar(255)
);

create table time_table_detail
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    time_table_id bigint,
    course_id bigint,
    weekday int NOT NULL,
    location varchar(100),
    start_time TIME,
    end_time TIME,
    FOREIGN KEY(time_table_id) REFERENCES time_table(id) ON DELETE CASCADE,
    FOREIGN KEY(course_id) REFERENCES course(id) ON DELETE CASCADE
);

create table assignment
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    course_id bigint,
    title varchar(255) NOT NULL,
    deadline DATETIME NOT NULL,
    FOREIGN KEY(course_id) REFERENCES course(id) ON DELETE CASCADE
);
