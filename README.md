# Passage Extension Example

## Overview

Welcome to the Passage Extension Example project! This repository serves as a reference implementation and example for
extending [Passage](https://github.com/scrayosnet/passage) through various gRPC adapters.

## What is Passage?

Passage is a modern and innovative entry point designed for Minecraft networks. Using modular adapters as its
foundation, it serves as a thoroughly customizable replacement for conventional proxies like Velocity, BungeeCord or
Gate. One kind of adapters are the gRPC adapters that enable completely customized integrations to handle the different
aspects of the passage instances.

## About This Example Project

This repository demonstrates how different gRPC adapters can be developed and implemented for the Passage framework. The
examples showcase best practices for:

- Creating service discovery adapters
- Implementing status query adapters
- Building resourcepack management adapters
- Developing transfer strategy adapters

## Features

- **Service Discovery**: Dynamically register and discover available backend servers
- **Status Information**: Unified status responses across different protocols and versions
- **Player Transfer**: Smart player routing based on custom strategies
- **Resource Management**: Centralized resourcepack distribution and synchronization
- **High Performance**: Optimized for high throughput and minimal latency
- **Type Safety**: Full validation of all communication through Protocol Buffers
- **Cross Platform**: Language-independent through the power of gRPC

## Getting Started

1. Clone this repository
2. Explore the example adapters in the source directory
3. Follow the documentation to implement your own adapter

## Technology Stack

- Kotlin/Java for the main implementation
- gRPC for service-to-service communication
- Protocol Buffers for data serialization
- Gradle as the build system

## Contributing
Contributions are welcome! Please read our contribution guidelines in [CONTRIBUTING.md](CONTRIBUTING.md).

## License
This project is available under the license specified in the [LICENSE](LICENSE) file.
