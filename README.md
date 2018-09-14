# springboot-atomikos
springboot druid atomikos jta
## 加了个事务的例子，spring使用的默认事务时RunntimeException 才会事务回滚，其他不会，所以在使用了@Transactional(Exception.class)这样的注解


```
get http://localhost:8080/test/fail
>>>
{
    "timestamp": 1536889909035,
    "status": 500,
    "error": "Internal Server Error",
    "exception": "java.lang.ArithmeticException",
    "message": "org.springframework.web.util.NestedServletException: Request processing failed; nested exception is java.lang.ArithmeticException: / by zero",
    "path": "/test/fail"
}

get http://localhost:8080/test/ok
success



参考

[https://blog.csdn.net/a510835147/article/details/75675311](https://blog.csdn.net/a510835147/article/details/75675311)