CREATE TABLE quantity_measurement (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    operation VARCHAR(50),
    value DOUBLE,
    unit VARCHAR(50),
    measurement_type VARCHAR(50)
);