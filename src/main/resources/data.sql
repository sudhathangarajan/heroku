INSERT INTO users (username, password, role) VALUES
('tech001', '{bcrypt}$2a$10$DS5udqIGvWn0kfgUDO9OyuvVBs8ZJHZvreXnLrcr.hi39gPz6YK/G', 'TECHNICIAN'),
('tech002', '{bcrypt}$2a$10$JJp0x48NFzv9GrZjbOFnGOv/pDHLMhm8dUWZzX1x08WkUGrTk9cr6', 'TECHNICIAN'),
('mgr001', '{bcrypt}$2a$10$cwYrcHbgg3D0eNAF5uvS6eiiBmSWhLGxKP7NkfUuVXVSsJuBN9r0u', 'MANAGER'),
('adm001', '{bcrypt}$2a$10$wzbQcB.Se1h9UJir57/D0eMlKbrawyNjbALJGMQ40Epqh.FAoFVmS', 'ADMIN');

INSERT INTO parts (name) VALUES
('25 gauge electrical cable'),
('LCD screen'),
('power button'),
('standard plug'),
('gas valve'),
('water valve'),
('gas tube'),
('1/2 inch water pipe');

INSERT INTO spare_parts (part_id, quantity) VALUES
((SELECT id FROM parts WHERE name = '25 gauge electrical cable'), 10),
((SELECT id FROM parts WHERE name = 'LCD screen'), 10),
((SELECT id FROM parts WHERE name = 'power button'), 9),
((SELECT id FROM parts WHERE name = 'standard plug'), 8),
((SELECT id FROM parts WHERE name = 'gas valve'), 10),
((SELECT id FROM parts WHERE name = 'water valve'), 9),
((SELECT id FROM parts WHERE name = 'gas tube'), 10),
((SELECT id FROM parts WHERE name = '1/2 inch water pipe'), 10);

INSERT INTO sites (postal_code, description) VALUES ('206294', 'Hoa Nam Builder');
INSERT INTO site_parts (site_id, part_id, integrity) VALUES
((SELECT IDENTITY()), (SELECT id FROM parts WHERE name = 'LCD screen'), 'FUNCTIONING'),
((SELECT IDENTITY()), (SELECT id FROM parts WHERE name = 'power button'), 'NEED_REPAIR'),
((SELECT IDENTITY()), (SELECT id FROM parts WHERE name = 'water valve'), 'FOR_REPLACEMENT'),
((SELECT IDENTITY()), (SELECT id FROM parts WHERE name = 'gas valve'), 'FOR_REPLACEMENT');

INSERT INTO sites (postal_code, description) VALUES('260003', '3 Queens Road');
INSERT INTO site_parts (site_id, part_id, integrity) VALUES
((SELECT IDENTITY()), (SELECT id FROM parts WHERE name = 'gas tube'), 'FUNCTIONING'),
((SELECT IDENTITY()), (SELECT id FROM parts WHERE name = 'standard plug'), 'FOR_REPLACEMENT');

INSERT INTO sites (postal_code, description) VALUES('018956', '10 Bayfront Ave');
INSERT INTO site_parts (site_id, part_id, integrity) VALUES
((SELECT IDENTITY()), (SELECT id FROM parts WHERE name = 'gas tube'), 'FUNCTIONING'),
((SELECT IDENTITY()), (SELECT id FROM parts WHERE name = 'standard plug'), 'FOR_REPLACEMENT');

INSERT INTO inspections (site_id, scheduled_at, status, technician_id) VALUES
((SELECT id FROM sites WHERE postal_code = '206294'), CURRENT_TIMESTAMP, 'PENDING', (SELECT id FROM users WHERE username = 'tech001'));
INSERT INTO reservations (part_id, inspection_id, quantity) VALUES
((SELECT id FROM parts WHERE name = 'gas valve'), (SELECT IDENTITY()), 1);

INSERT INTO inspections (site_id, scheduled_at, status, technician_id) VALUES
((SELECT id FROM sites WHERE postal_code = '206294'), CURRENT_TIMESTAMP, 'COMPLETED', (SELECT id FROM users WHERE username = 'tech001'));
INSERT INTO reservations (part_id, inspection_id, quantity) VALUES
((SELECT id FROM parts WHERE name = 'LCD screen'), (SELECT IDENTITY()), 1);

INSERT INTO inspections (site_id, scheduled_at, status, technician_id) VALUES
((SELECT id FROM sites WHERE postal_code = '206294'), DATEADD('DAY', 1, CURRENT_TIMESTAMP), 'PENDING', (SELECT id FROM users WHERE username = 'tech001'));
INSERT INTO reservations (part_id, inspection_id, quantity) VALUES
((SELECT id FROM parts WHERE name = 'water valve'), (SELECT IDENTITY()), 1);

INSERT INTO inspections (site_id, scheduled_at, status, technician_id) VALUES
((SELECT id FROM sites WHERE postal_code = '260003'), DATEADD('DAY', 10, CURRENT_TIMESTAMP), 'PENDING', (SELECT id FROM users WHERE username = 'tech002'));
INSERT INTO reservations (part_id, inspection_id, quantity) VALUES
((SELECT id FROM parts WHERE name = 'standard plug'), (SELECT IDENTITY()), 2);

INSERT INTO inspections (site_id, scheduled_at, status, technician_id) VALUES
((SELECT id FROM sites WHERE postal_code = '018956'), CURRENT_TIMESTAMP, 'REVISIT', (SELECT id FROM users WHERE username = 'tech002'));
INSERT INTO reservations (part_id, inspection_id, quantity) VALUES
((SELECT id FROM parts WHERE name = 'LCD screen'), (SELECT IDENTITY()), 1);