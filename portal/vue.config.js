module.exports = {
  // 基本路径 baseURL已经过时
  publicPath: './',
  devServer: {
    // 端口号
    port: 80,
    proxy: {
      "/api/": {
        target: "http://leanlee.top/",
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    },
  },
  // 输出文件目录
  outputDir: 'portal'
};