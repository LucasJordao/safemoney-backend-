# :star2: Safe Money - Aplicação Backend Monolítica 

Nessa aplicação foi utilizado Spring Boot como stack principal

O projeto tem como foco criar uma aplicação mobile que simula carteiras de dinheiro de maneira virtual, para isso utilizarei **Spring Boot** e **Ionic** como stacks principais.

<p align="center">
  <img src="https://uploaddeimagens.com.br/images/003/099/764/original/safemoney.png?1614217102" width="200">
</p>

<p align="center">
  <a href="#-criando">Criando o Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-instalar">Instalação</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-imagens">Imagens</a>
</p>

## 🔨 Criando
### Logo de início fiz um diagrama UML para servir de auxílio.

- [x] Diagrama UML com os devidos relacionamentos
- [x] Diagrama com as devidas Enumerações
- [x] Diagrama com exemplos de instâncias 

#### Diagrama UML:
![diagrama](https://uploaddeimagens.com.br/images/003/099/459/full/diagrama.png?1614200968)

#### Diagrama Enumerações:
![enumeracoes](https://uploaddeimagens.com.br/images/003/099/458/full/enums.png?1614200896)

#### Diagrama de Instâncias:
![instancias](https://uploaddeimagens.com.br/images/003/055/193/full/diagrama_uso.png?1611796217)

Depois de ter os diagramas em mãos, comecei a desenvolver a aplicação.

### Iniciando a criação do projeto:

#### Dividi a aplicação em:

* Domains (Entidades)
* Repositories
* Resources
* Services

#### A entidade foi feita assim **(Utilizei o lombok para auxiliar no clean code)**

![entidade](https://uploaddeimagens.com.br/images/003/059/058/full/classe-entidade.png?1611970517)

Depois de ter criado o **service**, o **repository** e o **resource** do **Usuario** criei um endpoint GET:
![endpoint](https://uploaddeimagens.com.br/images/003/059/081/full/endpoint-get.png?1611972742)

