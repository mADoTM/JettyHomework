CREATE TABLE company
(
    company_id SERIAL,
    name VARCHAR UNIQUE NOT NULL,
    CONSTRAINT company_pk PRIMARY KEY (company_id)
);

CREATE TABLE product
(
    product_id SERIAL,
    name VARCHAR NOT NULL,
    company_id INT NOT NULL REFERENCES company (company_id) ON UPDATE CASCADE ON DELETE CASCADE,
    count INT NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (product_id)
);