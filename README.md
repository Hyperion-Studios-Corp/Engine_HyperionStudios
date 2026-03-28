# Hyperion Engine

Engine gráfica autoral desenvolvida em Java utilizando LWJGL, com suporte a renderização 2D/3D, mapas isométricos e sistema próprio de animação.

[📦 Ver Releases](https://github.com/Hyperion-Studios-Corp/Engine_HyperionStudios/tags)

[📌 Última versão (v0.1.0)](../../releases/tag/v0.1.0-PreBuild)

## 🚀 Funcionalidades

- Renderização 2D (SpriteBatch)
- Renderização 3D (OBJ e FBX)
- Suporte a mapas isométricos (Tiled)
- Sistema de câmera
- Sistema de input (mouse/teclado com abstração para futuras plataformas)
- Sistema de animação FBX (curve-based)
- Loader próprio de:
  - OBJ
  - FBX (ASCII)
- Pipeline de assets customizado
- Arquitetura modular (Core + Desktop Backend)

## 🧠 Arquitetura

A engine foi projetada com separação entre:

- **Core**
  - lógica da engine
  - matemática (Vector2, Vector3)
  - cena, entidades
  - parsers (FBX, OBJ, Tiled)
  - sistema de animação

- **Desktop Backend**
  - implementação LWJGL
  - input (Keyboard/Mouse)
  - render OpenGL
  - gerenciamento de janela

Essa separação permite futura portabilidade para:
- Android
- outros backends gráficos (ex: OpenGL ES, DirectX)

## 📦 Tecnologias

- Java 17
- LWJGL 2.9.3
- OpenGL (pipeline legacy)

## 🎮 Objetivo

Desenvolver uma engine própria para jogos isométricos com controle total sobre:
- renderização
- assets
- animação
- arquitetura

## 📸 Demonstração

(Coloque prints ou vídeo aqui)

## 📌 Status

Em desenvolvimento ativo.

## 🧑‍💻 Autor

João Vítor - ( 21 anos )

Celular: +55 (51) 99293-7247
