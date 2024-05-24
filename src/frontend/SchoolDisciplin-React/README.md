# React + TypeScript + Vite

# Vite-React Project

## Introduction
Ce projet utilise Vite comme outil de build et React pour la création d'une application web moderne. 

## Prérequis
Assurez-vous d'avoir les logiciels suivants installés sur votre machine :
- Node.js (version 14 ou supérieure)
- npm (version 6 ou supérieure)

## Installation et Lancement de l'Application

### Extraction du Projet
Pour commencer, téléchargez et extrayez le fichier ZIP du projet dans le répertoire de votre choix.

### Installation des Dépendances
Ouvrez un terminal dans le répertoire du projet et exécutez la commande suivante pour installer les dépendances nécessaires :

```bash
# Utilisez npm
cd src\frontend\SchoolDisciplin-React
npm install
npm install react-router-dom
npm install clsx
npm i react-pro-sidebar
npm install ag-grid-react 
npm install --save ag-grid-community ag-grid-react @ag-grid-enterprise/all-modules
npm i react-data-table-component
npm install @react-pdf/renderer 
npm install --save @fortawesome/react-fontawesome @fortawesome/free-solid-svg-icons @fortawesome/fontawesome-svg-core
npm run dev

# Ou utilisez yarn
yarn install






This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react/README.md) uses [Babel](https://babeljs.io/) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

## Expanding the ESLint configuration

If you are developing a production application, we recommend updating the configuration to enable type aware lint rules:

- Configure the top-level `parserOptions` property like this:

```js
export default {
  // other rules...
  parserOptions: {
    ecmaVersion: 'latest',
    sourceType: 'module',
    project: ['./tsconfig.json', './tsconfig.node.json'],
    tsconfigRootDir: __dirname,
  },
}
```

- Replace `plugin:@typescript-eslint/recommended` to `plugin:@typescript-eslint/recommended-type-checked` or `plugin:@typescript-eslint/strict-type-checked`
- Optionally add `plugin:@typescript-eslint/stylistic-type-checked`
- Install [eslint-plugin-react](https://github.com/jsx-eslint/eslint-plugin-react) and add `plugin:react/recommended` & `plugin:react/jsx-runtime` to the `extends` list
