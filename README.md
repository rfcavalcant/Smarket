# Super market case

Projeto desenvolvido como parte de um case técnico, composto por dois microsserviços em Java Spring Boot e um frontend em Angular. A aplicação permite o gerenciamento de produtos e a visualização de estoque em tempo real.

---

## 🧩 Estrutura do Projeto

```

Smarket/
├── Market/
│   ├── product-service/      # Microsserviço de gerenciamento de produtos
│   └── stock-service/        # Microsserviço de consulta de estoque
└── smarket-frontend/         # Aplicação frontend em Angular

````

---

## 📦 Microsserviços

### 🛍️ product-service

- CRUD de produtos
- Banco de dados: MySQL
- Documentação Swagger: `/swagger-ui.html`
- Porta: `8080`

### 📦 stock-service

- Consulta de estoque via integração com o `product-service`
- Porta: `8081`

---

## 🖥️ Frontend - Angular

- Exibe a lista de produtos e informações de estoque
- Comunica-se com o `stock-service`
- Porta: `4200`

---

## ▶️ Como Executar

### Pré-requisitos

- Docker + Docker Compose
- Node.js + Angular CLI (para desenvolvimento frontend)
- Java 17 + Maven (para desenvolvimento backend)

### 1. Subir com Docker Compose

```bash
cd Market
docker-compose up --build
````

* `product-service`: [http://localhost:8080](http://localhost:8080)
* `stock-service`: [http://localhost:8081](http://localhost:8081)
* `MySQL`: Porta 3306

### 2. Rodar o Frontend

```bash
cd smarket-frontend
npm install
ng serve
```

Acesse: [http://localhost:4200](http://localhost:4200)

---

## ✅ Funcionalidades

* Cadastro, edição, listagem e exclusão de produtos
* Visualização de quantidade em estoque
* Indicação de produtos com estoque baixo

---

## 🧪 Testes

* Testes unitários com JUnit e Mockito no `product-service`
* Para executar:

```bash
cd Market/product-service
mvn test
```

---

## 📄 Documentação da API

* Swagger disponível em: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 📌 Observações

* Toda comunicação entre microsserviços é feita via REST
* Projeto modular e preparado para expansão
* Testado com dados simulados

---

## 👨‍💻 Autor

Rafael Vieira Cavalcante
[rfcavalcant](https://github.com/rfcavalcant)

---
