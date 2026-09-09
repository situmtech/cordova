// swift-tools-version: 5.9
import PackageDescription

// DO NOT MODIFY THIS FILE - managed by Capacitor CLI commands
let package = Package(
    name: "CapApp-SPM",
    platforms: [.iOS(.v16)],
    products: [
        .library(
            name: "CapApp-SPM",
            targets: ["CapApp-SPM"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", exact: "8.5.0"),
        .package(name: "CapacitorApp", path: "../../../../node_modules/.pnpm/@capacitor+app@8.1.1_@capacitor+core@8.5.0/node_modules/@capacitor/app"),
        .package(name: "CapacitorCamera", path: "../../../../node_modules/.pnpm/@capacitor+camera@8.2.3_@capacitor+core@8.5.0/node_modules/@capacitor/camera"),
        .package(name: "CapacitorFilesystem", path: "../../../../node_modules/.pnpm/@capacitor+filesystem@8.1.3_@capacitor+core@8.5.0/node_modules/@capacitor/filesystem"),
        .package(name: "CapacitorGeolocation", path: "../../../../node_modules/.pnpm/@capacitor+geolocation@8.2.2_@capacitor+core@8.5.0/node_modules/@capacitor/geolocation"),
        .package(name: "CapacitorHaptics", path: "../../../../node_modules/.pnpm/@capacitor+haptics@8.0.0_@capacitor+core@8.5.0/node_modules/@capacitor/haptics"),
        .package(name: "CapacitorKeyboard", path: "../../../../node_modules/.pnpm/@capacitor+keyboard@8.0.5_@capacitor+core@8.5.0/node_modules/@capacitor/keyboard"),
        .package(name: "CapacitorPreferences", path: "../../../../node_modules/.pnpm/@capacitor+preferences@8.0.1_@capacitor+core@8.5.0/node_modules/@capacitor/preferences"),
        .package(name: "CapacitorStatusBar", path: "../../../../node_modules/.pnpm/@capacitor+status-bar@8.0.3_@capacitor+core@8.5.0/node_modules/@capacitor/status-bar"),
        .package(name: "@situm/cordova", path: "../../../..")
    ],
    targets: [
        .target(
            name: "CapApp-SPM",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm"),
                .product(name: "CapacitorApp", package: "CapacitorApp"),
                .product(name: "CapacitorCamera", package: "CapacitorCamera"),
                .product(name: "CapacitorFilesystem", package: "CapacitorFilesystem"),
                .product(name: "CapacitorGeolocation", package: "CapacitorGeolocation"),
                .product(name: "CapacitorHaptics", package: "CapacitorHaptics"),
                .product(name: "CapacitorKeyboard", package: "CapacitorKeyboard"),
                .product(name: "CapacitorPreferences", package: "CapacitorPreferences"),
                .product(name: "CapacitorStatusBar", package: "CapacitorStatusBar"),
                .product(name: "@situm/cordova", package: "@situm/cordova")
            ]
        )
    ]
)
