# Desafio Técnico Beto Fróes

# Requisitos
 - Java 17
 - Maven 

# Para testar o código baixe o fonte descompactar em qualquer pasta executar o comando
  mvn clean install

# Acessar a pasta target e executar o comando
 java -jar .\desafioTecnicoBetofroes-0.0.1-SNAPSHOT.jar input=C:\\teste\\in output=C:\\teste\\out

# Onde 
- Input deve ser apontado para a pasta onde deve ter os arquvos de teste
- Output pasta onde salvar os arquivos

# Recomendações
Assim que programa for executado ele apagara os arquivos da pasta input logo faça um backup dos mesmo antes de colocar na pasta

# Outras Configurações por parametro

input=sua_pasta_entrada - default: C:\\input
output=sua_pasta_saída  - default: C:\\output\\data_output
properties_txt=arquivo_propriedades_arquivo_entrada_txt

# Exemplo de arquivo de propriedades do arquivo txt

[
   {
      "order":1,
      "id_field":"id_usuario",
      "size":10
   },
   {
      "order":2,
      "id_field":"nome",
      "size":45
   },
   {
      "order":3,
      "id_field":"id_pedido",
      "size":10
   },
   {
      "order":4,
      "id_field":"id_produto",
      "size":10
   },
   {
      "order":5,
      "id_field":"valor_do_produto",
      "size":12
   },
   {
      "order":6,
      "id_field":"data_compra",
      "size":8
   }
]
