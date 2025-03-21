# API do Chibi Loja 🎴

## Tecnologias usadas 👩‍💻
1. Java
2. Spring Boot
3. Maven

## Endpoints 🏪
Nossa API fornece endpoints de pagamento, gerenciamento e filtragem de clientes e produtos além da criação de PDF para a geração de extratos.

### Autenticação `/auth` 🔑

#### POST `/login`
Login de um cliente

### Cliente `/cliente` 🙋

#### GET `/list`
Pega todos os clientes

#### GET `?id={id}`
Pega um cliente por id

#### GET `/get-user?email={email}`
Pega um cliente por email

#### POST
Cria um novo cliente

### PDF `/pdf` 📄

#### GET `/extrato`
Retorna um extrato em bytes com base no nome, cpf e valor total da compra.

### Produto `/produto`

#### GET `/list`
Pega todos os produtos

#### GET `?id={id}`
Pega um produto por id

#### GET `/search`
Retorna uma lista filtrada de produtos

#### POST `/create`
Cria um novo produto

#### PUT `/id={id}`
Atualiza um produto

#### DELETE `/id={id}`
Remove um produto

### Pedido `/pedido` 🛒
#### GET `/list`
Pega todos os pedidos

#### GET `?id={id}`
Pega um pedido por id

#### GET `/search`
Retorna uma lista filtrada de pedido

#### POST `/create`
Cria um novo pedido
