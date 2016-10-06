<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>MVC MOCK</title>
</head>
<body>
    <h1>MVC VIEW TEST</h1>
    <ol><b>EL</b> search variable in four scopes:
        <li>page: ${page}</li>
        <li>request: ${request}</li>
        <li>session: ${session}</li>
        <li>application: ${application}</li>
    </ol>
    <p>requestAttribute.str = ${reqAtr.str}</p>
    <p>requestAttribute.map = ${reqAtr.map['key']}</p>
    <p>requestAttribute.mock.str = ${reqAtr.mock.str}</p>
    <p>sessionAttribute.arr[1] = ${sesAtr.arr[1]}</p>
    <p>sessionAttribute.list[0] = ${sesAtr.list[0]}</p>
    <p>sessionAttribute.list[1] = ${sesAtr.list[1]}</p>
    <p>servletContextAttribute.toString = ${appAtr}</p>
    <p>servletContextAttribute.mock.toString = ${appAtr.mock}</p>
    <jsp:useBean id="pageBean" scope="page" class="com.eshop.entity.MockEntity"/>
    <p>    pageBean.str = ${pageBean.str}  </p>
    <p>
        (pageBean.intValue0 gt -10) and (pageBean.intValue1 lt 10) =
        ${(pageBean.list[0] gt -10) and (pageBean.list[1] lt 10)}
    </p>
    <p>
        test: ${test}
    <br>test-page: ${page.test}
    <br>test-request: ${request.test}
    <br>test-session: ${session.test}
    <br>test-application: ${application.test}
    </p>
</body>
</html>