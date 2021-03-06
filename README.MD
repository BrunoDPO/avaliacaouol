# Avaliação UOL
*Autor: Bruno Di Prinzio de Oliveira*

## Primeiros Passos
Para que seja possível executar o código deste projeto, será necessário:
1. [Java](https://www.java.com)
2. [Maven](http://maven.apache.org)
3. Ambiente de Desenvolvimento
   1. [Eclipse IDE](https://www.eclipse.org)
   2. [Visual Studio Code](https://code.visualstudio.com)
4. [Lombok](https://projectlombok.org)
5. [MySQL Server](https://www.mysql.com) (na máquina local, porta 3306)
6. Base de dados configurada com o script disponibilizado (CRT_Cliente.sql)
7. Certificado para utilização do IpVigilante instalado (certificado.sh)

## Projeto
Este projeto consiste na criação de um micro serviço capaz de gerenciar um cliente (criação, obtenção, alteração e deleção), seguindo os requisitos citados no DOCX. O principal sendo obter as temperaturas mínima e máxima no momento do cadastro, utilizando os serviços [IpVigilante](https://www.ipvigilante.com) e [MetaWeather](https://www.metaweather.com).

### Obtenção das informações externas
Com o IP da requisição em mãos, foi necessário realizar uma chamada ao IpVigilante e obter latitude e longitude do chamador. Em seguida, foram realizadas duas chamadas ao MetaWeather, sendo que a primeira descobre as localizações mais próximas do local indicado que possuem informações de previsão do tempo. Como essa chamada traz os resultados ordenados do mais próximo ao mais distante, bastou obter a primeira posição da lista. Em seguida, com o WOEID (Where on Earth ID) do local, foi realizada uma consulta para obter a previsão do tempo consolidada e com isso pegar também as temperaturas mínima e máxima.

### Validações
Foram realizadas apenas validações básicas. É necessário, por exemplo, realizar uma checagem do retorno das APIs externas a fim de contornar os possíveis problemas, como indisponibilidade. Porém o foco aqui foi apenas verificar a existência ou não do cliente e variar os retornos, de acordo com os códigos HTTP corretos.

### Próximos passos
Como próximos passos, será necessário escolher uma solução de CI/CD, como por exemplo o [Jenkins](https://jenkins.io/) para criar um pipeline de build e deploy para os ambientes.
Além disso, ficou faltando o preenchimento dos testes unitários. Porém, estou ciente de que é imprescindível para validar cada build no processo de CI.
Outro passo que posso citar são os testes integrados, que poderiam ser realizados utilizando, por exemplo, o [Selenium](https://www.seleniumhq.org/).
Por fim, por ser uma API de cadastro, um próximo passo seria a implementação da camada de segurança (autenticação e autorização).

## Endpoints
Os endpoints criados para esse exemplo foram:

* `GET - /api/cliente/{id}` - Obtém um cliente através do seu ID
* `POST - /api/cliente` - Cria um novo cliente
  `BODY - { "nome": "string", "idade": int }`
* `PUT - /api/cliente/{id}` - Altera as informações do cliente
  `BODY - { "nome": "string", "idade": int }`
* `DELETE - /api/cliente/{id}` - Apaga um cliente através de seu ID
