### 报告 BUG
可以在 [issues](https://github.com/jinyahuan/courier/issues) 上创建 issue    
注意：尽量提供 BUG 的相关代码和截图（如果有隐私问题，请单独联系开发人员） 

### 贡献代码

#### Git 提交规范
* 提交信息分为3部分
  ```
  <type>[<scope>]: <subject>
  <body>
  <footer>
  ```
  例:
  ```
  feat[api]: 完善日志模块
  新增xxx实现
  新增xxx实现
  ```
  * type 分为以下几类
    * feat: 新功能（feature）
    * fix: 修补bug
    * docs: 文档（documentation）
    * style: 格式（不影响代码运行的变动，例如注释，代码格式化相关的改动）
    * refactor: 重构（即不是新增功能，也不是修改bug的代码变动）
    * test: 和测试相关的改动
    * chore: 构建过程或辅助工具的变动
  * scope 表示影响的范围
  * subject 简要说明本次提交的主题
  * body 详细说明本次提交的内容，如有需要
  * footer 对本次提交的额外补充
    * 兼容性说明等
    * 关闭 issue

#### 开发环境
* Oracle JDK 1.8.0_201
* Maven 3.6.3
* IntelliJ IDEA 2020.1 (Community Edition)
  * [lombok 插件][idea_plugin_lombok_uri]
* Git 2.25.1

备注: 建议```JDK```版本与其相同，其他软件可以选择最新的版本

#### 设置 Copyright
* 设置步骤请参考[IDEA 设置 Copyright][how_to_set_copyright_uri]
* Copyright 内容如下。注意将```[year]```替换为当前年份，例如: ```2020```。如果原先已经带有年份了，可以修改为```[old year]-[current year]```，例如: ```2019-2020```
  ```
  Copyright (c) [year] The Courier Authors
  
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  
      http://www.apache.org/licenses/LICENSE-2.0
  
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ```

#### 设置文件头 (File Header)
* 设置步骤请参考[IDEA 设置 File Header][how_to_set_file_header_uri]
* Copyright 内容如下。注意将```[your name]```替换为自己的姓名，例如: ```Yahuan Jin```或者```Yahuan.Jin```等等。还有要将```[version]```，也替换为项目的版本号，例如: ```0.1```，具体的版本查看[项目父级 pom 配置的版本号][project_pom_uri]
  ```
  /**
   * @author [your name]
   * @since [version]
   */
  ```

[how_to_set_copyright_uri]: https://github.com/jinyahuan/effective-notebook/blob/master/ide/idea/ide_idea_config.md#%E8%AE%BE%E7%BD%AE%E6%96%87%E4%BB%B6%E7%9A%84-copyright
[how_to_set_file_header_uri]: https://github.com/jinyahuan/effective-notebook/blob/master/ide/idea/ide_idea_config.md#%E8%AE%BE%E7%BD%AE%E4%BB%A3%E7%A0%81%E6%96%87%E4%BB%B6%E7%9A%84%E6%96%87%E4%BB%B6%E5%A4%B4%E6%B3%A8%E9%87%8A
[idea_plugin_lombok_uri]: https://github.com/jinyahuan/effective-notebook/blob/master/ide/idea/ide_idea_plugin.md#lombok
[project_pom_uri]: ./pom.xml
