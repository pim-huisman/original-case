<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>KLM Case Result</title>
   </head>
<body>
<div>
        <table>
          <tr> 
            <td>
               Origin : <c:out value = "${fare.origin}"/>
               Destination : <c:out value = "${fare.destination}"/>
               Amount : <c:out value = "${fare.amount}"/>
            </td>
          </tr>
       </table>       
</div>
</body>

</html>
