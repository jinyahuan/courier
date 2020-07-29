# Courier
[![Build Status](https://travis-ci.org/jinyahuan/courier.svg?branch=master)](https://travis-ci.org/jinyahuan/courier) [![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)    
Courier(信使): 集成的各大短信服务商的短信操作功能。提供一个统一化的短信操作方式，有点像[slf4j](https://github.com/qos-ch/slf4j) 。

## 为什么要开发这个类库 (吹下牛皮 ^^）
当今，手机已经是每个人必不可少的工具之一，而企业的服务经常会用到短信验证码，比如：注册、登录等等。而初创企业，
乃至中小企业是没有一套现有的工具库来快速投入使用的，其通常是一家家的接入第三方短信服务商，
而第三方短信服务商通常会提供简短的demo，好一点的会有api文档，再好一点的可能会提供sdk，但是这些其实都是重复劳动，
并且各个服务商的操作api通常是不统一的，如果想任意切换某个服务商通道时，就需要统一这些服务商的api，更是造成了开发效率低下的问题。
所以，才有了这个项目，本项目尽量做到易用易集成易拓展，将开发、学习、维护成本降到最低。

## 注意事项
> * **当前处于设计与Demo阶段，会有大量删改，请勿使用！**
> * **由于可用的业余时间不是特别多，所以开发速度会比较慢，一般节假日会更新，也欢迎有意向的朋友加入到这个项目中来**
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
