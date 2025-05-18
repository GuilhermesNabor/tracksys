# TrackSys

O TrackSys é uma aplicação Java com interface gráfica feita em JavaFX que roda em segundo plano monitorando o uso do sistema.

## Funcionalidades

- **Visualização gráfica dos dados monitorados com JFreeChart**
- **Tempo de inatividade do usuário**
- **Salvamento de logs em SQLite**
- **Aplicativos mais utilizados**
- **Conexão com a internet**
- **Uso de memória RAM**

## Tecnologias Utilizadas

- **Java 17+**: Linguagem principal do projeto.
- **JavaFX 20**: Utilizada para criar a janela principal do TrackSys com botões e interação do usuário.
- **JFreeChart**: Utilizada para exibir visualmente os dados monitorados, como o uso de RAM ou frequência de uso de apps, em formato de gráfico de barras, linhas, pizza etc.
- **Mavens**: Gerenciador de dependências e automação de build.
- **SQLite (via JDBC)**: Utilizado para armazenar todos os logs e informações coletadas do sistema de forma persistente.

## Como Usar

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/GuilhermesNabor/tracksys
   cd tracksys
   ```

2. **Execute o projeto**:
   Rode o seguinte comando para iniciar o TrackSys.
   ```bash 
   mvn clean javafx:run
   ```

## Contato

- **Guilherme Nabor** - [@GuilhermesNabor](https://github.com/GuilhermesNabor)
- **Email**: guilhermenabor@outlook.com.br
