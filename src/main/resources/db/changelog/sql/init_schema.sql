CREATE TYPE vh_role AS ENUM ('MANAGER', 'WORKER', 'OTHER');
CREATE TYPE vh_department AS ENUM ('ACCOUNTING', 'COMMERCIAL', 'MANAGERIAL');

CREATE TABLE vh_worker(
    id SERIAL PRIMARY KEY,
    name VARCHAR (40) NOT NULL,
    birthday DATE NOT NULL,
    start_date DATE NOT NULL,
    salary BIGINT NOT NULL,
    department vh_department NOT NULL,
    manager_id INT,
    description TEXT,
    role vh_role NOT NULL
);

ALTER TABLE vh_worker
    ADD CONSTRAINT fk_worker_manager FOREIGN KEY (manager_id) REFERENCES vh_worker (id);