# Certifica Estudos

Aplicativo Android desenvolvido em Java para auxiliar no planejamento, organização e acompanhamento de estudos voltados para certificações profissionais e cursos.

O projeto faz parte de uma atividade acadêmica de desenvolvimento para dispositivos móveis e foi criado utilizando componentes tradicionais de interface do Android.

## Objetivo

O objetivo do aplicativo é permitir que o usuário organize as certificações que pretende realizar, registre informações importantes e acompanhe a situação de cada uma delas.

O aplicativo foi pensado para uso pessoal e deverá funcionar localmente, sem depender de conexão com a internet.

## Funcionalidade atual

Nesta primeira entrega foi implementado o formulário de cadastro da entidade **Certificação**.

O formulário permite informar:

* Nome da certificação;
* Instituição responsável;
* Área de conhecimento;
* Situação da certificação;
* Indicação de prova agendada.

## Componentes utilizados

A interface contém os seguintes componentes:

* `TextView`;
* `EditText`;
* `Spinner`;
* `RadioGroup`;
* `RadioButton`;
* `CheckBox`;
* `Button`;
* `ScrollView`;
* `Toast`.

## Ações disponíveis

### Salvar

O botão **Salvar** realiza as seguintes operações:

* Recupera os valores preenchidos no formulário;
* Verifica se os campos obrigatórios foram informados;
* Verifica se uma situação foi selecionada;
* Verifica se uma área de conhecimento foi escolhida;
* Exibe uma mensagem de erro quando algum campo obrigatório não foi preenchido;
* Retorna o foco para o campo vazio quando possível;
* Exibe os dados informados por meio de um `Toast` quando o formulário está válido.

Nesta etapa, os dados ainda não são persistidos em banco de dados.

### Limpar

O botão **Limpar**:

* Apaga os valores dos campos de texto;
* Retorna o `Spinner` para a opção inicial;
* Desmarca os `RadioButton`;
* Desmarca o `CheckBox`;
* Retorna o foco para o primeiro campo;
* Exibe um `Toast` informando que o formulário foi limpo.

## Entidades previstas

O projeto completo terá duas entidades principais.

### Certificação

Responsável por armazenar as informações da certificação ou curso.

Principais atributos:

* ID;
* Nome;
* Instituição;
* Área de conhecimento;
* Data de início;
* Data prevista para conclusão;
* Percentual de conclusão;
* Situação;
* Indicação de prova agendada.

### Sessão de Estudo

Responsável por registrar cada período de estudo realizado pelo usuário.

Principais atributos:

* ID;
* Data;
* Duração em minutos;
* Conteúdo estudado;
* Observações;
* Certificação relacionada.

## Relacionamento entre as entidades

O relacionamento será de **um para muitos**, em que uma certificação poderá possuir várias sessões de estudo.

```text
Certificação 1 ------ N Sessões de Estudo
```

Cada sessão de estudo estará vinculada a somente uma certificação por meio de uma chave estrangeira.

## Tecnologias utilizadas

* Java;
* Android Studio;
* Android SDK;
* XML para construção das interfaces;
* Gradle para gerenciamento e compilação do projeto.

## Requisitos do projeto

* Android Studio Quail 1 2026.1.1 ou superior;
* Java;
* `minSdk` 24;
* `targetSdk` 36 ou superior;
* Dispositivo ou emulador Android;
* Compatibilidade com telas de aproximadamente 4,7 polegadas ou superiores.

## Estrutura principal

```text
app
├── manifests
│   └── AndroidManifest.xml
├── java
│   └── br.github.edu.carv.appestudos
│       └── MainActivity.java
└── res
    ├── layout
    │   └── activity_main.xml
    └── values
        ├── strings.xml
        ├── colors.xml
        └── themes.xml
```

## Como executar

1. Abra o Android Studio;
2. Selecione a opção **Open**;
3. Escolha a pasta do projeto;
4. Aguarde a sincronização do Gradle;
5. Crie ou selecione um dispositivo virtual no Device Manager;
6. Execute o aplicativo pelo botão **Run**;
7. Preencha o formulário e teste os botões **Salvar** e **Limpar**.

## Validações implementadas

O aplicativo verifica:

* Se o nome da certificação foi preenchido;
* Se a instituição foi preenchida;
* Se uma área de conhecimento foi selecionada;
* Se uma situação foi marcada no `RadioGroup`.

Caso alguma validação não seja atendida, o aplicativo apresenta uma mensagem por meio de um `Toast`.

## Próximas etapas

Entre as próximas funcionalidades previstas estão:

* Persistência local dos dados;
* Cadastro de sessões de estudo;
* Relacionamento entre certificações e sessões;
* Tela de listagem das certificações;
* Edição e exclusão de registros;
* Consulta do histórico de estudos;
* Cálculo do total de horas estudadas;
* Acompanhamento do progresso das certificações.

## Autor

**Eduardo Carvalho**

Projeto desenvolvido para fins acadêmicos.
