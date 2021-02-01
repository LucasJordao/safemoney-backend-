# :star2: Safe Money - Aplicação Backend Monolítica

Nessa aplicação foi utilizado Spring Boot como stack principal

### Para isso fiz um diagrama UML para servir de auxílio.

- [x] Diagrama UML com os devidos relacionamentos
- [x] Diagrama com as devidas Enumerações
- [x] Diagrama com exemplos de instâncias 

#### Diagrama UML:
![diagrama](https://uploaddeimagens.com.br/images/003/060/359/full/diagrama.png?1612135123)

#### Diagrama Enumerações:
![enumeracoes](https://uploaddeimagens.com.br/images/003/060/421/original/enum.png?1612140557)

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