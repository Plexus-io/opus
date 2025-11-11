<div align="center">


##   **<p>Logo here</p>**



<!-- Badges -->
<p>
  <a href="https://github.com/Plexus-io/opus/graphs/contributors">
    <img src="https://img.shields.io/github/contributors/Louis3797/awesome-readme-template" alt="contributors" />
  </a>
  <a href="https://github.com/Plexus-io/opus/network/members">
    <img src="https://img.shields.io/github/forks/Plexus-io/opus" alt="forks" />
  </a>
  <a href="https://github.com/Plexus-io/opus/stargazers">
    <img src="https://img.shields.io/github/stars/Plexus-io/opus" alt="stars" />
  </a>
  <a href="https://github.com/Plexus-io/opus/issues">
    <img src="https://img.shields.io/github/issues/Plexus-io/opus" alt="open issues" />
  </a>
  <a href="https://github.com/Plexus-io/opus/blob/master/LICENSE">
    <img src="https://img.shields.io/github/license/Plexus-io/opus.svg" alt="license" />
  </a>
</p>

<h4>
 <!-- On Release create docs page and update this tag -->
    <a href="/">Documentation</a>
  <span> · </span>
    <a href="https://github.com/Plexus-io/opus/issues/">Report Bug</a>
  <span> · </span>
    <a href="https://github.com/Plexus-io/opus/issues/">Request Feature</a>
  </h4>
</div>

<br />




<!-- About the Project -->
## About the Project

Opus is an open-source workflow orchestration platform that enables developers to build, deploy, and manage complex workflows with ease. It provides a user-friendly interface, robust API, and seamless integration with popular tools and services.


You need to have [Java 21](https://adoptium.net/temurin/releases/?arch=any&version=21&mode=filter) installed to run this project.


<!-- Getting Started -->
## Getting Started

<!-- Prerequisites -->
### Prerequisites

This project uses [Quarkus 3.27 LTS](https://quarkus.io/blog/quarkus-3-27-released/) with [Maven](https://maven.apache.org/).

### Run project in development mode ###
    mvn quarkus:dev

### Run Tests ##
    mvn test

### Run specific tests  ##

    mvn test -Dtest=classname


### Running code quality checks ###
There are two code quality analysis tools that we regularly run, spotbugs and checkstyle.
To run them locally, you can use the following commands:

#### Checkstyle ####
Checkstyle enforces a consistent coding style in Opus.
You can run checkstyle using:

    mvn checkstyle:check


#### Spotbugs ####
Spotbugs uses static analysis to look for bugs in the code. Navigate to the target/spotbugs.html and open the file to see the results directly in a browser.
You can run spotbugs using:

    mvn spotbugs:check

#### Spotless ####
The import order is a part of static check.
please call `spotlessApply` to optimize the imports of Java codes before filing pull request.

    mvn spotless:apply


<!-- Contributing -->
## Contributing

Our Contributors:

<a href="https://github.com/Plexus-io/opus/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Plexus-io/opus" alt="contributors"/>
</a>



Contributions are always welcome!

See [Contributing.md](/CONTRIBUTING.md)


<!-- Code of Conduct -->
### Code of Conduct

Please read the [Code of Conduct](https://github.com/Louis3797/awesome-readme-template/blob/master/CODE_OF_CONDUCT.md)

## License

Distributed under the **Apache 2.0 License**. See LICENSE for more information.