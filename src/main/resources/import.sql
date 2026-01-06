INSERT INTO pessoa (id, nome, anoNascimento)
VALUES (1,'Ana Silva', 1990),
       (2,'Bruno Souza', 1985),
       (3,'Carla Pereira', 1992),
       (4,'Diego Santos', 1988),
       (5,'Eduardo Lima', 1995),
       (6,'Fernanda Costa', 1983),
       (7,'Gabriel Almeida', 1998),
       (8,'Helena Rocha', 1987),
       (9,'Isabela Martins', 1993),
       (10,'João Oliveira', 1999);


alter sequence pessoa_sequencia restart with 7;