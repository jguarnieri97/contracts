-- CREACIÓN DE USUARIOS --

-- PROVEEDORES --

-- EMPRESAS DE ELECTRICIDAD (company_type = 0)
INSERT INTO USERS.SUPPLIER_COMPANY
(email, user_name, phone, user_address, cuit, is_active, lat, ln, is_verified, company_description, avg_score,
 avg_price, comments_count, company_type)
VALUES ('info@electroandes.com', 'Electro Andes', '1134567890', 'Av. Corrientes 1234, CABA', '30-11223344-5', 1,
        -34.6037, -58.3816, 1, 'Servicios de instalación eléctrica industrial y domiciliaria', 4.6, 1500.00, 28, 0),
       ('contacto@voltmax.com', 'VoltMax', '1167890123', 'Av. Brig. Gral. Juan Manuel de Rosas 3910, San Justo',
        '30-22334455-6', 1, -34.6839, -58.5634, 1, 'Mantenimiento eléctrico y sistemas de media tensión', 4.4, 1700.00,
        22, 0),
       ('servicios@luzsur.com', 'Luz Sur S.A.', '1122334455', 'Mendoza 2150, Capital Federal', '30-33445566-7', 1,
        -34.5547, -58.4564, 1, 'Montajes eléctricos y luminarias LED', 4.5, 1600.00, 19, 0),
       ('electricidad.norte@example.com', 'ElectroNorte SA', '+541145678901', 'Av. Corrientes 1234, CABA',
        '30-12345678-9', 1, -34.6037, -58.3816, 1,
        'Servicios de instalación y mantenimiento eléctrico residencial e industrial.', 4.8, 50.00, 120, 0),
       ('energia.azul@example.com', 'Energía Azul SRL', '+541123456789', 'Av. Rivadavia 13500, San Justo',
        '20-98765432-1', 1, -34.6784, -58.5627, 1,
        'Proveedor de soluciones energéticas y reparaciones eléctricas urgentes.', 4.5, 55.50, 95, 0),
       ('luz.segura@example.com', 'Luz Segura', '+543415678901', 'Av. Corrientes 5200, Capital Federal',
        '27-11223344-5', 1, -34.6021, -58.4303, 1,
        'Instalaciones eléctricas seguras y certificadas para hogares y comercios.', 4.7, 48.75, 110, 0);

-- EMPRESAS DE CONTRATISTAS (company_type = 1)
INSERT INTO USERS.SUPPLIER_COMPANY
(email, user_name, phone, user_address, cuit, is_active, lat, ln, is_verified, company_description, avg_score,
 avg_price, comments_count, company_type)
VALUES ('proyectos@construnorte.com', 'Construnorte', '1144778899', 'Ruta 8 Km 12, San Martín, Buenos Aires',
        '30-44556677-8', 1, -34.5585, -58.7283, 1, 'Obras civiles y contrataciones públicas', 4.2, 2500.00, 35, 1),
       ('gestion@obrasdelitoral.com', 'Obras del Litoral', '1155996633', 'Av. Illia 2900, San Justo', '30-55667788-9',
        1, -34.6792, -58.5715, 1, 'Empresa contratista para infraestructura urbana', 4.3, 2200.00, 31, 1),
       ('admin@infraandes.com', 'Infra Andes', '1133001122', 'Av. Cabildo 3200, Capital Federal', '30-66778899-0', 1,
        -34.5622, -58.4566, 1, 'Contratistas generales para obra pública', 4.1, 2300.00, 29, 1),
       ('construcciones.sur@example.com', 'Constructora del Sur', '+542234567890',
        'Av. Juan Manuel de Rosas 8499, San Justo', '30-55667788-9', 1, -34.6940, -58.5960, 1,
        'Servicios integrales de construcción y reformas edilicias.', 4.6, 120.00, 80, 1),
       ('reformas.express@example.com', 'Reformas Express', '+541178901234', 'Santa Fe 2000, CABA', '23-99887766-5', 1,
        -34.5997, -58.4190, 1, 'Contratistas especializados en remodelaciones rápidas y eficientes.', 4.4, 90.50, 65,
        1),
       ('proyectos.integrales@example.com', 'Proyectos Integrales', '+543511234567',
        'Av. Santa Fe 3100, Capital Federal', '30-44556677-8', 1, -34.5889, -58.4088, 1,
        'Gestión y ejecución de proyectos de obra civil y arquitectura.', 4.7, 130.25, 70, 1);

-- EMPRESAS DE LIMPIEZA (company_type = 2)
INSERT INTO USERS.SUPPLIER_COMPANY
(email, user_name, phone, user_address, cuit, is_active, lat, ln, is_verified, company_description, avg_score,
 avg_price, comments_count, company_type)
VALUES ('servicios@cleanpro.com', 'CleanPro', '1198765432', 'Av. Santa Fe 3000, CABA', '30-77889900-1', 1, -34.5865,
        -58.4103, 1, 'Limpieza de oficinas y espacios industriales', 4.7, 900.00, 40, 2),
       ('ventas@limpiezanorte.com', 'Limpieza Norte', '1177889900', 'Av. Presidente Perón 3300, San Justo',
        '30-88990011-2', 1, -34.6712, -58.5521, 1, 'Servicios de limpieza profesional y sanitización', 4.5, 850.00, 36,
        2),
       ('info@higienizar.com', 'Higienizar S.A.', '1122556677', 'Av. Belgrano 1100, Capital Federal', '30-99001122-3',
        1, -34.6126, -58.3843, 1, 'Empresa de limpieza integral y mantenimiento', 4.6, 880.00, 33, 2),
       ('limpieza.total@example.com', 'Limpieza Total SRL', '+541133445566', 'Juncal 800, CABA', '30-11223344-0', 1,
        -34.5950, -58.3870, 1, 'Servicios de limpieza profesional para oficinas, hogares y consorcios.', 4.9, 35.00,
        150, 2),
       ('brillo.extremo@example.com', 'Brillo Extremo', '+542617890123',
        'Av. Brig. Gral. Juan Manuel de Rosas 3910, San Justo', '20-66778899-0', 1, -34.6839, -58.5634, 1,
        'Limpieza profunda y desinfección para todo tipo de ambientes.', 4.6, 40.20, 100, 2),
       ('higienizar.ya@example.com', 'Higienizar Ya', '+543871234567', 'Av. Corrientes 5200, Capital Federal',
        '27-10203040-1', 1, -34.6021, -58.4303, 1,
        'Soluciones de higiene y mantenimiento para empresas y particulares.', 4.7, 38.50, 130, 2);


-- TRABAJADORES --

-- Trabajadores para la empresa 1 - Electro Andes (company_id = 1)
INSERT INTO USERS.WORKER (email, user_name, phone, user_address, cuit, is_active, company_id)
VALUES ('jorge.lopez@electroandes.com', 'Jorge López', '1140001111', 'Av. Corrientes 1234, CABA', '20-30123456-7', 1,
        1),
       ('maria.perez@electroandes.com', 'María Pérez', '1140002222', 'Av. Corrientes 1234, CABA', '27-30987654-3', 1,
        1);

-- Trabajadores para la empresa 2 - VoltMax (company_id = 2)
INSERT INTO USERS.WORKER (email, user_name, phone, user_address, cuit, is_active, company_id)
VALUES ('carlos.garcia@voltmax.com', 'Carlos García', '1160012233', 'Av. Brig. Gral. Juan Manuel de Rosas 3910',
        '20-31234567-8', 1, 2),
       ('luciana.mendez@voltmax.com', 'Luciana Méndez', '1160013344', 'Av. Brig. Gral. Juan Manuel de Rosas 3910',
        '27-32233445-9', 1, 2);

-- Trabajadores para la empresa 3 - Luz Sur S.A. (company_id = 3)
INSERT INTO USERS.WORKER (email, user_name, phone, user_address, cuit, is_active, company_id)
VALUES ('alejandro.ramos@luzsur.com', 'Alejandro Ramos', '1120034567', 'Mendoza 2150, Capital Federal', '20-33445566-7',
        1, 3),
       ('florencia.diaz@luzsur.com', 'Florencia Díaz', '1120045678', 'Mendoza 2150, Capital Federal', '27-34455667-8',
        1, 3);

-- Trabajadores para la empresa 4 - Construnorte (company_id = 7)
INSERT INTO USERS.WORKER (email, user_name, phone, user_address, cuit, is_active, company_id)
VALUES ('nicolas.ferreyra@construnorte.com', 'Nicolás Ferreyra', '1177001122', 'Ruta 8 Km 12, San Martín, Buenos Aires',
        '20-35566778-9', 1, 7),
       ('ana.suarez@construnorte.com', 'Ana Suárez', '1177002233', 'Ruta 8 Km 12, San Martín, Buenos Aires',
        '27-36677889-0', 1, 7);

-- Trabajadores para la empresa 5 - Obras del Litoral (company_id = 8)
INSERT INTO USERS.WORKER (email, user_name, phone, user_address, cuit, is_active, company_id)
VALUES ('gabriel.martinez@obrasdelitoral.com', 'Gabriel Martínez', '1133664477', 'Av. Illia 2900, San Justo',
        '20-37788990-1', 1, 8),
       ('carla.bustos@obrasdelitoral.com', 'Carla Bustos', '1133665588', 'Av. Illia 2900, San Justo', '27-38899001-2',
        1, 8);

-- Trabajadores para la empresa 6 - Infra Andes (company_id = 9)
INSERT INTO USERS.WORKER (email, user_name, phone, user_address, cuit, is_active, company_id)
VALUES ('daniel.sosa@infraandes.com', 'Daniel Sosa', '1122556677', 'Av. Cabildo 3200, Capital Federal', '20-39900112-3',
        1, 9),
       ('paula.ayala@infraandes.com', 'Paula Ayala', '1122557788', 'Av. Cabildo 3200, Capital Federal', '27-40011223-4',
        1, 9);

-- Trabajadores para la empresa 7 - CleanPro (company_id = 7)
INSERT INTO USERS.WORKER (email, user_name, phone, user_address, cuit, is_active, company_id)
VALUES ('sofia.alvarez@cleanpro.com', 'Sofía Álvarez', '1199123456', 'Av. Santa Fe 3000, CABA', '27-41122334-5', 1, 13),
       ('roberto.gomez@cleanpro.com', 'Roberto Gómez', '1199134567', 'Av. Santa Fe 3000, CABA', '20-42233445-6', 1, 13);

-- Trabajadores para la empresa 8 - Limpieza Norte (company_id = 8)
INSERT INTO USERS.WORKER (email, user_name, phone, user_address, cuit, is_active, company_id)
VALUES ('mariana.torres@limpiezanorte.com', 'Mariana Torres', '1177880011', 'Av. Presidente Perón 3300, San Justo',
        '27-43344556-7', 1, 14),
       ('luis.fernandez@limpiezanorte.com', 'Luis Fernández', '1177881122', 'Av. Presidente Perón 3300, San Justo',
        '20-44455667-8', 1, 14);

-- Trabajadores para la empresa 9 - Higienizar S.A. (company_id = 9)
INSERT INTO USERS.WORKER (email, user_name, phone, user_address, cuit, is_active, company_id)
VALUES ('andrea.lopez@higienizar.com', 'Andrea López', '1122113344', 'Av. Belgrano 1100, Capital Federal',
        '27-45566778-9', 1, 15),
       ('marcelo.ruiz@higienizar.com', 'Marcelo Ruiz', '1122114455', 'Av. Belgrano 1100, Capital Federal',
        '20-46677889-0', 1, 15);

-- SOLICITANTES --

-- EMPRESA IT 1
INSERT INTO USERS.APPLICANT_COMPANY
(email, user_name, phone, user_address, cuit, is_active, lat, ln, is_verified, company_description)
VALUES ('contacto@devcoretech.com', 'DevCore Tech', '1166112233', 'Av. Córdoba 5500, CABA', '30-60012345-7', 1,
        -34.5915, -58.4306, 1, 'Desarrollo de software a medida, APIs y soluciones backend para fintechs');

-- EMPRESA IT 2
INSERT INTO USERS.APPLICANT_COMPANY
(email, user_name, phone, user_address, cuit, is_active, lat, ln, is_verified, company_description)
VALUES ('info@cloudnetworks.com', 'CloudNetworks S.A.', '1133447788', 'Mendoza 2150, Capital Federal', '30-60123456-8',
        1, -32.9300, -60.7000, 1, 'Provisión de infraestructura cloud y servicios de virtualización de servidores');

-- EMPRESA IT 3
INSERT INTO USERS.APPLICANT_COMPANY
(email, user_name, phone, user_address, cuit, is_active, lat, ln, is_verified, company_description)
VALUES ('soporte@cybershield.com', 'CyberShield', '1177889900', 'Av. General Paz 13400, San Martín', '30-60234567-9', 1,
        -34.5800, -58.5200, 1, 'Consultoría en ciberseguridad y auditoría de sistemas críticos');

-- EMPRESA IT 4
INSERT INTO USERS.APPLICANT_COMPANY
(email, user_name, phone, user_address, cuit, is_active, lat, ln, is_verified, company_description)
VALUES ('contacto@datanova.ai', 'Datanova AI', '1122334455', 'Calle Figueroa Alcorta 4500, CABA', '30-60345678-0', 1,
        -34.5685, -58.4182, 1, 'Inteligencia artificial aplicada a procesos logísticos y predicción de demanda');

-- EMPRESA IT 5
INSERT INTO USERS.APPLICANT_COMPANY
(email, user_name, phone, user_address, cuit, is_active, lat, ln, is_verified, company_description)
VALUES ('rrhh@byteflow.io', 'ByteFlow', '1144556677', 'Av. Illia 2900, San Justo', '30-60456789-1', 1, -31.6333,
        -60.7000, 1, 'Desarrollo de plataformas móviles, front-end y soluciones UX/UI para startups');


-- CONTRATOS --

INSERT INTO CONTRACTS.WORK_CONTRACT
(code_number, price, date_from, date_to, contract_state, contract_detail, supplier_id, applicant_id)
VALUES (NEXT VALUE FOR work_contract_sequence, 475985.11, '2025-07-26', '2025-08-17', 0,
             'Reparación de Tablero Eléctrico.', 1, 1),
       (NEXT VALUE FOR work_contract_sequence, 262903.91, '2025-05-06', '2025-05-13', 2,
             'Instalación de Cableado Estructural.', 1, 2),
       (NEXT VALUE FOR work_contract_sequence, 176486.91, '2025-05-10', '2025-05-19', 2,
             'Mantenimiento Preventivo de Instalaciones', 1, 4),
       (NEXT VALUE FOR work_contract_sequence, 100296.95, '2025-04-05', '2025-04-18', 2,
             'Instalación de Iluminación LED', 1, 5),
       (NEXT VALUE FOR work_contract_sequence, 89205.13, '2025-05-29', '2025-06-29', 1,
             'Reparación de Cortocircuitos y Sobrecargas', 1, 1),
       (NEXT VALUE FOR work_contract_sequence, 139757.13, '2025-06-03', '2025-06-15', 1,
             'Instalación de Iluminación LED', 1, 3),
       (NEXT VALUE FOR work_contract_sequence, 499205.69, '2025-05-19', '2025-05-19', 2,
             'Instalación de Tomas de Corriente y Enchufes', 2, 1),
       (NEXT VALUE FOR work_contract_sequence, 481502.69, '2025-03-23', '2025-04-18', 2,
             'Remodelación Integral de Interiores.', 7, 1),
       (NEXT VALUE FOR work_contract_sequence, 273046.90, '2025-07-08', '2025-07-08', 0,
             'Mantenimiento Preventivo de Instalaciones.', 1, 2),
       (NEXT VALUE FOR work_contract_sequence, 277202.61, '2025-07-13', '2025-07-16', 0,
             'Reparación de Tablero Eléctrico.', 3, 3),
       (NEXT VALUE FOR work_contract_sequence, 104906.39, '2025-07-07', '2025-07-13', 0,
             'Instalación de Cableado Estructural', 1, 5),
       (NEXT VALUE FOR work_contract_sequence, 168967.53, '2025-08-11', '2025-08-13', 0,
             'Instalación de Iluminación LED', 3, 4),
       (NEXT VALUE FOR work_contract_sequence, 484250.27, '2025-08-23', '2025-08-24', 0,
             'Remodelación Integral de Interiores.', 9, 1),
       (NEXT VALUE FOR work_contract_sequence, 203218.33, '2025-08-19', '2025-09-07', 0,
             'Mantenimiento de Limpieza de Oficinas', 13, 3),
       (NEXT VALUE FOR work_contract_sequence, 251087.58, '2025-08-18', '2025-08-26', 0,
             'Reparación de Cortocircuitos y Sobrecargas.', 1, 4),
       (NEXT VALUE FOR work_contract_sequence, 204014.53, '2025-07-13', '2025-07-15', 0,
             'Conexión y Mantenimiento de Generadores Eléctricos.', 1, 5),
       (NEXT VALUE FOR work_contract_sequence, 103919.83, '2025-07-14', '2025-07-14', 0,
             'Contrato entre proveedor #5 y aplicante #6 en estado pending.', 8, 3),
       (NEXT VALUE FOR work_contract_sequence, 202620.95, '2025-08-20', '2025-08-30', 0,
             'Contrato entre proveedor #5 y aplicante #9 en estado finalized.', 5, 2),
       (NEXT VALUE FOR work_contract_sequence, 361050.96, '2025-07-28', '2025-08-07', 0,
             'Diseño y Construcción de Espacios Exteriores.', 7, 1),
       (NEXT VALUE FOR work_contract_sequence, 349884.84, '2025-08-28', '2025-09-23', 0,
             'Reparación de Cortocircuitos y Sobrecargas.', 1, 5),
       (NEXT VALUE FOR work_contract_sequence, 470670.47, '2025-08-25', '2025-09-09', 0,
             'Instalación de Sistemas de Puesta a Tierra.', 1, 3),
       (NEXT VALUE FOR work_contract_sequence, 316610.06, '2025-08-23', '2025-08-23', 0,
             'Contrato entre proveedor #7 y aplicante #2 en estado initiated.', 7, 2),
       (NEXT VALUE FOR work_contract_sequence, 109253.45, '2025-07-27', '2025-08-01', 0,
             'Instalación de Tomas de Corriente y Enchufes.', 1, 5),
       (NEXT VALUE FOR work_contract_sequence, 472299.97, '2025-07-26', '2025-07-27', 0,
             'Instalación de Drywall y Cielorrasos.', 8, 1),
       (NEXT VALUE FOR work_contract_sequence, 445148.63, '2025-08-19', '2025-08-19', 0,
             'Mantenimiento de Limpieza de Oficinas', 13, 1),
       (NEXT VALUE FOR work_contract_sequence, 240420.13, '2025-08-01', '2025-08-08', 0,
             'Instalación de Tomas de Corriente y Enchufes', 1, 3),
       (NEXT VALUE FOR work_contract_sequence, 391518.26, '2025-08-14', '2025-09-03', 0,
             'Impermeabilización de Terrazas y Cubiertas.', 9, 1);
