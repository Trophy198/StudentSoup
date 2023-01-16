module.exports = {
  root: true,
  env: {
    browser: true,
    es2021: true,
  },
  extends: ['plugin:react/recommended', 'standard-with-typescript'],
  overrides: [],
  parser: '@typescript-eslint/parser',
  parserOptions: {
    ecmaVersion: 'latest',
    sourceType: 'module',
    tsconfigRootDir: __dirname,
    project: ['./tsconfig.json'],
  },
  ignorePatterns: ['.eslintrc.js'],
  plugins: ['react'],
  rules: {
    'react/jsx-filename-extension': ['warn', { extensions: ['.tsx', '.js', '.jsx'] }],
    'react/react-in-jsx-scope': 'off',
    '@typescript-eslint/semi': 'off',
    '@typescript-eslint/explicit-function-return-type': 'off',
    '@typescript-eslint/no-unused-vars': 'off',
    '@typescript-eslint/space-before-function-paren': 'off',
    '@typescript-eslint/comma-dangle': 'off',
    'no-useless-computed-key': 'off',
    '@typescript-eslint/member-delimiter-style': 'off',
  },
};