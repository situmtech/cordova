import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.situm.capacitorexample',
  appName: 'Situm Capacitor Example',
  webDir: 'www',
  server: {
    androidScheme: 'https'
  }
};

export default config;
