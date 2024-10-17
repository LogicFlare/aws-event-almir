CREATE TABLE event (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    img_url VARCHAR(255),
    event_url VARCHAR(255),
    date TIMESTAMP
);

CREATE TABLE coupom (
    id UUID PRIMARY KEY,
    code VARCHAR(255) NOT NULL,
    discount INTEGER,
    valid TIMESTAMP,
    event_id UUID,
    CONSTRAINT fk_event
        FOREIGN KEY(event_id)
        REFERENCES event(id)
);

CREATE TABLE address (
    id UUID PRIMARY KEY,
    city VARCHAR(255) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    event_id UUID,
    CONSTRAINT fk_event
        FOREIGN KEY(event_id)
        REFERENCES event(id)
);