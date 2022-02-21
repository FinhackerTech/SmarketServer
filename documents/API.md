* 登录
  * POST
  * URL: `/login/api/login`
  * Content type: **x-www-form-urlencoded**
  * Param:
    * String username
    * String password
  * Return: JsonMsg\<Boolean> 登录是否成功
* 注册客户经理
  * POST
  * URL: `/login/api/register`
  * Content type: **x-www-form-urlencoded**
  * Param:
    * String username
    * String password
    * String managerName
  * Return: JsonMsg\<Boolean\> 注册是否成功
* 获取企业详细信息
  * GET
  * URL: `/enterprise/api/{id}`
  * Param:
    * Integer id
  * Return: JsonMsg\<Enterprise\>
  * Example: `/enterprise/api/123`
  * URL: `/enterprise/api/basic/{id}`
  * Return: JsonMsg\<Enterprise.Basic\>
  * URL: `/enterprise/api/about/{id}`
  * Return: JsonMsg\<Enterprise.About\>
  * URL: `/enterprise/api/analyse/{id}`
  * Return: JsonMsg\<Enterprise.Analyse\>
  * URL: `/enterprise/api/news/{id}`
  * Return: JsonMsg\<List\<News\>\> 
* 企业搜索
  * GET
  * URL: `/enterprise/api/search`
  * Content type: json
  * Param:
    * String searchText：搜索内容，可以不需要
    * Integer pageNumber：页码，可以不需要
    * List\<FilterType\> filterTypes：过滤器，可以不需要
  * Return: Page<Enterprise.Brief> 企业简略信息
* 添加收藏
  * POST
  * URL: `/myfavourite/api/add`
  * Content type: json
  * Param:
    * List\<Integer\> enterpriseList：企业ID
  * Return: JsonMsg\<Boolean\> 添加是否成功

* 移除收藏
  * POST
  * URL: `/myfavourite/api/remove`
  * Content type: json
  * Param:
    * List\<Integer\> enterpriseList：企业ID
  * Return: JsonMsg\<Boolean\> 移除是否成功
* 搜索收藏
  * GET
  * URL: `/myfavourite/api/search`
  * Content type: json
  * Param:
    * String searchText
    * Integer pageNumber
    * List\<FilterType\> filterTypes
  * Return: Page<Enterprise.Brief> 
* 更改密码
  * POST
  * URL: `/setting/api/changepassword`
  * Content type: x-www-form-urlencoded
  * Param:
    * String oldPassword
    * String newPassword
  * Return: JsonMsg\<Boolean\> 更改是否成功
* 爬虫发送爬取完毕信息
  * POST
  * URL: `/local/spider/api/finish`
  * Content type: json
  * Param:
    * List\<Integer\> enterpriseList：企业ID

* 获取当前用户
  * GET
  * URL: `/login/api/current`
  * Content type: json
  * Return: JsonMsg\<User\> 当前用户





JsonMsg\<T\>:

* Integer code：消息代码（1为成功）
* String message：消息内容
* T data：装载内容



Enterprise.Brief:

* String logoUrl
* String fullName
* Long registerCapital
* String industryName
* String description



Enterprise.Basic:

* String logoUrl
* String fullName
* Integer enterpriseVrisk
* Integer industryVrisk



Enterprise.About:

* String logoUrl
* Integer listedCoId
* Long securityId
* String symbol
* String industryName
* String website
* String shortName
* String fullName
* Long registerCapital
* String description



Enterprise.Analyse:

* BusinessCapacity businessCapacity
* DevelopmentCapacity developmentCapacity
* LoanRate loanRate
* Profitability profitability
* RiskLevel riskLevel
* Solvency solveny



FilterType:

* String name
* List\<Object\> parameters

* Example:

```
{
	"name": "RegisterCapital",
	"parameters": [50000000, 100000000]
}
```



News:

* String url
* String symbol
* String title
* Integer NOP
* Date time
* String context



Page\<T\>:

* List\<T\> content
* pageable:
  * sort:
    * Boolean empty
    * Boolean sorted
    * Boolean unsorted
  * Integer offset
  * Integer pageNumber
  * Integer pageSize
  * Boolean unpaged
  * Boolean paged
* Boolean last
* Integer totalElements
* Integer totalPages
* Integer size
* Integer number
* sort:
  * Boolean empty
  * Boolean sorted
  * Boolean unsorted
* Boolean first
* Integer numberOfElements
* Boolean empty