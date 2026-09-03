// swift-tools-version: 5.9

import PackageDescription

let package = Package(
    name: "SitumCordovaPlugin",
    platforms: [.iOS(.v16)],
    products: [
        .library(name: "SitumCordovaPlugin", targets: ["SitumCordovaPlugin"])
    ],
    dependencies: [
        // Keep this exact URL and branch format; see
        // https://cordova.apache.org/docs/en/latest/guide/platforms/ios/plugin.html
        // Cordova uses the app's cordova-ios version when installing this plugin.
        .package(url: "https://github.com/apache/cordova-ios.git", branch: "master"),
        .package(
            url: "https://github.com/situmtech/situm-sdk-spm.git",
            .upToNextMinor(from: "3.41.0")
        )
    ],
    targets: [
        .target(
            name: "SitumCordovaPlugin",
            dependencies: [
                .product(name: "Cordova", package: "cordova-ios"),
                .product(name: "SitumSDK", package: "situm-sdk-spm")
            ],
            path: "src/ios/SitumPlugin",
            sources: [
                "Constants.m",
                "SITUtils.m",
                "SitTextToSpeechManager.m",
                "SitumLocationWrapper.m",
                "SitumPlugin.m"
            ],
            publicHeadersPath: ".",
            linkerSettings: [
                .linkedFramework("AVFoundation")
            ]
        )
    ]
)
