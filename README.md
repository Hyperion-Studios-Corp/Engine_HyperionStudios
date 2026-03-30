# Hyperion Engine

Engine gráfica autoral desenvolvida em Java utilizando LWJGL, com suporte a renderização 2D/3D, mapas isométricos e sistema próprio de animação.

---

## 📦 Releases
➡️ Ver Releases

📌 Última versão: **v0.1.0**

---

## 🚀 Funcionalidades

- Renderização 2D (SpriteBatch)
- Renderização 3D (OBJ e FBX)
- Suporte a mapas isométricos (Tiled)
- Sistema de câmera
- Sistema de input (abstraído para múltiplas plataformas)
- Sistema de animação FBX (curve-based)
- Loader próprio:
  - OBJ
  - FBX (ASCII)
- Pipeline de assets customizado
- Arquitetura modular (Core + Desktop Backend)

---

## 🧠 Arquitetura

A engine foi projetada com separação clara entre camadas:

### 🔹 Core
- Lógica da engine
- Matemática (Vector2, Vector3)
- Sistema de cena e entidades
- Parsers (FBX, OBJ, Tiled)
- Sistema de animação

### 🔹 Desktop Backend
- Implementação com LWJGL
- Input (Keyboard/Mouse)
- Renderização OpenGL
- Gerenciamento de janela

---

## 🔄 Portabilidade

A arquitetura permite expansão futura para:

- Android
- OpenGL ES
- DirectX
- Outros backends

---

## 📦 Tecnologias

- Java 17
- LWJGL 2.9.3
- OpenGL (Legacy Pipeline)

---

## 🎮 Objetivo

Desenvolver uma engine própria focada em jogos isométricos, com controle total sobre:

- Renderização
- Assets
- Animação
- Arquitetura

---

## 📌 Status

🚧 Em desenvolvimento ativo

---

## 📜 License

This project is licensed under the Apache License 2.0.

© 2026 João Vitor

---

## 🧑‍💻 Autor

**João Vitor**
