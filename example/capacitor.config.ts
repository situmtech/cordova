import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.situm.capacitor_example',
  appName: 'Situm Capacitor Example',
  webDir: 'www',
  server: {
    androidScheme: 'https'
  }
};

export default config;
