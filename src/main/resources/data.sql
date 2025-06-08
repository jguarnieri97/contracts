INSERT INTO USERS.APPLICANT_COMPANY (user_name) VALUES ('Solicitante 1');
INSERT INTO USERS.APPLICANT_COMPANY (user_name) VALUES ('Solicitante 2');

INSERT INTO USERS.SUPPLIER_COMPANY (user_name) VALUES ('Proveedor 1');
INSERT INTO USERS.SUPPLIER_COMPANY (user_name) VALUES ('Proveedor 2');

-- Trabajadores para la empresa 1 - Electro Andes (company_id = 1)
INSERT INTO USERS.WORKER (email, user_name, phone, user_address, cuit, is_active, company_id)
VALUES
('jorge.lopez@electroandes.com', 'Jorge López', '1140001111', 'CABA', '20-30123456-7', 1, 1),
('maria.perez@electroandes.com', 'María Pérez', '1140002222', 'CABA', '27-30987654-3', 1, 1);

-- Trabajadores para la empresa 2 - VoltMax (company_id = 2)
INSERT INTO USERS.WORKER (email, user_name, phone, user_address, cuit, is_active, company_id)
VALUES
('carlos.garcia@voltmax.com', 'Carlos García', '1160012233', 'Córdoba', '20-31234567-8', 1, 2),
('luciana.mendez@voltmax.com', 'Luciana Méndez', '1160013344', 'Córdoba', '27-32233445-9', 1, 2);
