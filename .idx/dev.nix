# To learn more about how to use Nix to configure your environment
# see: https://firebase.google.com/docs/studio/customize-workspace
{ pkgs, ... }: {
  # Which nixpkgs channel to use.
  channel = "stable-23.11"; # or "unstable"
  # Use https://search.nixos.org/packages to find packages
  packages = [
    pkgs.zulu17
    pkgs.consul
  ];
  # Sets environment variables in the workspace
  services = {
    docker = {
      enable = true;
    };
  };
  env = {};
  idx = {
    # Search for the extensions you want on https://open-vsx.org/ and use "publisher.id"
    extensions = [
      "vscjava.vscode-java-pack"
      "rangav.vscode-thunder-client"
    ];
    workspace = {
      # Runs when a workspace is (re)started
      onStart = {
        run-init-script = ''
          echo 'Start'
          
          echo 'Pulling Neccessary images'
          docker pull amazoncorretto:21
          echo 'Pulling Neccessary images done'

          cat <<EOF > ./backend/gradle/wrapper/gradle-wrapper.properties
          distributionBase=GRADLE_USER_HOME
          distributionPath=wrapper/dists
          distributionUrl=https\\://services.gradle.org/distributions/gradle-8.11-bin.zip
          networkTimeout=10000
          validateDistributionUrl=true
          zipStoreBase=GRADLE_USER_HOME
          zipStorePath=wrapper/dist
          EOF

          chmod +x ./backend/local-start-app.sh
          ./backend/local-start-app.sh
          echo 'Done'
        '';

        consul-start = ''
          consul agent -dev -client=0.0.0.0 -ui -bind=127.0.0.1 -http-port=8500
        '';
      };
    };
  };
}