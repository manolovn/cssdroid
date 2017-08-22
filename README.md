[![Build Status](https://travis-ci.org/manolovn/cssdroid.svg?branch=master)](https://travis-ci.org/manolovn/cssdroid)
[![codecov](https://codecov.io/gh/manolovn/cssdroid/branch/master/graph/badge.svg)](https://codecov.io/gh/manolovn/cssdroid)
[![Maven Central](https://img.shields.io/maven-central/v/com.manolovn/cssdroid.svg)](http://search.maven.org/#artifactdetails%7Ccom.manolovn%7Ccssdroid%7C0.1.0%7Cjar)

# CSSdroid - convert CSS files to Android styles

CSSdroid is a gradle plugin to help you to create Android xml style files from CSS files. This project is a **proof of concept** that I've made **just for fun**. If you're really interested in this project, contact me and we'll talk about how to make it a production ready product :)

# Features

CSSdroid allow a minor subset of operators (inspired by less and sass)

* Variables (@variable: value;)
* Functions (property: function(valueA, valueB))
* Properties
* Selectors (only allowed class selector)
* Dimension values, color values and numeric values

# Usage

Add plugin classpath to your project:

```groovy
dependencies {
  classpath 'com.manolovn:cssdroid:0.1.0'
}
```

Apply the plugin:

```groovy
apply plugin: 'cssdroid'
```

And execute the generator task:

```bash
./gradlew CSSDROIDGENERATE
```

# Configuration

There are two different configuration params:

* **cssDir**: the path where the .css files are placed. Default value: /src/main/acss/
* **outputDir**: the path where the generated style files will be placed. Default value: /src/main/res/values/

Obviusly you can put whatever values you need.

# Future lines

IMHO to make this project really interesting and production ready, the way to go is to use lexical and grammar generators like lexx and yacc; or maybe a solution based on ANTLR or similar tools. In this way, is easier to maintain lexems allowed and all the syntax rules that can be applied.

# License

    Copyright 2016 Manuel Vera Nieto

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
