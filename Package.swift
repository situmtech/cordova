// swift-tools-version: 5.9

import PackageDescription

let package = Package(
    name: "SitumCordovaPlugin",
    platforms: [.iOS(.v16)],
    products: [
        .library(name: "SitumCordovaPlugin", targets: ["SitumCordovaPlugin"])
    ],
    dependencies: [
        // Cordova requires this dependency to resolve plugin packages.
        .package(url: "https://github.com/apache/cordova-ios.git", branch: "master"),
        .package(
            url: "https://github.com/situmtech/situm-sdk-spm.git",
            .upToNextMinor(from: "3.40.0")
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
