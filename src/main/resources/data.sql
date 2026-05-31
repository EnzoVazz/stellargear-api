-- Insere o usuário admin (a senha é 123456 encriptada em BCrypt)
INSERT INTO usuario_sistema (id_usuario, email, senha, role) 
VALUES (seq_usuario.NEXTVAL, 'admin@stellargear.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.', 'ADMIN');

INSERT INTO admin_sistema (id_usuario, setor_responsavel) 
VALUES (seq_usuario.CURRVAL, 'TI e Seguranca');