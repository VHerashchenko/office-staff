CREATE TABLE vh_worker(
    id SERIAL PRIMARY KEY,
    name VARCHAR (40) NOT NULL,
    birthday DATE NOT NULL,
    start_date DATE NOT NULL,
    salary BIGINT NOT NULL,
    manager_id INT,
    description TEXT
);

CREATE TABLE vh_role_worker(
    id SERIAL PRIMARY KEY,
    worker_id INT,
    role_id INT
);

CREATE TABLE vh_role(
    id INT PRIMARY KEY,
    name VARCHAR (10) NOT NULL
);

ALTER TABLE vh_worker
    ADD CONSTRAINT fk_worker_manager FOREIGN KEY (manager_id) REFERENCES vh_worker (id);

ALTER TABLE vh_role_worker
    ADD CONSTRAINT fk_worker_role FOREIGN KEY (worker_id) REFERENCES vh_worker (id);

ALTER TABLE vh_role_worker
    ADD CONSTRAINT fk_role_worker FOREIGN KEY (role_id) REFERENCES vh_role (id);
