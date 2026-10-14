## Unreleased

### Changed
- The TTS engine is now initialized only after `<map-view>` has loaded, so integrations that don't use TTS no longer initialize it unnecessarily.

### Fixed
- Fixed an ANR caused by the setup process of the native Android TTS engine (`TextToSpeechManager`).