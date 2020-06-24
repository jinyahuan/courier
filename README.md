# Courier
[![Build Status](https://travis-ci.org/jinyahuan/courier.svg?branch=master)](https://travis-ci.org/jinyahuan/courier) [![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)    
Courier(信使): 集成的各大短信服务商的短信操作功能。提供一个统一化的短信操作方式，有点像[slf4j](https://github.com/qos-ch/slf4j) 。

## 注意事项
> * **当前处于设计与Demo阶段，会有大量删改，请勿使用！**
> * 开发与运行需要 java 8+ (包括)，[其他的开发环境请查看][contributing_dev_env_uri]

## 项目计划
规划中是分2期进行开发
> * 第1期: 基础功能 + 接入个别的短信服务商
> * 第2期: 集成[spring-boot](https://github.com/spring-projects/spring-boot) + 功能完善

## 项目资料
### 1期相关资料
[1期-项目相关的图](https://www.processon.com/view/link/5ea38ed41e085346f71d167d)

#### 1期-项目模块
> * courier-api:  提供 api 规范
> * courier-core: 提供核心功能
> * courier-supplier
>     * courier-supplier-vacancy:    空缺短信服务商的 api 实现包
>     * courier-supplier-simulation: 仿真短信服务商的 api 实现包（可以用于测试，模拟等用途）
> * courier-supplier-xxx: 各个短信服务商的 api 实现包（每个服务商都会开一个独立的仓库）
> * courier-samples: 样例和demo

## 怎么为项目做贡献
查看[CONTRIBUTING.md](CONTRIBUTING.md)

[contributing_dev_env_uri]: ./CONTRIBUTING.md#开发环境
