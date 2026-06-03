# 🚀 StellarGear API - Global Solution

API REST desenvolvida em Java com Spring Boot para o projeto StellarGear. O sistema é responsável por gerenciar passageiros, equipes médicas e processar dados de telemetria contínua (temperatura, umidade e batimentos cardíacos) enviados por sensores IoT (ESP32) acoplados aos trajes.

---

## 👨‍💻 Integrantes (Turma: 2TDSPF )

- **Enzo Vaz** — RM: 561702
- **Lucas Ryuji Fukuda** — RM: 562152
- **Pietro Donella Salomão** — RM: 561722

---

## 🔗 Links Importantes

- **Deploy da API (Produção):** https://stellargear-api.onrender.com
- **Vídeo Pitch (3 min):** [Link do YouTube aqui]
- **Vídeo de Demonstração (10 min):** https://youtu.be/f69SwZGjTic
- **Documentação Swagger:** Para acessar o Swagger da API em produção, adicione `/swagger-ui/index.html` ao final da URL de deploy.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Descrição |
|------------|------------|
| **Linguagem** | Java 21 |
| **Framework** | Spring Boot 3 |
| **Banco de Dados** | Oracle Database (PL/SQL Avançado) |
| **ORM** | Spring Data JPA / Hibernate |
| **Segurança** | Spring Security + JWT (JSON Web Token) e BCrypt |
| **Documentação** | SpringDoc OpenAPI (Swagger) |
| **Validações** | Spring Boot Starter Validation |
| **Ferramentas** | Maven, Lombok, Postman, Eclipse/JetBrains Rider |

---

## ⚙️ Instruções de Execução (Local)

### 1. Clone este repositório

```bash
git clone https://github.com/EnzoVazz/stellargear-api.git
cd stellargear-api
```

### 2. Certifique-se de ter instalado

- Java 21
- Maven 3.9+
- Oracle Database

### 3. Configure as credenciais do banco de dados

No arquivo `application.properties`, configure:

```properties
spring.datasource.url=jdbc:oracle:thin:@//endereco:porta/servico
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 4. Execute a aplicação

Inicie a classe:

```java
StellargearApplication.java
```

ou execute pelo Maven:

```bash
mvn spring-boot:run
```

### 5. Acesse a API

A aplicação estará disponível em:

```text
http://localhost:8080
```

---

## 📚 Documentação da API

Após iniciar a aplicação, acesse o Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## 🔒 Testando a Autenticação

A API utiliza autenticação baseada em **JWT (JSON Web Token)**.

### Passo 1 — Realizar Login

Utilize o endpoint:

```http
POST /login
```


Para facilitar a avaliação, utilize as seguintes credenciais padrão de administrador:

- **Email:** `admin@stellargear.com`
- **Senha:** `123456`

*(Nota: Este usuário já está previamente cadastrado na base de dados para testes).*

### Passo 2 — Copiar o Token

A resposta retornará um token JWT semelhante a:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### Passo 3 — Autorizar no Swagger

1. Clique no botão **Authorize** no topo da página.
2. Cole o token recebido.
3. Confirme a autenticação.

Agora você poderá acessar todos os endpoints protegidos.

---

## 📌 Funcionalidades

- ✅ Cadastro e gerenciamento de passageiros
- ✅ Cadastro e gerenciamento de equipes médicas
- ✅ Monitoramento de telemetria em tempo real
- ✅ Registro de temperatura corporal
- ✅ Registro de umidade dos trajes
- ✅ Registro de frequência cardíaca
- ✅ Autenticação e autorização via JWT
- ✅ Documentação interativa com Swagger
- ✅ Integração com Oracle Database

---

## 📄 Licença

Projeto desenvolvido para fins acadêmicos como parte da **Global Solution FIAP**.
