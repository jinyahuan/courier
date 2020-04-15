# Courier
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)    
Courier(信使): 集成的各大短信服务商的短信操作功能。提供一个统一化的短信操作方式，有点像[slf4j](https://github.com/qos-ch/slf4j)。

> **注意: 当前处于设计与Demo阶段，会有大量删改，请勿使用！**

> 注意: 开发与运行需要 java 8+ (包括)

> * 规划中是分2期
>     * 1期: 基础功能 + 接入个别的短信服务商
>     * 2期: 集成[spring-boot](https://github.com/spring-projects/spring-boot) + 功能完善

> * 1期-项目模块
>     * courier-api:  提供 api 规范
>     * courier-core: 提供核心功能
>     * courier-host
>         * courier-host-vacancy:    空缺短信服务商的 api 实现包
>         * courier-host-simulation: 仿真短信服务商的 api 实现包（可以用于测试，模拟等用途）
>     * courier-host-xxx: 各个短信服务商的 api 实现包（每个服务商都会开一个独立的仓库）
