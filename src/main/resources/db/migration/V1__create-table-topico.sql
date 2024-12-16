CREATE TABLE Topicos (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         titulo VARCHAR(200) NOT NULL,
                         mensaje TEXT NOT NULL,
                         fecha_creacion TIMESTAMP NOT NULL,
                         status VARCHAR(50) NOT NULL,
                         autor VARCHAR(200) NOT NULL,
                         curso VARCHAR(200) NOT NULL
);
