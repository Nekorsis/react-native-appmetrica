// const path = require('path');

/**
 * Metro configuration for React Native
 * https://github.com/facebook/react-native
 *
 * @format
 */

// const packagePath = '/Users/nikita/Projects/react-native-appmetrica';

module.exports = {
  // projectRoot: path.resolve('example'),

  transformer: {
    getTransformOptions: async () => ({
      transform: {
        experimentalImportSupport: false,
        inlineRequires: false,
      },
    }),
  },
  // resolver: {
  //   nodeModulesPaths: [packagePath],
  //   // extraNodeModules: {
  //   //   'modules': packagePath + '/node_modules'
  //   // }
  // },
  // watchFolders: [packagePath],
};
