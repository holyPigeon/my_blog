const path = require('path');
const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    proxy : 'http://localhost:8080',
  },
  indexPath: '../../templates/vue/index.html', // /static/vue 기준 -> outputDir을 기준으로 함
  publicPath: '/vue', // 배포 url인 outputDir 경로인 vue 폴더를 가리킴
  outputDir: path.resolve(__dirname, "../../src/main/resources/static/vue"), // blog 프로젝트 폴더 기준
})