
<html>
  <head>
    <title>Original Case Web Application</title>
  </head>
  <body>
    <br></br>
    <div id="fareSearch">
      
       <form id="FARE_FORM" action="http://localhost:9000/fare" method="post">
        <table>
          <tr> 
            <td> <input name="origin" list="originList" />
                 <datalist id="originList"></datalist>
            </td>
             <td> 
                <input name="destination" list="destinationList" />
                <datalist id="destinationList"></datalist>
              </td>
            <td>
             <input name="currency"  list="currencyList"  /> </td>
             <datalist id="currencyList"></datalist>
           </tr>
         </table>
         
         <p>
           <input type="submit" name="Submit" value="search" />
         </p>
       </form>

    </div>
  </body>
</html>
<script>
 var mycurrency = ['EUR','USD'];
 var currencyList = document.getElementById('currencyList');

 mycurrency.forEach(function(item){
   var option = document.createElement('option');
   option.value = item;
   currencyList.appendChild(option);
});
</script>