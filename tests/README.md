<!--
#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
#  KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#
-->

# Situm Plugin Test Project

The project in this directory is an Android Test project that enables those
interested in further developing situm-plugin to validate their changes.

## Requirements

Ensure you have installed Gradle, and that it is (at the time of this
writing) at least version 3.3 or newer.

## Getting Started

You can run this test project from both the command line as well as from
Android Studio.

### Command Line

Ensure you have the gradle wrapper script, `gradlew`, in this directory. If
you do not, you can run the following to generate it:

    $ cd cordova-android/test
    $ gradle :wrapper -b build.gradle

To run the unit tests, run: `gradlew test`.
To run unit tests with node process, run: `node run_java_unit_tests.js`.

### Android Studio

Import this `test/` directory into Android Studio, and hit the Play button.
