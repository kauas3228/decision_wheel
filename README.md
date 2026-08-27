# Informações Capacita
- **Nome:** Antônio Kauã Silva Barros
- **Matrícula:**
- **Data:** 27/08/2026
- **Justificativa:** Escolhi usar este projeto para atividade pois venho desenvolvendo ele a um tempo a fim de futuramente publica-lo a fim de resolver aquela problemática de as vezes ficar tempos e tempos tentando decidir algo, seja o que vai comer, para onde vai sair ou o que vai fazer a noite. Desta indecisão temporária presente muitas vezes em várias pessoas do mundo nasceu a ideia de criar um aplicativo no qual eu poderia minimizar este tempo de escolha a partir de um sorteio feito dentro da própria aplicação.
---
# Decision Wheel 🎡

A Roda da Decisão é um aplicativo Android moderno projetado para ajudar você a fazer escolhas de uma forma divertida e interativa. Seja decidindo o que comer, para onde ir ou quem começa um jogo, basta girar a roda e deixar o destino decidir!

## ✨ Funcionalidades

- **Roda Interativa**: Um componente de roda customizado com animações suaves e amortecimento baseado em física.
- **Opções Personalizáveis**: Crie suas próprias listas de escolhas. Cada opção pode ter um título exclusivo e uma cor personalizada.
- **Seletor de Cores Avançado**: Seletor de cores HSV integrado para deixar sua roda tão vibrante quanto você desejar.
- **Persistência**: Suas opções e configurações são salvas localmente, para que estejam prontas sempre que você precisar.
- **Suporte Multi-idioma**: Totalmente localizado em:
  - 🇺🇸 Inglês
  - 🇧🇷 Português
  - 🇪🇸 Espanhol
- **Interface Moderna**: Construído com Jetpack Compose e Material Design 3 para uma experiência elegante e responsiva.

## 🛠️ Tecnologias Utilizadas

- **Kotlin**: A linguagem principal para o desenvolvimento Android moderno.
- **Jetpack Compose**: Para a construção de uma interface declarativa e reativa.
- **Material 3**: A evolução mais recente do Material Design.
- **Room Database**: Para persistência robusta de dados locais das suas opções.
- **DataStore Preferences**: Para gerenciar configurações do usuário, como idioma e estado do app.
- **ViewModel & StateFlow**: Seguindo a arquitetura MVVM para um código limpo e gerenciamento de estado previsível.
- **Jetpack Navigation**: Transições fluidas entre as telas.
- **KSP (Kotlin Symbol Processing)**: Para processamento de anotações eficiente.

## 🚀 Como Começar

### Pré-requisitos

- Android Studio Ladybug (ou mais recente)
- Android SDK 24+ (Min SDK)
- Gradle 8.0+

### Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/ikaroorg/decision-wheel.git
   ```
2. Abra o projeto no Android Studio.
3. Aguarde a conclusão da sincronização do Gradle.
4. Execute o aplicativo em um emulador ou dispositivo físico (Target SDK 36).

## 📁 Estrutura do Projeto

- `app/src/main/java/.../ui/components`: Componentes de interface reutilizáveis, como os `cards de opções`.
- `app/src/main/java/.../ui/screens`: Telas principais das funcionalidades (Home, Editar, Bem-vindo, etc.).
- `app/src/main/java/.../viewmodel`: Lógica e gerenciamento de estado.
- `app/src/main/java/.../data`: Entidades Room, DAOs e gerenciamento de DataStore.
- `app/src/main/res`: Strings localizadas e drawables vetoriais.

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests para melhorar o aplicativo.

---
