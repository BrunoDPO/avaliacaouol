# Avaliação UOL
*Autor: Bruno Di Prinzio de Oliveira*

## Primeiros Passos
Para que seja possível executar o código deste projeto, será necessário:
1. [Eclipse IDE](https://www.eclipse.org)
2. [Lombok](https://projectlombok.org)
3. [MySQL Server](https://www.mysql.com/) (na máquina local, porta padrão)
4. Base de dados configurada com o script disponibilizado (CRT_Cliente.sql)
5. Certificado para utilização do IpVigilante instalado (certificado.sh)

## Projeto
Este projeto consiste na criação de um micro serviço capaz de gerenciar um cliente (criação, obtenção, alteração e deleção), seguindo os requisitos citados no DOCX. O principal sendo obter as temperaturas mínima e máxima no momento do cadastro, utilizando os serviços [IpVigilante](https://www.ipvigilante.com) e [MetaWeather](https://www.metaweather.com).

### Obtenção das informações externas
Com o IP da requisição em mãos, foi necessário realizar uma chamada ao IpVigilante e obter latitude e longitude do chamador. Em seguida, foram realizadas duas chamadas ao MetaWeather, sendo que a primeira descobre as localizações mais próximas do local indicado que possuem informações de previsão do tempo. Como essa chamada traz os resultados ordenados do mais próximo ao mais distante, bastou obter a primeira posição da lista. Em seguida, com o WOEID (Where on Earth ID) do local, foi realizada uma consulta para obter a previsão do tempo consolidada e com isso pegar também as temperaturas mínima e máxima.

### Validações
Foram realizadas apenas validações básicas. É necessário, por exemplo, realizar uma checagem do retorno das APIs externas a fim de contornar os possíveis problemas, como indisponibilidade. Porém o foco aqui foi apenas verificar a existência ou não do cliente e variar os retornos, de acordo com os códigos HTTP corretos.

### Próximos passos
Como próximos passos, posso citar que o mais importante é a escolha de um repositório como o [GitHub](https://github.com/). Criarei um repositório privado com esse projeto e caso necessário, basta passar o usuário para acesso.
Será necessário também escolher uma solução de CI/CD, como por exemplo o [Jenkins](https://jenkins.io/) para criar um pipeline de build e deploy para os ambientes.
Além disso, ficou faltando o preenchimento dos testes unitários. Porém, estou ciente de que é imprescindível para validar cada build no processo de CI.
Outro passo que posso citar são os testes integrados, que poderia ser realizado utilizando, por exemplo, o [Selenium](https://www.seleniumhq.org/).

## Endpoints
Os endpoints criados para esse exemplo foram:

GET    /Cliente/?id={id} - Obtém um cliente através do seu ID
POST   /Cliente          - Cria um novo cliente
PUT    /Cliente/{id}/    - Altera as informações do cliente
DELETE /Cliente/{id}/    - Apaga um cliente através de seu ID
